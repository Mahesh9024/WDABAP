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
import lib.MouseActivity;
import lib.Utility;
import lib.VerifyExcepted;
import lib.Wait;

public class WD_ABA_RT_CONTEXT_MENU 
{
	
	//static String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_CONTEXT_MENU?sap-client=005&sap-language=DE#";
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
			String pAppName="WDR_TEST_CONTEXT_MENU";
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	@Test
	
	public  void WD_ABA_WD_RT_CONTEXT_MENU() throws Exception {
		MouseActivity.rightClick(driver, "Label.AA.xpath");
		Utility.ClickArrowButton(driver, 1);
		
		VerifyExcepted.getText(driver, "GroupBox.Label.xpath", "Piep", "1.check The group box text got changed.", "1.Fail the grop box text has not changed.", test);
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "ScrollDown.xpath");
		MouseActivity.rightClick(driver, "Label.BA.xpath");
		Utility.ClickArrowButton(driver, 1);
		VerifyExcepted.getText(driver, "GroupBox.Label.xpath", "Chips", "2.check The group box text got changed.", "2.Fail the grop box text has not changed.", test);
		
		for(int i=0;i<=5;i++)
		{
			Utility.checkElementOr(driver, "ScrollDown.xpath").click();
			Wait.sleep(2000);
		}
		
		MouseActivity.rightClick(driver, "Label.LH.xpath");
		Utility.ClickArrowButton(driver, 1);   
		VerifyExcepted.getText(driver, "GroupBox.Label.xpath", "Sie", "3.check The group box text got changed.", "3.Fail the grop box text has not changed.", test);
		test.log(LogStatus.PASS, "====================Finish======================================");
		
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
