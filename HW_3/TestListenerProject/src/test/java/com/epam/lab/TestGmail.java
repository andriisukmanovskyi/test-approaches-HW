package com.epam.lab;

import com.epam.lab.bo.GmailBO;
import com.epam.lab.bo.LoginBO;
import com.epam.lab.listener.TestListener;
import com.epam.lab.utils.PropertyUtils;
import com.epam.lab.utils.driverpool.ChromeDriverPool;
import com.epam.lab.utils.parsers.csv.CSVParser;
import com.epam.lab.utils.parsers.xlsx.XLSXParser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;

import static com.epam.lab.utils.constants.Constants.*;
import static com.epam.lab.utils.constants.TestDataConstants.*;

public class TestGmail {

    private Properties properties;
    private ChromeDriverPool chromeDriverPool;

    @DataProvider(name = "loginDataCSV", parallel = true)
    private Iterator<Object[]> loginDataCSV() {
        return CSVParser.parseCSVFile(new File(LOGIN_DATA_CSV_FILE_PATH)).iterator();
    }

    @DataProvider(name = "loginDataXLSX", parallel = true)
    private Iterator<Object[]> loginDataXLSX() {
        return XLSXParser.parseXLSFile(new File(LOGIN_DATA_XLSX_FILE_PATH)).iterator();
    }

    @BeforeClass
    public void init() {
        properties = new Properties();
        chromeDriverPool = new ChromeDriverPool();
        properties = PropertyUtils.getProperties();
        System.setProperty(properties.getProperty(CHROME_DRIVER_TYPE_PROPERTY_KEY), properties.getProperty(CHROME_DRIVER_PATH_PROPERTY_KEY));
    }

    // we can choose one of the defined above data providers
    @Test(dataProvider = "loginDataXLSX")
    public void testDeleteEmails(String userName, String password) {
        WebDriver driver = chromeDriverPool.getDriver();
        driver.get(GMAIL_LOGIN_PAGE_URL);
        LoginBO loginBO = new LoginBO(driver);
        GmailBO gmailBO = loginBO.loginGmail(userName, password);
        Assert.assertEquals(gmailBO.markEmailsAsImportant(EMAILS_COUNT_TO_MARK_AS_IMPORTANT), EMAILS_COUNT_TO_MARK_AS_IMPORTANT);
        gmailBO.openImportantFolder();
        Assert.assertEquals(gmailBO.deleteEmails(EMAILS_COUNT_TO_BE_DELETED), EXPECTED_MESSAGE_AFTER_DELETING);
    }

    @AfterMethod
    public void closeDriver() {
        chromeDriverPool.closeDriver();
    }

    @BeforeTest
    public void logTestStart(){
        LOG.info("================== successfulTest START ==================");
    }

    @AfterTest
    public void logTestEnd() {
        LOG.info("================== successfulTest END ==================");
    }

    @BeforeSuite
    public void logSuiteStart() {
        LOG.info("================== Suite1 START ==================");
    }

    @AfterSuite
    public void logSuiteEnd() {
        LOG.info("================== Suite1 END ==================\n");
    }
}