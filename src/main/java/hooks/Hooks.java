package hooks;

import com.DriverSetup.DriverFactory;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import java.time.Duration;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    public static String BugId;
    @BeforeTest
    public void beforeScenario() {
        DriverFactory.setDriver();
        DriverFactory.getDriver().get("https://redmine.openxcell.dev/login");
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
    }
}
