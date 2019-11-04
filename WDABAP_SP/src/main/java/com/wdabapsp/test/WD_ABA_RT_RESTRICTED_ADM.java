package com.wdabapsp.test;

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

public class WD_ABA_RT_RESTRICTED_ADM 
             //WD_ABA_RT_RESTRICTED_USER covered this test case also here.
{
	
	//static String url= "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/wdr_test_p13n?sap-client=005&sap-language=de&sap-config-mode=X#";
	
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
			Thread.sleep(5000);
			LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
			test.log(LogStatus.PASS, StepDetail);			
			String pNamespace="sap";
			String pAppName="WDR_TEST_P13N";
			 String pAdminMode="sap-config-mode=x";
			loginPage.loginAsAdmin(pNamespace, pAppName, pAdminMode);
			
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }	
	@Test
		
		public void WD_ABA_TOGGLEBUTTON() throws Exception {
		
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.TestinkludierterestriktierteComponent.xpath");
			
			MouseActivity.rightClick(driver, "FirstArea.uneingeschränktpersonalisiert.xpath");
			
			Utility.ClickArrowButton(driver, 1);	
					
			driver.switchTo().frame("URLSPW-0");
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.NichtpersonalisierbareComponent.xpath");			
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Abbrechen.xpath");
			Reporter.log("1.Check The Pop-Up has been disappered.",true);
			test.log(LogStatus.PASS, "1.Check The Pop-Up has been disappered.");
			
			driver.switchTo().defaultContent();
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "SecondArea.Enduser.xpath");
			
			MouseActivity.rightClick(driver, "SecondArea.Enduser.xpath");			
			Utility.ClickArrowButton(driver, 1);
					
			driver.switchTo().frame("URLSPW-0");
			
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Abbrechen.xpath");
			Reporter.log("2.Check The Pop-Up has been disappered.",true);
			test.log(LogStatus.PASS, "2.Check The Pop-Up has been disappered");
			
			driver.switchTo().defaultContent();
			
			MouseActivity.rightClick(driver, "ThirdArea.niemand.xpath");
						
		    String text= driver.findElement(By.xpath("//span[text()='Mehr Feldhilfe...']")).getText();
				
				if(text.equals("Mehr Feldhilfe..."))
				{
				Reporter.log("3. Check the Congifg context is not  present in third area box.",true);
				test.log(LogStatus.PASS, "3. Check the Congifg context is not  present in third area box.");
				test.log(LogStatus.PASS, "====================Finish======================================");
				}
				else
				{
				Reporter.log("3. Fail the Congifg context is  present in third area box.",true);
				test.log(LogStatus.FAIL, "3.Fail the Congifg context is  present in third area box.");
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
