package com.MyTest.UIPages;

import com.MyTest.Annotations.Alias;
import com.MyTest.Annotations.FindBy;
import com.MyTest.Annotations.SubItem;
import com.MyTest.MyControlObject.Control;
import com.MyTest.MyControlObject.TableView;
import com.MyTest.Pages.Page;
import org.openqa.selenium.WebDriver;

@Alias("Coin Market Cap")
public class MainPage extends Page {


    public MainPage(WebDriver driverValue) {
        super(driverValue);
    }

    @Alias("View All")
    @FindBy(locator = "//button[@data-qa-id='table-listing-filters-toggle']/following::*[text()='View All'][1]")
    public Control user;

    @Alias("Cryptocurrencies")
    @FindBy(locator = "//*[@class=\"cmc-table__table-wrapper-outer\"]/div/table", itemLocator = "/tbody/tr")
    public TableView CryptoCurrencies;

    @Alias("a Random Cryptocurrency")
    @FindBy(locator = "//*[@class=\"cmc-table__table-wrapper-outer\"]/div/table", itemLocator = "/tbody/tr[6]")
    @SubItem(name = "ellipsis", locator = "/td[9]/following::*[@role='img']")
    public TableView random1;

}
