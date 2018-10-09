package com.epam.lab.utils.driverpool;

import com.epam.lab.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.epam.lab.utils.constants.Constants.*;

public class ChromeDriverPool {

    private static ThreadLocal<WebDriver> chromeDriverPool = ThreadLocal.withInitial(() -> {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(
                Integer.parseInt(PropertyUtils.getProperties().getProperty(CHROME_DRIVER_TIMEOUT_PROPERTY_KEY)), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    });

    public WebDriver getDriver() {
        return chromeDriverPool.get();
    }

    public void closeDriver() {
        LOG.info("close driver " + Thread.currentThread().getId());
        chromeDriverPool.get().quit();
        chromeDriverPool.remove();
    }
}