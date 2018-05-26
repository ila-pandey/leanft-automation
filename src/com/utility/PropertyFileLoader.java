package com.utility;

import java.io.IOException;
import java.util.Properties;

public class PropertyFileLoader {

    private static Properties xpath;
    private static Properties application;
    private static Properties data;

    private static boolean load() {
        try {
            xpath = loadPropFile("xpath.properties");
            application = loadPropFile("application.properties");
            data = loadPropFile("data.properties");
        }catch(Exception ex) {
            System.out.println(ex.toString());
        }
        return false;
    }

    public static String getProperty(PROPERTYFILE file,String key) {
        String value = "";
        if(xpath == null || application == null || data == null) {
            load();
        }

        switch(file) {
            case XPATH:
                return xpath.getProperty(key);
            case APPLICATION:
                return application.getProperty(key);
            case DATA:
                return data.getProperty(key);
        }
        return value;
    }

    private static Properties loadPropFile(String file) throws IOException {
        Properties prop = new Properties();
        prop.load(PropertyFileLoader.class.getResourceAsStream(file));
        return  prop;
    }
}