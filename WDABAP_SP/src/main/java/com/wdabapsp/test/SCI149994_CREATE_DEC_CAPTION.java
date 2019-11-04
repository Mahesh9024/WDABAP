package com.wdabapsp.test;

import org.openqa.selenium.Keys;
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

public class SCI149994_CREATE_DEC_CAPTION 
{
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
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		String pNamespace = "sap";
		String pAppName = "WDR_TEST_P13N";
		String pAdminMode = "sap-config-mode=x";
		loginPage.loginAsAdmin(pNamespace, pAppName, pAdminMode); //loginAsAdmin
		StepDetail = "User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("1.User successfully Logged in", true);

	}

	@Test
	public void SCI149994_WD_CREATE_DEC_CAPTION() throws Exception {
		//Utility.resetPage(driver);
		MouseActivity.rightClick(driver, "Label.TestSuiteforP13N.xpath");
		Utility.ClickArrowButton(driver, 1);

		driver.switchTo().frame("URLSPW-0");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Hinzufügen.xpath");

		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-1");

		//DropDown.selecFromDropDownByValue(driver, "Input.TypdesdekorativenElements.xpath", "Überschrift");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Überschrift']/preceding-sibling::img", "xpath");
		test.log(LogStatus.PASS, "Check 2.Überschrift Selected from the dropdown.");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");

		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-0");
		Utility.checkElementOr(driver, "Input.Beschriftung.xpath").sendKeys("Textabc");
		Utility.checkElementOr(driver, "Input.Beschriftung.xpath").sendKeys(Keys.ENTER);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Sichernundschließen.xpath");

		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-1");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Radiobutton.KeinTransportauftrag.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");
		Wait.sleep(3000);
		driver.switchTo().defaultContent();
		try {
			Assert.assertEquals("Textabc", (Utility.checkElementOr(driver, "Label.Textabc.xpath")).getText());
			test.log(LogStatus.PASS, "Check 3.The entered text is displaying in page.");
			Reporter.log("Check 3.The entered text is displaying in page successfully ", true);
			test.log(LogStatus.PASS, "=========================Finish====================================");
		} catch (Error e) {
			test.log(LogStatus.FAIL, "Failed 3. The entered text is not displaying in page.");
			Reporter.log("Falied 3 The entered text is not displaying in page.", true);
		}

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception 
	{
		
		if (result.getStatus() == ITestResult.FAILURE) 
		{
			String ResScreenPath = Utility.getScreenshot(driver, Utility.reportPath);
			test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));
		}
		extent.endTest(test);
		extent.flush();
	    driver.quit();
	   
	    
	}


}
