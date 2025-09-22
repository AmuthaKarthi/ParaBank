package FitaMavenProject.ParabankPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import abstactComponents.BasePage;

public class AccountsPage extends BasePage {

    private By openNewAccountLink = By.linkText("Open New Account");
    private By accountTypeDropdown = By.id("type");
   // private By fromAccountDropdown = By.id("fromAccountId");
    private By openAccountBtn = By.xpath("//input[@value='Open New Account']");
    private By congratulationsMessage = By.xpath("//p[contains(text(),'Congratulations')]");
    private By accountTable = By.xpath("//table[@id='accountTable']");
    private By monthDropdown = By.id("month");
    private By transactionTypeDropdown = By.id("transactionType");
    private By goBtn = By.xpath("//input[@value='Go']");
    private By noTransactionsMsg = By.id("noTransactions");

    public AccountsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToOpenNewAccount() {
        click(openNewAccountLink);
    }

    public void selectAccountType(String typeValue) {
        Select select = new Select(driver.findElement(accountTypeDropdown));
        select.selectByValue(typeValue);
    }
   /*  public void selectAccountNumber(String typeValue1) {
        Select select = new Select(driver.findElement(fromAccountDropdown));
        select.selectByValue(typeValue1);
    } */
    public void clickOpenAccount() {
        click(openAccountBtn);
    }

    public String getAccountOpenedMessage() {
        return getText(congratulationsMessage);
    }

    public void navigateToAccountOverview() {
        click(By.linkText("Accounts Overview"));
        waitForVisibility(accountTable);
    }

    public String getAccountNumber(int row) {
        return getText(By.xpath("//table[@id='accountTable']/tbody/tr[" + row + "]/td[1]"));
    }

    public void openAccountDetails(String accountNumber) {
        click(By.linkText(accountNumber));
    }

    public void filterStatement(String month, String transactionType) {
        Select monthSelect = new Select(driver.findElement(monthDropdown));
        monthSelect.selectByVisibleText(month);

        Select typeSelect = new Select(driver.findElement(transactionTypeDropdown));
        typeSelect.selectByValue(transactionType);

        click(goBtn);
        waitForVisibility(noTransactionsMsg);
    }

    public String getStatementSummary() {
        return getText(noTransactionsMsg);
    }
}
