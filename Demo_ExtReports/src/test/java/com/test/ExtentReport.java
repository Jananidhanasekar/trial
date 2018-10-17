package com.test;

import java.io.File;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ExtentReport {
	

		

ExtentReports extent;
ExtentTest logger;
		// setup the driver connection and give the test path 
		
		@BeforeTest
		public void startReport() 
		{
			//location of the report , 
			// infor which come into the final 
			//load into config file 
			//happens before start testing 
			//user.dir is used bcz it can be accessed any system
			extent = new ExtentReports(System.getProperty("user.dir")+"/target/surefire-reports/ExtentReportResults.html",true);
		
			extent.addSystemInfo("HostName","Selenium test").addSystemInfo("Environment testing","Selenium test");
			
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		
		}
		
		
		//to close the report 
		@AfterTest
		public void tearDown()
		{

extent.flush();
extent.close();
		}
		
		

		// after every method -- report will be generated simulatenously
		
		@AfterMethod
		public void generateResults(ITestResult result)
		{
			//ItestResult-- information will be passed
			if(result.getStatus()==ITestResult.FAILURE)
			{
				logger.log(LogStatus.FAIL,"Test case failed is:"+result.getName());
				logger.log(LogStatus.FAIL,"Test case failed is:"+result.getThrowable());
			}
			else if(result.getStatus()==ITestResult.SKIP)
			{
				logger.log(LogStatus.SKIP,"Test case skipped is:"+result.getName());
				logger.log(LogStatus.SKIP,"Test case skipped is:"+result.getThrowable());	
			}
			
			extent.endTest(logger);
		}
		
		@Test
		 public void register() {
			
			logger = extent.startTest("register");
			Assert.assertTrue(true);
			logger.log(LogStatus.PASS, "test case is passed");
		}
		@Test
		public void login()
		{
			logger=extent.startTest("login");
			Assert.assertTrue(false);
		}

		@Test
		public void mobileRecharge()
		{
			logger=extent.startTest("MobileRecharge");
			throw new SkipException("This is not ready for testing");
			
		}
	}


