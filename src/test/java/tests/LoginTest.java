package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Var;

import java.io.File;
import java.io.IOException;

public class LoginTest extends TestBase {

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new Object[][]{
                {Var.correctUsername, Var.correctPassword, true, Var.myAccountText},
                {Var.correctUsername, Var.incorrectPassword, false, Var.wrongEmailOrPasswordMessage},
                {Var.correctUsername, Var.emptyInput, false, Var.enterPasswordMessage},
                {Var.incorrectUsername, Var.correctPassword, false, Var.wrongEmailOrPasswordMessage},
                {Var.invalidUsername, Var.correctPassword, false, Var.enterEmailMessage},
                {Var.emptyInput, Var.correctPassword, false, Var.enterEmailMessage}
        };
    }

    @Test(dataProvider = "Authentication")
    public void login(String dataUsername, String dataPassword, Boolean state, Object displayItem) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateHomePage();
//        wait = new WebDriverWait(driver.get(), 5);
        loginPage.click(Var.loginButton);
        loginPage.isElementDisplayed(Var.loginDialog);
        loginPage.enterTheInput(Var.emailInputArea, dataUsername);
        loginPage.enterTheInput(Var.passwordInputArea, dataPassword);
        loginPage.click(Var.loginSubmitButton);
        if (state) {
            loginPage.isElementDisplayed((By) displayItem);
        }
        else {
            loginPage.isTextDisplayed(Var.errorBox, (String) displayItem);
        }

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./screenshots/" + result.getName() + ".png"));
        }
    }
}
