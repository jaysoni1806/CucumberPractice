package stepdefinitions;

import POM.CreateBugPage;
import com.Utils.commonUtility;
import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;

public class CreateBug {
    private static final Logger logger = LogManager.getLogger(CreateBug.class);
    CreateBugPage crtBug = new CreateBugPage();
    @Given("Project dropdown should be present")
    public void projectDropdownShouldBePresent() throws InterruptedException {
        crtBug.toCheckThatProjectDropDownIsPresentOrNot();
        logger.info("Project's dropdown is present on the screen.");
    }

    @When("Click on project dropdown and select {string} project")
    public void clickOnProjectDropdownAndSelectProject(String project) {
        crtBug.selectProject(project);
        logger.info("Clicked on dropdown and select "+project+" as a Project");
    }

    @Then("User should redirect on the Project Overview screen")
    public void userShouldRedirectOnProjectOverviewScreen() {
        Assert.assertEquals(crtBug.currentProject(),"QA Internal");
        logger.info("User is redirected on Project Overview screen");
    }

    @Then("Click on Issues tab and verify that Issue screen is present or not")
    public void clickOnIssuesTabAndVerifyThatIssueScreenIsPresentOrNot() {
        crtBug.clickOnIssuesTab();
        Assert.assertEquals(crtBug.screenNameReturn(),"Issues");
    }

    @And("Check that New issue button and click on it if present")
    public void checkThatNewIssueButtonAndClickOnItIfPresent() {
        crtBug.clickNewIssueButton();
    }

    @Given("User should be on the New Issue Screen")
    public void userShouldBeOnTheNewIssueScreen() {
        Assert.assertTrue(crtBug.checkNewIssueScreen(),"New Issue Screen does not presented yet.");
    }

    @And("Fill all the required fields in the form")
    public void fillAllTheRequiredFieldsInTheForm() {
        ArrayList<String[]> testData = commonUtility.excelReader();
        String subject = "",description = "",priority = "",assignee = "",severity = "",screenName = "";
        for (String[] row : testData) {
            subject = row[0];
            description = row[1];
            priority = row[2];
            assignee = row[3];
            severity = row[4];
            screenName = row[5];
        }
        crtBug.fillTheIssuesFields(subject, description, priority, assignee, severity, screenName);
    }

    @When("Click on Create button")
    public void clickOnCreateButton() {
        
    }

    @Then("New issue should create")
    public void newIssueShouldCreate() {
    }
}
