package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

// Test running with local environment
public class Browser {
    static WebDriver driver;

    public static WebDriver getBrowser(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Internet Explorer")) {
            System.setProperty("webdriver.ie.driver", "driver/MicrosoftWebDriver");
            driver = new InternetExplorerDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }
}