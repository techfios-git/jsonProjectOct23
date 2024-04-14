package test;

import java.io.FileReader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import page.AddCustomerPage;
import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;

public class AddCustomerTest {

	WebDriver driver;
	JsonElement jsonEleObj;

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void readJson() {

		try {
			FileReader reader = new FileReader("src\\main\\java\\testData\\TF_TestData.json");
			JsonParser parseObj = new JsonParser();
			jsonEleObj = parseObj.parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test(groups = {"regression"})
	public void userShouldBeAbleToAddCustomer() throws InterruptedException {

		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.insertPassword(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("Password").getAsString());
		loginPage.clickSigninButton();

		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		dashboardPage.validateDashboardPage(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationTextLogin").getAsString());
		dashboardPage.clickOnCustomerButton();
		dashboardPage.clickOnAddCustomerButton();

		AddCustomerPage addCustomerPage = PageFactory.initElements(driver, AddCustomerPage.class);
		addCustomerPage.validateAddCustomerPage(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("ValidationTextAddCustomer").getAsString());
		addCustomerPage.insertFullName(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("FullName").getAsString());
		addCustomerPage.selectCompany(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Company").getAsString());
		addCustomerPage.insertEmail(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Email").getAsString());
		addCustomerPage.insertPhone(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Phone").getAsString());
		addCustomerPage.insertAddress(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("Street").getAsString());
		addCustomerPage.insertCity(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("City").getAsString());
		addCustomerPage.insertZip(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("Zip").getAsString());
		addCustomerPage.selectCountry(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address").getAsJsonArray().get(0).getAsJsonObject().get("Country").getAsString());
		addCustomerPage.cickOnSaveButton();
		
		addCustomerPage.validateInsertedNameAndDelete();
		
		BrowserFactory.tearDown();

	}

}
