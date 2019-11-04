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

public class WDA_UIEL_REALIGN_1 {
	
	// "http://ldciu3y.wdf.sap.corp:55000/sap/bc/webdynpro/sap/wdr_test_select_options?sap-client=200&sap-language=de#";
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
			String pAppName="demo_uiel_flow_layout";	
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	@Test

	public  void WDA_WD_UIEL_REALIGN_1() throws Exception {
		
		//Utility.clickOnElementUsingJavaScriptExecutor(driver, "Input.Fluggesellschaft.xpath");
		MouseActivity.rightClick(driver, Utility.checkElementOr(driver,"Input.Fluggesellschaft.xpath"));

		Utility.checkElementOr(driver, "ContextMenue.Benutzereinstellungen.xpath").click();
		Utility.checkElementOr(driver, "ContextMenue.EingabefeldFluggesellschaftausblenden.xpath").click();

		try {
			Assert.assertEquals(Utility.isElementPresentByXpath(driver, "Input.Fluggesellschaft.xpath"), false);
			Reporter.log("1.check The inputfield  has been disappeared", true);
			test.log(LogStatus.PASS, "1.check The inputfield  has been disappeared");
			test.log(LogStatus.PASS, "1.check The inputfield  has been disappeared");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "1.check The inputfield  has  been not disappeared");
		}

		MouseActivity.rightClick(driver, Utility.checkElementOr(driver, "Label.Flugnummer.xpath"));		
        Utility.checkElementOr(driver, "ContextMenue.Benutzereinstellungen.xpath").click();
		
		WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(),'Unsichtbare')]"));
		MouseActivity.mouseHoverAdvance(driver, ele1);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, ele1);
		Thread.sleep(5000);

		driver.switchTo().frame(0);
		Utility.checkElementOr(driver, "Frame.Link.TestButtonwiederherstellen.xpath").click();
		driver.switchTo().defaultContent();

		try {
			Assert.assertEquals(Utility.isElementPresentByXpath(driver, "Input.Fluggesellschaft.xpath"), true);
			Reporter.log("2.check The inputfield  has been appeared", true);
			test.log(LogStatus.PASS, "2.check The inputfield  has been appeared");
			 test.log(LogStatus.PASS, "=====================Finish========================================");
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "2.check The inputfield  shuold appeared");
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
