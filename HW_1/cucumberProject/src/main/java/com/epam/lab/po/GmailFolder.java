package com.epam.lab.po;

import com.epam.lab.custom.elements.ActionDoneMessageLabel;
import com.epam.lab.fielddecorator.MyFieldDecorator;
import com.epam.lab.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class GmailFolder {

    protected WebDriver driver;

    @FindBy(xpath = "//div[@class='vh']/span/span[1]")
    private ActionDoneMessageLabel actionDoneMessageLabel;

    public GmailFolder(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new MyFieldDecorator(driver), this);
    }

    public String getActionDoneMessageText() {
        WaitUtils.waitUntilActionDoneMessageIsDisplayed(driver, actionDoneMessageLabel);
        return actionDoneMessageLabel.getText();
    }

    public String getActionDoneMessageConversationsCount() {
        WaitUtils.waitUntilActionDoneMessageIsDisplayed(driver, actionDoneMessageLabel);
        return actionDoneMessageLabel.getConversationsCount();
    }

    public abstract void selectEmail(int index);
}