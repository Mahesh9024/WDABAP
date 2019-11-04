package com.wdabapsp.test;

import java.io.IOException;

import org.openqa.selenium.By;
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
import lib.Utility;

public class WD_ABA_RT_SUS_PLUG_RESUMEURLID 
{
	
	//static String url= "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/wdr_test_window_susres_a?sap-client=005&sap-language=de&sap-config-mode=X#";
	 ExtentReports extent;
		ExtentTest test;
		public WebDriver driver;
		String ScrPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();	   
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
			String pAppName="WDR_TEST_WINDOW_SUSRES_A";	
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
		@Test
		
		public  void WD_ABA_TOGGLEBUTTON() throws Exception {
		
			
			Utility.checkElementOr(driver, "Input.NamederResumeURL.xpath").sendKeys("myRedirectURL");
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[contains(text(),'springen')]", "xpath");
			Reporter.log("1.check Clicked the application button.");
			
			
			Assert.assertEquals("Test Suspend/Resume - Applikation B", Utility.checkElementOr(driver, "Label.TestSuspend/ResumeApplikationB.xpath").getText());
			
			Reporter.log("2.Check WebDynpro ABAP  application is started.",true);
			test.log(LogStatus.PASS, "2.Check WebDynpro ABAP  application is started.");
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
