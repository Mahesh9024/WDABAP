package com.wdabapsp.test;

import java.io.IOException;

import org.openqa.selenium.By;
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
import lib.Wait;

public class WDA_SELOPT_DISPLAYING_IN_POPUP
{
	
	
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
			String pAppName="WDR_TEST_SELECT_OPTIONS";
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }	
	@Test
	
		public  void WD_ABA_RADIOBUTTON() throws Exception {
			
//	1. Click on ".select-option Felder".
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.selectoption.xpath");
			test.log(LogStatus.PASS, "1.Clicked on select-option Felder");
//2. Click on "Typ-spezifische Testfälle".  
			Utility.checkElementPresense(driver, Utility.checkElementOr(driver, "Link.Typspezifische.xpath"));
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Typspezifische.xpath");
			test.log(LogStatus.PASS, "2.Clicked on yp-spezifische Testfälle");
//3. Click on Button "Alle elementaren ABAP-Typen".
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.AlleelementarenABAPTypen.xpath");
			test.log(LogStatus.PASS, "3.Clicked on Alle elementaren ABAP-Typen");
//4. Enter something in one of the fields appearing
			// Utility.checkElementOr(driver, "Input.TYPET.xpath").click();
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Input.TYPET.xpath");
			Utility.checkElementOr(driver, "Input.TYPET.xpath").sendKeys("123");
			Utility.checkElementOr(driver, "Input.TYPET.xpath").sendKeys(Keys.ENTER);
			
//5. Click on "In Popup anzeigen"
			
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.InPopupanzeigen.xpath");
			test.log(LogStatus.PASS, "3.Clicked on In Popup anzeigen");
//6. The selection screen should appear on the popup now			
			driver.switchTo().frame("URLSPW-0");
//7. Enter something in another field or change the previously changed one
			
		    //Utility.checkElementOr(driver, "Input.TYPEI.xpath").sendKeys("456");
			driver.findElement(By.xpath("//*[contains(text(),'TYPE=I')]/following::input[1]")).clear();
			driver.findElement(By.xpath("//*[contains(text(),'TYPE=I')]/following::input[1]")).sendKeys("456");
			driver.findElement(By.xpath("//*[contains(text(),'TYPE=I')]/following::input[1]")).sendKeys(Keys.ENTER);
			//driver.findElement(By.xpath("Input.TYPET.xpath")).sendKeys("456"); 
//8. Click the "Ok" button to close the popup window		
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");
			driver.switchTo().defaultContent();
//9. The selection screen should appear on the main window again.
			Wait.sleep(3000);
			String text=driver.findElement(By.xpath("//*[contains(text(),'TYPE=I')]/following::input[1]")).getAttribute("value");
         //  String text=driver.findElement(By.xpath("Input.TYPET.xpath")).getAttribute("value");
           System.out.println("text "+text);
           if(text.equalsIgnoreCase("456"))
           {
        	   Reporter.log("4.check The value has changed.",true);
        	   test.log(LogStatus.PASS,"4.check The value has changed.");
        	   test.log(LogStatus.PASS, "=====================Finish========================================");
           }
           else
           {
        	   test.log(LogStatus.FAIL,"4.Fail The value has not changed.");
        	   Reporter.log("4.Fail The value has not changed.",true);
           }
  
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
			driver.quit();	
			BrowserFactory.killBrowserProcess(BrowserFactory.browserName);
		   }

}
