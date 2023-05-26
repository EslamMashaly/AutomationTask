package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    private final By emailField=By.xpath("//div[@class='form-group']//input[@type='email']");
    private final By passwordField=By.xpath("//input[@type='password']");
    private final By rememberMeCheckBox=By.xpath("//label[normalize-space()='Remember Me']");
    private final By logInButton=By.cssSelector(".btn.btn-default.btn-lg.btn-block.effect.ladda-button.waves-effect");
    private final By loginAssertionText=By.cssSelector("h4[class='author__title'] strong");


    public LoginPage(WebDriver driver){
        this.driver=driver;
    }


    public LoginPage enterEmail(String email){
        type(emailField,email);
        return this;
    }


    public LoginPage enterPassword(String password){
        type(passwordField,password);
        return this;
    }



    public LoginPage checkRememberMeBox(){
        driver.findElement(rememberMeCheckBox).click();
        return this;
    }


    public void confirmLogin(){
        driver.findElement(logInButton).click();
    }


    public String getLoginAssertionText(){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(loginAssertionText)));
       return driver.findElement(loginAssertionText).getText();
    }


    private void type(By locator, String text){
        driver.findElement(locator).sendKeys(text);
    }

}
