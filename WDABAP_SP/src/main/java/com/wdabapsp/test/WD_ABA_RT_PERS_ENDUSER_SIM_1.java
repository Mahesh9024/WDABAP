package com.wdabapsp.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class WD_ABA_RT_PERS_ENDUSER_SIM_1 
{
	//static String url="https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_P13N%20?sap-client=005&sap-language=de#";
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
		String pAppName="WDR_TEST_P13N";	
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);
      }
	@Test

	public  void WD_WD_ABA_RT_PERS_ENDUSER_SIM_1() throws Exception {
		
		
		Utility.checkElementOr(driver, "Link.Button.xpath").click();
		MouseActivity.rightClick(driver, "Button.TestButton.xpath");
		Utility.checkElementOr(driver, "ContextMenue.Benutzereinstellungen.xpath").click();
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'Drucktaste')]"));
		MouseActivity.mouseHoverAdvance(driver, ele);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, ele);
		Thread.sleep(5000);
		Assert.assertEquals(false, Utility.isElementPresentByXpath(driver,"Button.TestButton.xpath"));
		Reporter.log("1.Check the buttton has disappered.",true);
		
		
		MouseActivity.rightClick(driver, "Label.Buttonpersonalisieren.xpath");
		Utility.checkElementOr(driver, "ContextMenue.Benutzereinstellungen.xpath").click();
		
		WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(),'Unsichtbare')]"));
		MouseActivity.mouseHoverAdvance(driver, ele1);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, ele1);
		Thread.sleep(5000);
		driver.switchTo().frame(0);
		Utility.checkElementOr(driver, "Frame.Link.TestButtonwiederherstellen.xpath").click();
		driver.switchTo().defaultContent();
		
		
		Assert.assertEquals(true, Utility.isElementPresentByXpath(driver,"Button.TestButton.xpath"));
		Reporter.log("1.Check the buttton has appered.",true);
		test.log(LogStatus.PASS, "2.Check the buttton has appered.");
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
