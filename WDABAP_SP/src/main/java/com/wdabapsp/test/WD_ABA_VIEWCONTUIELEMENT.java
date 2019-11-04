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
import lib.DropDown;
import lib.MouseActivity;
import lib.Utility;
import lib.VerifyExcepted;

public class WD_ABA_VIEWCONTUIELEMENT {
	//static String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_UI_ELEMENTS?sap-client=005&sap-language=DE#";

	
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
			String pAppName="WDR_TEST_UI_ELEMENTS";	
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	        @Test
	public  void WD_WD_ABA_VIEWCONTUIELEMENT() throws Exception {
		

		// Naviagte to the WD_ABA_TABSTRIP control
	    MouseActivity.mouseHoverAdvance(driver, Utility.checkElementOr(driver, "Standard.layout.xpath"));
	    MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "Standard.layout.xpath"));
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Standard.layout.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Standard.layout.ViewContainerUIElement.xpath");
		DropDown.selecFromDropDownByValue(driver, "DropDown.visible.xpath", "none");
	    Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.�nderungen.durchf�hren");

	
			Assert.assertEquals(false, Utility.isElementPresentByXpath(driver, "Label.Diesistder.xpath"));
			Reporter.log("1.Check the Element is not visible", true);
			test.log(LogStatus.PASS, "1.Check the Element is not visible");
		

		DropDown.selecFromDropDownByValue(driver, "DropDown.visible.xpath","visible");

		 Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.�nderungen.durchf�hren");
		 Thread.sleep(3000);

		
			Assert.assertEquals(true, Utility.isElementPresentByXpath(driver, "Label.Diesistder.xpath"));
			Reporter.log("2.Check the Element is  visible", true);
			test.log(LogStatus.PASS, "2.Check the Element is  visible");

		Utility.clickOnElementUsingJavaScriptExecutor(driver, "CheckBox.enabled.xpath1");
		Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.�nderungen.durchf�hren");
		Thread.sleep(3000);
		VerifyExcepted.getAttribute(Utility.checkElementOr(driver, "Label.Diesistder.xpath"), "class", "urTxtColorDsbl",
				"3.Check the Element is diabled.", "43Fail the element is not disabled.", test);
       test.log(LogStatus.PASS, "===================Finish===================================");
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
