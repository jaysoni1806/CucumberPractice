package stepdefinitions;

import POM.Login;
import com.DriverSetup.DriverFactory;
import com.Utils.commonUtility;
import hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;

public class LoginSteps {
    public Login login = new Login();;
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);

    @When("user enters invalid {string} and {string}")
    public void enterInvalidCredentials(String username , String password) throws InterruptedException {
        // login = new Login(Hooks.driver);
        login.LoginWithInValidCred(username,password);
        logger.info("Login with invalid credentials.-- "+username+" / "+password);
    }

    @Then("verify the error")
    public void verifyLoginError() throws InterruptedException {
        Assert.assertEquals(login.isvalidationMessage(), "Invalid user or password","Something went wrong in login validation");
        logger.info("Validate the invalid user error message."+ login.isvalidationMessage());
    }


    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() throws InterruptedException {
           // login = new Login(Hooks.driver);
            login.LoginWithCred("JayS","Openxcell@2023");
            logger.info("Login with valid user's credentials.");
    }
    @Then("user should redirect to the home page")
    public void user_should_redirect_to_the_home_page() throws InterruptedException {
        Assert.assertEquals(DriverFactory.getDriver().getCurrentUrl(),"https://redmine.openxcell.dev/my/page");
        logger.info("Redmine Home screen is presented.");
    }
}
