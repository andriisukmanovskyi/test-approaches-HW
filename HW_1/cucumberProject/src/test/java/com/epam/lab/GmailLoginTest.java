package com.epam.lab;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        glue = "com.epam.lab.steps",
        format = {
                "html:target/cucumber/gmail_login_test_report.html",
                "pretty"
        }
)
public class GmailLoginTest {
}