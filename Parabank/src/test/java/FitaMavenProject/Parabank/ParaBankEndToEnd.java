package FitaMavenProject.Parabank;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ParaBankEndToEnd 
{

	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.chromedriver", "/Users/ame/Documents/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.linkText("Register")).click();
		String enterName =RandomStringUtils.randomAlphabetic(20);
		String phoneNumber=RandomStringUtils.randomNumeric(20);
		WebElement customerName=driver.findElement(By.id("customer.firstName"));
		customerName.sendKeys(enterName);
		driver.findElement(By.id("customer.lastName")).sendKeys("K");
		
		driver.findElement(By.id("customer.address.street")).sendKeys("SIS Acropole,E-pudur");
		driver.findElement(By.id("customer.address.city")).sendKeys("Trichy");
		driver.findElement(By.id("customer.address.state")).sendKeys("TamilNadu");
		driver.findElement(By.id("customer.address.zipCode")).sendKeys("620012");
		driver.findElement(By.id("customer.phoneNumber")).sendKeys(phoneNumber);
		driver.findElement(By.id("customer.ssn")).sendKeys("1234");
		driver.findElement(By.id("customer.username")).sendKeys(enterName);
		driver.findElement(By.id("customer.password")).sendKeys("Cvmad135");
		driver.findElement(By.id("repeatedPassword")).sendKeys("Cvmad135");
		String customerNameEntered=customerName.getText();
		System.out.println(customerNameEntered);
		
		driver.findElement(By.xpath("//input[@value=\"Register\"]")).click();
		//Alert a = driver.switchTo().alert();
		//a.accept();
		String welcomeNmae=driver.findElement(By.cssSelector(".title")).getText();
		System.out.println(welcomeNmae);
		//String name[]=welcomeNmae.split(" ");
		//System.out.println("New name ="+ name[0]);
		//System.out.println("New name ="+ name[1]);
		
		//String name1=name[1];
		//System.out.println(name1);
		Assert.assertTrue(welcomeNmae.contains(customerNameEntered), "Username is  not there");
		driver.findElement(By.linkText("Open New Account")).click();
		WebElement dropDown =driver.findElement(By.xpath("//select[@id='type']")); //Saving Account DropDown
		System.out.println(dropDown);
		Select dropDoiwnList =new Select(dropDown);
		dropDoiwnList.selectByValue("1");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(12));
		
		Thread.sleep(3000);
		//WebElement dropDown2 =driver.findElement(By.xpath("//select[@id='fromAccountId']")); //Account Number DropDown
		//System.out.println(dropDown2);
		//Select dropDoiwnList2 =new Select(dropDown2);
		//dropDoiwnList2.selectByVisibleText("17118");
		driver.findElement(By.xpath("//input[@value='Open New Account']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Congratulations')]")));
		String accountOpenedActual = driver.findElement(By.xpath("//p[contains(text(),'Congratulations')]")).getText();
		System.out.println("Account opened text  :" +accountOpenedActual);
		String accountOpenedExpected ="Congratulations, your account is now open.";
		Assert.assertEquals(accountOpenedActual,accountOpenedExpected);
		WebElement accountOverview=driver.findElement(By.linkText("Accounts Overview"));
		accountOverview.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountTable']")));
		String basicAccountNUmber=driver.findElement(By.xpath("//table[@id='accountTable']/tbody/tr[1]/td[1]")).getText();
		System.out.println("Basic Account Number is : "+basicAccountNUmber);
		driver.findElement(By.linkText(basicAccountNUmber)).click();
		
	/*  By balanceLocator= By.xpath("//div[@id='accountDetails']/table/tbody/tr[4]/td[@id='availableBalance']");
		 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(balanceLocator));
		
		wait.until(ExpectedConditions.textToBePresentInElement(months, "June"));
	
		String accountBalance = driver.findElement(balanceLocator).getText();
		System.out.println("Basic Account Balance is: " +accountBalance);
	*/
		Thread.sleep(3000);
		
		List<WebElement> rows = driver.findElements(By.xpath("//div[@id='accountDetails']/table/tbody/tr"));
		for (WebElement row : rows) {
		    System.out.println("Row: " + row.getText());
		}
		WebElement months =driver.findElement(By.id("month"));
		Select monthDropdown =new Select(months);
		monthDropdown.selectByVisibleText("June");
		
		WebElement typeOfAccount =driver.findElement(By.id("transactionType"));
		Select accountType= new Select(typeOfAccount);
		accountType.selectByValue("Debit");
		driver.findElement(By.xpath("//input[@value='Go']")).click();
		By statemengtLocator=By.xpath("//p[@id='noTransactions']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(statemengtLocator));
		
		String statementSummery=driver.findElement(statemengtLocator).getText();
		System.out.println("Statement Summery is follow : "+statementSummery);
		WebElement accountOverview1=driver.findElement(By.linkText("Accounts Overview"));
		
		accountOverview1.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='accountTable']")));
		String savingAccountNUmber=driver.findElement(By.xpath("//table[@id='accountTable']/tbody/tr[2]/td[1]")).getText();
		System.out.println("Basic Account Number is : "+savingAccountNUmber);
		driver.findElement(By.linkText(savingAccountNUmber)).click();
		Thread.sleep(3000);
		
		List<WebElement> row1 = driver.findElements(By.xpath("//div[@id='accountDetails']/table/tbody/tr"));
		for (WebElement row : row1) {
		    System.out.println("Row: " + row.getText());
		}
		WebElement months1 =driver.findElement(By.id("month"));
		Select monthDropdown1 =new Select(months1);
		monthDropdown1.selectByVisibleText("March");
		
		WebElement typeOfAccount1 =driver.findElement(By.id("transactionType"));
		Select accountType1= new Select(typeOfAccount1);
		accountType1.selectByValue("Credit");
		driver.findElement(By.xpath("//input[@value='Go']")).click();
		By statemengtLocator1=By.xpath("//p[@id='noTransactions']");
		wait.until(ExpectedConditions.visibilityOfElementLocated(statemengtLocator1));
		String statementSummery1=driver.findElement(statemengtLocator).getText();
		System.out.println("Statement Summery is follow : "+statementSummery1);
		
		
		Thread.sleep(3000);
		//Assert.assertEquals(customerNameEntered,"Ameena6");
		/*driver.findElement(By.linkText("Log Out")).click();
		driver.findElement(By.linkText("Forgot login info?")).click();
		*/
		driver.quit();
		
		
	}

}
