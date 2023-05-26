# Automation Task
### 🌐Website: https://phptravels.net/signup

### Description:
* The project uses Selenium with Java, Maven, TestNG
* Tests registration process and login verification
* Designed with Page Object Model (POM) and fluent design
* DDT (reading data from an Excel file)
* Runs on multiple browsers
* Generates Allure report, Extent report and Screenshots for failed tests


### Components:
* **Pages classes**: contain elements locators and interactions
* **Test settings class**: contains URLs and paths used in the test
* **BaseTest**: class contains driver declaration, test configurations and data providers methods
* **Report package**: contains listeners class and Extent report and screenshots configurations
* **TestDataFiles**: contain Excel file for test data and the data reader class
* **The E2E test**

### How it works:
## The project uses data from Excel sheet to run six test cases:
* First one to validate first name
* Second one to validate last name
* Third one to validate phone number
* Fourth one to validate short password
* Fifth one to validate password without capital letters
* Last one runs the test with valid data


## To run this project
___
* Enter the browser name in the testng.xml file
```xml
        <parameter name="BrowserName" value="chrome"></parameter>
```

* run the testng.xml


#### Note: reCAPTCHA is designed to prevent browser automation ,so you must solve it manually (you have only 1min to solve it ^_^)
