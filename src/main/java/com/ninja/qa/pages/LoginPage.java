package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy (xpath="//input[@id='input-email']")
	private WebElement emailField;
	
	@FindBy (xpath="//input[@id='input-password']")
	private WebElement passwordField;
	
	@FindBy (xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy (xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement invalidLoginWarningMessage;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	public void inputEmailValue(String email)
	{
		emailField.sendKeys(email);
	}
	
	public void inputPasswordValue(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public void clickOnLoginBtn()
	{
		loginButton.click();
	}
	
	public String retrieveInvalidLoginWarningMessage() 
	{
		String loginWarningMsg = invalidLoginWarningMessage.getText();
		return loginWarningMsg;
	}
	
}
