package test;

import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;


public class LoginTest {
	
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
	
	@Test(groups = {"smoke", "regression"})
	public void validUserShouldBeAbleToLogin() {
		
		driver = BrowserFactory.init();
		
//		LoginPage loginPage1 = new LoginPage(driver);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.insertPassword(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("Password").getAsString());
		loginPage.clickSigninButton();
	
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
//		dashboardPage.validateDashboardPage(dashboardHearderText);
		
		Assert.assertEquals(dashboardPage.validateDashboardPageText(), jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationTextLogin").getAsString(), "Dashboard page not found.");
		BrowserFactory.tearDown();
		
	}
	
	@Test(groups = {"regression"})
	public void validateAlertMessages() {
		
		driver = BrowserFactory.init();
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.clickSigninButton();
		loginPage.validateUserAlertMsg(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserNameAlertMsg").getAsString());
		
		loginPage.insertUserName(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.clickSigninButton();
		loginPage.validatePasswordAlertMsg(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("PasswordAlertMsg").getAsString());
		
		BrowserFactory.tearDown();
		
	}
	
	@Test(groups = {"smoke"})
	public void smokeTest() {
		System.out.println("smoke test");
	}
	
	@Test(groups = {"regression"})
	public void regressionTest() {
		System.out.println("regression test");
	}

}
