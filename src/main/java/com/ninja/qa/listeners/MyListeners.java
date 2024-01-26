package com.ninja.qa.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ninja.qa.utilities.ExtentReport;

public class MyListeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReport.generateExtentReport();
		//System.out.println("Project Tests Execution started"); (remove this)
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " Execution started");
		//System.out.println(testName + " Execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testName = result.getName();
		extentTest.log(Status.PASS, testName + " Execution successful");
		//System.out.println(testName + " Execution successful");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		File srcSreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcSreenshot,new File(destScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Attach screenshot to the Extent Report
		extentTest.addScreenCaptureFromPath(destScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		//System.out.println(result.getThrowable());

		extentTest.log(Status.FAIL,testName + " Execution failed");
		//System.out.println(testName + " Execution failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		//System.out.println(result.getThrowable());
		extentTest.log(Status.SKIP,testName + " Execution got skipped");
		//System.out.println(testName + " Execution got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		//System.out.println("Project Tests Execution finished");
	}

}
