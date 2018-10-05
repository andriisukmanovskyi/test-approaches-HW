package com.epam.lab.bo;

import com.epam.lab.po.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginBO {

    private WebDriver driver;
    private LoginPage loginPage;

    public LoginBO(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void enterUserNameAndClickNext(String userName) {
        loginPage.enterLoginAndSubmit(userName);
    }

    public GmailBO enterPasswordAndClickNext(String password) {
        loginPage.enterPasswordAndSubmit(password);
        return new GmailBO(driver);
    }

    public boolean isWrongPasswordMessageDisplayed() {
        return !loginPage.getWrongPasswordMessageText().isEmpty();
    }
}