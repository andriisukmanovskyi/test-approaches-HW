package com.epam.lab.po;

import com.epam.lab.custom.elements.Button;
import com.epam.lab.custom.elements.CheckBox;
import com.epam.lab.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.epam.lab.utils.constants.Constants.LOG;

public class GmailImportantFolder extends GmailFolder {

    @FindBy(xpath = "//div[@class='AO']//div[@class='nH']/div[2]//tr/td[2]/div")
    private List<CheckBox> importantEmailCheckerElements;
    @FindBy(xpath = "//div[@class='aeH']/div[2]/div/div/div/div/div[2]/div[3]")
    private Button deleteSelectedBtn;

    public GmailImportantFolder(WebDriver driver) {
        super(driver);
        LOG.info("Important folder is opened");
    }

    public void selectEmail(int index) {
        LOG.info("Select " + index + " email");
        WaitUtils.waitUntilEmailCheckBoxIsAttachedAndClick(driver, importantEmailCheckerElements, index);
    }

    public String getActionDoneMessageText() {
        LOG.info("Get message about successful delete operation");
        return super.getActionDoneMessageText();
    }

    public String getActionDoneMessageConversationsCount() {
        LOG.info("Get deleted conversations count");
        return super.getActionDoneMessageConversationsCount();
    }

    public void clickDeleteBtn() {
        LOG.info("Click \"Delete\" icon");
        deleteSelectedBtn.click();
    }
}