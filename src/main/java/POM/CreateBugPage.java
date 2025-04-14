package POM;

import com.DriverSetup.DriverFactory;
import com.Utils.commonUtility;
import hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateBugPage {
    commonUtility util;

    public CreateBugPage(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
        util = new commonUtility();
    }

    @FindBy(id = "project-jump") public WebElement eleProjectDropDown;
    @FindBy(xpath = "//a[text()='All Projects']/parent::div/preceding-sibling::div/a") public List<WebElement> eleProjectsLists;
    @FindBy(className = "current-project") public WebElement eleCurrentProject;
    @FindBy(className = "issues") public WebElement eleIssuesTab;
    @FindBy(xpath = "//div[@id='content']/h2") public WebElement eleIssuesScreen;
    @FindBy(css = ".new-issue") public WebElement eleNewIssueButton;
    @FindBy(id = "issue-form") public WebElement eleNewIssuesScreen;
    @FindBy(id = "issue_subject") public WebElement eleSubject;
    @FindBy(xpath = "//textarea[@id='issue_description']") public WebElement eleDescription;
    @FindBy(id = "issue_priority_id") public WebElement eleSelectPriority;
    @FindBy(id = "issue_assigned_to_id") public WebElement eleSelectAssignee;
    @FindBy(id = "issue_custom_field_values_1") public WebElement elseSelectSeverity;
    @FindBy(id = "issue_custom_field_values_2") public WebElement eleScreenName;
    @FindBy(name = "commit") public WebElement eleCreateButton;
    @FindBy(id = "flash_notice") public WebElement eleSuccessBugCreateMessage;
    @FindBy(linkText = "Edit") public WebElement eleEdit;
    @FindBy(name = "issue[start_date]") public WebElement elsStartDate;
    @FindBy(name = "issue[due_date]") public WebElement eleDueDate;
    @FindBy(xpath = "//div[@id='update']//input[@name='commit']") public WebElement eleSubmitButton;


    public void toCheckThatProjectDropDownIsPresentOrNot() {
        if(!util.waitUntilElementVisible(eleProjectDropDown).isDisplayed()){
            System.out.println(eleProjectDropDown+" Element id not present");
        }
    }
    public void selectProject(String project) {
        eleProjectDropDown.click();
        util.waitSometime();
        for(WebElement projectItem : eleProjectsLists){
            if(projectItem.getText().equalsIgnoreCase(project)){
                projectItem.click();
                break;
            }
            else{
                System.out.println("Project does not exists...");
            }
        }
    }
    public String currentProject(){
        return util.waitUntilElementVisible(eleCurrentProject).getText();
    }
    public void clickOnIssuesTab() {
        util.waitUntilElementClickable(eleIssuesTab).click();
    }
    public String screenNameReturn(){
        String issueScreen;
        issueScreen = util.waitUntilElementVisible(eleIssuesScreen).getText();
        return issueScreen;
    }
    public void clickNewIssueButton() {
        util.waitUntilElementClickable(eleNewIssueButton).click();
    }
    public boolean checkNewIssueScreen(){
        return util.waitForElementVisible(eleNewIssuesScreen);
    }
    public void fillTheIssuesFields(String subject, String description, String priority, String assignee, String severity, String screenName) {
        fillSubjectDetails(subject);
        fillDescription(description);
        selectPriority(priority);
        selectAssignee(assignee);
        selectSeverity(severity);
        fillScreenName(screenName);
    }
    private void fillScreenName(String screenName) {
        eleScreenName.clear();
        eleScreenName.sendKeys(screenName);
    }
    private void selectSeverity(String severity) {
        Select select = new Select(elseSelectSeverity);
        select.selectByVisibleText(severity);
    }
    private void selectAssignee(String assignee) {
        Select select = new Select(eleSelectAssignee);
        select.selectByVisibleText(assignee);
    }
    private void selectPriority(String priority) {
        Select select = new Select(eleSelectPriority);
        select.selectByVisibleText(priority);
    }
    private void fillDescription(String description) {
        eleDescription.clear();
        eleDescription.sendKeys(description);
    }
    private void fillSubjectDetails(String subject) {
        eleSubject.clear();
        eleSubject.sendKeys(subject);
    }
    public void clickCreateButton() {
        util.waitUntilElementClickable(eleCreateButton).click();
        util.waitForElementVisible(eleSuccessBugCreateMessage);
    }
    public String verifyTheIssueCreate() {
        Hooks.BugId = eleSuccessBugCreateMessage.getText().replaceAll("\\D+", "");
        return eleSuccessBugCreateMessage.getText().replaceAll("#\\d+", "");
    }
    public void navigateToBugDetailsScreen(String bugId) {
        WebElement bugTR = DriverFactory.getDriver().findElement(By.id("issue-"+bugId));
        bugTR.findElement(By.className("id")).click();
    }
    public void clickOnEdit() {
        util.waitUntilElementClickable(eleEdit).click();
    }
    public void setDates() {
        util.waitUntilElementVisible(elsStartDate).clear();
        util.waitUntilElementVisible(elsStartDate).sendKeys(getIssueStartDate());
        util.waitUntilElementVisible(eleDueDate).clear();
        util.waitUntilElementVisible(eleDueDate).sendKeys(getIssueDueDate());
    }
    public void clickSubmitButton() {
        util.waitUntilElementClickable(eleSubmitButton).click();
        util.waitForElementVisible(eleSuccessBugCreateMessage);
    }
    public String verifyTheIssueUpdate() {
        return eleSuccessBugCreateMessage.getText();
    }

    public String getIssueStartDate(){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        // String currentDate = format.format(date);

        // Create Calendar instance and add 2 days
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);

        // Format the new date
        String dateAfterTwoDays = format.format(cal.getTime());
        return dateAfterTwoDays;
    }
    public String getIssueDueDate(){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        // String currentDate = format.format(date);

        // Create Calendar instance and add 2 days
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 3);

        // Format the new date
        String dateAfterThreeDays = format.format(cal.getTime());
        return dateAfterThreeDays;
    }


}
