package com.wdabapsp.test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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
import lib.Utility;
import lib.VerifyExcepted;
import lib.Wait;

public class WD_ABA_ERROR_EXPLANATION {
	
	ExtentReports extent;
	ExtentTest test;
	public WebDriver driver;
	String StepDetail;
	
	@BeforeMethod
	public void Setup() throws InterruptedException, IOException{
		extent = Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the display of error messages close to the involved UI Element using explanations.");
		driver = BrowserFactory.typeBrowser();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test.log(LogStatus.PASS, StepDetail);
		String pNamespace="sap";
		String pAppName="wdr_test_error_explanatio";
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE&wduiguideline=GL11");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);
	}
	
	@Test()
	
	public void ABA_ERROR_EXPLANATION() throws Exception{
	    
		//InputFields
		//Type "Joke" into the InputField with the label "Datum (keine Explanation)" and press enter and check th error message
		Utility.enterText(driver, Utility.getLocator("Input.Datum.keine.Explanation.Xpath"), "Joke", "xpath");
		Utility.webElement(driver, Utility.getLocator("Input.Datum.keine.Explanation.Xpath"), "xpath").sendKeys(Keys.ENTER);
		Utility.waitForelement(driver, Utility.getLocator("ErrorMsg.Area.Xpath"), "xpath");
		VerifyExcepted.getText(Utility.webElement(driver, Utility.getLocator("ErrorMsg.Area.Xpath"),"xpath"), "Joke kann nicht als Datum gelesen werden. Geben Sie das Datum im Format", "Pass: error message 'Joke' ist kein Datum appears in the MessageArea", "Fail: error message 'Joke' ist kein Datum not appears in the MessageArea", test);
		
		//Click on the error message link
		Utility.webElement(driver, Utility.getLocator("ErrorMsg.Area.Xpath"),"xpath").click();
		VerifyExcepted.getAttribute(Utility.webElement(driver, Utility.getLocator("Input.Datum.keine.Explanation.Xpath1"),"xpath"), "class", "lsField--focus", "Pass: input field Datum (keine Explanation) is focused", "Fail: input field Datum (keine Explanation) is not focused", test);
		VerifyExcepted.getText1(Utility.webElement(driver, Utility.getLocator("Data.TipText.Xpath"),"xpath"), "Joke kann nicht als Datum gelesen werden. Geben Sie das Datum im Format", "Pass: the explanation with the error message from the message area is shown at the input field", "Fail: the explanation with the error message from the message area is shown at the input field", test);
		Utility.clickSomewhereOnWebpage(driver, 250, 120);
		
		// Check that the explanation disappears
		assertFalse(Utility.isElementVisible(driver, Utility.getLocator("Data.TipText.Xpath"), "xpath"));
		assertFalse(Utility.webElement(driver, Utility.getLocator("Data.TipText.Xpath"),"xpath").isDisplayed());
		
		//Click on the lable "Datum (keine Explanation)" and verify error message
		Utility.webElement(driver, Utility.getLocator("Label.Datum.keine.Explanation.Xpath"),"xpath").click();
		VerifyExcepted.getText1(Utility.webElement(driver, Utility.getLocator("Data.TipText.Xpath"),"xpath"), "Joke kann nicht als Datum gelesen werden. Geben Sie das Datum im Format", "Pass: the explanation with the error message from the message area is shown at the input field", "Fail: the explanation with the error message from the message area is shown at the input field", test);
		
		//Clear the contents of  the Input Field  "Datum (keine Explanation)" and press enter and verify error message diappears
		Utility.webElement(driver, Utility.getLocator("Input.Datum.keine.Explanation.Xpath"),"xpath").clear();
		Utility.webElement(driver, Utility.getLocator("Input.Datum.keine.Explanation.Xpath"),"xpath").sendKeys(Keys.ENTER);
		Utility.waitForelement(driver, Utility.getLocator("Label.Datum.keine.Explanation.Xpath"), "xpath");
		Utility.webElement(driver, Utility.getLocator("Label.Datum.keine.Explanation.Xpath"),"xpath").click();
		Utility.waitForelementToDisappear(driver, Utility.getLocator("ErrorMsg.Area.Xpath"), "xpath");
		assertFalse(Utility.isElementPresent(driver, Utility.getLocator("ErrorMsg.Area.Xpath"),"xpath"));
		assertFalse(Utility.isElementPresent(driver, Utility.getLocator("Data.TipText.Xpath"),"xpath"));
		
		//Click on the label "Zeit (mit Explanation)" and verify explaination appears at input field
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.webElement(driver, Utility.getLocator("Label.Zeit.mitExplanation.Xpath"),"xpath"));
		assertTrue(Utility.isElementVisible(driver, "//div[text()='Dies ist ein Eingabefeld für eine Zeitangabe.']", "xpath"));
		
