package com.wdabapsp.test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
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
import lib.Wait;

public class WD_ABA_P13N_CEF_BOOL_FIELD {
	
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	String StepDetail;
	
	@BeforeTest
	public void setUp() throws Exception{
		
		extent=Utility.initReportPath(this.getClass().getSimpleName());
		test=extent.startTest(this.getClass().getSimpleName(), "This test case tests the list of possible cell editors for 'WDY_BOOLEAN' typed custom extension fields that extend a table.");
		driver=BrowserFactory.typeBrowser();
		LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);
		test.log(LogStatus.PASS, StepDetail);
		String pNamespace="sap";
		String pAppName="demo_custom_extension_fields";
		String pAdminMode="sap-config-mode=x";
		loginPage.loginAsAdmin(pNamespace, pAppName, pAdminMode);
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);
		
	}
	
	@Test
	public void WBD_ABA_P13N_CEF_BOOL_FIELD() throws Exception{
		        //Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
				MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
				Utility.ClickArrowButton(driver, 1);
				
				//switch to the coming frame
				driver.switchTo().frame("URLSPW-0");
				
		        //Click on the 'Bearbeiten' link.
				Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");
				Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.AlleKundenerweiterungsfelderZurücksetzen");
				Wait.sleep(3000);
				
				//For the line labelled 'FORCED_STOP' choose 'Sichtbar'. Then open the dropDown box and check the result
				Utility.selectRadioButtonOrCheckbox(driver, Utility.getLocator("RadioButton.ForcedStop.Sichtbar"), "xpath");
				Wait.sleep(3000);
				List<WebElement> ele = driver.findElements(By.xpath("//div[@class='lsListbox__value']"));
				assertTrue(ele.size()<=4);
				List<String> options = new ArrayList<String>();
				for(WebElement element:ele){
					options.add(element.getAttribute("innerHTML"));
				}
				assertTrue(options.contains("Automatisch"));
				assertFalse(options.contains("Gruppe von Auswahlknöpfen"));
				assertFalse(options.contains("Dropdown-Box"));
				assertFalse(options.contains("Texteingabefeld"));
				assertFalse(options.contains("Bild"));
				assertFalse(options.contains("Eingabefeld"));
				assertFalse(options.contains("Textanzeige"));
				test.log(LogStatus.PASS, "Automatisch option is displayed in the dropdown");
				Reporter.log("Automatisch option is displayed in the dropdown",true);	
				
				
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("DropDown.ForcedStop"), "xpath");
				assertTrue(Utility.isElementVisible(driver, "//div[text()='Automatisch']", "xpath"));
				assertFalse(Utility.isElementVisible(driver, "//div[text()='Texteingabefeld']", "xpath"));
				assertFalse(Utility.isElementVisible(driver, "//div[text()='Bild']", "xpath"));
				assertFalse(Utility.isElementVisible(driver, "//div[text()='Textanzeige']", "xpath"));
				test.log(LogStatus.PASS, "Only Automatisch option is displayed in the dropdown");
				Reporter.log("Only Automatisch option is displayed in the dropdown",true);
				
				//At the very bottom of the popup window press the 'Abbrechen' button verify popup window disappears
				Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Form.Abbrechen.xpath"), "xpath");
				Utility.waitForelementToDisappear(driver, "//div[text()='Einstellungen für aktuelle Konfiguration']", "xpath");
				assertFalse(Utility.isElementVisible(driver, "//div[text()='Einstellungen für aktuelle Konfiguration']", "xpath"));
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
