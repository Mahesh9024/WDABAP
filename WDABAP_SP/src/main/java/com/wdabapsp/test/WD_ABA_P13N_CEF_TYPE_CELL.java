package com.wdabapsp.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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

public class WD_ABA_P13N_CEF_TYPE_CELL 
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
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
	public void WD_ABA_WD_P13N_CEF_TYPE_CELL() 	{
	//•Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
		MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
		Utility.ClickArrowButton(driver, 1);
		
		//switch to the coming frame
		driver.switchTo().frame("URLSPW-0");
		
//•Click on the 'Bearbeiten' link.
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");
	
		
		
//•For the line labelled 'WEIGHT_TOTAL' 

		Utility.clickOnElementUsingJavaScriptExecutor(driver, "RadioButon.DESCRIPTION.Sichtbar.xpath");
		
		try {
			if
			(DropDown.selecFromDropDownByValue1(driver, "Dropdown.DESCRIPTION.xpath", "Automatisch")){
				test.log(LogStatus.PASS, "Check 1.The  dropdown options  has  displyed.");
				Reporter.log("check 1.The  boolean field has  displyed.",true);
			}
			else{
				  test.log(LogStatus.FAIL, "Check 2.The  dropdown options  has not displyed.");
					Reporter.log("check 1.The  dropdown options has not displyed.",true);
			}
			//DropDown.selecFromDropDownByElement(driver, "Dropdown.DESCRIPTION.xpath", "Dropdown-Box");
			//Wait.sleep(2000);
			if
			(DropDown.selecFromDropDownByValue1(driver, "Dropdown.DESCRIPTION.xpath", "Bild")){
				test.log(LogStatus.PASS, "Check 1.The  dropdown options  has  displyed.");
				Reporter.log("check 1.The  boolean field has  displyed.",true);
			}
			else{
				  test.log(LogStatus.FAIL, "Check 2.The  dropdown options  has not displyed.");
					Reporter.log("check 1.The  dropdown options has not displyed.",true);
			}
			if
			(DropDown.selecFromDropDownByValue1(driver, "Dropdown.DESCRIPTION.xpath", "Eingabefeld")){
				test.log(LogStatus.PASS, "Check 1.The  dropdown options  has  displyed.");
				Reporter.log("check 1.The  boolean field has  displyed.",true);
			}
			else{
				  test.log(LogStatus.FAIL, "Check 2.The  dropdown options  has not displyed.");
					Reporter.log("check 1.The  dropdown options has not displyed.",true);
			}
			if
			(DropDown.selecFromDropDownByValue1(driver, "Dropdown.DESCRIPTION.xpath", "Textanzeige")){
				test.log(LogStatus.PASS, "Check 1.The  dropdown options  has  displyed.");
				Reporter.log("check 1.The  boolean field has  displyed.",true);
			}
			else{
				  test.log(LogStatus.FAIL, "Check 2.The  dropdown options  has not displyed.");
					Reporter.log("check 1.The  dropdown options has not displyed.",true);
			}
			test.log(LogStatus.PASS, "Check 1.The  dropdown options  has  displyed.");
			Reporter.log("check 1.The  boolean field has  displyed.",true);
			
		   } catch (NoSuchElementException e1) 
		  {
			   test.log(LogStatus.FAIL, "Check 2.The  dropdown options  has not displyed.");
				Reporter.log("check 1.The  dropdown options has not displyed.",true);
		}

		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Form.Abbrechen.xpath");
		test.log(LogStatus.PASS, "====================Finish======================================");
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
