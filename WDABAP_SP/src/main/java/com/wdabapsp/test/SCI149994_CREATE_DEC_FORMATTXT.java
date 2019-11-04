package com.wdabapsp.test;

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
import lib.DropDown;
import lib.MouseActivity;
import lib.Utility;
import lib.Wait;

public class SCI149994_CREATE_DEC_FORMATTXT {
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	//public String SrcPath = System.getProperty("user.dir") + "//target//" + this.getClass().getSimpleName();
	String StepDetail;

	@BeforeTest
	public void setUp() throws Exception {
		extent = Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(),
				"This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
		driver = BrowserFactory.typeBrowser();
		Thread.sleep(5000);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test.log(LogStatus.PASS, StepDetail);
		String pNamespace = "sap";
		String pAppName = "WDR_TEST_P13N";
		String pAdminMode = "sap-config-mode=x";
		loginPage.loginAsAdmin(pNamespace, pAppName, pAdminMode);
		StepDetail = "User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in", true);

	}

	@Test
	public void SCI149994_CREATE_DEC_FORMATTXT() throws Exception {
		Utility.resetPage(driver);
		Wait.sleep(3000);
		MouseActivity.rightClick(driver, "Label.TestSuiteforP13N.xpath");
		Utility.ClickArrowButton(driver, 1);
		Wait.sleep(3000);

		driver.switchTo().frame(0);
		Utility.checkElementOr(driver, "Link.Hinzufügen.xpath").click();
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);

		DropDown.selecFromDropDownByElement(driver, "Input.TypdesdekorativenElements.xpath", "Formatierter Text");
		Thread.sleep(2000);
		test.log(LogStatus.PASS, "Check 2.Formatierter Text successfully selected from the dropdown.");
		Utility.checkElementOr(driver, "Label.OK.xpath").click();
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		Utility.checkElementOr(driver, "TextArea.Text.xpath").sendKeys("Textabc");
		Utility.checkElementOr(driver, "Button.Sichernundschließen.xpath").click();

		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		Utility.checkElementOr(driver, "Radiobutton.KeinTransportauftrag.xpath").click();
		Thread.sleep(2000);
		Utility.checkElementOr(driver, "Label.OK.xpath").click();
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		try {
			Assert.assertEquals("Textabc", (Utility.checkElementOr(driver, "Label.Textabc.xpath")).getText());
			test.log(LogStatus.PASS, "Check 3.The Created link has opended the new tab successfully.");
			Reporter.log("Check 3.The Created link has opended the new tab successfully ", true);
			test.log(LogStatus.PASS, "=========================Finish====================================");
		} catch (Error e) {
			test.log(LogStatus.PASS, "Failed 3. The Created link has not opended the new tab successfully.");
			Reporter.log("Falied 3. The Created link has not opended the new tab successfully ", true);
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
