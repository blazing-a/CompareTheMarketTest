package com.MyTest.TestControlObject;


import com.MyTest.Configuration;
import com.MyTest.Pages.Page;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Control {
    protected static final long TIMEOUT = Configuration.timeout();
    private Page parent;
    private By locator;
    private String locatorText = "";
    private String itemLocatorText = "";
    public Control(Page parentValue, By locatorValue) {
        this.parent = parentValue;
        this.locator = locatorValue;
        this.locatorText = this.locator.toString().replaceFirst("^By\\.(\\S+): ", "");
    }

    public WebDriver getDriver() {
        return parent.getDriver();
    }

    public String getLocatorText() {
        return locatorText;
    }

    public void setItemLocatorText(String subItemLocatorText) {
        this.itemLocatorText = subItemLocatorText;
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


    public boolean enabled(long timeout) {
        return waitUntil(ExpectedConditions.elementToBeClickable(locator), timeout);
    }

    public boolean enabled() {
        return enabled(TIMEOUT);
    }



    public void setText(String value) {
        this.click();
        this.element().clear();
        this.element().sendKeys(value);
    }

    public Select getSelect() {
        return new Select(this.element());
    }

    public void selectByText(String value){
        this.exists();
        this.getSelect().selectByVisibleText(value);
    }
}





