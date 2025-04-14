package hooks;

import com.DriverSetup.DriverFactory;
import extentreportmanager.ExtentManager;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import listener.ExtentReport;
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
   @AfterSuite
    public void tearDown(){
        DriverFactory.quiteDriver();
        //ExtentManager.extentFlush();
    }
}
