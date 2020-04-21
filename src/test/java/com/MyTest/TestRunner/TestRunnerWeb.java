package com.MyTest.TestRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(
        monochrome = true,
        features = { "src/test/resources/com.MyTest.features" },
        glue = {"com/MyTest/StepDefinitions"},
       tags ={"@web"},
        plugin = {
        "json:target/cucumber-report/cucumber.json", "html:target/cucumber-html-report",
        "pretty:target/cucumber-pretty.txt",
        "junit:target/cucumber-junit-results.xml","pretty", "html:target/cucumber.html",
                "json:target/cucumber-report.json",
                "junit:target/cucumber.xml"}
)

public class TestRunnerWeb {

}
