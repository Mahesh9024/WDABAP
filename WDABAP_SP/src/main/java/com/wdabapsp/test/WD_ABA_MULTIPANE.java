package com.wdabapsp.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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
import lib.Wait;

public class WD_ABA_MULTIPANE 
{
	//static String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_UI_ELEMENTS?&sap-client=005&sap-language=de#";

    ExtentReports extent;
ExtentTest test;
public WebDriver driver;
//String ScrPath=System.getProperty("user.dir")+"//target//"+this.getClass().getSimpleName();	   
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
	String pAppName="WDR_TEST_UI_ELEMENTS";	
	String pUIElementToStart="";
	loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
	StepDetail="User successfully Logged in";
	test.log(LogStatus.PASS, StepDetail);
	Reporter.log("User successfully Logged in",true);
  }
	@Test

	public  void WD_WD_ABA_MULTIPANE() throws Exception {	
		
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "Standard.layout.xpath"));
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "Label.MultiPane.xpath"));
		
//1. Change the 'colCount' property from 2 through 5. Reset to 1.
		
		for(int i=2;i<=5;i++)
		{
			//System.out.println("i"+i);		
			Utility.checkElementOr(driver, "Input.colCount.xpath").clear();
		    Utility.checkElementOr(driver, "Input.colCount.xpath").sendKeys(String.valueOf(i));
		    Utility.checkElementOr(driver, "Input.colCount.xpath").sendKeys(Keys.ENTER);
		 
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Änderungen durchführen']", "xpath");
		Wait.sleep(1000);
		
		 List<WebElement> al=new ArrayList<WebElement>();
		 al=driver.findElements(By.xpath("//*[text()='MultiPane']/following::table[5]/child::*[@class='urLinStd']/child::tr[1]/child::td"));
//1. The five panes are rearranged depending on the column count set from 2 columns through 5.
        if(al.size()==i)
        {
       
        	Reporter.log( i+1+"a.check the panes are arranged depending on the colomn .",true);
        	test.log(LogStatus.PASS, i+1+"a.check the panes are arranged depending on the colomn .");
        }
        else
        {
        	Reporter.log(i+1+"a.Fail the panes are not  arranged depending on the colomn .",true);
        	test.log(LogStatus.FAIL, i+1+"a.Fail the panes are not  arranged depending on the colomn .");
        }
       }
		Utility.checkElementOr(driver, "Input.colCount.xpath").clear();
		Utility.checkElementOr(driver, "Input.colCount.xpath").sendKeys("1");
		Utility.checkElementOr(driver, "Input.colCount.xpath").sendKeys(Keys.ENTER);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Änderungen durchführen']", "xpath");
//2. Change the 'firstVisiblePane' property from 1 to 5. Reset to 0.	
		
		int count=5;
		for(int j=1;j<=5;j++)
		{
			count=count-1;
		Utility.checkElementOr(driver, "Input.firstVisiblePane.xpath").clear();
		Utility.checkElementOr(driver, "Input.firstVisiblePane.xpath").sendKeys(String.valueOf(j));
		Utility.checkElementOr(driver, "Input.firstVisiblePane.xpath").sendKeys(Keys.ENTER);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Änderungen durchführen']", "xpath");
		Wait.sleep(1000);
				
		List<WebElement> all=new ArrayList<WebElement>();
		all=driver.findElements(By.xpath("//*[text()='Einstellungen von MultiPane']/preceding::div[@class='lsHTMLContainer']"));
////2. One by one the panes disappear until with 5 nothing is displayed		
		//System.out.println("count="+count+"size"+all.size());
		if(all.size()==count)
		{
		 Reporter.log(j+5+"b.Check the One by one the panes disappered.",true); 
		 test.log(LogStatus.PASS, j+5+"b.Check the One by one the panes disappered.");
		}
		else
		{
			 Reporter.log(j+5+"b.Fail the One by one the panes are not disappered.",true);
			 test.log(LogStatus.FAIL, j+5+"b.Fail the One by one the panes are not disappered.");
		}	
		
		}
		Utility.checkElementOr(driver, "Input.firstVisiblePane.xpath").clear();
		Utility.checkElementOr(driver, "Input.firstVisiblePane.xpath").sendKeys("0");
		Utility.checkElementOr(driver, "Input.firstVisiblePane.xpath").sendKeys(Keys.ENTER);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Änderungen durchführen']", "xpath");
		Wait.sleep(1000);
//3. Change the 'visible' property to 'none'.
		DropDown.selecFromDropDownByValue(driver, "DropDown.visible.xpath", "none");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Änderungen durchführen']", "xpath");
		Wait.sleep(1000);
	
//3. The sample panes disappear. The occupied space is deleted.	
	
	try {
		Assert.assertEquals(false, Utility.isElementPresentByXpath(driver, "Check.Visible.xpath"));
		Reporter.log("10.check the Element is not visible.",true);
		 test.log(LogStatus.PASS, "10.check the Element is not visible.");
		
	} catch (Error e) {
		Reporter.log("10.Fail the Element is still visible.",true);
		 test.log(LogStatus.FAIL, "10.Fail the Element is still visible.");
	}
	
	DropDown.selecFromDropDownByValue(driver, "DropDown.visible.xpath", "visible");
	Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Änderungen durchführen']", "xpath");
	
//5. Change the 'rowCount' property from 0 through 5. Reset to -1.	
	int cou=0;
	for(int j=1;j<=5;j++)
	{
	Utility.checkElementOr(driver, "Input.rowCount.xpath").clear();
	Utility.checkElementOr(driver, "Input.rowCount.xpath").sendKeys(String.valueOf(j));
	Utility.checkElementOr(driver, "Input.rowCount.xpath").sendKeys(Keys.ENTER);
	Utility.clickOnElementUsingJavaScriptExecutor(driver, "//span[text()='Änderungen durchführen']", "xpath");
	Wait.sleep(2000);
	cou=cou+1;		
	List<WebElement> all=new ArrayList<WebElement>();
	all=driver.findElements(By.xpath("//*[text()='Einstellungen von MultiPane']/preceding::div[@class='lsHTMLContainer']"));
////2. One by one the panes disappear until with 5 nothing is displayed
	if(all.size()==cou)
	{
		test.log(LogStatus.PASS, "c.Check the One by one the panes appered.");
	 Reporter.log(j+12+"c.Check the One by one the panes appered.",true);  	
	}
	else
	{
		test.log(LogStatus.PASS, "c.Fail the One by one the panes are not appered.");
		 Reporter.log(j+3+"c.Fail the One by one the panes are not appered.",true);
	}	
	
	}
	Utility.checkElementOr(driver, "Input.rowCount.xpath").clear();
	Utility.checkElementOr(driver, "Input.rowCount.xpath").sendKeys("0");
	Utility.checkElementOr(driver, "Input.rowCount.xpath").sendKeys(Keys.ENTER);
	
	}
	
	@AfterMethod
	   public void tearDown(ITestResult result) throws Exception
	   {
	   if(result.getStatus()==ITestResult.FAILURE)
	   {
		     String ResScreenPath=Utility.getScreenshot(driver,Utility.reportPath);
			 test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));
	   }
	    extent.endTest(test);
		extent.flush();
		driver.quit();	
		BrowserFactory.killBrowserProcess(BrowserFactory.browserName);
	   }
}
