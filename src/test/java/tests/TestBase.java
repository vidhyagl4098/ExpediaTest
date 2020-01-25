package tests;

import core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

import java.util.concurrent.TimeUnit;

public class TestBase {
    private WebDriver driver = null;

    public WebDriver initDriver(String browser) throws Exception{
        DriverFactory driverFactory = new DriverFactory();
        this.driver = driverFactory.getDriver(browser);
        return this.driver;
    }

    @AfterClass
    public void cleanUp() {
        if(this.driver!=null){
            this.driver.quit();
        }
    }


}
