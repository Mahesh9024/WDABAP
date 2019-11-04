package com.wdabapsp.test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wdabapsp.page.LoginPage;

import lib.BrowserFactory;
import lib.Utility;
import lib.Wait;

public class WDA_CLIENT_PRINT 
{
	
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	String ScrPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();	   
	String StepDetail;
	
	@BeforeTest
	public void setUp() throws Exception
	{
		extent=Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
		driver = BrowserFactory.typeBrowser();
		LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
		test.log(LogStatus.PASS, StepDetail);			
		String pNamespace="sap";
		String pAppName="wdr_test_events";			
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"EN");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);		
	}
	
	@Test
	public void CLIENT_PRINT() throws InterruptedException
	{
	
	
		//Click button PrintPreview
		Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.PrintPreview.xpath");
		StepDetail="Click button PrintPreview";
		test.log(LogStatus.PASS, StepDetail);
		Wait.sleep(2000);
		System.out.println(driver.getPageSource());
		driver.findElement(By.xpath("//span[text()='Libraries']"));
		
							
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		    System.out.println("these are the windows: "+driver.getWindowHandle());
		    System.out.println("these are the windows titles: "+driver.getTitle());
		}
		
		driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		System.out.println(driver.getWindowHandle());
		Wait.sleep(2000);
		driver.navigate().refresh();
		driver.switchTo().defaultContent();
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='BreadCrumb']", "xpath");
		
		driver.findElement(By.xpath("//span[text()='Libraries']"));
		
		try {
			System.out.println(driver.getPageSource());
			assertTrue((driver.getPageSource()).contains("Libraries"), "window not appearing with same content");
			assertTrue((driver.getPageSource()).contains("BreadCrumb"), "window not appearing with same content");
			assertTrue((driver.getPageSource()).contains("RowRepeater"), "window not appearing with same content");
			driver.findElement(By.xpath(".//span[contains(text(),'BreadCrumb')]"));
			assertTrue((driver.getTitle()).contains("UI Elements & Events - Print Preview"), "new window not displaying");
			StepDetail="Pass : The new window should appear with the same content";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
			 test.log(LogStatus.PASS, "=====================Finish========================================");
			  
	     } catch (NoSuchElementException e) {
	    	 
	    	 StepDetail="Fail : The new window should appear with the same content";
			 test.log(LogStatus.FAIL, StepDetail);
			 Reporter.log(StepDetail,true);
			 
		 }

}


	
	@AfterMethod
	public void afterTest() {
		StepDetail="User successfully Logged off";
		test.log(LogStatus.PASS, StepDetail);
	    extent.endTest(test);
		extent.flush();
		driver.quit();		
	}

}
