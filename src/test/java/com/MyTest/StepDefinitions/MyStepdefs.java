package com.MyTest.StepDefinitions;

import com.MyTest.Configuration;
import com.MyTest.Driver;
import com.MyTest.MyControlObject.Control;
import com.MyTest.Pages.Page;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.math.BigDecimal;
import java.util.List;


public class MyStepdefs {

    @Given("^I navigate to the Coin Market Website$")
    public void iNavigateToTheCoinMarketWebsite() {
        Driver.current().get(Configuration.get("main_url"));
    }


        @Given("^I am on the \"(.*)\" (?:page|screen)$")
        public void i_am_on_the_page(String pageName) throws Throwable {
            Page page = Page.screen(pageName);
            Assert.assertNotNull("Unable to find page for name: '" + pageName + "'", page);
            page.navigate();
            Page.setCurrent(page);
        }


        @When("^(?:I |)(?:click|select) the \"([^\"]*)\" (?:element|button|ellipsis)$")
        public void click_on_the_element(String fieldName) throws Throwable {
            Control control = (Control) Page.getCurrent().onPage(fieldName);
            Assert.assertNotNull("Unable to find element with the name '" + fieldName + "' on current page", control);
            Assert.assertTrue("Unable to find element: '" + fieldName  + "'", control.visible());
            control.click();

        }

        @Then(value = "^I should see the \"(.*)\" (?:page|screen)$", timeout = 60000)
        public void verifyCurrentPage(String name) throws Throwable {
            Page page = Page.screen(name);
            Assert.assertNotNull("Unable to find page for name: '" + name + "'", page);
            Page.setCurrent(page);
        }

    @And("^I should see that the \"([^\"]*)\" table has \"([^\"]*)\" items$")
    public void iShouldSeeThatTheTableHasItems(String table, int row) throws Throwable {
        List<WebElement> rows = Driver.current().findElements(By.xpath("//*[@class=\"cmc-table__table-wrapper-outer\"]/div/table/tbody/tr"));
        Assert.assertEquals("Unexpected row count for the '" + table + "' table", row, rows.size());

    }

}

