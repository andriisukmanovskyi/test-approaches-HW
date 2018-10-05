package com.epam.lab.steps;

import com.epam.lab.bo.GmailBO;
import com.epam.lab.bo.LoginBO;
import com.epam.lab.utils.PropertyUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.epam.lab.utils.constants.Constants.*;
import static junit.framework.TestCase.assertTrue;

public class GmailLoginSteps {

    private WebDriver driver;
    private LoginBO loginBO;
    private GmailBO gmailBO;

    @Before
    public void initDriver() {
        Properties properties = PropertyUtils.getProperties();
        System.setProperty(properties.getProperty(CHROME_DRIVER_TYPE_PROPERTY_KEY),
                properties.getProperty(CHROME_DRIVER_PATH_PROPERTY_KEY));
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(
                Integer.parseInt(properties.getProperty(CHROME_DRIVER_TIMEOUT_PROPERTY_KEY)), TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Given("Open gmail login page")
    public void openGmailLoginPage() {
        driver.get(GMAIL_LOGIN_PAGE_URL);
        loginBO = new LoginBO(driver);
    }

    @When("^User enters username: (.*) and clicks 'next'$")
    public void enterUserName(String userName) {
        loginBO.enterUserNameAndClickNext(userName);
    }

    @And("User enters password: (.*) and clicks 'next'")
    public void enterPassword(String password) {
        gmailBO = loginBO.enterPasswordAndClickNext(password);
    }

    @Then("Navigation to Gmail inbox page with title contains '(.*)'")
    public void verifyTitle(String titlePart) {
        assertTrue(gmailBO.titleContains(titlePart));
    }

    @Then("^Wrong password message is displayed$")
    public void wrongPasswordMessageIsDisplayed() {
        assertTrue(loginBO.isWrongPasswordMessageDisplayed());
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}