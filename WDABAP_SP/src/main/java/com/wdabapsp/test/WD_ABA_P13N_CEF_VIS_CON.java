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
import lib.Wait;

public class WD_ABA_P13N_CEF_VIS_CON {
	
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
	public void WBD_ABA_P13N_CEF_VIS_CON() throws Exception{
		        //Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
				MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
				Utility.ClickArrowButton(driver, 1);
				
				//switch to the coming frame
				driver.switchTo().frame("URLSPW-0");
				
		        //Click on the 'Bearbeiten' link.
				Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");
				
		        //In the line with label 'FORCED_STOP' choose 'Sichtbar' and in the line labelled 'REASON_FOR_STOP' choose 'Ausgeblendet'
				Utility.selectRadioButtonOrCheckbox(driver, Utility.getLocator("RadioButton.ForcedStop.Sichtbar"), "xpath");
				Wait.sleep(3000);
				Utility.selectRadioButtonOrCheckbox(driver, Utility.getLocator("RadioButton.Reason_For_Stop.Ausgeblendet"), "xpath");
				Wait.sleep(3000);
				test.log(LogStatus.PASS, "Selected required radio buttons in 'Settings for current configuration' page");
				Reporter.log("Selected required radio buttons in 'Settings for current configuration' page",true);
				
				//At the very bottom of the popup window press the 'Sichern und Schließen' button
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Sichernundschließen.xpath"), "xpath");
				driver.switchTo().defaultContent();
				Utility.waitForFrameToAvoilableAndSwitchToIt(driver,"URLSPW-1");
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Label.OK.xpath"), "xpath");
				driver.switchTo().defaultContent();
				assertTrue(Utility.isElementPresent(driver, Utility.getLocator("CheckBox.Boolean"), "xpath"));
				
				test.log(LogStatus.PASS, "Verified the elements under 'Kundenerweiterungsfelder' field");
				Reporter.log("Verified the elements under 'Kundenerweiterungsfelder' field",true);
				
				//Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
				MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
				Utility.ClickArrowButton(driver, 1);
				driver.switchTo().frame("URLSPW-0");
				
				//A popup window comes up. In the right half you can see two lines with little asterisks in front of the lines. The 'Zurücksetzen' buttons of these lines are enabled
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Asterisks.Element.Umsortieren"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Asterisks.Kundenerweiterungsfelder.Bearbeiten"), "xpath"));
				assertTrue(Utility.webElement(driver, Utility.getLocator("Button.Bearbeiten.Zurücksetzen.xpath"), "xpath").isEnabled());
				assertTrue(Utility.webElement(driver, Utility.getLocator("Button.Umsortieren.Zurücksetzen"), "xpath").isEnabled());
				
				//Press both 'Zurücksetzen' buttons
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Bearbeiten.Zurücksetzen.xpath"), "xpath");
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Umsortieren.Zurücksetzen"), "xpath");
				
				//The asterisks have gone and the 'Zurücksetzen' buttons are disabled now
				Utility.waitForelementToDisappear(driver, Utility.getLocator("Asterisks.Element.Umsortieren"), "xpath");
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("Asterisks.Element.Umsortieren"), "xpath"));
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("Asterisks.Kundenerweiterungsfelder.Bearbeiten"), "xpath"));
				//assertFalse(Utility.webElement(driver, Utility.getLocator("Button.Bearbeiten.Zurücksetzen.xpath"), "xpath").isEnabled());
				//assertFalse(Utility.webElement(driver, Utility.getLocator("Button.Umsortieren.Zurücksetzen"), "xpath").isEnabled());
				
				
			    //At the very bottom of the popup window press the 'Sichern und Schließen' button
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Sichernundschließen.xpath"), "xpath");
				driver.switchTo().defaultContent();
				driver.switchTo().frame("URLSPW-1");
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Label.OK.xpath"), "xpath");
				driver.switchTo().defaultContent();
				
				//The line labelled 'Boolean' has also disappeared
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("CheckBox.Boolean"), "xpath"));
				
				test.log(LogStatus.PASS, "Verified Boolean label not displaying under 'Kundenerweiterungsfelder' field, after resetting");
				Reporter.log("Verified Boolean checkbox not displaying under 'Kundenerweiterungsfelder' field, after resetting",true);
				
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
