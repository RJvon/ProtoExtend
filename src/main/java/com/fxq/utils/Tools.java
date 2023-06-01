package com.fxq.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools {

    public static HashMap<String, String> map = new HashMap<>();

    //需要生成方法的protobuf类
    public static HashMap<String, ArrayList<String>> protosMap = new HashMap<>();

    public static String targetpath;

    public static void readYaml() {

        String filePath = "src/main/resources/application.yml";

        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            File file = new File(filePath);

            // 读取YAML文件内容
            Map<String, Object> data = objectMapper.readValue(file, Map.class);

            // 获取targetpath属性值
            List<String> targetPaths = (List<String>) data.get("targetpath");
            System.out.println("targetpath values:");
            for (String path : targetPaths) {
                targetpath = path;
                System.out.println(path);
            }

            // 获取compiler-proto-classes属性值
            List<String> compilerProtoClasses = (List<String>) data.get("compiler-proto-classes");
            System.out.println("compiler-proto-classes values:");
            for (String className : compilerProtoClasses) {
                String[] split = className.split(" ");
                if(split.length>1){
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 1; i < split.length; i++) {
                        list.add(split[i]);
                    }
                    protosMap.put(split[0],list);
                }
                System.out.println(className);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize() {
        readYaml();
//        只配置了现在用到的
        map.put("DOUBLE","double");
        map.put("INT32","int");
        map.put("STRING","String");
        map.put("FLOAT","float");
        map.put("LONG","long");
        map.put("BOOLEAN","boolean");
    }





//    //所有的映射关系
//    DOUBLE(Descriptors.FieldDescriptor.JavaType.DOUBLE),
//    FLOAT(Descriptors.FieldDescriptor.JavaType.FLOAT),
//    INT64(Descriptors.FieldDescriptor.JavaType.LONG),
//    UINT64(Descriptors.FieldDescriptor.JavaType.LONG),
//    INT32(Descriptors.FieldDescriptor.JavaType.INT),
//    FIXED64(Descriptors.FieldDescriptor.JavaType.LONG),
//    FIXED32(Descriptors.FieldDescriptor.JavaType.INT),
//    BOOL(Descriptors.FieldDescriptor.JavaType.BOOLEAN),
//    STRING(Descriptors.FieldDescriptor.JavaType.STRING),
//    GROUP(Descriptors.FieldDescriptor.JavaType.MESSAGE),
//    MESSAGE(Descriptors.FieldDescriptor.JavaType.MESSAGE),
//    BYTES(Descriptors.FieldDescriptor.JavaType.BYTE_STRING),
//    UINT32(Descriptors.FieldDescriptor.JavaType.INT),
//    ENUM(Descriptors.FieldDescriptor.JavaType.ENUM),
//    SFIXED32(Descriptors.FieldDescriptor.JavaType.INT),
//    SFIXED64(Descriptors.FieldDescriptor.JavaType.LONG),
//    SINT32(Descriptors.FieldDescriptor.JavaType.INT),
//    SINT64(Descriptors.FieldDescriptor.JavaType.LONG);
}
