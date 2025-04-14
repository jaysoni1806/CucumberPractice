package extentreportmanager;

import com.Utils.commonUtility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExtentManager {
    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;
    public static ExtentTest test;

    public static void ExtentSetup() throws FileNotFoundException {
        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/ExtentReport/Report_"+ commonUtility.getDateAndTime()+".html");
        extentSparkReporter.config().setDocumentTitle("Redmine Report");
        extentSparkReporter.config().setReportName("Jay Soni");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }
    public static void extentFlush(){
        extentReports.flush();
    }
}
