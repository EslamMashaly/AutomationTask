package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;


@Listeners(Report.Listeners.class)
public class SignUpTest extends BaseTest {

    LoginPage loginPage;


    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Successful Registration Test case")
    @Test(dataProvider = "ExcelData")
    public void successfulRegistration(String firstName, String lastName, String phone, String email, String password, String accountType) {

        signUpPage.closeCookiesAlert();
        boolean firstNameAssertion = signUpPage.enterFirstName(firstName);
        Assert.assertTrue(firstNameAssertion, "First name must start with capital letter");

        boolean lastNameAssertion = signUpPage.enterLastName(lastName);
        Assert.assertTrue(lastNameAssertion, "Last name must start with capital letter");

        Assert.assertNotEquals(firstName, lastName, "First name and Last name mustn't be equal");

        boolean phoneAssertion = signUpPage.enterPhone(phone);
        Assert.assertTrue(phoneAssertion, "Invalid phone number");

        signUpPage.enterEmail(email);

        boolean passwordAssertion = signUpPage.enterPassword(password);
        Assert.assertTrue(passwordAssertion, "Password must have capital letter, small letter, with a limit of 8 characters");

        loginPage = signUpPage.chooseAccountType(accountType)
                .selectCaptcha()
                .confirmSignUp();
        Assert.assertEquals(signUpPage.getSignUpAssertionText(), "Please enter your account credentials below", "SignUp failed");
        loginPage.enterEmail(email)
                .enterPassword(password)
                .checkRememberMeBox()
                .confirmLogin();
        Assert.assertEquals(loginPage.getLoginAssertionText(), firstName, "Login failed");

    }

}
