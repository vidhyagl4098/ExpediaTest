package core;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private WebDriver driver = null;

    public WebDriver getDriver(String browser) throws Exception{
        DesiredCapabilities desiredCapabilities = null;
        switch(browser.toLowerCase()) {
            case "chrome"  :
                desiredCapabilities = new DesiredCapabilities().chrome();
                driver = new ChromeDriver();
                break;
            case "firefox" :
                desiredCapabilities = new DesiredCapabilities().firefox();
            default :
                System.out.println("Incorrect browser name provided "+ browser);
                System.out.println("Hence running in Chrome");
                desiredCapabilities = new DesiredCapabilities().chrome();
                break;
        }
        desiredCapabilities.setPlatform(Platform.MAC);
        driver = new RemoteWebDriver(new URL("http://192.168.0.105:4444/wd/hub"),desiredCapabilities);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);  // One time config done for the whole project
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // One time config
        return driver;
    }

    public void quitDriver() {
        if(driver!=null) {
            driver.quit();
        }
    }

}
