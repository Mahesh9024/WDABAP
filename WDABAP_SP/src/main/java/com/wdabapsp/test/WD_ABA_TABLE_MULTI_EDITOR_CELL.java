package com.wdabapsp.test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import lib.Wait;

public class WD_ABA_TABLE_MULTI_EDITOR_CELL {
	
	ExtentReports extent; 
	ExtentTest test;
	public WebDriver driver;
	String StepDetail;
	
	@BeforeMethod
	public void Setup() throws InterruptedException, IOException{
		extent = Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(), "This test case deals with the new cell variant TableMultiEditorCell of the WebDynpro ABAP Table UI Element. It allows displaying several UI Elements in one table cell.");
		driver = BrowserFactory.typeBrowser();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test.log(LogStatus.PASS, StepDetail);
		String pNamespace="sap";
		String pAppName="wdr_test_table_cell_varia";
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);
	}

	
	@Test
	public void WBD_ABA_TABLE_MULTI_EDITOR_CELL() throws Exception{
		
		//Scroll the top table "Bestellungen im Restaurant" and have a look at the table cells in the table column "Aktionen".
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Column.Label.Aktionen"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Button.Ändern"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Button.Löschen"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Link.SAP"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Link.Info"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Image.Info"), "xpath"));
		
		//Click on a button "Löschen
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Löschen.FirstRow"), "xpath");
		
		//Check: the table row in which the button was positioned disappears
		Utility.waitForelementToDisappear(driver, Utility.getLocator("Button.Löschen.FirstRow"), "xpath");
		assertFalse(Utility.isElementPresent(driver, Utility.getLocator("Button.Löschen.FirstRow"), "xpath"));
		
		//Click on a link "SAP"
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.SAP"), "xpath");
		Wait.sleep(3000);
		
		//Check: a new browser window opens with the SAP website
		String pw = driver.getWindowHandle();
		Set<String> aw = driver.getWindowHandles();
		Iterator<String> itr = aw.iterator();
		while(itr.hasNext()){
			String cw = itr.next().toString();
			if(!cw.contains(pw)){
				driver.switchTo().window(cw);
			}
		}
		assertTrue((driver.getTitle()).equals("SAP Software Solutions | Business Applications and Technology"));
		driver.close();
		driver.switchTo().window(pw);
		
		//Click on a link "Info"
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Info.SecondRow"), "xpath");
		
		//Check: a popin opens underneath
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Popin.Info"), "xpath"));
		assertTrue(Utility.isElementVisible(driver, Utility.getLocator("Text.Detailinformationen"), "xpath"));
		
		//In another table row open the menu for the link "Info" and select the entry "Löschen"
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Image.Info.ThirdRow"), "xpath");
		//Wait.sleep(3000);
		Actions action = new Actions(driver);
		Wait.sleep(3000);
		action.sendKeys(Keys.RETURN).build().perform();
		Wait.sleep(3000);
		
		//Check: the table row disappears
		Utility.waitForelementToDisappear(driver, "Image.Info.ThirdRow", "xpath");
		assertFalse(Utility.isElementPresent(driver, Utility.getLocator("Image.Info.ThirdRow"), "xpath"));
		
		// In another row change the state of the cell editor in the table column "Wahl", i.e. there should be either a checkbox, a text edit or dropdown.
		WebElement ele = driver.findElement(By.xpath("//span[text()='4']/ancestor::td[1]/following-sibling::td[3]//textarea"));
		ele.click();
		ele.clear();
		ele.sendKeys("modified");
		driver.findElement(By.xpath("//span[text()='6']/ancestor::td[1]/following-sibling::td[3]//input/..")).click();
		
		DropDown.selecFromDropDownByValue(driver, "Dropdown.FifthRow", "Orange");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Button.Änderungen.xpath"), "xpath");
		Wait.sleep(3000);
		
		//Check: you can (un)check the checkbox or edit the textedit or select a new value of the dropdown, depending on which cell editor is there
		WebElement ele1 = driver.findElement(By.xpath("//span[text()='4']/ancestor::td[1]/following-sibling::td[3]//textarea"));
		assertTrue((ele1.getText()).equals("modified"));
		assertTrue((driver.findElement(By.xpath("//span[text()='5']/ancestor::td[1]/following-sibling::td[3]//td/input")).getAttribute("value")).equals("Orange"));
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
