package POM;

import com.DriverSetup.DriverFactory;
import com.Utils.commonUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
}
