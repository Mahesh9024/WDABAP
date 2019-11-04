package com.wdabapsp.test;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
import lib.MouseActivity;
import lib.Utility;
import lib.VerifyExcepted;

public class WD_ABA_OVS
{
	//static String url = "https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_OVS?sap-client=005&sap-language=en#";
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
		String pAppName="WDR_TEST_OVS";	
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"EN");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);
      }
	@Test
	public  void WD_WD_ABA_OVS() throws Exception {
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.SelectionView+ResultTable.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.valueHelp.xpath");
		
		//switching to the frame
		
		driver.switchTo().frame("URLSPW-0");
		
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Search.xpath.ovs");
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "FlightNumber.0788.xpath"));
		
		driver.switchTo().defaultContent();
		
		VerifyExcepted.getAttribute(driver, "Input.Depairport.xpath", "value", "FCO", "1.Check The value got selected.", "1.Fail the value has not selected.", test);
//2. Execute: Select testcase "Selection View + Result Table 2" and open the valuehelp for field "Flight Number". Use the value help in different variations
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.SelectionView+ResultTable2.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.valueHelp.xpath");
		
		driver.switchTo().frame("URLSPW-0");
		
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Search.xpath.ovs");
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "FlightNumber.0788.xpath"));
		driver.switchTo().defaultContent();
		VerifyExcepted.getAttribute(driver, "Input.FlightNumber.xpath", "value", "0788", "2.Check The value got selected.", "2.Fail the value has not selected.", test);
		 
//3. Execute: Select testcase "Immediate Value Display" and open the valuehelp for field "Dep. airport". Use the value help in different variations
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.ImmediateValueDisplay.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.valueHelp.xpath");
		driver.switchTo().frame("URLSPW-0");
		
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "FlightNumber.0788.xpath"));
	
		driver.switchTo().defaultContent();
		VerifyExcepted.getAttribute(driver, "Input.Depairport.xpath", "value", "FCO", "3.Check The value got selected.", "3.Fail the value has not selected.", test);
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.ResultTablewithMultipleSelection.xpath");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.valueHelp.xpath");
		
		driver.switchTo().frame("URLSPW-0");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Search.xpath.ovs");
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "FlightNumber.0788.xpath"));
		
		/*List<WebElement> tableRows = driver.findElements(By.xpath("//tr[@rt='1']"));
		Iterator< WebElement> itr=tableRows.iterator() ;
				{
			       while(itr.hasNext())
			       {
			    	   System.out.println("tabletr"+itr.next());
			       }
				}
		
				Actions actions = new Actions(driver);
				actions.click(tableRows.get(2)).keyDown(Keys.SHIFT).keyDown(Keys.SHIFT).keyDown(Keys.SHIFT).keyDown(Keys.SHIFT).				
				build().perform();
				
				actions.click(items.get(0)).keyDown(Keys.CONTROL)
				.click(items.get(2)).keyUp(Keys.CONTROL).build().perform();*/
    

		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Label.OK.xpath");
	    driver.switchTo().defaultContent();
		
		VerifyExcepted.getText(driver, "FlightNumber.0788.xpath", "0788", "4.Check the value got selected.", "4.Fail the value has not selected.", test);
//// open the valuehelp for the fields "Airline Carrier". Use the value help in different variations		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.DynamicValueSetinContext.xpath");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "ValueHelp.AirlineCarrier1.xpath");
		
		
		driver.switchTo().frame("URLSPW-0");
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "Cell.Carr.IK.xpath"));
		driver.switchTo().defaultContent();
		VerifyExcepted.getAttribute(driver, "Input.AirlineCarrier1.xpath", "value", "IK", "5.Check The value KI got selected.", "5.Fail the value has not selected.", test);
		
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.ObsoleteFunctions.xpath");
        Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.valueHelp.xpath");
		
		//switching to the frame
		
		driver.switchTo().frame("URLSPW-0");
		
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Search.xpath.ovs");
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "FlightNumber.0788.xpath"));
		
		driver.switchTo().defaultContent();  
		
		//Assert.assertEquals(true, false);
		
		VerifyExcepted.getAttribute(driver, "Input.Depairport.xpath", "value", "FCO", "6.Check The value got selected.", "6.Fail the value has not selected.", test);
		
