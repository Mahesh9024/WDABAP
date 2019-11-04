package com.wdabapsp.test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

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
import lib.Utility;

public class WD_ABA_NAVIGATION {
	
	ExtentReports extent; 
	ExtentTest test;
	public WebDriver driver;
	String StepDetail;
	
	@BeforeMethod
	public void Setup() throws InterruptedException, IOException{
		extent = Utility.initReportPath(this.getClass().getSimpleName());
		test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the navigation between several views in a Web Dynpro ABAP application");
		driver = BrowserFactory.typeBrowser();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test.log(LogStatus.PASS, StepDetail);
		String pNamespace="sap";
		String pAppName="wdr_test_navigation";
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);
	}
	
	@Test
	
	public void ABA_NAVIGATION() throws Exception{
	
		//Select 'Navigation: View ->View' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Navigation.View.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION; View V1']", "xpath"));
		test.log(LogStatus.PASS, "1. Select 'Navigation: View ->View'");
		Reporter.log("1. PASS:Select 'Navigation: View ->View'", true);
		
		//Select 'Navigation: View ->Eingebettete Component' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Navigation.EingebetteteComponent.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION2; View DEFAULT']", "xpath"));
		test.log(LogStatus.PASS, "2. Select 'Navigation: View ->Eingebettete Component'");
		Reporter.log("2. PASS:Select 'Navigation: View ->Eingebettete Component'", true);
		
		//Select 'Navigation: View ->Einbettendes Window ->View' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Navigation.EinbettendesWindow.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION; View V2']", "xpath"));
		test.log(LogStatus.PASS, "3. Select 'Navigation: View ->Einbettendes Window ->View'");
		Reporter.log("3. PASS:Select 'Navigation: View ->Einbettendes Window ->View'", true);
		
		//Select 'Navigation: Window -> Eingebettete Component' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Navigation.Window.EingebetteteComponent.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION2; View DEFAULT']", "xpath"));
		test.log(LogStatus.PASS, "4. Select 'Navigation: Window -> Eingebettete Component'");
		Reporter.log("4. PASS:Select 'Navigation: Window -> Eingebettete Component'", true);
		
		//Select 'Navigation: View -> Eingebettete Component -> View (über Window-Plugs)' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Navigation.EingebetteteComponent.View.WindowPlug.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION2; View V1']", "xpath"));
		test.log(LogStatus.PASS, "5. Select 'Navigation: View -> Eingebettete Component -> View (über Window-Plugs)'");
		Reporter.log("5. PASS:Select 'Navigation: View -> Eingebettete Component -> View (über Window-Plugs)'", true);
		
		//Select 'Navigation: View -> Eingebettete Component -> Eingebettete Component (über Window-Plugs)' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Navigation.EingebetteteComponent.WindowPlug.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION3; View DEFAULT']", "xpath"));
		test.log(LogStatus.PASS, "6. Select 'Navigation: View -> Eingebettete Component -> Eingebettete Component (über Window-Plugs)'");
		Reporter.log("6. PASS:Select 'Navigation: View -> Eingebettete Component -> Eingebettete Component (über Window-Plugs)'", true);
		
		//Select 'Navigation: Eingebettete Component -> Einbettendes Window -> View (über Window-Plugs)' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Navigation.EingebetteteComponent.EinbettendesWindow.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION; View V5']", "xpath"));
		test.log(LogStatus.PASS, "7. Select 'Navigation: Eingebettete Component -> Einbettendes Window -> View (über Window-Plugs)'");
		Reporter.log("7. PASS:Select 'Navigation: Eingebettete Component -> Einbettendes Window -> View (über Window-Plugs)'", true);
		
		//Select 'Navigation: Eingebettete Component -> View der einbettenden Component' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Navigation.EingebetteteComponent.Viewder.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION; View V6']", "xpath"));
		test.log(LogStatus.PASS, "8. Select 'Navigation: Eingebettete Component -> View der einbettenden Component'");
		Reporter.log("8. PASS:Select 'Navigation: Eingebettete Component -> View der einbettenden Component'", true);
		
		//Select 'Dynamische Navigation: View -> View' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.DynamischeNavigation.View.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION; View V3']", "xpath"));
		test.log(LogStatus.PASS, "9. Select 'Dynamische Navigation: View -> View'");
		Reporter.log("9. PASS:Select 'Dynamische Navigation: View -> View'", true);
		
		//Select 'Dynamische Navigation: View -> Eingebettete Component' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.DynamischeNavigation.View.EingebetteteComponent.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION4; View VIEW1']", "xpath"));
		test.log(LogStatus.PASS, "10. Select 'Dynamische Navigation: View -> Eingebettete Component'");
		Reporter.log("10. PASS:Select 'Dynamische Navigation: View -> Eingebettete Component'", true);
		
		//Select 'Umschalten zweier Views -1' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Umschaltenzweier.Views1.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erster View']", "xpath"));
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Zweiter View']", "xpath"));
		test.log(LogStatus.PASS, "11. Select 'Umschalten zweier Views -1'");
		Reporter.log("11. PASS:Select 'Umschalten zweier Views -1'", true);
		
		//Select 'Umschalten zweier Views -2' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Umschaltenzweier.Views2.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erster View']", "xpath"));
		test.log(LogStatus.PASS, "12. Select 'Umschalten zweier Views -2'");
		Reporter.log("12. PASS:Select 'Umschalten zweier Views -2'", true);
		
		//Select 'Default Inbound Plugs' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.DefaultInboundPlugs.xpath"), "xpath");
		String x1 = "//span[text()='Inbound Plug gerufen']/ancestor::td[1]/../following-sibling::tr[1]//span[text()='Name des Ereignisbehandlers']/ancestor::td[1]/following-sibling::td[1]/span/input[@value='HANDLEIN_V7']";
		String x2 = "//span[text()='Inbound Plug gerufen']/ancestor::td[1]/../following-sibling::tr[1]//span[text()='Name des Ereignisbehandlers']/ancestor::td[1]/following-sibling::td[1]/span/input[@value='HANDLEIN_V8']";
		String x3 = "//span[text()='Inbound Plug gerufen']/ancestor::td[1]/../following-sibling::tr[1]//span[text()='Name des Ereignisbehandlers']/ancestor::td[1]/following-sibling::td[1]/span/input[@value='HANDLEIN_V9']";
		String x4 = "//span[text()='Inbound Plug gerufen']/ancestor::td[1]/../following-sibling::tr[1]//span[text()='Name des Ereignisbehandlers']/ancestor::td[1]/following-sibling::td[1]/span/input[@value='HANDLEIN_V10']";
		assertTrue(Utility.isElementVisible(driver, x1, "xpath"));
		assertTrue(Utility.isElementVisible(driver, x2, "xpath"));
		assertTrue(Utility.isElementVisible(driver, x3, "xpath"));
		assertTrue(Utility.isElementVisible(driver, x4, "xpath"));
		test.log(LogStatus.PASS, "13. Select 'Default Inbound Plugs'");
		Reporter.log("13. PASS:Select 'Default Inbound Plugs'", true);
		
		// Select 'Component Interfaces' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.ComponentInterfaces.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Erwartet: Component WDR_TEST_NAVIGATION6; View V1']", "xpath"));
		test.log(LogStatus.PASS, "14. Select 'Component Interfaces'");
		Reporter.log("14. PASS:Select 'Component Interfaces'", true);
		
		//Restart the application. Check 'Navigation unterbinden'. Select 'Navigation: View -> View' and verify the result
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.DefaultInboundPlugs2.xpath"), "xpath");
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Inbound-Plug FROM_WINDOW von View V1 der Component WDR_TEST_NAVIGATION7 wurde gerufen']", "xpath"));
		assertTrue(Utility.isElementVisible(driver, "//span[text()='Default-Inbound-Plug DEFAULT von View V1 der Component WDR_TEST_NAVIGATION7 wurde gerufen']", "xpath"));
		driver.navigate().refresh();
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Checkbox.Navigationunterbinden.xpath"), "xpath");
		Utility.clickOnElementUsingJavaScriptExecutor(driver, Utility.getLocator("Link.Navigation.View.xpath"), "xpath");
		assertFalse(Utility.isElementVisible(driver, "//*[text()='WDR_TEST_NAVIGATION']/ancestor::td[1]/preceding-sibling::td//span[text()='Component']", "xpath"));
		test.log(LogStatus.PASS, "15. No navigation should happen.");
		Reporter.log("15. PASS:No navigation should happen.", true);
		
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
