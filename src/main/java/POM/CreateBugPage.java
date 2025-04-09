package POM;

import com.DriverSetup.DriverFactory;
import com.Utils.commonUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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



    public void toCheckThatProjectDropDownIsPresentOrNot() throws InterruptedException {
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
}
