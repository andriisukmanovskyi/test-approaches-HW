package com.epam.lab.utils.constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {

    public static final Logger LOG = LogManager.getLogger("com.epam.lab");

    public static final String LOGIN_DATA_XML_FILE_PATH = "src/test/resources/users_login_data.xml";
    public static final String LOGIN_DATA_CSV_FILE_PATH = "src/test/resources/users_login_data.csv";
    public static final String LOGIN_DATA_XLSX_FILE_PATH = "src/test/resources/users_login_data.xlsx";

    public static final String CHROME_DRIVER_TYPE_PROPERTY_KEY = "chromeDriverType";
    public static final String CHROME_DRIVER_PATH_PROPERTY_KEY = "chromeDriverPath";
    public static final String CHROME_DRIVER_TIMEOUT_PROPERTY_KEY = "chromeDriverTimeOut";

    public static final int EXPLICITY_WAIT_TIME_OUT_VALUE = 10;

    public static final String GMAIL_LOGIN_PAGE_URL = "https://mail.google.com";
}