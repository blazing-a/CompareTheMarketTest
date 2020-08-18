package com.MyTest.UIPages;

import com.MyTest.Annotations.Alias;
import com.MyTest.Annotations.FindBy;
import com.MyTest.Pages.Page;
import com.MyTest.TestControlObject.Control;
import org.openqa.selenium.WebDriver;


@Alias("Your Details")
public class DetailsPage extends Page {
    public DetailsPage(WebDriver driverValue) {
        super(driverValue);
    }

    @Alias("Email Address")
    @FindBy(locator = "EnergyComparison_YourDetails_YourContactDetails_WhatIsYourEmailAddress")
    public Control email;

    @Alias("Show Me Deals")
    @FindBy(locator = "//*[@class='BaseButton_2rxRq Button_iAL6V Button__primary_27HHt Button__large_2UDdG']")
    public Control energyPlanDropDown;

}
