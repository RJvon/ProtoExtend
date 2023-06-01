package com.fxq.config;


import java.io.InputStream;
import java.util.Properties;


public class Constant {

    public final static String CLASSPATH = getProperty().getProperty("classpath");

    public final static String TARGETPATH = getProperty().getProperty("targetpath");


    public static Properties getProperty() {
        Properties properties = new Properties();
        InputStream is = Class.class.getResourceAsStream("/application.properties");
        try {
            properties.load(is);
        } catch (Exception e) {
            System.out.println("加载配置文件异常");
        }
        return properties;
    }
}
