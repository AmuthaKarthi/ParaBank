package FitaMavenProject.Parabank;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import FitaMavenProject.ParabankPageObject.AccountsPage;
import FitaMavenProject.ParabankPageObject.RegistrationPage;
import ReuseableBase.BaseTest;

public class ParaBankTest extends BaseTest {

    @Test
    public void testFullEndToEndFlow() {
        // Registration
        driver.findElement(By.linkText("Register")).click();
        String randomName = RandomStringUtils.randomAlphabetic(8);
        String randomPhone = RandomStringUtils.randomNumeric(10); 

        RegistrationPage regPage = new RegistrationPage(driver);
        //regPage.registerLinkPage();
        regPage.registerUser(randomName, "K", randomPhone, randomName, "Cvmad135");

        String welcome = regPage.getWelcomeText();
        System.out.println("Welcome text: " + welcome);
        Assert.assertTrue(welcome.contains(randomName), "User name not found in welcome message");

        // Open New Account
        AccountsPage accountsPage = new AccountsPage(driver);
        accountsPage.navigateToOpenNewAccount();
        accountsPage.selectAccountType("1"); // Savings
       // accountsPage.selectAccountNumber("0");
        accountsPage.clickOpenAccount();

        String accountMsg = accountsPage.getAccountOpenedMessage();
        System.out.println("Account Opened Message: " + accountMsg);
        Assert.assertEquals(accountMsg, "Congratulations, your account is now open.");

        // Account Overview & Statement
        accountsPage.navigateToAccountOverview();
        String firstAcc = accountsPage.getAccountNumber(1);
        String secondAcc = accountsPage.getAccountNumber(2);

        accountsPage.openAccountDetails(firstAcc);
        accountsPage.filterStatement("June", "Debit");
        System.out.println("Statement Summary (First Account): " + accountsPage.getStatementSummary());

        accountsPage.navigateToAccountOverview();
        accountsPage.openAccountDetails(secondAcc);
        accountsPage.filterStatement("March", "Credit");
        System.out.println("Statement Summary (Second Account): " + accountsPage.getStatementSummary());
        System.out.println(" THANK YOY ");
        System.out.println(" THANK YOY ");
        System.out.println(" THANK YOY ");
        System.out.println(" THANK YOY ");
        System.out.println(" THANK YOY ");
    }
}

