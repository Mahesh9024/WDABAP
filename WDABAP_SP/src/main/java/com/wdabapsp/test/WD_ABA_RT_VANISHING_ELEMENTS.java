package com.wdabapsp.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class WD_ABA_RT_VANISHING_ELEMENTS 
{

	//static String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/wdr_test_p13n?sap-client=005&sap-language=DE#";
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
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	@Test
	
	public  void WD_WD_ABA_RT_VANISHING_ELEMENTS() throws Exception {
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Page.ResetWithOutAdmin.xpath");
///	•Click on the 'Verschwindende UI-Elemente' link.
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.VerschwindendeUI-Elemente.xpath");
		
		
		MouseActivity.rightClick(driver, Utility.checkElementOr(driver, "Input.DiesesEingabefeld.xpath"));
		
//•Open context menu for the input field and choose 'Benutzer-Einstellungen' -> 'Mehr...' .		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "ContextMenue.Benutzereinstellungen.xpath");
		
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'Eingabefeld ')]"));
		Utility.ClickArrowButton(driver, 6);
		//Actions action = new Actions(driver);
		//action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
				//.sendKeys(Keys.ENTER).click().build().perform();

//•Click on the 'Abbrechen' link at the very bottom of the popup window.
		
		driver.switchTo().frame("URLSPW-0");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Form.Abbrechen.xpath");
		
		
		try {
			Assert.assertEquals(false, Utility.isElementPresentByXpath(driver, "Input.DiesesEingabefeld.xpath"));
			Reporter.log("1.Check the input field no more visible.",true);
			test.log(LogStatus.PASS, "1.Check the input field no more visible.");
			test.log(LogStatus.PASS, "=====================Finish========================================");
		    } catch (Error e) 
		{
		    	Reporter.log("1.Fail the input field still visible.",true);
		    	test.log(LogStatus.FAIL, "1.Fail the input field still visible.");
			
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
