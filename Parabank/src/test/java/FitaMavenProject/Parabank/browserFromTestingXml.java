package FitaMavenProject.Parabank;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class browserFromTestingXml 
{
	WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        // Choose browser based on parameter
        if (browser.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chromedriver", "/Users/ame/Documents/chromedriver");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) 
        {
            System.setProperty("webdriver.geckodriver", "/Users/ame/Documents/geckodriver");
            driver = new FirefoxDriver();
        } else 
        {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

    @Test
    public void openGoogle() {
        driver.get("https://www.google.com");
        System.out.println("Title: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
