package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ExpediaTestSuite extends TestBase{

    private WebDriver driver = null;

    @Parameters({"browser"})
    @BeforeClass
    public void initTest(String browser) {
        driver = initDriver(browser);
        driver.get("https://amazon.co.uk");
    }

    @Test
    public void TC001(){
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("One Plus 6t");
        driver.findElement(By.xpath(".//input[@value='Go']")).click();
        driver.findElement(By.xpath("(.//span[contains(text(),'OnePlus 6T')])[1]/..")).click();
    }

    @Test
    public void TC002(){
        try {
            List<WebElement> footerLinks = driver.findElements(By.xpath(".//div[contains(@class,'navFooterLinkCol')]//a"));
            for(WebElement links : footerLinks) {
                URL url = new URL(links.getAttribute("href"));
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int code = connection.getResponseCode();

                System.out.println(code);
                if(code >= 200 && code < 400) {
                    System.out.println(links.getAttribute("href") + " staus is ACTIVE");
                }else {
                    System.out.println(links.getAttribute("href") + " staus is INACTIVE");
                }
            }
        }catch(Exception e) {
            e.getStackTrace();
        }
    }



}
