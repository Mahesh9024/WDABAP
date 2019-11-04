package com.wdabapsp.test;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import lib.DropDown;
import lib.MouseActivity;
import lib.Utility;
import lib.VerifyExcepted;

public class WDR_TEST_CTXT_PROPERTIES 
{
//	static String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/wdr_test_ctxt_properties?sap-client=005&sap-language=de#";
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
			String pAppName="wdr_test_ctxt_properties";
			String pUIElementToStart="";
			loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
			StepDetail="User successfully Logged in";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log("User successfully Logged in",true);
	      }
	@Test
	
	public  void WDR_WD_TEST_CTXT_PROPERTIES() throws Exception {
		
		
//single properties
		
		DropDown.selecFromDropDownByValue(driver, "DropDown.Property.xpath", "Visible");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath(".//*[@id='sapwd_main_window_root_']")).click();
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Reset.xpath");
		
		try {
			Assert.assertEquals(Utility.isElementPresentByXpath(driver, "Input.Feld.xpath"), false);
			Reporter.log("1.check The inputfield  has been disappeared", true);
			test.log(LogStatus.PASS, "1.check The inputfield  has been disappeared");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "1.check The inputfield  has  been not disappeared");
		}
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Set.xpath");
		DropDown.selecFromDropDownByValue(driver, "DropDown.Property.xpath", "Required");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Set.xpath");
		
		VerifyExcepted.getAttribute(Utility.checkElementOr(driver, "Symbol.Star.xpath"), "class", "req", "2.check The start has visible", "2.Fail Star has not visible.", test);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Reset.xpath");
		
		try {
			Assert.assertEquals(Utility.isElementPresentByXpath(driver, "Symbol.Star.xpath"), false);
			Reporter.log("3.check The star  has been disappeared", true);
			test.log(LogStatus.PASS, "3.check The star  has been disappeared");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "3.check The star  has been not disappeared");
		}
		
//2) Click on "Properties per element"  on the left. A form with 3 test fields appear and a table with 3 active rows beneath it
		
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Propertiesperelement.xpath");	
		 Reporter.log("4.check clicked to the second link",true);
//2.a) Check the checkbox "RO"(Readonly) of the first row in the Properties table, press "Set selected Properties".
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "CheckBox.V1.RO.xpath");
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Setselectedproperties.xpath");
		 Thread.sleep(5000);
//2.a)  The first field gets light blue, and there is no entry possible into this field. The label "V1" is still dark
		 System.out.println(Utility.checkElementOr(driver, "Label.TopV1.xpath1").getAttribute("class"));
		 VerifyExcepted.getAttribute(driver, "Label.TopV1.xpath1", "class", "readonly", "5.check The V1 has Readonly.", "5.check The V1 has not Readonly.", test);
		 
//2.b) Select the second row of the table, uncheck "visible" in the second row, check "keep others" beneath the table, and press "Set selected properties".
		 WebElement element = driver.findElement(By.xpath("//input[@value='V2']/preceding::td[1]"));
		 //Utility.checkElementOr(driver, "Row.V2.xpath");
		 MouseActivity.pressEnter(driver,  element);
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "Row.V2.xpath");
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "CheckBox.V2.Visible.xpath");
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "CheckBox.keepothers.xpath");
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Setselectedproperties.xpath");
//2.b) The field labelled "V2" vanished together with the label. The first field stays read-only.
		 try {
			Assert.assertEquals(false, Utility.isElementPresentByXpath(driver, "Label.V2.xpath"));
			Reporter.log("6.Check the V2 has vanished together with the label.",true);
			test.log(LogStatus.PASS, "6.Check the V2 has vanished together with the label.");
		} catch (Error e) {
			test.log(LogStatus.FAIL, "6.Check the V2 has not vanished together with the label.");
			Reporter.log("Fail: 6.Check the V2 has not vanished together with the label.",true);
		}
		 VerifyExcepted.getAttribute(driver, "Label.TopV1.xpath1", "class", "readonly", "6.check The V1 has read only mode.", "6.check The V1 has not disabled.", test);
		
//2.c) Select the third row of the table,. uncheck "enabled" in this row, uncheck "keep others" and press "Set selected properties"
		 MouseActivity.pressEnter(driver,  Utility.checkElementOr(driver, "Row.V3.xpath"));
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "Row.V3.xpath");
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "CheckBox.V3.Enabled.xpath");
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "CheckBox.keepothers.xpath");
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Setselectedproperties.xpath");
		 
//2.c) The first 2 fields get their normal appearance, the third field becomes light blue and the label "V3" is grey.
		 VerifyExcepted.getAttribute(driver, "Label.TopV3.xpath1", "class", "disabled", "7.Check The V3 has disabled.", "7.check the v3 has not disabled.", test);
//3) Click on "Properties per node" on the left		 
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Propertiespernode.xpath");
//3.a) In the second table select the row with index = 3 and Name = "V1". Check "RO"(Read-only)" , press "set selected properties"
		 MouseActivity.pressEnter(driver,  Utility.checkElementOr(driver, "Row.V1.3.xpath"));
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "Row.V1.3.xpath");
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "CheckBox.V1.3.RO.xpath1");
		 Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Setselectedproperties.xpath");
		 
		 try {
			Assert.assertEquals(true, Utility.isElementPresentByXpath(driver, "Input.Xtext.xpath"));
			Reporter.log("8.chech the inputBox readonly mode.",true);
			test.log(LogStatus.PASS, "8.chech the inputBox readonly mode.");
		} catch (Error e) {
			test.log(LogStatus.FAIL, "8.chech the inputBox readonly mode.");
			Reporter.log("Fail: 8.chech the inputBox not readonly mode.",true);
		}
		 
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.InsertRow.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "CheckBox.V1.1.Required.xpath");
		Thread.sleep(3000);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "CheckBox.keepothers.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Setselectedproperties.xpath");		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Checkrequiredfields.xpath");		 
		VerifyExcepted.getText(driver, "Label.FüllenSie.xpath", "Füll", "9.Check the Error msg visible.", "9.check check the error msg has not come.", test);
		 test.log(LogStatus.PASS, "=====================Finish========================================");
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