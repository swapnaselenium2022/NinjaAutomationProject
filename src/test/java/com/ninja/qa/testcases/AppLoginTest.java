package com.ninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ninja.qa.base.Base;
import com.ninja.qa.pages.HomePage;
import com.ninja.qa.pages.LoginPage;
import com.ninja.qa.pages.MyAccountPage;
import com.ninja.qa.utilities.Utilities;

public class AppLoginTest extends Base{

	public WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		loadConfigFile();
		driver = initializeBrowserAndOpenAppURL(pro.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountMenu();
		homePage.selectLoginOption_MyAccount();
	}
	
	@Test (priority = 1, dataProvider="validLoginCredentials")
	public void verifyLoginWithValidCredentials(String email, String password) throws Exception
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.inputEmailValue(email);
		Thread.sleep(2000);
		loginPage.inputPasswordValue(password);
		loginPage.clickOnLoginBtn();
		MyAccountPage myaccountPage = new MyAccountPage(driver);
		
		Assert.assertTrue(myaccountPage.validationMessageInAccountPage());
		Thread.sleep(2000);

	}
	
	@DataProvider (name="validLoginCredentials")
	public Object[][] loginTestData()
	{
		Object[][] data = Utilities.getTestDatafromExcel("Login");
		return data;		
	}
	
	@Test (priority = 2)
	public void verifyLoginWithValidEmailAndInvalidPassword() throws Exception
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.inputEmailValue(pro.getProperty("email"));
		Thread.sleep(2000);
		loginPage.inputPasswordValue(dataPro.getProperty("invalidPassword"));
		loginPage.clickOnLoginBtn();
		
		String warningMessage = loginPage.retrieveInvalidLoginWarningMessage();
		System.out.println(warningMessage);
		Thread.sleep(2000);
		String warningMessage_Expected = dataPro.getProperty("invalidLoginWarningMsg");
		System.out.println(warningMessage_Expected);
		Assert.assertEquals(warningMessage,warningMessage_Expected, "failed");
		
		Assert.assertTrue(warningMessage.contains(warningMessage_Expected), "failed");
		
		System.out.println(warningMessage);
	}
	
	@Test (priority = 3)
	public void VerifyloginWithInvalidEmailAndValidPassword() throws Exception
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.inputEmailValue(Utilities.generateEmailWithTimeStamp());
		Thread.sleep(2000);
		loginPage.inputPasswordValue(pro.getProperty("password"));
		loginPage.clickOnLoginBtn();
		
		String warningMessage = loginPage.retrieveInvalidLoginWarningMessage();
		Thread.sleep(2000);
		String warningMessage_Expected = dataPro.getProperty("invalidLoginWarningMsg");
				
		Assert.assertEquals(warningMessage,warningMessage_Expected, "failed");
		
		Assert.assertTrue(warningMessage.contains(warningMessage_Expected), "failed");
		
		System.out.println(warningMessage);
	}
	
	@Test (priority = 4)
	public void VerifyLoginWithInvalidCredentials() throws Exception
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.inputEmailValue(Utilities.generateEmailWithTimeStamp());
		Thread.sleep(2000);
		loginPage.inputPasswordValue(dataPro.getProperty("invalidPassword"));
		loginPage.clickOnLoginBtn();
		
		String warningMessage = loginPage.retrieveInvalidLoginWarningMessage();
		Thread.sleep(2000);
		String warningMessage_Expected = dataPro.getProperty("invalidLoginWarningMsg");
				
		Assert.assertEquals(warningMessage,"failing intentionally to verify listeners", "failed");
		
		Assert.assertTrue(warningMessage.contains(warningMessage_Expected), "failed");
		
		System.out.println(warningMessage);
	}
	
	@Test (priority = 5, dependsOnMethods= {"VerifyLoginWithInvalidCredentials"})
	public void VerifyLoginWithoutCredentials() throws Exception
	{
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnLoginBtn();
		String warningMessage = loginPage.retrieveInvalidLoginWarningMessage();

		Thread.sleep(2000);
		String warningMessage_Expected = dataPro.getProperty("invalidLoginWarningMsg");
				
		Assert.assertEquals(warningMessage,warningMessage_Expected, "failed");
		
		Assert.assertTrue(warningMessage.contains(warningMessage_Expected), "failed");
		
		System.out.println(warningMessage);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	} 
	
	
}
