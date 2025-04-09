package com.DriverSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public static WebDriver getDriver(){
        return driver.get();
    }
    public static void setDriver(){
            driver.set(new ChromeDriver());
    }
    public static void quiteDriver(){
        driver.get().quit();
    }
}
