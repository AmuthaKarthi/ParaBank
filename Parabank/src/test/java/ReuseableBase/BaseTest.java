package ReuseableBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    protected Properties config;
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        // Choose browser based on parameter
    	 config = new Properties();
         try {
             FileInputStream fis = new FileInputStream("src/main/java/resources/config.properties");
             config.load(fis);
         } catch (IOException e) {
             e.printStackTrace();
         }

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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to URL from config
        driver.get(config.getProperty("baseURL"));
    }

   // @BeforeMethod
    public void setUp() {
        // Load config properties
        config = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/java/resources/config.properties");
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize browser based on config
        String browser = config.getProperty("browser", "chrome").trim().toLowerCase();
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chromedriver", "/Users/ame/Documents/chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.geckodriver", "/Users/ame/Documents/geckodriver");
                driver = new FirefoxDriver();
                break;
           /* case "edge":
                System.setProperty("webdriver.edge.driver", "/Users/ame/Documents/msedgedriver");
                driver = new EdgeDriver();
                break; */
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to URL from config
        driver.get(config.getProperty("baseURL"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
