package com.wdabapsp.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wdabapsp.page.LoginPage;

import lib.BrowserFactory;
import lib.Utility;
import lib.VerifyExcepted;
import lib.Wait;

public class WD_ABA_EXT_CONTEXT_MAPPING 
{
	//static String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_EXT_MAPPING?=&sap-client=005&sap-language=DE#";
	  ExtentReports extent;
		ExtentTest test;
		public WebDriver driver;
		//String ScrPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();	   
		String StepDetail;
	        @BeforeMethod
		public void Setup() throws InterruptedException, IOException
		{
		        extent=Utility.initReportPath(this.getClass().getSimpleName());
			test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
			driver = BrowserFactory.typeBrowser();
			LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
			test.log(LogStatus.PASS, StepDetail);			
			String pNamespace="sap";
			String pAppName="WDR_TEST_EXT_MAPPING";		
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	@Test

	public  void WD_ABA_WD_EXT_CONTEXT_MAPPING() throws Exception 
	{
		
	VerifyExcepted.getAttribute(driver, "Label.Einbetter.xpath", "innerHTML","Einbetter", "1.check The first Tray visible", "1.Fail the first is not visible. ", test);
	VerifyExcepted.getAttribute(driver, "Label.eingebettete.xpath","innerHTML", "C2", "2.check The second Tray visible", "2.Fail the second is not visible. ", test);	
	VerifyExcepted.getAttribute(driver, "Label.erzeugte.xpath", "innerHTML","erzeugte", "3.check The Thrid Tray visible", "3.Fail the Third is not visible. ", test);
	}
	@AfterMethod
	   public void tearDown(ITestResult result) throws Exception
	   {
	   if(result.getStatus()==ITestResult.FAILURE)
	   {
		     String ResScreenPath=Utility.getScreenshot(driver,Utility.reportPath);
			 test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));
	   }
	    extent.endTest(test);
		extent.flush();
		driver.quit();
		BrowserFactory.killBrowserProcess(BrowserFactory.browserName);
	   }
}
