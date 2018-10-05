package com.epam.lab.utils;

import com.epam.lab.custom.elements.ActionDoneMessageLabel;
import com.epam.lab.custom.elements.CheckBox;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

import java.util.List;

import static com.epam.lab.utils.constants.Constants.EXPLICITY_WAIT_TIME_OUT_VALUE;

public class WaitUtils {

    public static void waitUntilWrongPasswordMessageIsDisplayed(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICITY_WAIT_TIME_OUT_VALUE);
        wait.until(new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                return !element.getText().isEmpty();
            }
        });
    }

    public static void waitUntilEmailCheckBoxIsAttachedAndClick(WebDriver driver, List<CheckBox> importantEmailCheckerElements, int index) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICITY_WAIT_TIME_OUT_VALUE);
        wait.until(new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                try {
                    importantEmailCheckerElements.get(index).click();
                    return true;
                } catch (StaleElementReferenceException e) {
                    importantEmailCheckerElements.set(index, new CheckBox(driver.findElement(By.xpath("//div[@class='AO']//div[@class='nH']/div[2]//tr[" + (index + 1) + "]/td[2]/div"))));
                    return false;
                }
            }
        });
    }

    public static void waitUntilActionDoneMessageIsDisplayed(WebDriver driver, ActionDoneMessageLabel label) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICITY_WAIT_TIME_OUT_VALUE);
        wait.until(new ExpectedCondition<Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable WebDriver driver) {
                try {
                    label.isDisplayed();
                    return true;
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }
        });
    }
}