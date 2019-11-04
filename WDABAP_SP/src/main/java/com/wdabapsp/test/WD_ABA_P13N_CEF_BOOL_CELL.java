package com.wdabapsp.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
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
import lib.DropDown;
import lib.MouseActivity;
import lib.Utility;
import lib.Wait;

public class WD_ABA_P13N_CEF_BOOL_CELL {
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	//public String SrcPath = System.getProperty("user.dir") + "//target//" + this.getClass().getSimpleName();
	String StepDetail;

	@BeforeTest
	public void setUp() throws Exception {
		extent = Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(),
				"This test case tests the behaviour of the Web Dynpro BOOL_CELL UI element");
		driver = BrowserFactory.typeBrowser();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test.log(LogStatus.PASS, StepDetail);
		String pNamespace = "sap";
		String pAppName = "demo_custom_extension_fields";
		String pAdminMode = "sap-config-mode=x";
		loginPage.loginAsAdmin(pNamespace, pAppName, pAdminMode);
		StepDetail = "User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in", true);
	}

	@Test
	public void WD_ABA_P13N_CEF_TYPE_CELL() {
		// •Open context menu for the header of the group box called
		// 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle
		// Konfiguration'.
		MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
		Utility.ClickArrowButton(driver, 1);
		// switch to the coming frame
		driver.switchTo().frame("URLSPW-0");
		// •Click on the 'Bearbeiten' link.
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");

		// •For the line labelled 'FORCED_STOP'
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "RadioButon.FORCED_STOP.Sichtbar.xpath");

		
			//DropDown.selecFromDropDownByValue(driver, "Dropdown.FORCED_STOP.xpath", "Automatisch");
		        if(DropDown.selecFromDropDownByValue1(driver, "Dropdown.FORCED_STOP.xpath", "Automatisch")==true){
				test.log(LogStatus.PASS, "Check 1.The  dropdown options  has  displyed.");
				Reporter.log("check 1.The dropdown options has displyed.", true);
				test.log(LogStatus.PASS, "====================Finish======================================");
		} else {
			test.log(LogStatus.FAIL, "Check 2.The  dropdown options  has not displyed.");
			Reporter.log("check 2.The dropdown options has not displyed.", true);
			
		}

		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Form.Abbrechen.xpath");
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
