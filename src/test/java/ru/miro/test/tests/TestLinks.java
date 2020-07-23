package ru.miro.test.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.miro.test.utils.ConfigurationHelper;
import ru.miro.test.utils.WebDriverHelper;

import java.util.Properties;

public class TestLinks {

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

    @Parameters({ "linkForClick", "redirectUrl" })
    @Test
    public void linkTest(String linkForClick, String redirectUrl){

        this.driver.findElement(By.linkText(linkForClick)).click();

        Assert.assertEquals(this.wait.until(ExpectedConditions.urlContains(redirectUrl))
                .booleanValue(), true);

    }



}
