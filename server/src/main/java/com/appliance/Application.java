package com.appliance;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Application {
    public static final String ROOT_PATH;
    public static final String CONF_PATH;
    static {
        ROOT_PATH = System.getProperty("application.home", "/opt/ports");
        CONF_PATH = ROOT_PATH + "/conf";

        try {
            loadProperties("application.properties");


        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = getProperty("application.name", "client");

    }
    public static String getProperty(String name, String defaultValue) {
        return System.getProperty(name, defaultValue);
    }
    public static int getProperty(String name, int defaultValue) {
        String value = System.getProperty(name);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }
    private static void loadProperties(String name) throws IOException {
        URL url = Application.class.getClassLoader().getResource(name);
        if (url != null) {
            setProperties(getProperties(url));
        }
        File applicationFile = new File(CONF_PATH, name);
        if (applicationFile.exists()) {
            setProperties(getProperties(applicationFile));
        }
    }
    public synchronized static void setProperties(Map<String, String> properties) {
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                System.clearProperty(name);
            } else {
                System.setProperty(name, value);
            }
        }
    }
    public static Map<String, String> getProperties(URL url) throws IOException {
        InputStream in = url.openStream();
        if (in == null) {
            return new LinkedHashMap<String, String>();
        }
        return getProperties(in);
    }
    public static Map<String, String> getProperties(File file) throws IOException {
        if (!file.exists()) {
            return new LinkedHashMap<String, String>();
        }
        return getProperties(new FileInputStream(file));
    }

    public static Map<String, String> getProperties(InputStream in) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            return getProperties(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    public static Map<String, String> getProperties(BufferedReader in) throws IOException {
        Map<String, String> properties = new LinkedHashMap<String, String>();
        String line;
        while ((line = in.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }
            int index = line.indexOf('=');
            if (index > 0) {
                String name = trim(line.substring(0, index));
                String value = trim(line.substring(index + 1));
                properties.put(name, value);
            }
        }
        return properties;
    }
    private static String trim(String value) {
        if (value == null) {
            return null;
        }
        return value.trim();
    }
}
