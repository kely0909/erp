package com.cskaoyan.util.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ParseDomainConfigFile {

    private static Map serviceMap;

    private static Map controllerMap = new HashMap();

    static {
        String path = ParseDomainConfigFile.class.getClassLoader().getResource("/").getPath();
        String filePath = path + "/super";

        // json解析
        File file = new File(filePath, "DomainMapping.json");
        String content = null;
        try {
            content = FileUtils.readFileToString(file, "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            serviceMap = mapper.readValue(content, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // properties解析
        try {
            InputStream is = new FileInputStream(new File(filePath, "URIMapping.properties"));
            Properties prop = new Properties();
            prop.load(is);
            for (Object key : prop.keySet()) {
                controllerMap.put(key, prop.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDomain(Object key) {
        return (String) controllerMap.get(key);
    }

    public static Map getDomainIfMulti(Object key){
        return (Map) serviceMap.get(key);
    }


}
