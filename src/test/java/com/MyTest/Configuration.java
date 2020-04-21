package com.MyTest;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;
public class Configuration {
    private Configuration() {
    }

    private static Properties properties;
    public static void load() throws IOException {
        Configuration config = new Configuration();
        properties = new Properties();
        Enumeration<URL> enumerator = config.getClass().getClassLoader().getResources("/");
        while(enumerator.hasMoreElements()) {
            System.out.println(enumerator.nextElement());
        }
        try (InputStream is = Files.newInputStream(Paths.get("Test.properties"))) {


            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            is,
                            StandardCharsets.UTF_8));
            try {
                properties.load(reader);
            } finally {
                is.close();
                reader.close();
            }
        }
    }
    public static String get(String option) {
        if (properties == null) {
            try {
                load();
            } catch (IOException e) {
             System.err.println(e);
            }
        }
        String value = properties.getProperty(option);
        if (value == null) {
            return "";
        }
        return value;
    }
    public static long timeout() {
        String value = get("timeout");
        if (value == null || value.trim().equals("")) {
            return 60L;
        }
        return Long.parseLong(value.trim());
    }
}