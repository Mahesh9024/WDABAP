package com.wdabapsp.test;

import java.io.IOException
;
import java.util.Iterator;
import java.util.Set;

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
import lib.Wait;

public class WD_ABA_POPUPS {
	//static String url = "https://ldciq3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/wdr_test_popups_rt?sap-client=005&sap-language=de#";
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
			String pAppName="wdr_test_popups_rt";
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	@Test

	public  void WD_ABA_TOGGLEBUTTON() throws Exception {
		

		// Expand the tray 'Popup Tests: Runtime'.

		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.PopupTestsRuntime.xpath");

		// 1. Select 'Modales Popup'
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.ModalesPopup.xpath");

		driver.switchTo().frame("URLSPW-0");

		try {
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Schliessen.xpath");
			test.log(LogStatus.PASS, "1.check the Modales Popup came and afre click on cancel it disappered.");
			Reporter.log("1.check the Modales Popup came and afre click on cancel it disappered.", true);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "1.Fail the pop up has not disappered.");
			Reporter.log("1.Fail the pop up has not disappered.", true);
		}
		driver.switchTo().defaultContent();

		// 2. Select 'Externes URL-Fenster'

		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.ExternesURLFenster.xpath");
		Wait.sleep(2000);
		String parent = driver.getWindowHandle();
		System.out.println("parent" + parent);
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();

		while (itr.hasNext()) {
			String child = itr.next();
			if (!parent.equals(child)) {
				driver.switchTo().window(child);
				// System.out.println("child"+child);
				Reporter.log("2.check Externes URL-Fenster popup open.", true);
				test.log(LogStatus.PASS, "2.check Externes URL-Fenster popup open.");
			}
			
		}
		
		driver.close();
		driver.switchTo().window(parent);
		// 3. Select 'Modales Popup, Action am Close-Button'
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.CloseButton.xpath");
		driver.switchTo().frame("URLSPW-0");

		try {
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Close.form.xpath");
			test.log(LogStatus.PASS, "3.check the Modales Popup, Action am Close-Button came and afre click on cancel it disappered.");
			Reporter.log(
					"3.check the Modales Popup, Action am Close-Button came and afre click on cancel it disappered.",
					true);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "3.Fail the pop up has not disappered.");
			Reporter.log("3.Fail the pop up has not disappered.", true);
		}

		driver.switchTo().defaultContent();

		try {
			Assert.assertEquals(Utility.isElementPresentByXpath(driver, "Label.Popupwurdegeschlossen.xpath"), true);
			test.log(LogStatus.PASS, "4.check  the text 'Popup wurde geschlossen' is displaye");
			Reporter.log("4.check  the text 'Popup wurde geschlossen' is displaye",true);
		} catch (Error e1) {
			test.log(LogStatus.FAIL, "4.Fail  the text 'Popup wurde geschlossen' has not displaye.");
			Reporter.log("4.Fail  the text 'Popup wurde geschlossen' has not displaye",true);
		}
		// 4. Select 'Popup im Popup'.
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.PopupimPopup.xpath");
		driver.switchTo().frame("URLSPW-0");
		try {
			Utility.clickOnElementUsingJavaScriptExecutor(driver, "Close.form.xpath");
			test.log(LogStatus.PASS, "5.check the Popup im Popup opened and after click on close button it disappered.");
			Reporter.log("5.check the Popup im Popup opened and after click on close button it disappered.", true);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "5.Fail the pop up has not disappered");
			Reporter.log("5.Fail the pop up has not disappered.", true);
		}

		driver.switchTo().defaultContent();
		// 5. Select 'Default Inbound-Plugs'.
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.DefaultInboundPlugs1.xpath");
		
		driver.switchTo().frame("URLSPW-0");
		
		
		try {
			Assert.assertEquals((Utility.checkElementOr(driver, "Frame.V4.xpath")).getText().contains("V4"), true);
			test.log(LogStatus.PASS, "6.check the DefaultInboundPlugs Popup opened and after click on close button it disappered.");
			Reporter.log("6.check the DefaultInboundPlugs Popup opened and after click on close button it disappered.", true);
			test.log(LogStatus.PASS, "====================Finish======================================");
		} catch (Error e) {
			test.log(LogStatus.FAIL, "6.Fail the pop up has not disappered.");
			Reporter.log("6.Fail the pop up has not disappered.", true);
		}
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Close.form.xpath");
		driver.switchTo().defaultContent();	
		

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
