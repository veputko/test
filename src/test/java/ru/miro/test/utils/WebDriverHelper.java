package ru.miro.test.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Properties;

public class WebDriverHelper {

    public enum Browser {
        SAFARI, FIREFOX, CHROME, EDGE, OPERA, PHANTOMJS
    }


    public static WebDriver createDriver(Properties prop) {

        switch (Browser.valueOf(prop.getProperty("webdriver.name",
                "PHANTOMJS").toUpperCase())) {
            case SAFARI -> {
                WebDriver driver = new SafariDriver();
                driver.manage().window().maximize();
                return driver;
            }
            case FIREFOX -> {
                System.setProperty("webdriver.gecko.driver", prop.getProperty("webdriver.gecko.driver"));
                WebDriver driver = new FirefoxDriver();
                driver.manage().window().maximize();
                return driver;
            }
            case CHROME -> {
                System.setProperty("webdriver.chrome.driver", prop.getProperty("webdriver.chrome.driver"));
                WebDriver driver = new ChromeDriver();
                driver.manage().window().maximize();
                return driver;
            }
            case EDGE -> {
                System.setProperty("webdriver.edge.driver", prop.getProperty("webdriver.edge.driver"));
                WebDriver driver = new EdgeDriver();
                driver.manage().window().maximize();
                return driver;
            }
            case OPERA -> {
                System.setProperty("webdriver.opera.driver", prop.getProperty("webdriver.opera.driver"));
                WebDriver driver = new OperaDriver();
                driver.manage().window().maximize();
                return driver;
            }
            case PHANTOMJS -> {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setJavascriptEnabled(true);
                caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                        prop.getProperty("webdriver.phantomjs.driver"));
                caps.setCapability("takesScreenshot", true);

                WebDriver driver = new PhantomJSDriver(caps);
                driver.manage().window().maximize();
                return driver;
            }
            default -> throw new RuntimeException("Something wrong with select browser ...");
        }
    }
}