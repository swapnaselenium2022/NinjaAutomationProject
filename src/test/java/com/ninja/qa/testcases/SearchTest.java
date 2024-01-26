package com.ninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ninja.qa.base.Base;

public class SearchTest extends Base{
WebDriver driver;
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@BeforeMethod
	public void setUp()
	{
		driver = initializeBrowserAndOpenAppURL("edge");
	}
	@Test (priority = 1)
	public void verifySearchWithValidProduct() throws Exception
	{
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("HP");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[normalize-space()='HP LP3065']")).isDisplayed(),"Valid serach products not displayed");	
		Thread.sleep(3000);
	}
	@Test (priority = 2)
	public void verifySearchWithInvalidProduct() throws Exception
	{
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Shanti");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).isDisplayed(),"Invalid serach failed");	
		Thread.sleep(3000);
	}
	@Test (priority = 3)
	public void verifySearchWithNoProduct() throws Exception
	{
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).isDisplayed(),"Invalid serach failed");	
		Thread.sleep(3000);
	}
}