//8. Execute: Select testcase "Include Structure" and open the valuehelp for the fields "Airline". Use the value help in different variations
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.IncludeStructure.xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.valueHelp.xpath");
		
		//switching to the frame
		
		driver.switchTo().frame("URLSPW-0");
		
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "Cell.Airline.xpath"));
		
		driver.switchTo().defaultContent(); 
		
		VerifyExcepted.getAttribute(driver, "Input.Airline.xpath", "value", "AA", "7.Check The value got selected.", "7.Fail the value has not selected.", test);
		
//9. Execute: Select testcase "Result Table is STRING_TABLE" and open the valuehelp for the fields "Airline". Use the value help in different variations	
	   Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.ResultTableIsSTRING_TABLE.xpath");
	   
	   Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.valueHelp.xpath");
		
		//switching to the frame
		
		driver.switchTo().frame("URLSPW-0");
		
		MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "Label.AA.xpath"));
		
		driver.switchTo().defaultContent();
		
		VerifyExcepted.getAttribute(driver, "Input.Airline.xpath", "value", "AA", "8.Check The value got selected.", "8.Fail the value has not selected.", test);
//11. Execute: Select testcase "Multiple Selection (Result Table is STRING_TABLE)" and open the valuehelp for the fields "Help for Airline". Use the value help in different variations
   //11 need to do. 
//12. Execute: Select testcase "Message with Empty Result Table" and open the valuehelp for the fields "Dep. airport". Enter  something in the field "Text wurde überschrieben: Stadt" to get an empty Result List
	   
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.MessagewithEmptyResultTable.xpath");
		
		  Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.valueHelp.xpath");			
		  //switching to the frame
		  
		  driver.switchTo().frame("URLSPW-0");
		  
		  
		  Utility.checkElementOr(driver, "Input.TextwurdeüberschriebenStadt.xpath").sendKeys("This is my Life..");
		  Utility.checkElementOr(driver, "Input.TextwurdeüberschriebenStadt.xpath").sendKeys(Keys.ENTER);
		  Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Search.xpath.ovs");
		  
		  VerifyExcepted.getText(driver, "ErrorMsg.SearchCriteria.xpath", "Die Ergebnistabelle ist leer", "9.Check the Error msg visible.", "9.Fail the Error msg not visible.", test);
		  
		  
		  Utility.clickOnElementUsingJavaScriptExecutor(driver, "Close.form.xpath");
		 driver.switchTo().defaultContent();
		
//13. Execute: Select testcase "F4 field is read-only"
		  Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.F4fieldisreadonly.xpath");
		  Thread.sleep(3000);
		  
		  VerifyExcepted.getAttribute(driver, "Input.Depairport.xpath1", "class", "readonly ", "10.Check the Field is  read only. ", "10.Fail the field is not read only.", test);
//14. Execute: Select testcase "Navigation in Phase 3" . Use the value help
		  Utility.clickOnElementUsingJavaScriptExecutor(driver, "Link.NavigationinPhase3.xpath");
		  Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.valueHelp.xpath");			
		  //switching to the frame
		  
		  driver.switchTo().frame("URLSPW-0");
		  
		  MouseActivity.pressEnter(driver, Utility.checkElementOr(driver, "Cell.Airline.xpath"));
			
			driver.switchTo().defaultContent();
			
			try {
				Assert.assertEquals(true, Utility.isElementPresentByXpath(driver, "Label.Navigationerfolgreich.xpath"));
				test.log(LogStatus.PASS, "11.check the Navigation erfolgreich msg displayed.");
			Reporter.log("11.check the Navigation erfolgreich msg displayed.",true);
			} catch (Error e) {
				test.log(LogStatus.FAIL, "11.Fail the Navigation erfolgreich msg has not  displayed.");
				Reporter.log("11.Fail the Navigation erfolgreich msg has not  displayed.",true);
			}
	
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
