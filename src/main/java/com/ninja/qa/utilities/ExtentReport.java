package com.ninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReports = new ExtentReports();
		File extentReportLocation = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\Report.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportLocation);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Ninja Project Automation Report");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReports.attachReporter(sparkReporter);
		
		Properties pro = new Properties();
		File proFile = new File (System.getProperty("user.dir")+"\\src\\main\\java\\com\\ninja\\qa\\config\\config.properties");

		try {
			FileInputStream fis = new FileInputStream(proFile);
			pro.load(fis);

		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
		extentReports.setSystemInfo("Application URL", pro.getProperty("url"));
		extentReports.setSystemInfo("BrowserName", pro.getProperty("browserName"));
		extentReports.setSystemInfo("Email", pro.getProperty("email"));
		extentReports.setSystemInfo("Password", pro.getProperty("password"));
		extentReports.setSystemInfo("OS", pro.getProperty("os.name"));
		extentReports.setSystemInfo("User Name", pro.getProperty("user.name"));
		extentReports.setSystemInfo("Java Version", pro.getProperty("java.version"));

		return extentReports;

	}
}
