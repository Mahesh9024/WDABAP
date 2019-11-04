package com.wdabapsp.test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.By;
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

public class WDA_SHLP_DDIC_DDLB_FAVORITES {
	
	ExtentReports extent; 
	ExtentTest test;
	public WebDriver driver;
	String StepDetail;
	
	@BeforeMethod
	public void Setup() throws InterruptedException, IOException{
		extent = Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(), "The test verifies that the personal values list of the ABAP Dictionary based value help will be displayed in a special dropdown listbox");
		driver = BrowserFactory.typeBrowser();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test.log(LogStatus.PASS, StepDetail);
		String pNamespace="sap";
		String pAppName="WDR_TEST_DDIC_SHLP";
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);
	}
	
	@Test
	
	public void WBDA_SHLP_DDIC_DDLB_FAVORITES() throws Exception{
		
		//Open the value help for the first inputfield (labeled "Country")
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Help.InputField.Land1"), "xpath");
		test.log(LogStatus.PASS, "Clicked on help of land filed");
		Reporter.log("Clicked on help of land filed",true);
		driver.switchTo().frame("URLSPW-0");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.PersönlicheWertelisteBearbeiten"), "xpath");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-1");
		
		//select city
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Option.City.ALICESPRINGS"), "xpath");
		MouseActivity.doubleClick(driver, Utility.webElement(driver, Utility.getLocator("Option.City.ALICESPRINGS"), "xpath"));
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Arrow.AddElement"), "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Ok"), "xpath");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-0");
		
		//verify selected city is displaying
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Option.City.ALICESPRINGS"), "xpath");
		MouseActivity.doubleClick(driver, Utility.webElement(driver, Utility.getLocator("Option.City.ALICESPRINGS"), "xpath"));
		test.log(LogStatus.PASS, "Selected required country");
		Reporter.log("Selected required country",true);
		driver.switchTo().defaultContent();
		Utility.waitForelement(driver, Utility.getLocator("Input.Land1"), "xpath");
		Utility.waitForelement(driver, Utility.getLocator("Input.Abflugstadt"), "xpath");
		Utility.waitForAttributeValueContains(driver, Utility.getLocator("Input.Land1"), "xpath", "value", "AU");
		Utility.waitForAttributeValueContains(driver, Utility.getLocator("Input.Abflugstadt"), "xpath", "value", "ALICE SPRINGS");
		VerifyExcepted.getAttribute(Utility.webElement(driver, Utility.getLocator("Input.Land1"), "xpath"), "value", "AU", "Pass: Selected country name is displaying", "Fail: Selected country name is displaying", test);
		VerifyExcepted.getAttribute(Utility.webElement(driver, Utility.getLocator("Input.Abflugstadt"), "xpath"), "value", "ALICE SPRINGS", "Pass: Selected city name is displaying", "Fail: Selected city name is displaying", test);
		
		
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception{
		if(result.getStatus()==ITestResult.FAILURE){
			
			 String ResScreenPath=Utility.getScreenshot(driver,Utility.reportPath);
			 test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));
	   }
	    extent.endTest(test);
		extent.flush();
		driver.quit();	
		BrowserFactory.killBrowserProcess(BrowserFactory.browserName);
	   }


}
