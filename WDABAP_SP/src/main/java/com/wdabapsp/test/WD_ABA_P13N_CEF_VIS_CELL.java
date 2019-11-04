package com.wdabapsp.test;

import static org.testng.Assert.assertEquals;

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
import lib.DropDown;
import lib.MouseActivity;
import lib.Utility;
import lib.Wait;

public class WD_ABA_P13N_CEF_VIS_CELL
{
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	public String SrcPath = System.getProperty("user.dir") + "//target//" + this.getClass().getSimpleName();
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
	public void WD_ABA_WD_P13N_CEF_VIS_CELL() throws InterruptedException
	{
	//•Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
		MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
		Utility.ClickArrowButton(driver, 1);
		
		//switch to the coming frame
		driver.switchTo().frame("URLSPW-0");
		
//•Click on the 'Bearbeiten' link.
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");
		
//•For the line labelled 'WEIGHT_TOTAL' 
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "RadioButon.WEIGHT_TOTAL.Sichtbar.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Sichernundschließen.xpath");
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-1");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Radiobutton.KeinTransportauftrag.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");
		driver.switchTo().defaultContent();
		
		try {
			assertEquals(Utility.isElementPresentByXpath(driver, "Input.Gepäckgew.xpath"), true);
			test.log(LogStatus.PASS, "Check 1.The  input field has  displyed.");
			Reporter.log("check 1.The  input field has  displyed.",true);
			
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "Fail 1.The  input field not visible");
			Reporter.log("Fail 1.The  input field not visible.",true);
		}
		
		driver.navigate().refresh();
		MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
		Utility.ClickArrowButton(driver, 1);
		
		//switch to the coming frame
		driver.switchTo().frame("URLSPW-0");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");
		
		DropDown.selecFromDropDownByValue(driver, "Dropdown.WEIGHT_TOTAL.xpath", "Textanzeige");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Sichernundschließen.xpath");
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-1");
			
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Radiobutton.KeinTransportauftrag.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");
	
	    driver.switchTo().defaultContent();
	    driver.navigate().refresh();
			
	
		
		try {
			System.out.println("state "+Utility.isElementPresentByXpath(driver, "Label.Gepäckgew.xpath"));
			Assert.assertEquals(true, Utility.isElementPresentByXpath(driver, "Label.Gepäckgew.xpath"));

			
			test.log(LogStatus.PASS, "check 3.The  Label field  visible");
			Reporter.log("check 3.The  Label field  visible.",true);			
			
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "Fail 3.The  Label field still  displyed.");
			Reporter.log("Fail 3.The  Label field still  displyed.",true);
		}
//removing the label
		
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
			Assert.assertEquals(false,Utility.isElementPresentByXpath(driver, "Label.Gepäckgew.xpath"));
			test.log(LogStatus.PASS, "check 4.The  input field not visible");
			Reporter.log("check 4.The  input field not visible.",true);	
			test.log(LogStatus.PASS, "====================Finish======================================");
			
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "Fail 4.The  input field still  displyed.");
			Reporter.log("Fail 4.The  input field still  displyed.",true);
		}	
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			String ResScreenPath = Utility.getScreenshot(driver, SrcPath);
			test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));
		}
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
}
