package com.fxq.lombok;

import com.fxq.annotation.PutStringMethod;
import com.fxq.utils.Tools;
import javassist.*;
import java.util.ArrayList;

public class PutStringMethodClassOperate {

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
                        builder.defrost();
                        if (builder.hasAnnotation(PutStringMethod.class)) {
                            PutStringMethod annotation = (PutStringMethod) builder.getAnnotation(PutStringMethod.class);
                            String[] value = annotation.value();
                            // 创建新的方法
                            CtMethod newMethod = new CtMethod(builder, "put", new CtClass[]{pool.get("java.lang.String"), pool.get("java.lang.String")}, builder);
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
                                sb.append("return this.set").append(s).append("($2);}");
                            }
                            sb.append("return null;}");
                            System.out.println(sb.toString());
                            // 定义方法体
                            newMethod.setBody(sb.toString());
                            // 添加方法到静态内部类
                            builder.addMethod(newMethod);
                            // 保存修改后的类
                            builder.writeFile("target/classes/");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
