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
import lib.VerifyExcepted;

public class WD_ABA_RT_ADMIN_PRESETTING 
{
	//static String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/wdr_test_p13n?sap-client=005&sap-config-mode=x&sap-language=DE#";
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
			String pAdminMode="sap-config-mode=X";
			loginPage.loginAsAdmin(pNamespace, pAppName, pAdminMode);
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	@Test

	public  void WD_WD_ABA_RT_ADMIN_PRESETTING() throws Exception {
		
		
// Click on the 'Presetting-Test' link on the left hand side
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.PresettingTest.xpath");
		
        VerifyExcepted.getText(Utility.checkElementOr(driver, "Label.Eingabefelder.xpath"), "Eingabefelder", "1.check The Group box Eingabefelder displayed.", "1.Fail The group box Eingabefelder has not come.", test);
	    Utility.checkElementOr(driver, "Input.EingabefeldmitON.xpath").sendKeys("WDABAP");
	    Utility.checkElementOr(driver, "Input.EingabefeldmitON.xpath").sendKeys(Keys.ENTER);
        Utility.checkElementOr(driver, "Input.Eingabefeldohne.xpath").sendKeys("WDABAP");
        Utility.checkElementOr(driver, "Input.Eingabefeldohne.xpath").sendKeys(Keys.ENTER);
        
        MouseActivity.rightClick(driver, "Input.Eingabefeldohne.xpath");
        Utility.ClickArrowButton(driver, 2);
        
        driver.switchTo().frame("URLSPW-0");
        
        Utility.clickOnElementUsingJavaScriptExecutor(driver, "Radiobutton.Wert.xpath");
//check for astrick.
        
        try {
			Assert.assertEquals(true, Utility.isElementPresentByXpath(driver, "Symbol.astrick.xpath"));
			test.log(LogStatus.PASS, "1.check The astrick sign has displayed");
			Reporter.log("1.check The astrick sign has displayed",true);
		} catch (Error e) {
			test.log(LogStatus.FAIL, "1.Fail The astrick sign has  not displayed.");
			Reporter.log("1.Fail The astrick sign has  not displayed.",true);
		}
        
        Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.EingabefeldmitWertausAnwen.xpath");
        Utility.clickOnElementUsingJavaScriptExecutor(driver, "Radiobutton.Wert.xpath");
        
        Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Sichernundschlieﬂen.xpath");
        
      
        driver.switchTo().defaultContent();
        //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@pwid='WDWL2']"))); 
        
        driver.switchTo().frame("URLSPW-1");
        Utility.clickOnElementUsingJavaScriptExecutor(driver, "Radiobutton.KeinTransportauftrag.xpath");        
        Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");
        
        driver.switchTo().defaultContent();        
        driver.navigate().refresh();
        
        Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.PresettingTest.xpath");
       VerifyExcepted.getAttribute(driver, "Input.Eingabefeldohne.xpath", "value","WDABAP" ,"4.check The text got changed,", "4.Fail the text has not changed.", test);
       test.log(LogStatus.PASS, "====================Finish======================================");
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
		Utility.resetPage(driver);
		driver.quit();	
		BrowserFactory.killBrowserProcess(BrowserFactory.browserName);
	   }

}
