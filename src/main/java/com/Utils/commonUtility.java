package com.Utils;

import com.DriverSetup.DriverFactory;
import excelReader.Xls_Reader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

public class commonUtility {

    WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30));
    public void waitSometime(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement waitUntilElementClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public WebElement waitUntilElementVisible(WebElement element){
            return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public boolean waitForElementVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        return true;
    }

    @org.jetbrains.annotations.NotNull
    public static ArrayList<String[]> excelReader(){
        Xls_Reader reader = new Xls_Reader(System.getProperty("user.dir")+"/src/test/resources/External Files/Redmine_issue_format.xlsx");
        ArrayList<String[]> myData = new ArrayList<>();
        String Subject = null;
        String Description = null;
        String Priority = null;
        String Assignee = null;
        String Severity = null;
        String Screen_Name = null;
        for(int rowNum = 2; rowNum <=reader.getRowCount("Issue Format"); rowNum++){
            Subject = reader.getCellData("Issue Format", "Subject",rowNum);
            Description = reader.getCellData("Issue Format", "Description",rowNum);
            Priority = reader.getCellData("Issue Format", "Priority",rowNum);
            Assignee = reader.getCellData("Issue Format", "Assignee",rowNum);
            Severity = reader.getCellData("Issue Format", "Severity",rowNum);
            Screen_Name = reader.getCellData("Issue Format", "Screen Name",rowNum);
            myData.add(new String[]{Subject, Description, Priority, Assignee, Severity, Screen_Name});
        }
        return myData;
    }

    public static String getDateAndTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        return currentDate;
    }
}
