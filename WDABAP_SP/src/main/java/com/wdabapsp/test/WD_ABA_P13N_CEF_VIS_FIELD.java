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
import lib.DropDown;
import lib.MouseActivity;
import lib.Utility;

public class WD_ABA_P13N_CEF_VIS_FIELD {
	
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
	public void WBD_ABA_P13N_CEF_VIS_FIELD() throws Exception{
		//Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
				MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
				Utility.ClickArrowButton(driver, 1);
				
				//switch to the coming frame
				driver.switchTo().frame("URLSPW-0");
				
		//Click on the 'Bearbeiten' link.
				Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");
				
		//For the line labelled 'REASON_FOR_STOP' choose radio button 'Sichtbar' and press the 'Sichern und Schließen' button at the very bottom of the popup window. In the upcoming popup window press the 'OK' button
				Utility.selectRadioButtonOrCheckbox(driver, Utility.getLocator("RadioButton.Reason_For_Stop.Sichtbar"), "xpath");
				test.log(LogStatus.PASS, "Selected required radio buttons in 'Settings for current configuration' page");
				Reporter.log("Selected required radio buttons in 'Settings for current configuration' page",true);
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Sichernundschließen.xpath"), "xpath");
				driver.switchTo().defaultContent();
				Utility.waitForFrameToAvoilableAndSwitchToIt(driver,"URLSPW-1");
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Label.OK.xpath"), "xpath");
				driver.switchTo().defaultContent();	
				try{
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("DropDown.GrfürStop"), "xpath"));
				test.log(LogStatus.PASS, "Verified the elements under 'Kundenerweiterungsfelder' field");
				Reporter.log("Verified the elements under 'Kundenerweiterungsfelder' field",true);
				}
				catch(Error e){
					test.log(LogStatus.FAIL, "Verified the elements under 'Kundenerweiterungsfelder' field");
					Reporter.log("Fail: Verified the elements under 'Kundenerweiterungsfelder' field",true);
					
				}
				catch(Exception e){
					test.log(LogStatus.FAIL, "Verified the elements under 'Kundenerweiterungsfelder' field");
					Reporter.log("Fail: Verified the elements under 'Kundenerweiterungsfelder' field",true);
					
				}
				
				//Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
				MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
				Utility.ClickArrowButton(driver, 1);
				driver.switchTo().frame("URLSPW-0");
				
				//A popup window comes up. In the right half you can see a line called 'Kundenerweiterungsfelder'. There is a little asterisk in front of the line
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Asterisks.Kundenerweiterungsfelder.Bearbeiten"), "xpath"));
				test.log(LogStatus.PASS, "There is a little asterisk in front of the 'Kundenerweiterungsfelder' line");
				Reporter.log("There is a little asterisk in front of the 'Kundenerweiterungsfelder' line",true);
				
				//Click on the 'Bearbeiten' link.
				Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");
				
				//In the enabled dropDown box choose the entry 'Gruppe von Auswahlknöpfen' and press the 'Sichern und Schließen' button at the very bottom of the popup window
				DropDown.selecFromDropDownByValue(driver, "DropDown.ReasonForStop", "Gruppe von Auswahlknöpfen");
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Sichernundschließen.xpath"), "xpath");
				driver.switchTo().defaultContent();
				Utility.waitForFrameToAvoilableAndSwitchToIt(driver,"URLSPW-1");
				
				//In the upcoming popup window press the 'OK' button
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Label.OK.xpath"), "xpath");
				driver.switchTo().defaultContent();	
				driver.navigate().refresh();
				
				//check the popup windows disappear. The 'Gr. für Stop' line remains, but now there are lots of radio buttons
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.keineZwischenlandung"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.SchweresUnwetter"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.ZuwenigTreibstoff"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.Maschinenschaden"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.MenschlichesVersagen"), "xpath"));
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.FehlerVerkehrsleitsystem"), "xpath"));
				test.log(LogStatus.PASS, "All the radio buttons are displaying");
				Reporter.log("All the radio buttons are displaying",true);
				
				//Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
				MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
				Utility.ClickArrowButton(driver, 1);
				driver.switchTo().frame("URLSPW-0");
				assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Asterisks.Kundenerweiterungsfelder.Bearbeiten"), "xpath"));
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Bearbeiten.Zurücksetzen.xpath"), "xpath");
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Sichernundschließen.xpath"), "xpath");
				driver.switchTo().defaultContent();
				Utility.waitForFrameToAvoilableAndSwitchToIt(driver,"URLSPW-1");
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Label.OK.xpath"), "xpath");
				driver.switchTo().defaultContent();	
				
				//check the popup windows disappear. The additional line has disappeared now
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("DropDown.GrfürStop"), "xpath"));
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.keineZwischenlandung"), "xpath"));
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.SchweresUnwetter"), "xpath"));
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.ZuwenigTreibstoff"), "xpath"));
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.Maschinenschaden"), "xpath"));
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.MenschlichesVersagen"), "xpath"));
				assertFalse(Utility.isElementVisible(driver, Utility.getLocator("RadioButton.FehlerVerkehrsleitsystem"), "xpath"));
				test.log(LogStatus.PASS, "The additional line and radio buttons has disappeared now");
				Reporter.log("The additional line and radio buttons has disappeared now",true);
				
				
				
				
				
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
