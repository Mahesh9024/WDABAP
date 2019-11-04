package com.wdabapsp.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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

public class WD_ABA_LIFECYCLE_ACTIVE_COMP 
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
		//driver = BrowserFactory.typeBrowser();
		//~~~~~~~~~~~~~~~~~~~~~ initialize browser driver ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		String DRIVER_PATH = Utility.class.getClassLoader().getResource("IEDriverServer.exe").getFile();
		System.setProperty("webdriver.ie.driver", DRIVER_PATH);
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); 
		driver = new InternetExplorerDriver(capabilities);
		
		//driver = new InternetExplorerDriver(capabilities);
	
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    //~~~~~~~~~~~~~~~~~~~ ============================  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	    
		LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
		test.log(LogStatus.PASS, StepDetail);			
		String pNamespace="sap";
		String pAppName="wdr_test_lifecycle_ac";			
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"EN");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);		
	}
	
	@Test
	public void LIFECYCLE_ACTIVE_COMP() throws InterruptedException, IOException
	{
		
		//On the left hand side you'll find links to navigate though the test suite. Click the link "Flash Islands".
		//Result: The content area changes and shows a Flash Island application with input fields 
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver,Utility.getLocator("Link.FlashIsland.xpath"),"xpath");
		StepDetail="On the left hand side you'll find links to navigate though the test suite. Click the link Flash Islands";
		test.log(LogStatus.PASS, StepDetail);
		
		try {
			Utility.waitForelement(driver, "//div[contains(@title,'Flash Island')]", "xpath");
			driver.findElement(By.xpath("//div[contains(@title,'Flash Island')]"));
			StepDetail="Pass : The content area changes and shows a Flash Island application with input fields";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
			  
	     } catch (Exception e) {
	    	 
	    	 StepDetail="Fail : The content area changes and shows a Flash Island application with input fields";
			 test.log(LogStatus.FAIL, StepDetail);
			 Reporter.log(StepDetail,true);
			 
		 }
		
		//Click the toggle button "Sichtbarkeit" twice.
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.Sichtbarkeit.xpath1");
		StepDetail="Click the toggle button Sichtbarkeit twice";
		test.log(LogStatus.PASS, StepDetail);
		
		try {
			Wait.sleep(5000);
			 Utility.waitForelementToDisappear(driver, "//div[contains(@title,'Flash Island')]", "xpath");
			 driver.findElement(By.xpath("//div[contains(@title,'Flash Island')]"));
			 StepDetail="Fail : The Flash Island gets invisible";
			 test.log(LogStatus.FAIL, StepDetail);
			  
	     } catch (Exception e) {
	    	 
    	 	StepDetail="Pass : The Flash Island gets invisible";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
	    	
			 
		 }
        Thread.sleep(5000);
		Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.Sichtbarkeit.xpath1");
		StepDetail="Click the toggle button Sichtbarkeit twice";
		test.log(LogStatus.PASS, StepDetail);
		
		//The Flash Island gets invisible and visible again. 
		
		try {
			Utility.waitForelement(driver, "//div[contains(@title,'Flash Island')]", "xpath");
			driver.findElement(By.xpath("//div[contains(@title,'Flash Island')]"));
			StepDetail="Pass : The Flash Island gets invisible and visible again";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
			  
	     } catch (Exception e) {
	    	 
	    	 StepDetail="Fail : The Flash Island gets invisible and visible again";
			 test.log(LogStatus.FAIL, StepDetail);
			 Reporter.log(StepDetail,true);
			 
		 }
		
		//In the navigation area on left hand side click the link "Silverlight Island".
		Utility.clickOnElementUsingJavaScriptExecutor(driver,"Link.SilverlightIsland.xpath");
		StepDetail="Click the toggle button Sichtbarkeit twice";
		test.log(LogStatus.PASS, StepDetail);
		
		//The content area changes and shows an Silverlight application.
		
		try {
			Wait.sleep(6000);
			//Utility.waitForelement(driver, "//object/a/img[contains(@alt,'Get Microsoft Silverlight')][contains(@src,'microsoft.com')]", "xpath");
			driver.findElement(By.xpath(".//*[contains(@alt,'Microsoft Silverlight')]"));
			StepDetail="Pass : The content area changes and shows an Silverlight application";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
			  
	     } catch (Exception e) {
	    	 
	    	 StepDetail="Fail : The content area changes and shows an Silverlight application";
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
