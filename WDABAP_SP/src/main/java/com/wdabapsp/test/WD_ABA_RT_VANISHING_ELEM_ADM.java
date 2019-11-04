package com.wdabapsp.test;

import java.io.IOException;

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

public class WD_ABA_RT_VANISHING_ELEM_ADM 
{
	//static String url= "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/wdr_test_p13n?sap-client=005&sap-language=de&sap-config-mode=X#";
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
			String pAppName="wdr_test_p13n";	
			 String pAdminMode="sap-config-mode=x";
			 loginPage.loginAsAdmin(pNamespace, pAppName,pAdminMode);
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
		@Test
		
		public  void WD_WD_ABA_RT_VANISHING_ELEM_ADM() throws Exception {
			Utility.resetPage(driver);
			Utility.waitForelement(driver, Utility.getLocator("Link.VerschwindendeUIElemente.xpath"), "xpath");
		    Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.VerschwindendeUIElemente.xpath"), "xpath");
		    MouseActivity.rightClick(driver, "Input.VerschwindendeUIElemente.xpath");
		    
		    Utility.ClickArrowButton(driver, 2);   
		  
		    driver.switchTo().frame("URLSPW-0");

		    
		    Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Abbrechen.xpath");		    
		    driver.switchTo().defaultContent();
		    
		    
		    Assert.assertEquals(false, Utility.isElementPresentByXpath(driver, "Label.Eingabefeld.xpath"));
		    Reporter.log("1.check the input field has disappered.",true);
		    test.log(LogStatus.PASS, "1.check the input field has disappered.");
		    test.log(LogStatus.PASS, "=====================Finish========================================");
		    		
		}
		@AfterMethod
		   public void tearDown(ITestResult result) throws Exception
		   {
		   if(result.getStatus()==ITestResult.FAILURE)
		   {
			     String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
				 test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));
		   }
		    extent.endTest(test);
			extent.flush();
			driver.quit();				
			BrowserFactory.killBrowserProcess(BrowserFactory.browserName);
		   }
		

}
