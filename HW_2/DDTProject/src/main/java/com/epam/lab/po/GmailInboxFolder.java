package com.epam.lab.po;

import com.epam.lab.custom.elements.CheckBox;
import com.epam.lab.custom.elements.TextBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.epam.lab.utils.constants.Constants.*;

public class GmailInboxFolder extends GmailFolder {

    @FindBy(xpath = "//div[@class='aio UKr6le']//a")
    private WebElement inboxFolderBtn;
    @FindBy(xpath = "//div[@role='checkbox']")
    private List<CheckBox> inboxEmailCheckerElements;
    @FindBy(xpath = "//span[@class='asa bjy']")
    private WebElement moreCheckedEmailsOptionsElement;
    @FindBy(xpath = "//div[contains(text(),'Mark as important')]")
    private WebElement importantMarkItem;
    @FindBy(xpath = "//input[@name='q']")
    private TextBox seachMailTextBox;

    public GmailInboxFolder(WebDriver driver) {
        super(driver);
        LOG.info("Inbox folder");
    }

    public void openInboxFolder() {
        try {
            inboxFolderBtn.click();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            inboxFolderBtn.click();
        }
    }

    public void selectEmail(int index) {
        LOG.info("Select " + index + " email");
        inboxEmailCheckerElements.get(index).click();
    }

    public void openCheckedEmailsMoreOptions() {
        LOG.info("Open more options for selected emails");
        moreCheckedEmailsOptionsElement.click();
    }

    public void clickImportantItem() {
        LOG.info("Click on \"Mark as important\" item");
        importantMarkItem.click();
    }

    public String getActionDoneMessageText() {
        LOG.info("Get message about successful marked to important operation");
        return super.getActionDoneMessageText();
    }

    public String getActionDoneMessageConversationsCount() {
        LOG.info("Get marked conversations count");
        return super.getActionDoneMessageConversationsCount();
    }

    public GmailImportantFolder typeAndSubmitSearchMailInput(String value) {
        LOG.info("Navigate to \"Important folder\" using search input");
        seachMailTextBox.typeAndSubmit(value);
        return new GmailImportantFolder(driver);
    }
}