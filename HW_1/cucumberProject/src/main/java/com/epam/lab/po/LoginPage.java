package com.epam.lab.po;

import com.epam.lab.custom.elements.TextBox;
import com.epam.lab.fielddecorator.MyFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.epam.lab.utils.WaitUtils.waitUntilWrongPasswordMessageIsDisplayed;
import static com.epam.lab.utils.constants.Constants.LOG;

public class LoginPage {

    @FindBy(id = "identifierId")
    private WebElement loginInput;
    @FindBy(id = "identifierNext")
    private WebElement nextButton;
    @FindBy(xpath = "//input[@name='password']")
    private TextBox passwordInput;
    @FindBy(id = "passwordNext")
    private WebElement passwordNextButton;
    @FindBy(xpath = "//div[@id='password']//div[@jsname='B34EJ']")
    private WebElement wrongPasswordMessage;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new MyFieldDecorator(driver), this);
        LOG.info("Login page " + Thread.currentThread().getId());
    }

    public void enterLoginAndSubmit(String login) {
        LOG.info("Enter login and submit " + Thread.currentThread().getId());
        loginInput.sendKeys(login);
        nextButton.click();
    }

    public void enterPasswordAndSubmit(String password) {
        LOG.info("Enter password and submit " + Thread.currentThread().getId());
        passwordInput.typeAndSubmit(password);
    }

    public String getWrongPasswordMessageText() {
        waitUntilWrongPasswordMessageIsDisplayed(driver, wrongPasswordMessage);
        return wrongPasswordMessage.getText();
    }
}