package com.fxq.lombok;

import com.fxq.annotation.GetStringMethod;
import com.fxq.annotation.IsNullMethod;
import com.fxq.config.Constant;
import com.fxq.utils.StringUtils;
import com.fxq.utils.Tools;
import javassist.*;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class IsNullMethodClassOperate {

    public void doClassOperate() throws Exception {
        try {
            // 获取外部类的CtClass对象
            ClassPool pool = ClassPool.getDefault();
            if (Tools.protosMap.size() > 0) {
                for (String protoPath : Tools.protosMap.keySet()) {
                    CtClass ctClass = pool.get(protoPath);
                    ArrayList<String> list = Tools.protosMap.get(protoPath);
                    for (String innerClassName : list) {
                        CtClass innerCtClass = pool.get(protoPath + "$" + innerClassName);
                        CtClass builder = pool.get(protoPath + "$" + innerClassName + "$Builder");
                        innerCtClass.defrost();
                        if (innerCtClass.hasAnnotation(IsNullMethod.class)) {
                            IsNullMethod annotation = (IsNullMethod) innerCtClass.getAnnotation(IsNullMethod.class);
                            String[] value = annotation.value();
                            // 创建新的方法
                            CtMethod newMethod = new CtMethod(CtClass.booleanType, "isNull", new CtClass[]{pool.get("java.lang.String")}, innerCtClass);
                            newMethod.setModifiers(Modifier.PUBLIC);
                            boolean useElseIf = false;
                            StringBuilder sb = new StringBuilder();
                            sb.append("{");
                            for (String s : value) {
                                if (!useElseIf) {
                                    sb.append("if(\"").append(s).append("\"").append(".equals($1)").append(") {");
                                    useElseIf = true;
                                } else {
                                    sb.append("else if(\"").append(s).append("\"").append(".equals($1)").append(") {");
                                }
                                sb.append("return this.has").append(s).append("();}");
                            }
                            sb.append("return true;}");
                            System.out.println(sb.toString());
                            // 定义方法体
                            newMethod.setBody(sb.toString());
                            // 添加方法到静态内部类
                            innerCtClass.addMethod(newMethod);
                            // 保存修改后的类
                            innerCtClass.writeFile(Tools.targetpath);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getReturnTypeName(Field field) {
        if (field == null) {
            return "";
        }
        Class<?> returnType = field.getType();
        return returnType.getSimpleName();
    }

    private CtClass getCtClass(String classPath) {
        ClassPool classPool = new ClassPool(true);
        try {
            classPool.insertClassPath(classPath);
            CtClass ctClass = classPool.get(classPath);
            return ctClass;
        } catch (NotFoundException e) {
            return null;
        }
    }

}
