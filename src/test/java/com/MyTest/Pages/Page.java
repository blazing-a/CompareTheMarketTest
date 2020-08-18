package com.MyTest.Pages;

import com.MyTest.Annotations.Alias;
import com.MyTest.Configuration;
import com.MyTest.Driver;
import com.MyTest.TestControlObject.Control;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class Page {
    private static final long TIMEOUT = Configuration.timeout();


    private static ConcurrentHashMap<String, Page> currentPages = new ConcurrentHashMap<String, Page>();

    private WebDriver driver;

    public static ConcurrentHashMap<String, Page> getCurrentPages() {
        return currentPages;
    }

    public Page(WebDriver driverValue) {
        this.driver = driverValue;
    }

    public static Page screen(String name) throws Exception {
        return screen(name, Configuration.get("pages_package"));
    }

    public static Page screen(String name, String pagePackage) throws Exception {
        Reflections reflections = new Reflections(pagePackage);
        Set<Class<? extends Page>> subTypes = reflections.getSubTypesOf(Page.class);
        for (Class<? extends Page> type : subTypes) {
            Alias annotation = type.getAnnotation(Alias.class);
            if (annotation != null && annotation.value().equals(name)) {
                return PageFactory.init(Driver.current(), type);
            }
        }
        return null;
    }

    public static Page getCurrent() {
        return getCurrentPages().get(Driver.getThreadName());
    }

    public static void setCurrent(Page newPage) {
        getCurrentPages().put(Driver.getThreadName(), newPage);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Page navigate() throws Exception {
        return this;
    }

    public Control onPage(String name) throws Exception {
        for (Field field : this.getClass().getFields()) {
            if (Control.class.isAssignableFrom(field.getType())) {
                Alias alias = field.getAnnotation(Alias.class);
                if (alias != null && name.equals(alias.value())) {
                    return (Control) field.get(this);
                }
            }
        }
        return null;
    }
    public Control getExactPageText(String text) {
        String locator = String.format("//*[text()='%s']", text);
        Control element = new Control(this, By.xpath(locator));
        return element;
    }

    public static byte[] captureScreenShot() throws IOException {
        WebDriver augmentedDriver = new Augmenter().augment(Driver.current());
        byte[] data = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
        return data;
    }


}


