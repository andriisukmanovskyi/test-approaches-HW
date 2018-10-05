package com.epam.lab.bo;

import com.epam.lab.po.GmailFolder;
import com.epam.lab.po.GmailInboxFolder;
import com.epam.lab.po.GmailImportantFolder;
import org.openqa.selenium.WebDriver;

public class GmailBO {

    private static final String IMPORTANT_FOLDER_SEARCH_VALUE = "is:important";

    private GmailInboxFolder gmailInboxFolder;
    private GmailImportantFolder gmailImportantFolder;

    public GmailBO(WebDriver driver) {
        gmailInboxFolder = new GmailInboxFolder(driver);
    }

    public int markEmailsAsImportant(int emailsCount) {
        gmailInboxFolder.openInboxFolder();
        selectEmails(emailsCount, gmailInboxFolder);
        gmailInboxFolder.openCheckedEmailsMoreOptions();
        gmailInboxFolder.clickImportantItem();
        return Integer.parseInt(gmailInboxFolder.getActionDoneMessageConversationsCount());
    }

    public void openImportantFolder() {
        gmailImportantFolder = gmailInboxFolder.typeAndSubmitSearchMailInput(IMPORTANT_FOLDER_SEARCH_VALUE);
//        gmailImportantFolder.refresh();
    }

    public String deleteEmails(int emailsCount) {
        selectEmails(emailsCount, gmailImportantFolder);
        gmailImportantFolder.clickDeleteBtn();
        return gmailImportantFolder.getActionDoneMessageText();
    }

    private void selectEmails(int emailsCount, GmailFolder gmailFolder) {
        for (int i = 0; i < emailsCount; i++) {
            gmailFolder.selectEmail(i);
        }
    }
}