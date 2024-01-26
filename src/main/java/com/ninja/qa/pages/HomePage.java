package com.ninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	@FindBy (xpath="//span[normalize-space()='My Account']")
	private WebElement myAccountDropDownMenu;
	
	@FindBy (xpath="//a[normalize-space()='Login']")
	private WebElement loginOption_MyAccount;
	
	@FindBy (xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	private WebElement registerOption_MyAccount;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccountMenu()
	{
		myAccountDropDownMenu.click();
	}
	
	public void selectLoginOption_MyAccount()
	{
		loginOption_MyAccount.click();
	}
	
	public void selectRegisterOption_MyAccount()
	{
		registerOption_MyAccount.click();
	}
}
