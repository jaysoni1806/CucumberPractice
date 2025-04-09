package stepdefinitions;

import POM.CreateBugPage;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CreateBug {
    private static final Logger logger = LogManager.getLogger(CreateBug.class);
    CreateBugPage crtBug = new CreateBugPage();
    @Given("Project dropdown should be present")
    public void projectDropdownShouldBePresent() throws InterruptedException {
        crtBug.toCheckThatProjectDropDownIsPresentOrNot();
        logger.info("Project's dropdown is present on the screen.");
    }

    @When("click on project dropdown and select {string} project")
    public void clickOnProjectDropdownAndSelectProject(String project) {
        crtBug.selectProject(project);
        logger.info("Clicked on dropdown and select "+project+" as a Project");
    }

    @Then("user should redirect on the Project Overview screen")
    public void userShouldRedirectOnProjectOverviewScreen() {
        Assert.assertEquals(crtBug.currentProject(),"QA Internal");
        logger.info("User is redirected on Project Overview screen");
    }
}
