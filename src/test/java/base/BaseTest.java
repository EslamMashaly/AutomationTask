package base;

import TestDataFiles.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import pages.SignUpPage;
import Report.TakeScreenShot;
import settings.TestSettings;

import java.io.IOException;


public class BaseTest {

    private WebDriver driver;
    protected SignUpPage signUpPage;
    protected TakeScreenShot takeScreenShot;

    @Parameters({"BrowserName"})
    @BeforeMethod
    public void launchApplication(String browserName){

            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();

            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();

            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }

        driver.get(TestSettings.URL);
        driver.manage().window().maximize();
        signUpPage = new SignUpPage(driver);
        takeScreenShot = new TakeScreenShot(driver);
    }


    @AfterMethod()
    public void CloseBrowser( ) {
        driver.quit();
    }

    @DataProvider(name = "ExcelData")
    public Object [][] registrationData() throws IOException {
        ExcelReader er=new ExcelReader();
        return er.getExcelData();
    }

}
