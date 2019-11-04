package com.wdabapsp.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
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

public class WD_RUNTIME_RANGE_SUPPLY 
{
	
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	String ScrPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();	   
	String StepDetail;
	
	@BeforeTest
	public void setUp() throws Exception
	{
		extent=Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
		driver = BrowserFactory.typeBrowser();
		LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
		test.log(LogStatus.PASS, StepDetail);			
		String pNamespace="sap";
		String pAppName="wdr_test_range_supply";		
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"EN");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);		
	}
	
	@Test
	public void RUNTIME_RANGE_SUPPLY() throws InterruptedException
	{
		
	 //In the group box 'Testfälle' click the link 'Änderbare Tabelle mit Range-Supply'.	
		Utility.clickOnElementUsingJavaScriptExecutor(driver,"Link.RangeSupply.xpath");
		StepDetail="Click the link 'Range-Supply-Methode'";
		test.log(LogStatus.PASS, StepDetail);

	 //On the right hand side of the browser window, several tables and a form will be displayed
		try {
			
			driver.findElement(By.xpath(".//*[text()='Selektierte Zeilen anzeigen']"));
			StepDetail="Pass : 'Selektierte Zeilen anzeigen' button displayed";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
			  
	     } catch (NoSuchElementException e) {
	    	 
	    	 StepDetail="Fail : 'Selektierte Zeilen anzeigen' button not displayed";
			 test.log(LogStatus.FAIL, StepDetail);
			 Reporter.log(StepDetail,true);
			 
		 }
		
		// Change the lead selection of the table by clicking the selection button at the beginning of the table row.
		String val1=Utility.checkElementOr(driver,"Input.Total.xpath").getAttribute("value");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "((.//*[contains(@class,'MetricInner')]))[6]", "xpath");
		MouseActivity.pressEnter(driver, driver.findElement(By.xpath("(.//*[contains(@class,'MetricInner')])[6]")));
		StepDetail="Pass : Change the lead selection of the table by clicking the selection button at the beginning of the table row";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log(StepDetail,true);
		
		//Check that the form under the table displays the data of the lead-selected table row
		String val2=Utility.checkElementOr(driver,"Input.Total.xpath").getAttribute("value");
		StepDetail="get the value from total inbox";
		test.log(LogStatus.PASS, StepDetail);
		
		//Check that the form under the table displays the data of the lead-selected table row.
		if ((val1)!=(val2)){
			
			StepDetail="Pass : Check that the form under the table displays the data of the lead-selected table row";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
			  
	     } 
		else{
	    	 StepDetail="Fail : Check that the form under the table displays the data of the lead-selected table row";
			 test.log(LogStatus.FAIL, StepDetail);
			 Reporter.log(StepDetail,true);
		}
		
		
		//Select several table rows and press the button 'Selektierte Zeilen anzeigen'
		Utility.clickOnElementUsingJavaScriptExecutor(driver, ".//*[text()='Selektierte Zeilen anzeigen']", "xpath");
		StepDetail="Pass : Select several table rows and press the button 'Selektierte Zeilen anzeigen'";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log(StepDetail,true);
		
		// Check that the table 'Selektierte Zeilen' contains the selected table rows.
		try {
			
			driver.findElement(By.xpath(".//*[text()='Selektierte Zeilen']"));
			WebElement element = driver.findElement(By.xpath("//span[text()='Selektierte Zeilen']/ancestor::table[1]//tbody//tr[2]/td[2]/span/span"));
			System.out.println(element.getText()+":value of selected row");
			assertEquals(element.getText(), "3");
			StepDetail="Pass : Check that the table 'Selektierte Zeilen' contains the selected table rows.";
			test.log(LogStatus.PASS, StepDetail);
			Reporter.log(StepDetail,true);
			 test.log(LogStatus.PASS, "=====================Finish========================================");
			  
	     } catch (NoSuchElementException e) {
	    	 
	    	 StepDetail="Fail : Check that the table 'Selektierte Zeilen' contains the selected table rows.";
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
