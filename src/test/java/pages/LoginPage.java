package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Var;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {

    public By allBoutiqueLinks = By.xpath("//article[@class='component-item']//a");
    public By allImageLinks = By.xpath("//article[@class='component-item']//a//span//img");
    public By closeGenderButton = By.xpath("//a[@class='fancybox-item fancybox-close']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateHomePage() {
        driver.get(Var.base);
        closeModal();
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    public void isElementDisplayed(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(driver.findElement(locator).isDisplayed());
    }

    public void enterTheInput(By locator, String input) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(input);
    }

    public void closeModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeGenderButton));
        driver.findElement(closeGenderButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeGenderButton));
    }

    public void isTextDisplayed(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String value = driver.findElement(locator).getText();
        Assert.assertTrue(value.contains(text));
    }

    public ArrayList<String> getAllBoutiqueLinks() {
        int size = driver.findElements(allBoutiqueLinks).size();
        List<WebElement> allLinks = driver.findElements(allBoutiqueLinks);
        ArrayList<String> linkArray = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String endpoint = allLinks.get(i).getAttribute("href");
            linkArray.add(endpoint);
        }
        return linkArray;
    }

    public int getStatusCode(String endpoint) {
        RestAssured.baseURI = Var.base;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.post("/" + endpoint);
        int statusCode = response.getStatusCode();

        return statusCode;
    }

    public void writeDataToTheCsvFile() throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(Var.responseCodesPath));
        StringBuilder sb = new StringBuilder();

        int size = getAllBoutiqueLinks().size();
        for (int i = 0; i < size; i++) {
            int code = getStatusCode(getAllBoutiqueLinks().get(i));
            sb.append("-" + getAllBoutiqueLinks().get(i) + "-");
            sb.append(",");
            sb.append("-" + code + "-" + "\n");
        }
        String columnNamesList = "Link , Status Code";
        br.append(columnNamesList + "\n");
        br.write(sb.toString());
        br.close();
    }

    public ArrayList<String> getAllImageLinks() {
        int size = driver.findElements(allImageLinks).size();
        List<WebElement> allImages = driver.findElements(allImageLinks);
        ArrayList<String> images = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String image = allImages.get(i).getAttribute("src");
            images.add(image);
        }
        return images;
    }

    public boolean isFileEmpty(String path) {
        File file = new File(path);
        boolean check = file.length() < 1;
        return check;
    }

    public long getImageLoadTime(String path) {
        RestAssured.baseURI = Var.base;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.post(path);
        long time = response.getTime();
        return time;
    }

    public void writeTimeIntoCsvFile() throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(Var.timesPath));
        StringBuilder sb = new StringBuilder();

        int size = getAllImageLinks().size();
        for (int i = 0; i < size; i++) {
            long time = getImageLoadTime(getAllImageLinks().get(i));
            int status = getStatusCode(getAllImageLinks().get(i));
            sb.append("-" + getAllImageLinks().get(i) + "-");
            sb.append(",");
            sb.append("-" + time + "-");
            sb.append(",");
            sb.append("-" + status + "-" + "\n");
        }
        String columnNamesList = "Image Link , Time , Status Code";
        br.append(columnNamesList + "\n");
        br.write(sb.toString());
        br.close();
    }

    public void scrollDownUntilBoutiqueEnds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        js.executeScript("return document.readyState").equals("complete");
    }
}