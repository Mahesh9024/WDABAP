package com.wdabapsp.test;

import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wdabapsp.page.LoginPage;

import lib.BrowserFactory;
import lib.Utility;
import lib.Wait;

public class WDA_DECFLOAT_1 
{
	
		    ExtentReports extent;
			ExtentTest test;
			public WebDriver driver;
			String ScrPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();	   
			String StepDetail;
			
		    @BeforeMethod
			public void Setup() throws InterruptedException, IOException
			{
			    extent=Utility.initReportPath(this.getClass().getSimpleName());
				test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
				driver = BrowserFactory.typeBrowser();
				LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
				test.log(LogStatus.PASS, StepDetail);			
				String pNamespace="sap";
				String pAppName="wdr_test_decfloat";			
				String pUIElementToStart="";
				loginPage.login(pNamespace, pAppName,pUIElementToStart,"EN");
				StepDetail="User successfully Logged in";
				test.log(LogStatus.PASS, StepDetail);
				Reporter.log("User successfully Logged in",true);

	}

	@Test
	public void DECFLOAT_1() throws Exception {
		
		
		//A browser window opens.
		StepDetail="A browser window opens";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log(StepDetail,true);
		
		
		// Enter "-12E4" into each of the fields, and press the button "Schlapp".
		Utility.checkElementOr(driver,"Input.Dcf16.xpath").clear();
		Utility.checkElementOr(driver,"Input.Dcf16.xpath").sendKeys("-12E4");
		StepDetail="Enter -12E4 into first field.";
		test.log(LogStatus.PASS, StepDetail);
		
		Utility.checkElementOr(driver,"Input.Dcf34.xpath").clear();
		Utility.checkElementOr(driver,"Input.Dcf34.xpath").sendKeys("-12E4");
		StepDetail="Enter -12E4 into second field";
		test.log(LogStatus.PASS, StepDetail);
		
		Utility.checkElementOr(driver,"Input.Decfloat.xpath").clear();
		Utility.checkElementOr(driver,"Input.Decfloat.xpath").sendKeys("-12E4");
		StepDetail="Enter -12E4 into third field";
		test.log(LogStatus.PASS, StepDetail);
		
		
		Utility.checkElementOr(driver,"Input.Darstellung.xpath").clear();
		Utility.checkElementOr(driver,"Input.Darstellung.xpath").sendKeys("-12E4");
		StepDetail="Enter -12E4 into fourth field";
		test.log(LogStatus.PASS, StepDetail);
		
		Utility.checkElementOr(driver,"Input.NULLEN.xpath").clear();
		Utility.checkElementOr(driver,"Input.NULLEN.xpath").sendKeys("-12E4");
		StepDetail="Enter -12E4 into fifth field";
		test.log(LogStatus.PASS, StepDetail);
		
		//Click on the button Schlapp
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.Schlapp.xpath");
		StepDetail="Click on the button Schlapp";
		test.log(LogStatus.PASS, StepDetail);
		
		//The value in the first 3 fields is changed to -120.000 
		String[] frameStyle={Utility.checkElementOr(driver,"Input.Dcf16.xpath").getAttribute("value"),
				Utility.checkElementOr(driver,"Input.Dcf34.xpath").getAttribute("value"),
				Utility.checkElementOr(driver,"Input.Decfloat.xpath").getAttribute("value")};
		for(int i=0;i<frameStyle.length;i++){
		if (frameStyle[i].contains("120"))
		{
			StepDetail="The value in the "+(i+1)+" field is changed to -120.000 ";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
		}
		else
		{
			StepDetail="The value in the "+(i+1)+" field has not changed to -120.000";
			test.log(LogStatus.FAIL, StepDetail);
			Reporter.log(StepDetail,true);
		}}	
		
		
		// Enter "1.2345,6" into the first field. Press "Schlapp".
		Utility.checkElementOr(driver,"Input.Dcf16.xpath").clear();
		Utility.checkElementOr(driver,"Input.Dcf16.xpath").sendKeys("1.2345,6");
		Utility.checkElementOr(driver,"Input.Dcf16.xpath").sendKeys(Keys.ENTER);
		StepDetail="Enter 1.2345,6 into first field.";
		test.log(LogStatus.PASS, StepDetail);
		
		//Click on the button Schlapp	
		Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.Schlapp.xpath");
		Wait.sleep(2000);
		StepDetail="Click on the button Schlapp";
		test.log(LogStatus.PASS, StepDetail);

		// An error message should be displayed
		if (driver.getPageSource().contains("Character '.' is at an invalid position"))
			
		{
			StepDetail="An error message should be displayed";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
			 test.log(LogStatus.PASS, "=====================Finish========================================");
		}
		else
		{
			StepDetail="Fail: An error message should be displayed";
			test.log(LogStatus.FAIL, StepDetail);
			Reporter.log(StepDetail,true);
		}

	}

	@AfterMethod
	public void afterTest() {
		StepDetail="User successfully Logged off";
		test.log(LogStatus.PASS, StepDetail);
	    extent.endTest(test);
		extent.flush();
		driver.quit();		
	}


}
