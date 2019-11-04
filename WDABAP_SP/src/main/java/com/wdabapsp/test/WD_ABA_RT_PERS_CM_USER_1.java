package com.wdabapsp.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class WD_ABA_RT_PERS_CM_USER_1 
{
	//static String url="https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_P13N%20?sap-client=005&sap-language=de##";
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

	public  void WD_WD_ABA_RT_PERS_CM_USER_1() throws Exception {
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//*[text()='Personalisierungsdaten zurücksetzen']","xpath");
		Utility.checkElementOr(driver, "Link.InuptField.xpath").click();
		Utility.checkElementOr(driver, "Link.InuptField.xpath").click();
		MouseActivity.rightClick(driver, "Input.LabelzumInputfield.xpath");
		
		Utility.checkElementOr(driver, "ContextMenue.Benutzereinstellungen.xpath").click();

		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'Eingabefeld ')]"));
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).click().build().perform();
		
		
		
//1.Open context menu for the input field called 'Label zum Inputfield' and open submenu for the first menu entry of the context menu.
//Choose 'Eingabefeld "Label zum Inputfield" ausblenden' out of the opened submenu.
		
     
    	  List<WebElement> input=driver.findElements(By.xpath("//*[contains(text(),'Label zum Inputfield')]/following::input[1]"));
    	  
		  //Assert.assertEquals(false, Utility.isElementPresentByXpath(driver, "Input.LabelzumInputfield.xpath"));
    	  if(input.size()>=1)
    	  {
		   Reporter.log("1.check the input field no more visible",true);
		   test.log(LogStatus.PASS, "1.check the input field no more visible");
		   test.log(LogStatus.PASS, "====================Finish======================================");
    	  }
    	  else
    	  {
		   Reporter.log("1.Fail the input field still visible",true);
		   test.log(LogStatus.FAIL, "1.Fail the input field still visible");
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
