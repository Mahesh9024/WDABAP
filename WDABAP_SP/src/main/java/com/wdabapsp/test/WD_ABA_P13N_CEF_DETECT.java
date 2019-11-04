package com.wdabapsp.test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

public class WD_ABA_P13N_CEF_DETECT {

	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	String StepDetail;
	
	@BeforeMethod
	public void Setup() throws Exception{
		extent = Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName());
		driver = BrowserFactory.typeBrowser();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
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
	public void WBD_ABA_P13N_CEF_DETECT() throws Exception{
		//Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
				MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
				Utility.ClickArrowButton(driver, 1);
				
				//switch to the coming frame
				driver.switchTo().frame("URLSPW-0");
				
				//A popup window comes up. In the right half you can see a line called 'Kundenerweiterungsfelder' and a link called 'Bearbeiten'.
				assertTrue(Utility.isElementPresent(driver, Utility.getLocator("PopUP.EinstellungenFürAktuelleKonfiguration"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("PopUP.EinstellungenFürAktuelleKonfiguration"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Link.Bearbeiten.Kundenerweiterungsfelder"), "xpath"));
				test.log(LogStatus.PASS, "A popup window comes up. In the right half you can see a line called 'Kundenerweiterungsfelder' and a link called 'Bearbeiten'.");
				Reporter.log("A popup window comes up. In the right half you can see a line called 'Kundenerweiterungsfelder' and a link called 'Bearbeiten'.",true);
				
				//Click on the 'Abbrechen' button at the very bottom of the popup menu.
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Abbrechen.xpath"), "xpath");
				driver.switchTo().defaultContent();
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("PopUP.EinstellungenFürAktuelleKonfiguration"), "xpath"));
				test.log(LogStatus.PASS, "The popup window disappears");
				Reporter.log("The popup window disappears",true);
				
				//Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'
				MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
                Utility.ClickArrowButton(driver, 1);				
				//switch to the coming frame
				driver.switchTo().frame("URLSPW-0");
				
				//A popup window comes up. In the right half you can see a line called 'Kundenerweiterungsfelder' and a link called 'Bearbeiten'
				assertTrue(Utility.isElementPresent(driver, Utility.getLocator("PopUP.EinstellungenFürAktuelleKonfiguration"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("PopUP.EinstellungenFürAktuelleKonfiguration"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Link.Bearbeiten.Kundenerweiterungsfelder"), "xpath"));
				test.log(LogStatus.PASS, "A popup window comes up. In the right half you can see a line called 'Kundenerweiterungsfelder' and a link called 'Bearbeiten'.");
				Reporter.log("A popup window comes up. In the right half you can see a line called 'Kundenerweiterungsfelder' and a link called 'Bearbeiten'.",true);
				
				//Click on the 'Abbrechen' button at the very bottom of the popup menu
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Abbrechen.xpath"), "xpath");
				driver.switchTo().defaultContent();
				
				//The popup window disappears
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("PopUP.EinstellungenFürAktuelleKonfiguration"), "xpath"));
				test.log(LogStatus.PASS, "The popup window disappears");
				Reporter.log("The popup window disappears",true);
				
				

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
