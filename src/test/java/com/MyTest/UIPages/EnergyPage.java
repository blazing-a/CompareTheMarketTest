package com.MyTest.UIPages;

import com.MyTest.Annotations.Alias;
import com.MyTest.Annotations.FindBy;
import com.MyTest.Pages.Page;
import com.MyTest.TestControlObject.Control;
import org.openqa.selenium.WebDriver;

@Alias("Your Energy")
public class EnergyPage extends Page {


    public EnergyPage(WebDriver driverValue) {
        super(driverValue);
    }


    @Alias("Energy Plan")
    @FindBy(locator = "//*[@class='BaseDropdown_3HYJa DropdownBold_2KeXQ DropdownValid_1DFJ2 HaveBill_dropdown__1wMRw']")
    public Control energyPlanDropDown;

    @Alias("Currency")
    @FindBy(locator = "EnergyComparison_NoBill_YourEnergy_Gas_HowMuchDoYouSpendOnGas_Usage")
    public Control currency;

    @Alias("Go To Prices")
    @FindBy(locator = "EnergyComparison_YourDetails_GoToPrices")
    public Control goToPricesButton;

    @Alias("Next")
    @FindBy(locator = "//*[@class='BaseButton_2rxRq Button_iAL6V Button__primary_27HHt Button__large_2UDdG']")
    public Control nextGas;

    @Alias("Yes")
    @FindBy(locator = "//*[@id=\"EnergyComparison_HaveBill_YourEnergy_YourElectricity_DoYouHaveAnEconomy7Meter\"]/div[1]/label/span/div/span")
    public Control economy7Yes;

    @Alias("No")
    @FindBy(locator = "//*[@id=\"EnergyComparison_HaveBill_YourEnergy_YourElectricity_IsElectricityYourMainSourceOfHeating\"]/div[2]/label/span/div/span")
    public Control sourceOfHeating;

    @Alias("Electricity Amount")
    @FindBy(locator = "EnergyComparison_HaveBill_YourEnergy_YourElectricity_WhatIsYourCurrentElectricityUsage_YourCurrentUsageQuestionPounds")
    public Control ammountOfElecUsage;

    @Alias("Gas Amount")
    @FindBy(locator = "EnergyComparison_HaveBill_YourEnergy_YourGas_WhatIsYourCurrentGasUsage_YourCurrentUsageQuestionPounds")
    public Control ammountOfGasUsage;

    @Alias("Date")
    @FindBy(locator = "EnergyComparison_HaveBill_YourEnergy_YourElectricity_WhatIsYourCurrentElectricityUsage_YourCurrentUsageQuestionPounds")
    public Control date;

    @Alias("Day")
    @FindBy(locator = "//*[@name='DateInput__day']")
    public Control day;

    @Alias("Month")
    @FindBy(locator = "//*[@name='DateInput__month']")
    public Control month;

    @Alias("Year")
    @FindBy(locator = "//*[@name='DateInput__year']")
    public Control year;


    public void clickYesEconomy7(){
        this.economy7Yes.click();
    }

    public void clickNoSourceOfHeating(){
        this.sourceOfHeating.click();
    }


    public void enterDate(String day, String month, String Year){
        this.day.setText(day);
        this.month.setText(month);
        this.year.setText(Year);
    }

}