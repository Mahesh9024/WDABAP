package com.wdabapsp.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import lib.Wait;

public class WD_ABA_P13N_CEF_UNDO_COL 
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
	public void WD_ABA_WD_P13N_CEF_UNDO_COL() throws InterruptedException, IOException
	{
	//•Open context menu for the header of the group box called 'Kundenerweiterungsfelder' and choose 'Einstellungen für aktuelle Konfiguration'.
		MouseActivity.rightClick(driver, "Label.Kundenerweiterungsfelder.xpath");
		Utility.ClickArrowButton(driver, 1);
		
		//switch to the coming frame
		driver.switchTo().frame("URLSPW-0");
		
//•Click on the 'Bearbeiten' link.
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.Bearbeiten.xpath");
		
//•For the line labelled 'WEIGHT_TOTAL' 
		//Utility.checkElementOr(driver, "RadioButon.WEIGHT_TOTAL.Sichtbar.xpath").click();
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//*[contains(text(),'DESCRIPTION')]/following::img[contains(@class,'urRImgOff')][position()=1]", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//*[contains(text(),'FL_STATE')]/following::img[contains(@class,'urRImgOff')][position()=1]", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//*[contains(text(),'FORCED_STOP')]/following::img[contains(@class,'urRImgOff')][position()=1]", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//*[contains(text(),'REASON_FOR_STOP')]/following::img[contains(@class,'urRImgOff')][position()=1]", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//*[contains(text(),'WEIGHT_TOTAL')]/following::img[contains(@class,'urRImgOff')][position()=2]", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//*[contains(text(),'WUNIT_TOTAL')]/following::img[contains(@class,'urRImgOff')][position()=2]", "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Sichernundschließen.xpath");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("URLSPW-1");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Radiobutton.KeinTransportauftrag.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");
		driver.switchTo().defaultContent();
		
		try {
			Assert.assertEquals(Utility.isElementPresent(driver, Utility.getLocator("CheckBox.Boolean.xpath"),"xpath"), true);
			test.log(LogStatus.PASS, "Check 1.The  boolean field has  displyed.");
			Reporter.log("check 1.The  boolean field has  displyed.",true);
			
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "Fail 1.The  boolean field not visible");
			Reporter.log("Fail 1.The  boolean field not visible.",true);
		}
		
		
		driver.navigate().refresh();
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
			test.log(LogStatus.PASS, "check 4.The  Boolean field not visible");
			Reporter.log("check 4.The  Boolean field not visible.",true);
			test.log(LogStatus.PASS, "====================Finish======================================");
			
		} catch (NoSuchElementException e) {
			test.log(LogStatus.FAIL, "Fail 4.The  Boolean field still  displyed.");
			Reporter.log("Fail 4.The  Boolean field still  displyed.",true);
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
