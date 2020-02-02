package utils;

import org.openqa.selenium.By;

public class Var {
    public static final String base = "https://www.trendyol.com";
    public static final String correctUsername = "gydemir@gmail.com";
    public static final String correctPassword = "gulen2017";
    public static final String incorrectUsername = "trendyol@gitlab.com";
    public static final String incorrectPassword = "Trendyol";
    public static final String emptyInput = "";
    public static final String invalidUsername = "gydemirgmail.com";
    public static final String responseCodesPath = "csvFile/responseCodes.csv";
    public static final String timesPath = "csvFile/times.csv";

    // Login
    public static String enterEmailMessage = "Lütfen email adresinizi giriniz.";
    public static String enterPasswordMessage = "Lütfen şifre giriniz.";
    public static String wrongEmailOrPasswordMessage = "Hatalı E-Posta / Şifre. Tekrar Deneyin.";
    public static By loginSubmitButton = By.xpath("//a[@class='login-submit send-button']");
    public static By myAccountText = By.xpath("//span[text()='Hesabım']");
    public static By errorBox = By.xpath("//div[@id='errorBox']");
    public static By loginButton = By.xpath("//div[@class='icon-container']//i[@class='icon navigation-icon-user']");
    public static By loginDialog = By.xpath("//div[@class='popup-form-main']");
    public static By emailInputArea = By.xpath("//input[@id='email']");
    public static By passwordInputArea = By.id("password");

}
