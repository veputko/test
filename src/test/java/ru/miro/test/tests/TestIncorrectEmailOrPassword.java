package ru.miro.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.miro.test.utils.ConfigurationHelper;
import ru.miro.test.utils.WebDriverHelper;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestIncorrectEmailOrPassword {
    private WebDriver driver;
    private WebDriverWait wait;
    private Properties prop;


    @BeforeTest
    public void init(){

        this.prop = ConfigurationHelper.getConfigurationParameters();
        this.driver = WebDriverHelper.createDriver(prop);
        this.wait = new WebDriverWait(driver,
                Long.parseLong(prop.getProperty("page.wait.timeout", "40")));

        this.driver.get(prop.getProperty("start.page.url","https://miro.com/"));
        this.wait.until(ExpectedConditions.elementToBeClickable(
                By.id(prop.getProperty("start.page.wait.element","header-login-btn"))));
    }

    @AfterTest
    public void tearDown(){
        this.driver.close();
    }

    @Parameters({ "email", "password", "errorMessage" })
    @Test
    public void incorrectEmailOrPassword(String email, String password,String errorMessage){

        this.driver.findElement(By.id("email")).sendKeys(email);
        this.driver.findElement(By.id("password")).sendKeys(password);
        this.driver.findElement(By.cssSelector("button[data-autotest-id='mr-form-login-btn-signin-1']")).click();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            Assert.assertEquals(this.driver.getPageSource().contains(errorMessage), true);


    }
}
