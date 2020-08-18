package com.MyTest;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Driver {
    private Driver() {
    }

    public static ConcurrentHashMap<String, WebDriver> getDriverThreadMap() {
        return driverThreadMap;
    }

    private static ConcurrentHashMap<String, WebDriver> driverThreadMap = new ConcurrentHashMap<String, WebDriver>();
    private static final HashMap<String, Class<?>> driverMap = new HashMap<String, Class<?>>() {
        {
            put("chrome", ChromeDriver.class);
        }
    };
    public static void add(String browser, Capabilities capabilities) throws Exception {
        Class<?> driverClass = driverMap.get(browser);
        WebDriver driver =(WebDriver) driverClass.getConstructor(Capabilities.class).newInstance(capabilities);
        driverThreadMap.put(getThreadName(), driver);
    }
    public static WebDriver current() {
        return driverThreadMap.get(getThreadName());
    }
    public static String getThreadName() {
        return Thread.currentThread().getName() + "-" + Thread.currentThread().getId();
    }
}