		//Enter "Blablabla" into the InputField "Zeit (mit Explanation)" and press enter and verify the error message "Die Zeitangabe ist ungültig"
		Utility.enterText(driver, Utility.getLocator("Input.Zeitmit.Explanation.Xpath"), "Blablabla","xpath");
		Utility.webElement(driver, Utility.getLocator("Input.Zeitmit.Explanation.Xpath"),"xpath").sendKeys(Keys.ENTER);
		Utility.waitForelement(driver, Utility.getLocator("ErrorMsg.Area.Xpath"), "xpath");
		VerifyExcepted.getText(Utility.webElement(driver, Utility.getLocator("ErrorMsg.Area.Xpath"),"xpath"), "Die Zeitangabe ist ungültig", "Pass: an error message appears Die Zeitangabe ist ungültig.", "Fail: an error message appears Die Zeitangabe ist ungültig.", test);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.webElement(driver, Utility.getLocator("Input.Zeitmit.Explanation.Xpath"),"xpath"));
		Utility.webElement(driver, Utility.getLocator("Input.Zeitmit.Explanation.Xpath"),"xpath").click();
		VerifyExcepted.getText1(Utility.webElement(driver, Utility.getLocator("Data.TipText.Xpath"),"xpath"), "Die Zeitangabe ist ungültig", "Pass: an error message appears Die Zeitangabe ist ungültig.", "Fail: an error message appears Die Zeitangabe ist ungültig.", test);
		Utility.isElementVisible(driver, "//div[text()='Dies ist ein Eingabefeld für eine Zeitangabe.']", "xpath");
		
		//Clear the Input Field "Zeit (mit Explanation)" and hit enter and verify error message disappears
		Utility.webElement(driver, Utility.getLocator("Input.Zeitmit.Explanation.Xpath"),"xpath").clear();
		Utility.webElement(driver, Utility.getLocator("Input.Zeitmit.Explanation.Xpath"),"xpath").sendKeys(Keys.ENTER);
		Utility.waitForelementToDisappear(driver, Utility.getLocator("ErrorMsg.Area.Xpath"), "xpath");
		assertFalse(Utility.isElementPresent(driver, Utility.getLocator("ErrorMsg.Area.Xpath"),"xpath"));
		Utility.isElementVisible(driver, "//div[text()='Dies ist ein Eingabefeld für eine Zeitangabe.']", "xpath");
		
		//DropDown By Key
		//Click on the labels of the two dropdown boxes and check the results
		Utility.webElement(driver, Utility.getLocator("Label.DropDownByKey.ohne.Explanation.Xpath"),"xpath").click();
		assertFalse(Utility.isElementVisible(driver, "//div[text()='Eine gesunde DropDownBox mit viel Obst und deshalb reich an Vitaminen!']","xpath"));
		Utility.webElement(driver, Utility.getLocator("Label.DropDownByKey.mit.Explanation.Xpath"),"xpath").click();
		assertTrue(Utility.isElementPresent(driver, "//div[text()='Eine gesunde DropDownBox mit viel Obst und deshalb reich an Vitaminen!']","xpath"));
		
		//Click on the button "Erzeuge Fehler" just below the dropdown boxes and verify three error messages displayed
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "Button.Erzeuge.Fehler.DropDown.Xpath");
		try{
			assertTrue(Utility.isElementVisible(driver, "(//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[1]", "xpath"));
			assertTrue(Utility.isElementVisible(driver, "//span[text()='Fehler bei Berechtigungsprüfung, Fehlercode ; siehe F1-Hilfe']", "xpath"));
			assertTrue(Utility.isElementVisible(driver, "(//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[2]", "xpath"));
			Reporter.log("Pass: three error messages appear in the message area",true);
			test.log(LogStatus.PASS, "Pass: three error messages appear in the message area");
		}
		catch(Exception e){
			Reporter.log("Fail: three error messages appear in the message area",true);
			test.log(LogStatus.FAIL, "Fail: three error messages appear in the message area");
		}
		//Click on the first error message link and Check that the focus is set to the top dropdown box 
		Utility.webElement(driver, "(//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[1]","xpath").click();
		Utility.waitForAttributeValueContains(driver, Utility.getLocator("Dropdown.DropDownByKey.ohne.Explanation.Xpath1"), "xpath", "class", "lsField--focus");
		VerifyExcepted.getAttribute(Utility.webElement(driver, Utility.getLocator("Dropdown.DropDownByKey.ohne.Explanation.Xpath1"),"xpath"), "class", "lsField--focus", "Pass: input field Datum (keine Explanation) is focused", "Fail: input field Datum (keine Explanation) is not focused", test);
		assertTrue(Utility.isElementVisible(driver, "(//td[@class='lsDataTipIcon urDataTipImgError']/following-sibling::td[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[2]", "xpath"));
		Utility.webElement(driver, Utility.getLocator("Label.DropDownByKey.mit.Explanation.Xpath"), "xpath").click();
		assertTrue(Utility.isElementVisible(driver, "(//td[@class='lsDataTipIcon urDataTipImgError']/following-sibling::td[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[2]", "xpath"));
		assertTrue(Utility.isElementVisible(driver, "//td[@class='lsDataTipIcon urDataTipImgError']/following-sibling::td[text()='Fehler bei Berechtigungsprüfung, Fehlercode ; siehe F1-Hilfe']", "xpath"));
		assertTrue(Utility.isElementVisible(driver, "//div[text()='Eine gesunde DropDownBox mit viel Obst und deshalb reich an Vitaminen!']", "xpath"));
		
		//Click on the label of the bottom dropdown box and verify the result
		DropDown.selecFromDropDownByValue(driver, "Dropdown.DropDownByKey.ohne.Explanation.Xpath", "Apfel");
		Utility.waitForelementToDisappear(driver, "(//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[1]", "xpath");
		assertFalse(Utility.isElementPresent(driver, "(//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[1]","xpath"));
		assertFalse(Utility.isElementPresent(driver, "//span[text()='Fehler bei Berechtigungsprüfung, Fehlercode ; siehe F1-Hilfe']","xpath"));
		assertFalse(Utility.isElementPresent(driver, "(//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[2]","xpath"));
		Utility.webElement(driver, Utility.getLocator("Label.DropDownByKey.mit.Explanation.Xpath"), "xpath").click();
		Thread.sleep(3000);
		assertTrue(Utility.isElementVisible(driver, "//div[text()='Eine gesunde DropDownBox mit viel Obst und deshalb reich an Vitaminen!']", "xpath"));
		Utility.webElement(driver, Utility.getLocator("Label.DropDownByKey.ohne.Explanation.Xpath"), "xpath").click();
		assertFalse(Utility.isElementVisible(driver, "//td[@class='lsDataTipIcon urDataTipImgError']/following-sibling::td[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath"));
		
		//Checkbox
		driver.navigate().refresh();
		Wait.sleep(3000);
		//Click on the labels of the two checkboxes and check the result
		Utility.webElement(driver, Utility.getLocator("Label.Checkbox.ohne.Explanation2.Xpath"), "xpath").click();
		assertTrue(Utility.isElementPresent(driver, "//div[text()='Dies ist eine Checkbox ohne Vitamine.']", "xpath"));
		Utility.webElement(driver, Utility.getLocator("Label.Checkbox.ohne.Explanation1.Xpath"), "xpath").click();
		assertFalse(Utility.isElementVisible(driver, "//div[text()='Dies ist eine Checkbox ohne Vitamine.']", "xpath"));
		
		//Click on the button "Erzeuge Fehler" just below the checkboxes check two error messages displayed
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Erzeuge.Fehler.Checkbox.Xpath"),"xpath");
		try{
			assertTrue(Utility.isElementVisible(driver, "//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath"));
			assertTrue(Utility.isElementVisible(driver, "//span[text()='Fehler bei Berechtigungsprüfung, Fehlercode ; siehe F1-Hilfe']", "xpath"));
			Reporter.log("Pass: two error messages appear in the message area.",true);
			test.log(LogStatus.PASS, "Pass: two error messages appear in the message area.");
		}
		
		catch(Exception e){
			Reporter.log("Fail: two error messages appear in the message area.",true);
			test.log(LogStatus.FAIL, "Fail: two error messages appear in the message area.");
		}
		
		Utility.webElement(driver, "//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath").click();
		assertTrue(Utility.isElementVisible(driver, "//td[@class='urDataTipTxt'][text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath"));
		
		//Click on the label of the bottom checkbox and check the result
		Utility.webElement(driver, Utility.getLocator("Label.Checkbox.ohne.Explanation2.Xpath"), "xpath").click();
		Utility.waitForelementToDisappear(driver, "//td[@class='urDataTipTxt'][text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath");
		assertFalse(Utility.isElementVisible(driver, "//td[@class='urDataTipTxt'][text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath"));
		assertTrue(Utility.isElementPresent(driver, "//td[@class='urDataTipTxt'][text()='Fehler bei Berechtigungsprüfung, Fehlercode ; siehe F1-Hilfe']", "xpath"));
		assertTrue(Utility.isElementPresent(driver, "//div[text()='Dies ist eine Checkbox ohne Vitamine.']", "xpath"));
		DropDown.selecFromDropDownByValue(driver, "Dropdown.DropDownByKey.ohne.Explanation.Xpath", "Apfel");
		Utility.waitForelementToDisappear(driver, "//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath");
		assertFalse(Utility.isElementVisible(driver, "//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath"));
		assertFalse(Utility.isElementVisible(driver, "//span[text()='Fehler bei Berechtigungsprüfung, Fehlercode ; siehe F1-Hilfe']", "xpath"));
		Utility.webElement(driver, Utility.getLocator("Label.Checkbox.ohne.Explanation.Xpath"), "xpath").click();
		assertFalse(Utility.isElementVisible(driver, "//td[@class='urDataTipTxt'][text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath"));
		Utility.webElement(driver, Utility.getLocator("Label.Checkbox.ohne.Explanation2.Xpath"), "xpath").click();
		assertTrue(Utility.isElementPresent(driver, "//div[text()='Dies ist eine Checkbox ohne Vitamine.']", "xpath"));
		
		//Table
		//Enter xxx to input field just underneath the column header "Fluggesellschaft"
		Utility.enterText(driver, Utility.getLocator("Input.Table.Filter.Xpath"), "XXX", "xpath");
		Utility.webElement(driver, Utility.getLocator("Input.Table.Filter.Xpath"), "xpath").sendKeys(Keys.ENTER);
		assertFalse(Utility.isElementPresent(driver, "//span[text()='XXX']", "xpath"));
		
		//Click on the button "Erzeuge Fehler" just below the table and verify error message
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Erzeuge.Fehler.imFilter.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath"));
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Fehler bei Berechtigungsprüfung, Fehlercode ; siehe F1-Hilfe']", "xpath"));
		assertTrue(Utility.isElementVisible(driver, "(//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[2]", "xpath"));
		
		//Click on the first error message link in the message area and verify the result
		Utility.webElement(driver, "//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath").click();
		VerifyExcepted.getAttribute(Utility.webElement(driver, Utility.getLocator("Input.Table.Filter.Xpath1"), "xpath"), "class", "lsField--focus", "Pass: the focus is set to the table filter input field", "Fail: the focus is set to the table filter input field", test);
		assertTrue(Utility.isElementVisible(driver, "(//td[@class='urDataTipTxt'][text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[2]", "xpath"));
		Utility.webElement(driver, Utility.getLocator("Input.Table.Filter.Xpath"), "xpath").click();
		Utility.webElement(driver, Utility.getLocator("Input.Table.Filter.Xpath"), "xpath").sendKeys(Keys.ENTER);
		Utility.waitForelementToDisappear(driver, "//a[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath");
		assertFalse(Utility.isElementVisible(driver, "//a[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath"));
		assertFalse(Utility.isElementVisible(driver, "//a[text()='Fehler bei Berechtigungsprüfung, Fehlercode ; siehe F1-Hilfe']", "xpath"));
		assertFalse(Utility.isElementVisible(driver, "(//a[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[2]", "xpath"));
		
		//Click on the button "Erzeuge Fehler" just below the table. Click into the table filter input field and verify error message and explaination reappears
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Erzeuge.Fehler.imFilter.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe']", "xpath"));
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Fehler bei Berechtigungsprüfung, Fehlercode ; siehe F1-Hilfe']", "xpath"));
		assertTrue(Utility.isElementVisible(driver, "(//span[text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[2]", "xpath"));
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Input.Table.Filter.Xpath"), "xpath");
		driver.findElement(By.xpath(Utility.getLocator("Input.Table.Filter.Xpath"))).click();
		assertTrue(Utility.isElementVisible(driver, "(//td[@class='urDataTipTxt'][text()='Fehler beim Befehl start/enddbmo; siehe F1-Hilfe'])[2]", "xpath"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception{
		if(result.getStatus()==ITestResult.FAILURE){
			
			 String ResScreenPath=Utility.getScreenshot(driver,Utility.reportPath);
			 test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));
	   }
	    extent.endTest(test);
		extent.flush();
		driver.quit();	
		BrowserFactory.killBrowserProcess(BrowserFactory.browserName);
	   }

}
