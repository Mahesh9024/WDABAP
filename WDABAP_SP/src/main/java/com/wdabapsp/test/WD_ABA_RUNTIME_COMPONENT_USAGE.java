package com.wdabapsp.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import lib.Utility;

public class WD_ABA_RUNTIME_COMPONENT_USAGE 
{
	//static String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_CMPUSAGE?sap-client=005&sap-language=DE#";
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
			String pAppName="WDR_TEST_CMPUSAGE";
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	@Test
	
	public  void WD_WD_ABA_RUNTIME_COMPONENT_USAGE() throws Exception {
		
//1. Select the link 'Eingebettete Component erzeugen und löschen,; keine Anzeige'.
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Eingebettete Component erzeugen und löschen; keine Anzeige']", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Verwendete Component erzeugen']", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Verwendete Component löschen']", "xpath");
	
		
		try {
			
			Assert.assertEquals("Component wurde gelöscht", driver.findElement(By.xpath("//span[text()='Component wurde gelöscht']")).getText());
		     Reporter.log("1.Check the button worked fine. ",true);
		     test.log(LogStatus.PASS, "1.Check the button worked fine.");
		} catch (Exception e) {

			Reporter.log("1.Fail the button worked fine. ",true);
			 test.log(LogStatus.FAIL, "1.Fail the button worked fine. ");
		}
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Eingebettete Component erzeugen, anzeigen und löschen']", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Verwendete Component erzeugen']", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Verwendete Component löschen']", "xpath");
		
try {
			
			Assert.assertEquals("Component wurde gelöscht", driver.findElement(By.xpath("//span[text()='Component wurde gelöscht']")).getText());
		     Reporter.log("2.Check the button worked fine. ",true);
		     test.log(LogStatus.PASS, "2.Check the button worked fine. ");
		     
		} catch (Error e) {

			Reporter.log("2.Fail the button worked fine. ",true);
			test.log(LogStatus.FAIL, "2.Fail the button worked fine.");
		}

//
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Eingebettete Component erzeugen; löschen via Eventing']", "xpath");
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Verwendete Component erzeugen']", "xpath");
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Event an Einbetter schicken -> Component löschen']", "xpath");


try {
	
	Assert.assertEquals("Component wurde gelöscht", driver.findElement(By.xpath("//span[text()='Component wurde gelöscht']")).getText());
     Reporter.log("3.Check the button worked fine. ",true);
     test.log(LogStatus.PASS, "3.Check the button worked fine.");
} catch (Error e) {

	Reporter.log("3.Fail the button worked fine. ",true);
	test.log(LogStatus.FAIL, "3.Fail the button worked fine.");
}

Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Component Interface: Implementierende Component austauschen']", "xpath");
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Implementierung 1 erzeugen']", "xpath");
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Implementierung 2 erzeugen']", "xpath");

try {
	
	Assert.assertEquals("MAIN", driver.findElement(By.xpath("//span[text()='MAIN']")).getText());
     Reporter.log("4.Check the button worked fine. ",true);
     test.log(LogStatus.PASS, "4.Check the button worked fine.");
} catch (Error e) {

	Reporter.log("4.Fail the button worked fine. ",true);
	test.log(LogStatus.FAIL, "4.Fail the button worked fine. ");
}
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Component verwendet sich selbst über Component-Interface']", "xpath");
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Component erzeugen']", "xpath");
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Component erzeugen']", "xpath");


try {
	
	 Assert.assertEquals("500 SAP Internal Server Error", driver.findElement(By.xpath("html/body/h1")).getText());
     Reporter.log("5.Check the button worked fine. ",true);
     test.log(LogStatus.PASS, "5.Check the button worked fine. ");
} catch (Error e) {

	Reporter.log("5.Fail the button worked fine. ",true);
	test.log(LogStatus.FAIL, "5.Fail the button worked fine.");
}
driver.navigate().refresh();
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Die Component implementiert das Component-Interface nicht']", "xpath");



try {
	
	Assert.assertEquals("WebDynpro Exception: Die Component WDR_TEST_CMPUSAGE5 implementiert nicht das Cmp.-Interface WDR_TEST_CMPUSAGE_CI1", driver.findElement(By.xpath("//span[text()='WebDynpro Exception: Die Component WDR_TEST_CMPUSAGE5 implementiert nicht das Cmp.-Interface WDR_TEST_CMPUSAGE_CI1']")).getText());
     Reporter.log("6.Check the button worked fine. ",true);
     test.log(LogStatus.PASS, "6.Check the button worked fine.");
    
} catch (Error e) {

	Reporter.log("6.Fail the button worked fine. ",true);
	test.log(LogStatus.FAIL, "6.Fail the button worked fine. ");
}

Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Verwendete Component erzeugen']", "xpath");
Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Verwendete Component löschen']", "xpath");

try {
	
	Assert.assertEquals("Component wurde gelöscht", driver.findElement(By.xpath("//span[text()='Component wurde gelöscht']")).getText());
     Reporter.log("7.Check the button worked fine. ",true);
     test.log(LogStatus.PASS, "7.Check the button worked fine. ");
     test.log(LogStatus.PASS, "=====================Finish========================================");
} catch (Error e) {

	Reporter.log("7.Fail the button worked fine. ",true);
	test.log(LogStatus.FAIL, "7.Fail the button worked fine. ");
}


//2. Select the link 'Eingebettete Component erzeugen, anzeigen und löschen'.3. Select the link 'Eingebettete Component erzeugen; löschen via Eventing'.4. Select the link 'Component Interface: Implementierende Component austauschen'.5. Select the link 'Component verwendet sich selbst über Component-Interface'.6. Select the link 'Die Component implementiert das Component-Interface' nicht'.7. Select the link 'Weiterleiten von Event-Registrierungen'.
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
