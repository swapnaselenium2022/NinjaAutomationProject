package com.ninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninja.qa.base.Base;
import com.ninja.qa.pages.HomePage;
import com.ninja.qa.pages.RegisterPage;
import com.ninja.qa.utilities.Utilities;

public class RegisterTest extends Base{

	public WebDriver driver;
	@BeforeMethod
	public void setUp()
	{
		loadConfigFile();
		driver = initializeBrowserAndOpenAppURL(pro.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccountMenu();
		homePage.selectRegisterOption_MyAccount();
	}
	@Test (priority=1)
	public void verifyRegisterAnAccountWithMandatoryFields() throws Exception
	{
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.inputFirstName(dataPro.getProperty("firstName"));
		registerPage.inputLastName(dataPro.getProperty("lastName"));
		registerPage.inputEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.inputTelephone(dataPro.getProperty("telePhone"));
		registerPage.inputPassword(dataPro.getProperty("password"));
		registerPage.inputConfirmPassword(dataPro.getProperty("password"));
		registerPage.selectAgreeCheckBox();
		registerPage.clickOnContinueBtn();
		Thread.sleep(4000);
		Assert.assertTrue(registerPage.verifyAccountCreationSuccessMessage(),"Account creation is unsuccessful");
	}
	
	@Test (priority=2)
	public void verifyRegisterAnAccountWithoutPrivacyPolicy()
	{
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.inputFirstName(dataPro.getProperty("firstName"));
		registerPage.inputLastName(dataPro.getProperty("lastName"));
		registerPage.inputEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.inputTelephone(dataPro.getProperty("telePhone"));
		registerPage.inputPassword(dataPro.getProperty("password"));
		registerPage.inputConfirmPassword(dataPro.getProperty("password"));
		registerPage.clickOnContinueBtn();

		String actualPrivaryWarningMessage = registerPage.verifyPrivacyWarningMessage();
		Assert.assertEquals(actualPrivaryWarningMessage,dataPro.getProperty("privacyWarningMessage"),"Relevent warning message not displayed");
	}
	
	@Test (priority=3)
	public void verifyRegisterAnAccountWithExistingEmail()
	{
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.inputFirstName(dataPro.getProperty("firstName"));
		registerPage.inputLastName(dataPro.getProperty("lastName"));
		registerPage.inputEmail(pro.getProperty("email"));
		registerPage.inputTelephone(dataPro.getProperty("telePhone"));
		registerPage.inputPassword(dataPro.getProperty("password"));
		registerPage.inputConfirmPassword(dataPro.getProperty("password"));
		registerPage.selectAgreeCheckBox();
		registerPage.clickOnContinueBtn();
		
		String actualEmailWarningMessage = registerPage.verifyExistingEmailWarningMessage();
		Assert.assertEquals(actualEmailWarningMessage,dataPro.getProperty("existingEmailWarning"),"Relevent warning message not displayed");
	}
	
	@Test (priority=4)
	public void verifyRegisterAnAccountWithoutAnyDetails()
	{
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueBtn();
		
		String actualFirstNameErrMessage = registerPage.verifyFirstNameWarningMessage();
		Assert.assertEquals(actualFirstNameErrMessage,dataPro.getProperty("firstNameWarning"),"Relevent warning message not displayed for firstName");
		
		String actualLastNameErrMessage = registerPage.verifyLastNameWarningMessage();
		Assert.assertEquals(actualLastNameErrMessage,dataPro.getProperty("lastNameWarning"),"Relevent warning message not displayed for LastName");
		
		String actualEmailErrMessage = registerPage.verifyEmailWarningMessage();
		Assert.assertEquals(actualEmailErrMessage,dataPro.getProperty("emailWarning"),"Relevent warning message not displayed for Email");
		
		String actualTelephoneErrMessage = registerPage.verifyTelephoneWarningMessage();
		Assert.assertEquals(actualTelephoneErrMessage,dataPro.getProperty("telePhoneWarning"),"Relevent warning message not displayed for Telephone");
		
		String actualPasswordErrMessage = registerPage.verifyPasswordWarningMessage();
		Assert.assertEquals(actualPasswordErrMessage,dataPro.getProperty("passwordWarning"),"Relevent warning message not displayed for Password");
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	} 
}
