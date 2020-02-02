package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Var;

import java.io.IOException;

public class BoutiqueImagesTest extends TestBase {

    @Test
    public void imageTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateHomePage();
        loginPage.scrollDownUntilBoutiqueEnds();
        try {
            loginPage.writeTimeIntoCsvFile();
        }
        catch (Exception e) {
            System.out.println("kurek");
        }

        Assert.assertFalse(loginPage.isFileEmpty(Var.timesPath));
    }
}