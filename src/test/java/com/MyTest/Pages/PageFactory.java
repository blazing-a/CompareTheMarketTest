package com.MyTest.Pages;



import com.MyTest.Annotations.FindBy;
import com.MyTest.TestControlObject.Control;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;


public class PageFactory {

    private static By toLocator(String input) {
        if (input.matches("^(xpath=|/)(.*)")) {
            return By.xpath(input.replaceAll("^xpath=", ""));
        } else if (input.matches("^id=(.*)")) {
            return By.id(input.substring("id=".length()));
        } else if (input.matches("^name=(.*)")) {
            return By.name(input.substring("name=".length()));
        } else if (input.matches("^css=(.*)")) {
            return By.cssSelector(input.substring("css=".length()));
        } else if (input.matches("^class=(.*)")) {
            return By.className(input.substring("class=".length()));
        } else {
            return By.id(input);
        }
    }

    private static FindBy getLocator(FindBy locators[]) {
        for (FindBy locator : locators) {
             {
                 if (locator !=null)
                return locator;
            }
        }
        return null;
    }


    public static <T extends Page> T init(WebDriver driver, Class<T> pageClass)
            throws Exception {
        T page = pageClass.getConstructor(WebDriver.class).newInstance(driver);
        for (Field field : pageClass.getFields()) {
            FindBy locators[] = field.getAnnotationsByType(FindBy.class);
            if (locators != null && locators.length > 0) {
                FindBy anno = getLocator(locators);
                if (anno == null) {
                    anno = getLocator(locators);
                }

            if (anno != null) {
                Control control = (Control) field
                        .getType()
                        .getConstructor(Page.class, By.class)
                        .newInstance(
                                page,
                                toLocator(anno.locator()));
                control.setItemLocatorText(anno.itemLocator());
                field.set(page, control);
            }
        }
        }
        return page;
    }
}



