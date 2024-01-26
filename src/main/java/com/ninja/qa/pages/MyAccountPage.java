package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

	WebDriver driver;
	
	@FindBy (xpath="//h2[normalize-space()='My Account']")
	private WebElement myAccountTextMessage;
	
	public MyAccountPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean validationMessageInAccountPage()
	{
		boolean eleDispalyStatus = myAccountTextMessage.isDisplayed();
		return eleDispalyStatus;
	}
}
