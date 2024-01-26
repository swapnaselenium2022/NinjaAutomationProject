package com.ninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ninja.qa.utilities.Utilities;

public class Base {

	WebDriver driver;
	public Properties pro; //Declare as global and public
	public Properties dataPro;
	
	public void loadConfigFile()
	{
		dataPro = new Properties();
		File dataproFile = new File (System.getProperty("user.dir")+"\\src\\main\\java\\com\\ninja\\qa\\testdata\\testdataconfig.properties");

		try {
			FileInputStream dataProfis = new FileInputStream(dataproFile);
			dataPro.load(dataProfis);

		} catch (Throwable e) {
				
			e.printStackTrace();
		}

		pro = new Properties();
		File proFile = new File (System.getProperty("user.dir")+"\\src\\main\\java\\com\\ninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(proFile);
			pro.load(fis);

		} catch (Throwable e) {
			
			e.printStackTrace();
		}
	}
	public WebDriver initializeBrowserAndOpenAppURL(String browserName)
	{
		if (browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (browserName.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_TIME));
		driver.get(pro.getProperty("url"));
		return driver;
	}	
}
