package hooks;

import Reports.ExcelResultWriter;
import com.DriverSetup.DriverFactory;
import com.Utils.commonUtility;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    public static Properties prop = new Properties();


    public static String BugId;

    @BeforeTest
    public void beforeScenario() throws FileNotFoundException {
        try {
            prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Properties/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DriverFactory.setDriver();
        DriverFactory.getDriver().get(prop.getProperty("url"));
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //ExtentManager.ExtentSetup();
    }
    @AfterStep
    public void afterStep(Scenario scenario) {

        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            logger.error("Step failed: " + scenario.getName());
        }
    }
    @After
    public void tearDown1(Scenario scenario) {
        String scenarioName = scenario.getName();
        String status = scenario.isFailed() ? "Fail" : "Pass";
        ExcelResultWriter.writeResult(scenarioName, status);
    }
   @AfterSuite
    public void tearDown(){
       // Optional: Save after each scenario or once at the end
        ExcelResultWriter.saveExcel("src/test/resources/ExcelReport/ExecutionResults_"+ commonUtility.getDateAndTime()+".xlsx");
        DriverFactory.quiteDriver();
        //ExtentManager.extentFlush();
    }
}
