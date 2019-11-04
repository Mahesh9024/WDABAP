package com.wdabapsp.test;

import java.io.IOException;

import org.openqa.selenium.Keys;
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

public class WD_ABA_COMPONENT_USAGE_GROUPS
{
	
	    ExtentReports extent;
		ExtentTest test;
		public WebDriver driver;
		//String ScrPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();	   
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
			String pAppName="WDR_TEST_CMP_USAGE_GROUP";
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	//tatic String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_CMP_USAGE_GROUP?=&sap-client=005&sap-language=DE#";

	@Test
	
	public  void WD_ABA_EXT_CONTEXT_MAPPING() throws Exception 
	{
		String usage="USAGE3";
		String dynamic="WDR_TEST_DYNAMIC_3";
		String main="MAIN/CONTAINER2.V1/VC3";
		for(int i=1;i<=3;i++)
		{
			if(i==2)
			{
			usage="USAGE1";
			}
			if(i==3)
			{
				usage="USAGE4";
				dynamic="WDR_TEST_DYNAMIC_1";
				main="MAIN/CONTAINER2.V1/VC1";
			}
		
			if(i==1)
			{
		Utility.clickOnElementUsingJavaScriptExecutor(driver,"CheckBox.Navigationvorbereiten.xpath");	
			}
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Tabstripanzeigen.xpath");
		if(i==1)
		{
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Eintraganlegen.xpath");		
		}
		
		Utility.checkElementOr(driver, "Input.ComponentVerwendung.xpath").clear();
		Utility.checkElementOr(driver, "Input.ComponentVerwendung.xpath").sendKeys(usage);
		
		Utility.checkElementOr(driver, "Input.ZuerzeugendeComponent.xpath").clear();
		Utility.checkElementOr(driver, "Input.ZuerzeugendeComponent.xpath").sendKeys(dynamic);
		Utility.checkElementOr(driver, "Input.Einbettungsposition.xpath").clear();
		Utility.checkElementOr(driver, "Input.Einbettungsposition.xpath").sendKeys(main);
		Utility.checkElementOr(driver, "Input.Einbettungsposition.xpath").sendKeys(Keys.ENTER);
		
		if(i==1)
		{
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Hinzufügen.xpath");
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Tabstripanzeigen.xpath");
		VerifyExcepted.getAttribute(driver, "Label.USAGE3.xpath", "innerHTML", "USAGE3", "1.check the new tab appeared labeled as 'USAGE3'.", "1.Fail The new tab has not appered as USAGE3", test);
		}
		else if(i==2)
		{
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Hinzufügen.xpath");
		//System.out.println("text"+Utility.checkElementOr(driver, "Label.DieComponent.xpath").getText());
		VerifyExcepted.getAttribute(driver, "Label.DieComponent.Usage1.Error.xpath", "innerHTML", "Component", "2.check the Error msg displayed.", "1.Fail The Error msg has not displayed.", test);
		}
		else if(i==3)
		{
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Hinzufügen.xpath");
			VerifyExcepted.getAttribute(driver, "Label.DieComponentError.xpath", "innerHTML", "Element", "3.check the Error msg displayed.", "3.Fail The Error msg has not displayed.", test);
			
		}
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
