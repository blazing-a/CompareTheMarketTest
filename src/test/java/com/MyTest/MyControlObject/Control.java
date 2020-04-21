package com.MyTest.MyControlObject;


import com.MyTest.Annotations.SubItem;
import com.MyTest.Configuration;
import com.MyTest.Driver;
import com.MyTest.Pages.Page;
import com.MyTest.Pages.PageFactory;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;


public class Control {
    protected static final long TIMEOUT = Configuration.timeout();
    private Page parent;
    private By locator;
    private String locatorText = "";
    private String itemLocatorText = "";
    private HashMap<String, SubItem> subItemsMap;
    private boolean excludeFromSearch = false;

    public Control(Page parentValue, By locatorValue) {
        this.parent = parentValue;
        this.locator = locatorValue;
        this.locatorText = this.locator.toString().replaceFirst("^By\\.(\\S+): ", "");
        subItemsMap = new HashMap<String, SubItem>();
    }

    public WebDriver getDriver() {
        return parent.getDriver();
    }

    public String getLocatorText() {
        return locatorText;
    }

    public String getItemLocatorText() {
        return itemLocatorText;
    }

    public void setItemLocatorText(String subItemLocatorText) {
        this.itemLocatorText = subItemLocatorText;
    }

    public void addSubItems(SubItem[] items) {
        for (SubItem item : items) {
            this.subItemsMap.put(item.name(), item);
        }
    }

    public HashMap<String, SubItem> getSubItemsMap() {
        return subItemsMap;
    }

    public boolean isExcludeFromSearch() {
        return excludeFromSearch;
    }

    public void setExcludeFromSearch(boolean excludeFromSearch) {
        this.excludeFromSearch = excludeFromSearch;
    }

    public Page getParent() {
        return parent;
    }

    public WebElement element() {
        return getDriver().findElement(locator);
    }

    public boolean exists(long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean visible(long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean exists() {
        return exists(TIMEOUT);
    }

    public boolean visible() {
        return visible(TIMEOUT);
    }


    public void click() {
        Assert.assertTrue(
                "Unable to find element: " + this.locator, exists());
        Assert.assertTrue(
                "Element isn't visible: " + this.locator, visible());
        this.element().click();
    }


    public boolean waitUntil(ExpectedCondition<?> condition, long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        try {
            wait.until(condition);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean disappears(long timeout) {
        return waitUntil(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(locator)), timeout);

    }

    protected String getFullItemLocator() {
        return String.format("%s%s", this.getLocatorText(), this.getItemLocatorText());
    }

    public Control getItem(int index) {
        String locator = String.format("(%s)[%d]", this.getFullItemLocator(), index + 1);
        return new Control(this.getParent(), By.xpath(locator));
    }

    public boolean isEmpty(long timeout) {
        return this.getItem(0).disappears(timeout);
    }



    public boolean isNotEmpty(long timeout) {
        return this.getItem(0).exists();
    }

    public boolean isNotEmpty() {
        return isNotEmpty(TIMEOUT);
    }

    public By getSubItemLocator(String name, int index) {
        SubItem item = this.getSubItemsMap().get(name);
        String locator = String.format("(%s)[%d]%s", this.getFullItemLocator(), index + 1, item.locator());
        return By.xpath(locator);
    }

    public <T extends Control> T getSubItem(String name, int index, Class<T> itemType) throws Exception {
        T element = itemType.getConstructor(Page.class, By.class).
                newInstance(this.getParent(), getSubItemLocator(name, index));
        return element;
    }

    public boolean enabled(long timeout) {
        return waitUntil(ExpectedConditions.elementToBeClickable(locator), timeout);
    }

    public boolean enabled() {
        return enabled(TIMEOUT);
    }

    public boolean disabled(long timeout) {
        return waitUntil(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)), timeout);
    }












}





