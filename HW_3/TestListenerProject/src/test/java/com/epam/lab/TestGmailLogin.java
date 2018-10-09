package com.epam.lab;

import com.epam.lab.bo.GmailBO;
import com.epam.lab.bo.LoginBO;
import com.epam.lab.listener.TestListener;
import com.epam.lab.utils.PropertyUtils;
import com.epam.lab.utils.driverpool.ChromeDriverPool;
import com.epam.lab.utils.parsers.stax.UsersLoginDataSTAXParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.util.Iterator;
import java.util.Properties;

import static com.epam.lab.utils.constants.Constants.*;
import static com.epam.lab.utils.constants.TestDataConstants.GMAIL_INBOX_PARTIAL_TITLE;
import static org.testng.Assert.assertTrue;

public class TestGmailLogin {

    private Properties properties;
    private ChromeDriverPool chromeDriverPool;

    @BeforeClass
    public void initDriver() {
        properties = new Properties();
        chromeDriverPool = new ChromeDriverPool();
        properties = PropertyUtils.getProperties();
        System.setProperty(properties.getProperty(CHROME_DRIVER_TYPE_PROPERTY_KEY), properties.getProperty(CHROME_DRIVER_PATH_PROPERTY_KEY));
    }

    @DataProvider(name = "loginDataXML", parallel = true)
    private Iterator<Object[]> loginDataXML() {
        return UsersLoginDataSTAXParser.parseXML(new File(LOGIN_DATA_XML_FILE_PATH)).iterator();
    }

    @Test(dataProvider = "loginDataXML")
    public void testLogin(String userName, String password) {
        WebDriver driver = chromeDriverPool.getDriver();
        driver.get(GMAIL_LOGIN_PAGE_URL);
        LoginBO loginBO = new LoginBO(driver);
        GmailBO gmailBO = loginBO.loginGmail(userName, password);
        assertTrue(gmailBO.titleContains(GMAIL_INBOX_PARTIAL_TITLE));
    }

    @AfterMethod
    public void closeDriver() {
        chromeDriverPool.closeDriver();
    }

    @BeforeTest
    public void logTestStart() {
        LOG.info("================== failedTest START ==================");
    }

    @AfterTest
    public void logTestEnd() {
        LOG.info("================== failedTest END ==================");
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