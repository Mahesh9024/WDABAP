package com.wdabapsp.test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.common.collect.Ordering;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wdabapsp.page.LoginPage;
import lib.BrowserFactory;
import lib.MouseActivity;
import lib.Utility;

public class WD_ABA_TABLE_COL_HEADER_DD {
	ExtentReports extent; 
	ExtentTest test;
	public WebDriver driver;
	String StepDetail;
	
	@BeforeMethod
	public void Setup() throws InterruptedException, IOException{
		extent = Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(), "This testcase describe the new design for the table header and the selection possibilities.");
		driver = BrowserFactory.typeBrowser();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test.log(LogStatus.PASS, StepDetail);
		String pNamespace="sap";
		String pAppName="wdr_test_table_btc";
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);
	}
	
	@Test
	
	public void WBD_ABA_TABLE_COL_HEADER_DD() throws Exception{
		
		//Move with the mouse to the table column header of the first column ("Fluggesellschaft") of the first table ("Flüge").
		MouseActivity.mouseHoverAdvance(driver, Utility.webElement(driver, Utility.getLocator("Column.Header.Fluggesellschaft"), "xpath"));
		
		//A small triagle appears on the right hand side of the area
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("HoverActMenu.Fluggesellschaft"), "xpath"));
		test.log(LogStatus.PASS, "A small triagle appears on the right hand side of the area.");
		Reporter.log("A small triagle appears on the right hand side of the area.",true);
		
		//Click onto the table column header of the column ("Fluggesellschaft") of the first table ("Flüge").
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Column.Header.Fluggesellschaft"), "xpath");
		
		//A menu appears with following entries: "Aufsteigend sortieren" "Absteigend sortieren" "(Alle)" "(Anpassen)" and as example some of the following values: "AA" "AZ" "DL" and much more
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("DropdownOption.Fluggesellschaft.Aufsteigendsortieren"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("DropdownOption.Fluggesellschaft.Absteigendsortieren"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("DropdownOption.Fluggesellschaft.Alle"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("DropdownOption.Fluggesellschaft.AZ"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("DropdownOption.Fluggesellschaft.SQ"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("DropdownOption.Fluggesellschaft.LH"), "xpath"));
		test.log(LogStatus.PASS, "A menu appears with following entries: 'Aufsteigend sortieren' 'Absteigend sortieren' '(Alle)'  and as example some of the following values: 'SQ' 'AZ' 'DL' and much more");
		Reporter.log("A small triagle appears on the right hand side of the area.",true);
		
		//Do the same as in point before and click onto entry "LH". After that click into one row of the top table so that the second table is filled.
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("DropdownOption.Fluggesellschaft.LH"), "xpath");
		Thread.sleep(3000);
		String xpath = "//span[text()='Flüge']/ancestor::table[2]//td[@id='WD22-content']/table[@class='urST3BdBrd urST3BdF urFontStd']//td[2][@subct='STC']";
		List<WebElement> all = driver.findElements(By.xpath(xpath));
		for(int i=0;i<all.size();i++){
			String s=all.get(i).getText().trim();
			assertTrue(s.equals("LH"));
		}
		all.get(2).click();
		
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Image.Filter.Fluggesellschaft"), "xpath"));
		
		//Click in second table ("Buchungen") into the third column header (" Passagiername") and select in menu the entry "Aufsteigend sortieren" (english is this: sort ascending).
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Column.Header.Passagiername"), "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("DropdownOption.Aufsteigendsortieren"), "xpath");
		Thread.sleep(3000);
		Actions a = new Actions(driver);
		a.clickAndHold(driver.findElement(By.xpath("//div[@id='WDDE-scrollV-hdl']/div")));
		//a.dragAndDropBy(driver.findElement(By.xpath("//div[@id='WDDE-scrollV-hdl']/div")), 0, 600);
		Utility.ClickArrowButton(driver, 80);
		Thread.sleep(3000);
		
		
		String xpath1 = "//span[text()='Buchungen']/ancestor::table[2]//table[@class='urST3BdBrd urST3BdF urFontStd']//td[5][@subct='STC']";
		ArrayList<String> data = new ArrayList<String>();
		List<WebElement> all1 = driver.findElements(By.xpath(xpath1));
		for(int j=0;j<all1.size();j++){
			if(all1.get(j).getText().isEmpty()){
				
			}
			else{
			data.add(all1.get(j).getText());
			}
			
		}
		
		//Check that the list in table "Buchungen" is sorted following the ascending sequence of the passagenger name of column "Passagiername".
	boolean b = Ordering.natural().isOrdered(data);
	assertTrue(b);
		
		
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
