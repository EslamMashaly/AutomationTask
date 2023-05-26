package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPage {

    private final WebDriver driver;

    private final By firstNameField=By.xpath("//input[@name='first_name']");
    private final By lastNameField=By.xpath("//input[@name='last_name']");
    private final By phoneField=By.xpath("//input[@name='phone']");
    private final By emailField=By.xpath("//input[@name='email']");
    private final By passwordField=By.xpath("//input[@name='password']");
    private final By accountTypeDropdown=By.id("account_type");
    private final By reCaptcha=By.id("recaptcha-anchor");
    private final By signUpButton=By.id("button");
    private final By cookiesAlert=By.id("cookie_stop");
    private final By confirmSignUpText=By.xpath("//p[text()='Please enter your account credentials below']");


    public SignUpPage(WebDriver driver){
        this.driver=driver;
    }


    public boolean enterFirstName(String firstName) {
        type(firstNameField, CapitalizeFirstLetter(firstName));
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*$");
        Matcher matcher = pattern.matcher(firstName);
        if (matcher.matches()) {
            return true;
        } else {
           return false;
        }

    }

    public boolean enterLastName(String lastName){
        type(lastNameField, CapitalizeFirstLetter(lastName));
        Pattern pattern = Pattern.compile("^^[A-Z][a-z]*$");
        Matcher matcher = pattern.matcher(lastName);
        if (matcher.matches()) {
           return true;
        } else {
           return false;
        }

    }


    public boolean enterPhone(String phone){
        type(phoneField,phone);
        Pattern pattern = Pattern.compile("^01\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        } else {
        return false;
        }
    }


    public void enterEmail(String email){
        type(emailField,email);
    }


    public boolean enterPassword(String password) throws RuntimeException {
        type(passwordField,password);
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
                return true;
        } else {
                return false;
        }
    }


    public SignUpPage closeCookiesAlert(){
        driver.findElement(cookiesAlert).click();
        return this;
    }


    public SignUpPage chooseAccountType(String dropDownValue){
        Select dd=new Select(driver.findElement(accountTypeDropdown));
        dd.selectByValue(dropDownValue);
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']"))));
        return this;
    }


    public SignUpPage selectCaptcha(){
        driver.findElement(reCaptcha).click();
        //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(reCaptcha)));
        driver.switchTo().parentFrame();
        return this;
    }


    public LoginPage confirmSignUp(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(signUpButton)));
        driver.findElement(signUpButton).click();
        return new LoginPage(driver);
    }


    public String getSignUpAssertionText(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(confirmSignUpText)));
        return driver.findElement(confirmSignUpText).getText();
    }


    private void type(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }
    private String CapitalizeFirstLetter(String word){
        return word.substring(0,1).toUpperCase()+word.substring(1);
    }
}
