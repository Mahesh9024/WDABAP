package com.wdabapsp.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wdabapsp.page.LoginPage;

import lib.BrowserFactory;
import lib.Utility;

public class WDA_SSR_CLIENT_DELTA_RENDERING 
{
	
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	String ScrPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();	   
	String StepDetail;
	
	@BeforeTest
	public void setUp() throws Exception
	{
		extent=Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
		driver = BrowserFactory.typeBrowser();
		LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
		test.log(LogStatus.PASS, StepDetail);			
		String pNamespace="sap";
		String pAppName="wdr_test_delta_rendering";			
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"EN");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);		
	}
	
	@Test
	public void SSR_CLIENT_DELTA_RENDERING () throws InterruptedException
	{
			
		StepDetail="The test runs automatically. No user interaction is needed.";
		test.log(LogStatus.PASS, StepDetail);
		
		//If the test was run successfully a green check mark and a success message is displayed.
		try {
			
			driver.findElement(By.xpath(".//*[contains(@src,'SuccessMessage.gif')]"));
			StepDetail="Pass : Test was run successfully and a green check mark displayed";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
			 test.log(LogStatus.PASS, "=====================Finish========================================");
			  
	     } catch (NoSuchElementException e) {
	    	 
	    	 StepDetail="Fail : Test was run successfully and a green check mark displayed";
			 test.log(LogStatus.FAIL, StepDetail);
			 Reporter.log(StepDetail,true);
			 
		 }					
		

}


	
	@AfterMethod
	public void afterTest() {
		StepDetail="User successfully Logged off";
		test.log(LogStatus.PASS, StepDetail);
	    extent.endTest(test);
		extent.flush();
		driver.quit();		
	}

}
