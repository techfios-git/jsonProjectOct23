package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class LoginPage extends BasePage{
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	//WebElements (attributes)
	@FindBy(how = How.XPATH, using = "//*[@id=\"user_name\"]") WebElement userNameElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"password\"]") WebElement passwordElemnt;
	@FindBy(how = How.XPATH, using = "//*[@id=\"login_submit\"]") WebElement signinButtonElement;
	
	//Methods (actions)
	
	public void insertUserName(String userName) {
		userNameElement.sendKeys(userName);
	}
	
	public void insertPassword(String password) {
		passwordElemnt.sendKeys(password);
	}
	
	public void clickSigninButton() {
		signinButtonElement.click();
	}
	
	public void validateUserAlertMsg(String expectedAlertMsg) {
		String actualAlertMsg = driver.switchTo().alert().getText();
		Assert.assertEquals(actualAlertMsg, expectedAlertMsg, "Username alert mgs do not match.");
		driver.switchTo().alert().accept();
	}
	
	public void validatePasswordAlertMsg(String expectedAlertMsg) {
		String actualAlertMsg = driver.switchTo().alert().getText();
		Assert.assertEquals(actualAlertMsg, expectedAlertMsg, "Password alert mgs do not match.");
		driver.switchTo().alert().accept();
	}
	

}
