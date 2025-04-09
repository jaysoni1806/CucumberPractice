package POM;

import com.DriverSetup.DriverFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Login {

    public Login(){
        PageFactory.initElements(DriverFactory.getDriver(),this);
    }

    @FindBy(id = "username")public WebElement eleUserName;
    @FindBy(id = "password")public WebElement elePassword;
    @FindBy(id = "login-submit")public WebElement eleLoginButton;
    @FindBy(id = "flash_error")public List<WebElement> eleInvalidLoginMessage;

    public void LoginWithCred(String uname,String password) throws InterruptedException {
        EnterUserName(uname);
        EnterPassword(password);
        ClickLoginButton();
    }

    private void EnterPassword(String password) {
        //commonUtil.waitUntillElementDisplay(elePassword);
        elePassword.clear();
        elePassword.sendKeys(password);
    }

    private void EnterUserName(String uname) {
        //commonUtil.waitUntillElementDisplay(eleUserName);
        eleUserName.clear();
        eleUserName.sendKeys(uname);
    }
    public void ClickLoginButton() throws InterruptedException {
        Thread.sleep(2000);
        eleLoginButton.click();
        Thread.sleep(2000);
    }

    public String isvalidationMessage() throws InterruptedException {
        WebElement messageLogin = null;
        if(eleInvalidLoginMessage.size()> 0 ){
            //commonUtil.waitUntillElementDisplay(eleInvalidLoginMessage);
            messageLogin = eleInvalidLoginMessage.get(0);
            return messageLogin.getText();
        }
        else{
            Thread.sleep(2000);
            return null;
        }

    }

    public void LoginWithInValidCred(String uname, String password) throws InterruptedException {

        EnterUserName(uname);
        EnterPassword(password);
        ClickLoginButton();
    }
}
