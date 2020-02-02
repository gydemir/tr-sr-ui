package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Var;

import java.io.IOException;

public class BoutiqueLinksTest extends TestBase {

    @Test
    public void linkTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateHomePage();
        loginPage.scrollDownUntilBoutiqueEnds();
        try {
            loginPage.writeDataToTheCsvFile();
        }
        catch (Exception e) {
            System.out.println("kurek");
        }
        Assert.assertFalse(loginPage.isFileEmpty(Var.responseCodesPath));
    }
}