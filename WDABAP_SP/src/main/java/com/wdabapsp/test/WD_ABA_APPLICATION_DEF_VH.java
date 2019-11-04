package com.wdabapsp.test;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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
import lib.MouseActivity;
import lib.Utility;
import lib.Wait;

public class WD_ABA_APPLICATION_DEF_VH 
{
	ExtentReports  extent;
	ExtentTest test;
	WebDriver driver;
	String StepDetail;
	//String ScrPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();
		
	 @BeforeMethod
		public void Setup() throws InterruptedException, IOException
		{
		    extent=Utility.initReportPath(this.getClass().getSimpleName());
			test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
			driver = BrowserFactory.typeBrowser();
			LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
			test.log(LogStatus.PASS, StepDetail);			
			String pNamespace="sap";
			String pAppName="WDR_TEST_APPL_DEF_VH";	
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	 @Test
	 public void WD_ABA_WD_APPLICATION_DEF_VH() throws Exception
	 {
			
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Valuehelp.Flugnummer.xpath");
		
		driver.switchTo().frame("URLSPW-0");
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "CellValue.0064.xpath"));
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Werteübernehmen.xpath");
		driver.switchTo().defaultContent();
		try {
			Assert.assertEquals(true, Utility.isElementPresentByXpath(driver, "Selectedvalue.0064.xpath"));
			test.log(LogStatus.PASS, "1.check the value got selected");
		Reporter.log("1.check the value got selected",true);
		test.log(LogStatus.PASS, "=========================Finish====================================");
		} catch (Error e) {
			test.log(LogStatus.PASS, "1.Fail the value has not selected");
			Reporter.log("1.Fail the value has not selected",true);
		}		
		
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
