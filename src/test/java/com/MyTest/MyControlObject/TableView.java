package com.MyTest.MyControlObject;


import com.MyTest.Annotations.SubItem;
import com.MyTest.Pages.Page;
import org.openqa.selenium.By;

public class TableView extends Control {

    public TableView(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    protected String getFullItemLocator() {
        return String.format("%s%s", this.getLocatorText(), this.getItemLocatorText());
    }
    public int getItemsCount() {
        return this.getDriver().findElements(By.xpath(getFullItemLocator())).size();
    }
    public Control getItem(int index) {
        String locator = String.format("(%s)[%d]", this.getFullItemLocator(), index + 1);
        return new Control(this.getParent(), By.xpath(locator));
    }

    public boolean isEmpty(long timeout) {
        return this.getItem(0).disappears(timeout); }

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
}
