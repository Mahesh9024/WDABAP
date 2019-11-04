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

public class WDA_REN_UA_INFO 
{       ExtentReports extent;
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
			String pAppName="WDR_TEST_USER_AGENT_INFO";	
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
@Test
public  void WDA_WD_REN_UA_INFO() throws Exception {

Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Properties.xpath");
System.out.println("text"+Utility.checkElementOr(driver, "Label.Windows.xpath").getText());
VerifyExcepted.getText(Utility.checkElementOr(driver, "Label.Windows.xpath"), "Windows", "1.Check Platform: Windows And OS: NT", "1.Fail Platform is not properly displayed", test);

VerifyExcepted.getText(Utility.checkElementOr(driver, "Label.Operatingsystemversion.xpath"), "10.0", "2.check the Renderer engine version: 7.0 ", "2.Fail Renderer engine version is not properly displayed", test);
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
