package page;



import java.util.Random;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AddCustomerPage extends BasePage{
	
	WebDriver driver;
	
	

	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
	}
	 

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div[1]/div[1]/div/div/header/div/strong") WebElement addCustomerHeaderElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[1]/div/input") WebElement fullNameElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[2]/div/select") WebElement companyDropdownElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[3]/div/input") WebElement emailElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"phone\"]") WebElement phoneElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[5]/div/input") WebElement addressElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[6]/div/input") WebElement cityElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"port\"]") WebElement zipElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"general_compnay\"]/div[8]/div[1]/select") WebElement countryDropdownElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"save_btn\"]") WebElement saveButtonElement;
	
			
	public void validateAddCustomerPage(String addCustomerHeaderText) {
		Assert.assertEquals(addCustomerHeaderElement.getText(), addCustomerHeaderText, "Dashboard page not found.");
	}

	String insertedName;
	public void insertFullName(String fullName) {
		insertedName = fullName + generateRandomNum(999);
		fullNameElement.sendKeys(insertedName);
	}
	
	public void selectCompany(String visibleText) {
		selectFromDrowdown(companyDropdownElement, visibleText);
	}
	
	public void insertEmail(String email) {
		emailElement.sendKeys(generateRandomNum(9999) + email);
	}
	
	public void insertPhone(String phone) {
		phoneElement.sendKeys(phone + generateRandomNum(9999));
	}
	
	public void insertAddress(String address) {
		addressElement.sendKeys(address);
	}
	
	public void insertCity(String city) {
		cityElement.sendKeys(city);
	}

	public void insertZip(String zip) {
		zipElement.sendKeys(zip);
	}

	public void selectCountry(String country) {
		selectFromDrowdown(countryDropdownElement, country);
	}
	
	public void cickOnSaveButton() {
		saveButtonElement.click();
	}

	//tbody/tr[1]/td[2]/a
	//tbody/tr[2]/td[2]/a
	//tbody/tr[3]/td[2]/a
	//tbody/tr[i]/td[2]/a
	//tbody/tr[1]/td[9]/button
	
	String before_xpath = "//tbody/tr[";
	String after_xpath = "]/td[2]/a";
	String after_xpath_delete = "]/td[9]/button";
	
	public void validateInsertedNameAndDelete() throws InterruptedException {
		
		for(int i = 1; i <= 10; i++) {
			String actualName = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			System.out.println(actualName);
//			Assert.assertEquals(actualName, insertedName, "Name doesn't match!!");
			if(actualName.contains(insertedName)) {
				System.out.println("name exist");
				Thread.sleep(5000);
				driver.findElement(By.xpath(before_xpath + i + after_xpath_delete)).click();
			}
			break;
		}
		
	}
	

}
