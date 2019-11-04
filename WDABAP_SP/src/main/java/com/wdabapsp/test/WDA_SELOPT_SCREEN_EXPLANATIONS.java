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

public class WDA_SELOPT_SCREEN_EXPLANATIONS {

	//static String url = "http://ldciu3y.wdf.sap.corp:55000/sap/bc/webdynpro/sap/wdr_test_select_options?sap-client=200&sap-language=de#";
	    ExtentReports extent;
		ExtentTest test;
		public WebDriver driver;
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
			String pAppName="WDR_TEST_SELECT_OPTIONS";	
			 String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	@Test
	
	public  void WDA_SELOPT_WD_SCREEN_EXPLANATIONS() throws Exception {
		

		try {
			
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "label.VordefinierteTestfälle.xpath");
			Reporter.log("1.Check  The tab VordefinierteTestfälle has clicked.",true);
			test.log(LogStatus.PASS, "1.1Check  The tab VordefinierteTestfälle has clicked.");
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Collapse.Button.Parameter");
			Reporter.log("1.Check The Collapse button has clicked.",true);
			test.log(LogStatus.PASS, "1.2.Check The Collapse button has clicked.");
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Sonderfälle.xpath");
			Reporter.log("1.Check The Sonderfälle button has clicked.",true);
			test.log(LogStatus.PASS, "1.3.Check The Sonderfälle button has clicked.");
		} catch (Error e)
		{
			Reporter.log("1.Fail Button has clicked.",true);
			test.log(LogStatus.FAIL, "1.Fail Button has clicked.");

		}

		//MouseActivity.mouseHoverAdvance(driver, Utility.checkElementOr(driver, "Label.MitExplanation.xpath"));
		VerifyExcepted.getAttribute(Utility.checkElementOr(driver, "Label.MitExplanation.xpath"), "class", "lsLabel",
				"2.Check The text has shown after over the mouse", "2.Fail The test has not come.", test);
		 test.log(LogStatus.PASS, "=====================Finish========================================");

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
