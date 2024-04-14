package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class DashboardPage extends BasePage{

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "/html/body/div[1]/section/div/div[2]/div/div/header/div/strong")
	WebElement dashboardHeaderElement;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/aside[1]/div/nav/ul[2]/li[2]/a/span")
	WebElement customerButtonElement;
	@FindBy(how = How.XPATH, using = "//*[@id=\"customers\"]/li[2]/a/span")
	WebElement addCustomerButtonElement;

	public void validateDashboardPage(String dashboarHeaderText) {
		Assert.assertEquals(dashboardHeaderElement.getText(), dashboarHeaderText, "Dashboard page not found.");
	}

	public String validateDashboardPageText() {
		String actualHeaderText = dashboardHeaderElement.getText();
		return actualHeaderText;
	}

	public void clickOnCustomerButton() {
		customerButtonElement.click();
	}

	public void clickOnAddCustomerButton() {
		addCustomerButtonElement.click();
	}

}
