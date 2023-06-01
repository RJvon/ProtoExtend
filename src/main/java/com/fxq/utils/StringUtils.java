package com.fxq.utils;


public class StringUtils {

    public static String toUpperCaseFirstIndex(String fieldName) {
        if (org.apache.commons.lang.StringUtils.isEmpty(fieldName)) {
            return "";
        }
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

}
