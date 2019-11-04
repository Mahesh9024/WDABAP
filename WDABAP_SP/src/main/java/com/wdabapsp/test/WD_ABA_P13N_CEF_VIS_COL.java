package com.wdabapsp.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
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
import lib.MouseActivity;
import lib.Utility;

public class WD_ABA_P13N_CEF_VIS_COL
{
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	//public String SrcPath = System.getProperty("user.dir") + "//target//" + this.getClass().getSimpleName();
	String StepDetail;
	@BeforeTest
	public void setUp() throws Exception
	{
		 extent=Utility.initReportPath(this.getClass().getSimpleName());
		 test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
		 driver = BrowserFactory.typeBrowser();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
		 test.log(LogStatus.PASS, StepDetail);
		 String pNamespace="sap";
		 String pAppName="demo_custom_extension_fields";
		 String pAdminMode="sap-config-mode=x";
		 loginPage.loginAsAdmin(pNamespace, pAppName,pAdminMode);
		 StepDetail="User successfully Logged in";
		 test.log(LogStatus.PASS, StepDetail);
		 Reporter.log("User successfully Logged in",true);			 
	}
	@Test
	public void WD_WD_ABA_P13N_CEF_VIS_COL()
	{
	//•Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
		MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
		Utility.ClickArrowButton(driver, 1);
		
		//switch to the coming frame
		driver.switchTo().frame("URLSPW-0");
		
//•Click on the 'Bearbeiten' link.
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");
		
//•In the line with label 'FORCED_STOP' chosse 'Sichtbar' 
//and in the line labelled 'REASON_FOR_STOP' choose 'Ausgeblendet'
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "RadioButon.FORCED_STOP.Sichtbar.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "RadioButon.REASON_FOR_STOP.Ausgeblendet.xpath");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Sichernundschließen.xpath");
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-1");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Radiobutton.KeinTransportauftrag.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");
		driver.switchTo().defaultContent();
		
		System.out.println("check"+Utility.isElementPresentByXpath(driver,"CheckBox.Boolean.xpath"));

		try {
			Assert.assertEquals(true,Utility.isElementPresentByXpath(driver, "CheckBox.Boolean.xpath"));
			test.log(LogStatus.PASS, "check 1.The  checkbox field has visible");
			Reporter.log("check 1.The  checkbox field has visible.",true);			
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "Fail 2.The  checkbox field not  displyed.");
			Reporter.log("Fail 2.The  checkbox field not  displyed.",true);
		}	

		driver.navigate().refresh();
//•Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'
				
		MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
		Utility.ClickArrowButton(driver, 1);
		
		//switch to the coming frame
		driver.switchTo().frame("URLSPW-0");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Bearbeiten.Zurücksetzen.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Sichernundschließen.xpath");
		
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-1");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Radiobutton.KeinTransportauftrag.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");
		
		driver.switchTo().defaultContent();
		try {
			Assert.assertEquals(false,Utility.isElementPresentByXpath(driver, "CheckBox.Boolean.xpath"));
			test.log(LogStatus.PASS, "check 2.The  CheckBox field not visible");
			Reporter.log("check 2.The  CheckBox field not visible.",true);	
			test.log(LogStatus.PASS, "====================Finish======================================");
			
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "Fail 2.The  CheckBox field still  displyed.");
			Reporter.log("Fail 2.The  CheckBox field still  displyed.",true);
		}	
		
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			String ResScreenPath = Utility.getScreenshot(driver, Utility.reportPath);
			test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));
		}
		extent.endTest(test);
		extent.flush();
		driver.quit();
		BrowserFactory.killBrowserProcess(BrowserFactory.browserName);
	}


}
