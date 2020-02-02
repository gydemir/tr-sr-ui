package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;


public class TestBase {
    public WebDriverWait wait ;
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public CapabilityFactory capabilityFactory = new CapabilityFactory();

    @Parameters({ "browser" })
    @BeforeMethod
    public void setup(String browser) throws MalformedURLException {
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));
    }

    public WebDriver getDriver() {
        //Get driver from ThreadLocalMap
       wait = new WebDriverWait(driver.get(), 3);
        return driver.get();
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }

    @AfterClass
    public void terminate() {
        driver.remove();
    }
}
