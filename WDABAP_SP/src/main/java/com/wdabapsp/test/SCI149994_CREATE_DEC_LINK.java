package com.wdabapsp.test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wdabapsp.page.LoginPage;

import lib.BrowserFactory;
import lib.DropDown;
import lib.MouseActivity;
import lib.Utility;
import lib.Wait;

public class SCI149994_CREATE_DEC_LINK 
{
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	//public String SrcPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();
	String StepDetail;
	@BeforeTest
	public void setUpPrereqisites() throws Exception
	{
		 extent=Utility.initReportPath(this.getClass().getSimpleName());
		 test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
		 driver = BrowserFactory.typeBrowser();
		 Thread.sleep(5000);
		 LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
		 StepDetail =loginPage.browserInfo();
		 test.log(LogStatus.PASS, StepDetail);
		 String pNamespace="sap";
		 String pAppName="wd_analyze_config_user";	
		 String pUIElementToStart="";
		 loginPage.login(pNamespace,pAppName,pUIElementToStart,"DE");
		 StepDetail="1.User successfully Logged in";
		 test.log(LogStatus.PASS, StepDetail);
		 Reporter.log("User successfully Logged in",true);	 
		 
	}
	
	@Test
	public void SCI149994_CREATE_DEC_LINK() throws Exception	 
	     {
		  Utility.checkElementOr(driver, "Input.WebDynproComponent.xpath").sendKeys("WDR_TEST_P13N");
		  Utility.checkElementOr(driver, "Img.Benutzer.xpath").click();
		  Thread.sleep(3000);
		  driver.switchTo().frame(0);
		  Utility.checkElementOr(driver, "Img.sBGEQUAgif.xpath").click();
		  Thread.sleep(2000);
		  driver.switchTo().defaultContent();
		  Thread.sleep(2000);
		  Utility.checkElementOr(driver, "Input.Benutzer.xpath").sendKeys("*");	  
		  
		  Utility.checkElementOr(driver, "Button.Anzeigen.xpath").click();
		  
		  if(Utility.isElementPresentByXpath(driver, "Button.Zurück.xpath"))
		  {
			  Utility.checkElementOr(driver, "Button.Zurück.xpath").click();
			  test.log(LogStatus.PASS, "Check 1. Clicked the Zurück.");
			  test.log(LogStatus.PASS, "Check 2.The Created link has opended the new tab successfully.");
			  Reporter.log("Check 1. Clicked the Zurück.",true);
		  }
		  else
		  {
			  driver.findElement(By.xpath("//*[text()='WDR_TEST_P13N']")).click();
			  Thread.sleep(2000);
			  Utility.checkElementOr(driver, "Button.Löschen.xpath").click();
			   driver.switchTo().frame(0);
			  driver.findElement(By.xpath("//span[text()='Ja']")).click();
			  Wait.sleep(3000);
			  driver.findElement(By.xpath("//span[text()='Nein']")).click();
			  
		  }
		  
		  driver.close();
//opening the new tab
		  
		  /*  Robot r = new Robot(); 
			r.keyPress(KeyEvent.VK_CONTROL); 
			r.keyPress(KeyEvent.VK_T); 
			r.keyRelease(KeyEvent.VK_CONTROL); 
			r.keyRelease(KeyEvent.VK_T);
			
			Thread.sleep(5000);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles()); 
			driver.switchTo().window(tabs2.get(1));
			*/
		 
		     driver = BrowserFactory.typeBrowser();
			 Thread.sleep(5000);		
			 LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
			 test.log(LogStatus.PASS, StepDetail);
			
			    String pNamespace = "sap";
				String pAppName = "WDR_TEST_P13N";
				String pAdminMode = "sap-config-mode=x";
				loginPage.loginAsAdmin(pNamespace, pAppName, pAdminMode);
			 StepDetail="2.User successfully Logged in";
			 test.log(LogStatus.PASS, StepDetail);
			 Reporter.log("User successfully Logged in",true);
			 
			 
			// https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_P13N?sap-config-mode=x%3fsap-language%3dde&sap-client=005&sap-language=EN#
			
			MouseActivity.rightClick(driver, "Label.TestSuiteforP13N.xpath");
			Utility.ClickArrowButton(driver, 1);
			Thread.sleep(3000);
			
			driver.switchTo().frame(0);			
			Utility.checkElementOr(driver, "Link.Hinzufügen.xpath").click();
			Thread.sleep(2000);
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			
			DropDown.selecFromDropDownByElement(driver, "Input.TypdesdekorativenElements.xpath", "Link");		
			test.log(LogStatus.PASS, "2.check Selected the Link from DropDown.");
			Utility.checkElementOr(driver, "Label.OK.xpath").click();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			Utility.checkElementOr(driver, "InputBox.Text.xpath").sendKeys("Text1");
			Utility.checkElementOr(driver, "Input.URL.xpath").sendKeys("http://www.sap.com");			
			Utility.checkElementOr(driver, "Button.Sichernundschließen.xpath").click();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			Utility.checkElementOr(driver, "Radiobutton.KeinTransportauftrag.xpath").click();
			Thread.sleep(2000);
			Utility.checkElementOr(driver, "Label.OK.xpath").click();
			
			driver.switchTo().defaultContent();
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[text()='Text1']")).click();
			
			Thread.sleep(5000);			
			ArrayList<String> al=  new ArrayList<String> (driver.getWindowHandles()); 
			
			driver.switchTo().window(al.get(1));
			test.log(LogStatus.PASS, "3.check Switched to the newly open window.");
			//System.out.println("text"+driver.getTitle());
			
			try {
				Assert.assertEquals(true,(driver.getTitle()).contains("SAP"));
				 test.log(LogStatus.PASS, "Check 4.The Created link has opended the new tab successfully.");
				 Reporter.log("Check 4.The Created link has opended the new tab successfully ",true);
				 test.log(LogStatus.PASS, "=========================Finish====================================");
			} catch (Exception e) {
				test.log(LogStatus.PASS, "4.Check The Created link has not opended the new tab successfully.");
				Reporter.log("4.Check The Created link has not opended the new tab successfully ",true);
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
