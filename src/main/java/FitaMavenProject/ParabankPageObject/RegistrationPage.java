package FitaMavenProject.ParabankPageObject;

//import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import abstactComponents.BasePage;

public class RegistrationPage extends BasePage {

//	private By registrationLink = By.linkText("Register");
    
	private By firstName = By.id("customer.firstName");
    private By lastName = By.id("customer.lastName");
    private By address = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zipCode = By.id("customer.address.zipCode");
    private By phone = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By confirmPassword = By.id("repeatedPassword");
    private By registerBtn = By.xpath("//input[@value='Register']");
    private By welcomeText = By.cssSelector(".title");

    public RegistrationPage(WebDriver driver) {
        super(driver);
       
    }
    public void registerLinkPage()
    {
    	click(By.linkText("Register"));
    	 
    }

    public void registerUser(String fName, String lName, String phoneNo, String user, String pass) {
        type(firstName, fName);
        type(lastName, lName);
        type(address, "SIS Acropole,E-pudur");
        type(city, "Trichy");
        type(state, "TamilNadu");
        type(zipCode, "620012");
        type(phone, phoneNo);
        type(ssn, "1234");
        type(username, user);
        type(password, pass);
        type(confirmPassword, pass);
        click(registerBtn);
    }

    public String getWelcomeText() {
        return getText(welcomeText);
    }
}

