package com.test.Configurations;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ProperityLoader {

    public static String getProperityByKey(String Key) throws Exception {
        FileReader reader = new FileReader(System.getProperty("user.dir")+"/appium.properties");
        Properties p = new Properties();
        p.load(reader);
        return p.getProperty(Key);
    }

}
