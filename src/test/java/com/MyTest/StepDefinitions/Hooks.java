package com.MyTest.StepDefinitions;



import com.MyTest.Configuration;
import com.MyTest.Driver;
import com.MyTest.Pages.Page;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;


public class Hooks {
    @Before
    public void beforeScenario(Scenario scenario) throws Exception {

        Configuration.load();
        System.setProperty("webdriver.chrome.driver", new File("browser/chromedriver.exe").getAbsolutePath());
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);
        Driver.current().manage().window().maximize();
    }
    @After
    public void afterScenario(Scenario scenario) throws Exception {

        fullScreenshot(scenario);
        Driver.current().quit();
    }

    public void fullScreenshot(Scenario scenario)throws Exception{
        final byte[] screenshot = Page.captureScreenShot();
        scenario.embed(screenshot, "image/png");

    }
}
