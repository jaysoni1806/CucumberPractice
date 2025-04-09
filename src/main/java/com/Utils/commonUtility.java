package com.Utils;

import com.DriverSetup.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class commonUtility {

    WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30));
    public void waitSometime(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitUntilElementClickable(WebElement element){
            wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement waitUntilElementVisible(WebElement element){
            return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
