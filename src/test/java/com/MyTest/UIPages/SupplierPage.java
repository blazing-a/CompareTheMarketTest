package com.MyTest.UIPages;

import com.MyTest.Annotations.Alias;
import com.MyTest.Annotations.FindBy;
import com.MyTest.TestControlObject.Control;
import com.MyTest.Pages.Page;
import org.openqa.selenium.WebDriver;

@Alias("Your supplier")
public class SupplierPage extends Page {


    public SupplierPage(WebDriver driverValue) {
        super(driverValue);
    }

    @Alias("Post Code")
    @FindBy(locator = "EnergyComparison_YourSupplier_YourCurrentSupplier_WhatsYourPostcode")
    public Control postCodeField;

    @Alias("Next")
    @FindBy(locator = "EnergyComparison_YourSupplier_Next")
    public Control nextSupplierButton;



}
