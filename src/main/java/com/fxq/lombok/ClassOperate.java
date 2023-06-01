package com.fxq.lombok;

import com.fxq.annotation.MyData;
import com.fxq.config.Constant;
import com.fxq.utils.StringUtils;
import javassist.*;

import java.lang.reflect.Field;


public class ClassOperate {



    public void doClassOperate() throws Exception {

        //创建CtClass容器
        CtClass ctClass = getCtClass(Constant.CLASSPATH);

        //获取Class对象和属性
        Class<?> clazz = Class.forName(Constant.CLASSPATH);
        Field[] fields = clazz.getDeclaredFields();
        MyData myData = clazz.getAnnotation(MyData.class);

        if (myData == null) {
            return;
        }

        for (Field field : fields) {
            String fieldName = field.getName();
            //截取返回值类型
            String returnTypeName = getReturnTypeName(field);
            //生成get方法
            getMethod(fieldName, ctClass);
            //生成set方法
            setMethod(fieldName, ctClass, returnTypeName);
        }

        //更新class文件
        ctClass.writeFile(Constant.TARGETPATH);

    }

    private String getReturnTypeName(Field field) {
        if (field == null) {
            return "";
        }
        Class<?> returnType = field.getType();
        return returnType.getSimpleName();
    }


    private void setMethod(String fieldName, CtClass ctClass, String returnTypeName) {
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append("String").append(" ").append("set").
                append(StringUtils.toUpperCaseFirstIndex(fieldName)).append("(").
                append(returnTypeName).append(" ").append(fieldName).
                append(") { return ").
                append(fieldName).append(";} ");
        try {
            CtMethod setMethod = CtMethod.make(sb.toString(), ctClass);
            ctClass.addMethod(setMethod);
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }

    }

    private void getMethod(String fieldName, CtClass ctClass) {
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append("String").append(" ").append("get").
                append(StringUtils.toUpperCaseFirstIndex(fieldName)).append("() { return ").
                append(fieldName).append(";} ");
        System.out.println(sb.toString());
        try {
            CtMethod getMethod = CtMethod.make(sb.toString(), ctClass);
            ctClass.addMethod(getMethod);
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
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
