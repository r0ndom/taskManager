package com.pb.task.manager.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.Properties;

public class PropertyReader {
    public static Properties getProperties(String path) {
        //Resource resource = new ClassPathResource(path);
        InputStreamReader stream = null;
        try {
            stream = new InputStreamReader(PropertyReader.class.getClassLoader().getResourceAsStream(path), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();
        try {
            props.load(stream); //PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

}