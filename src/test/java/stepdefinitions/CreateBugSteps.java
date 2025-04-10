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

public class CreateBugSteps {
    private static final Logger logger = LogManager.getLogger(CreateBugSteps.class);
    CreateBugPage crtBug = new CreateBugPage();
    @Given("Project dropdown should be present")
    public void projectDropdownShouldBePresent() {
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
        logger.info("User is redirected on Issue screen");
    }
    @And("Check that New issue button and click on it if present")
    public void checkThatNewIssueButtonAndClickOnItIfPresent() {
        crtBug.clickNewIssueButton();
        logger.info("Clicked on New issue button");
    }

    @Given("User should be on the New Issue Screen")
    public void userShouldBeOnTheNewIssueScreen() {
        Assert.assertTrue(crtBug.checkNewIssueScreen(),"New Issue Screen does not presented yet.");
        logger.info("User is redirected on New Issue screen");
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
        logger.info("Fill all the required bug details.");
    }
    @When("Click on Create button")
    public void clickOnCreateButton() {
        crtBug.clickCreateButton();
        logger.info("Click on Create button.");
    }
    @Then("New issue should create and Back to issue screen")
    public void newIssueShouldCreate() {
        Assert.assertEquals(crtBug.verifyTheIssueCreate(),"Issue  created.");
        crtBug.clickOnIssuesTab();
        logger.info("New bug created-> "+ Hooks.BugId+", and back to issue listing.");
    }

    @Given("User should be on bug details screen.")
    public void userShouldBeOnBugDetailsScreen() {
        crtBug.navigateToBugDetailsScreen(Hooks.BugId);
        logger.info("Clicked on issue id and navigate to view issue screen.");
    }
    @And("Click on Edit")
    public void clickOnEdit() {
        crtBug.clickOnEdit();
        logger.info("Click on Edit and navigate to edit screen.");
    }
    @When("Set Start date and Due date for the existing opened issue")
    public void setStartDateAndDueDateForTheExistingOpenedIssue() {
        crtBug.setDates();
        logger.info("Update the Start Date and the Due Date.");
    }
    @And("Click on Submit button")
    public void clickOnSubmitButton() {
        crtBug.clickSubmitButton();
        logger.info("Click on Submit button.");
    }
    @Then("Issue should be update")
    public void issueShouldBeUpdate() {
        Assert.assertEquals(crtBug.verifyTheIssueUpdate(),"Successful update.");
        logger.info("Issue successfully updated.");
    }
}
