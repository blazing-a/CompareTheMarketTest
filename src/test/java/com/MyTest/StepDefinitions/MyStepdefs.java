package com.MyTest.StepDefinitions;

import com.MyTest.Configuration;
import com.MyTest.Driver;
import com.MyTest.TestControlObject.Control;
import com.MyTest.Pages.Page;
import com.MyTest.UIPages.EnergyPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class MyStepdefs {

    @Given("^I navigate to the Energy Comparison Website$")
    public void iNavigateToTheCoinMarketWebsite() {
        Driver.current().get(Configuration.get("main_url"));
    }

        @Then(value = "^I should see the \"(.*)\" (?:page)$", timeout = 60000)
        @Given("^I am on the \"(.*)\" (?:page|screen)$")
        public void i_am_on_the_page(String pageName) throws Throwable {
            Page page = Page.screen(pageName);
            Assert.assertNotNull("Unable to find page for name: '" + pageName + "'", page);
            page.navigate();
            Page.setCurrent(page);
        }


        @When("^(?:I |)(?:click|select) the \"([^\"]*)\" (?:element|button|)$")
        public void click_on_the_element(String fieldName) throws Throwable {
            Control control = (Control) Page.getCurrent().onPage(fieldName);
            Assert.assertNotNull("Unable to find element with the name '" + fieldName + "' on current page", control);
            Assert.assertTrue("Unable to find element: '" + fieldName  + "'", control.visible());
            control.click();
        }

    @When("^(?:I |)enter \"([^\"]*)\" text into the \"([^\"]*)\" field$")
    public void enterValue(String text, String fieldName) throws Throwable {
        Control control =  Page.getCurrent().onPage(fieldName);
        Assert.assertNotNull("Unable to find element with the name '" + fieldName + "' on current page", control);
        Assert.assertTrue("Unable to find element: '" + fieldName  + "'", control.enabled());
        control.setText(text);
    }


    @Then("^I should see the \"([^\"]*)\" screen$")
    public void iShouldSeeTheIcon(String text) throws Throwable {
        Control control = (Control) Page.getCurrent().getExactPageText(text);
        Assert.assertTrue("Unable to find text: '" + text + "'", control.enabled());
    }

    @And("^I click the \"([^\"]*)\" text$")
    public void iClickTheText(String text) throws Throwable {
        Control control = (Control) Page.getCurrent().getExactPageText(text);
        Assert.assertTrue("Unable to find text: '" + text + "'", control.enabled());
        control.click();
    }

    @And("^I select \"([^\"]*)\" for the \"([^\"]*)\" (?:question|section|)$")
    public void iClickTheTextForTheQuestion(String text1, String text2) throws Throwable {
        Control control = (Control) Page.getCurrent().getExactPageText(text1);
        Assert.assertTrue("Unable to find text: '" + text1 + "'", control.visible());
        control.click();

        Control control2 = (Control) Page.getCurrent().getExactPageText(text2);
        Assert.assertTrue("Unable to find text: '" + text2 + "'", control.visible());
    }

    @When("^I select \"([^\"]*)\" for the \"([^\"]*)\" dropdown$")
    public void iSelectForTheDropdown(String option, String dropdown) throws Throwable {
        Control control = (Control) Page.getCurrent().onPage(dropdown);
       Assert.assertNotNull("Unable to find element with the name '" + dropdown + "' on current page", control);
        Assert.assertTrue("Unable to find element: '" + dropdown  + "'", control.visible());
        control.click();
        control.selectByText(option);
    }

    @And("^I select Yes for the \"([^\"]*)\" question$")
    public void iSelectYesForTheQuestion(String text) throws Throwable {
        Control control = (Control) Page.getCurrent().getExactPageText(text);
        Assert.assertTrue("Unable to find text: '" + text + "'", control.visible());
        Page page = Page.screen("Your Energy");
        Assert.assertNotNull("Unable to find page'" , page);
        Page.setCurrent(page);
        EnergyPage energyPage= ((EnergyPage)Page.getCurrent());
        energyPage.clickYesEconomy7();
    }

    @And("^I select No for the \"([^\"]*)\" question$")
    public void iSelectNoForTheQuestion(String text) throws Throwable {
        Control control = (Control) Page.getCurrent().getExactPageText(text);
        Assert.assertTrue("Unable to find text: '" + text + "'", control.visible());
        Page page = Page.screen("Your Energy");
        Assert.assertNotNull("Unable to find page'" , page);
        Page.setCurrent(page);
        EnergyPage energyPage= ((EnergyPage)Page.getCurrent());
        energyPage.clickNoSourceOfHeating();
    }

    @And("^I enter \"([^\"]*)\"-\"([^\"]*)\"-\"([^\"]*)\" into the Date field$")
    public void iEnterIntoTheDateField(String day, String month, String year) throws Throwable {
        Page page = Page.screen("Your Energy");
        Assert.assertNotNull("Unable to find page'" , page);
        Page.setCurrent(page);
        EnergyPage energyPage= ((EnergyPage)Page.getCurrent());
        energyPage.enterDate(day,month,year);
    }
}

