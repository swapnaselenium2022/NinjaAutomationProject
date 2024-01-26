package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

WebDriver driver;
	
	@FindBy (xpath="//input[@id='input-firstname']")
	private WebElement firstName;
	
	@FindBy (xpath="//input[@id='input-lastname']")
	private WebElement lastName;
	
	@FindBy (xpath="//input[@id='input-email']")
	private WebElement emailAddress;
	
	@FindBy (xpath="//input[@id='input-telephone']")
	private WebElement telephoneNumber;
	
	@FindBy (xpath="//input[@id='input-password']")
	private WebElement password;
	
	@FindBy (xpath="//input[@id='input-confirm']")
	private WebElement confirmPassword;
	
	@FindBy (xpath="//input[@name='agree']")
	private WebElement agreeCheckBox;
	
	@FindBy (xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy (xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	private WebElement successfulAccountCreationMessage;
	
	@FindBy (xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyWarningMessage;
	
	@FindBy (xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement existingEmailWarningMessage;
	
	@FindBy (xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarningMessage;
	
	@FindBy (xpath="//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarningMessage;
	
	@FindBy (xpath="//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailWarningMessage;
	
	@FindBy (xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarningMessage;
	
	@FindBy (xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWarningMessage;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void inputFirstName(String fname)
	{
		firstName.sendKeys(fname);
	}
	
	public void inputLastName(String lname)
	{
		lastName.sendKeys(lname);
	}
	
	public void inputEmail(String email)
	{
		emailAddress.sendKeys(email);
	}
	
	public void inputTelephone(String phoneNumber)
	{
		telephoneNumber.sendKeys(phoneNumber);
	}
	
	public void inputPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void inputConfirmPassword(String confirmPwd)
	{
		confirmPassword.sendKeys(confirmPwd);
	}
	
	public void selectAgreeCheckBox()
	{
		agreeCheckBox.click();
	}
	
	public void clickOnContinueBtn()
	{
		continueButton.click();
	}
	
	public boolean verifyAccountCreationSuccessMessage()
	{
		boolean accountSuccessfulMessageStatus = successfulAccountCreationMessage.isDisplayed();
		return accountSuccessfulMessageStatus;
	}
	
	public String verifyPrivacyWarningMessage()
	{
		String privacyWarningMsg = privacyWarningMessage.getText();
		return privacyWarningMsg;
	}
	
	public String verifyExistingEmailWarningMessage()
	{
		String existingEmailWarningMsg = existingEmailWarningMessage.getText();
		return existingEmailWarningMsg;
	}
	
	public String verifyFirstNameWarningMessage()
	{
		String firstNameWarningMsg = firstNameWarningMessage.getText();
		return firstNameWarningMsg;
	}
	
	public String verifyLastNameWarningMessage()
	{
		String lastNameWarningMsg = lastNameWarningMessage.getText();
		return lastNameWarningMsg;
	}
	
	public String verifyEmailWarningMessage()
	{
		String emailWarningMsg = emailWarningMessage.getText();
		return emailWarningMsg;
	}
	
	public String verifyTelephoneWarningMessage()
	{
		String telePhoneWarningMsg = telephoneWarningMessage.getText();
		return telePhoneWarningMsg;
	}
	
	public String verifyPasswordWarningMessage()
	{
		String passwordWarningMsg = passwordWarningMessage.getText();
		return passwordWarningMsg;
	}

}
