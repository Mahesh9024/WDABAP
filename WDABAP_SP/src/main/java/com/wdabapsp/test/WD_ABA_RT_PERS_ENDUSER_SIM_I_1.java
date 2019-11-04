package com.wdabapsp.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
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
import lib.MouseActivity;
import lib.Utility;
import lib.Wait;

public class WD_ABA_RT_PERS_ENDUSER_SIM_I_1 
{
	//static String url="https://ldai1q3w.wdf.sap.corp:44334/sap/bc/webdynpro/sap/WDR_TEST_P13N%20?sap-client=005&sap-language=de#";
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
		Thread.sleep(5000);
		LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);	
		test.log(LogStatus.PASS, StepDetail);			
		String pNamespace="sap";
		String pAppName="WDR_TEST_P13N";	
		String pUIElementToStart="";
		loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
		StepDetail="User successfully Logged in";
		test.log(LogStatus.PASS, StepDetail);
		Reporter.log("User successfully Logged in",true);
      }
	@Test
	
	public  void WD_ABA_RT_PERS_ENDUSER_SIM_1() throws Exception {	
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//*[text()='Personalisierungsdaten zurücksetzen']", "xpath");
		Utility.checkElementOr(driver, "Link.InuptField.xpath").click();
		Wait.sleep(2000);
		List<WebElement> labels=driver.findElements(By.xpath("//*[contains(text(),'Label zum Inputfield')]"));	
		int i1 = labels.size();
		System.out.println(labels.size());
		MouseActivity.rightClickByElement(driver,labels.get(0));
		Utility.checkElementOr(driver, "ContextMenue.Benutzereinstellungen.xpath").click();
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'ausblenden')]"));
		MouseActivity.mouseHoverAdvance(driver, ele);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, ele);
		Thread.sleep(5000);
		
		List<WebElement> inputLabel=driver.findElements(By.xpath("//span[text()='Label zum Inputfield']"));
		System.out.println(inputLabel.size());
		int i2 = inputLabel.size();
		Assert.assertTrue(i1>i2);
		test.log(LogStatus.PASS, "1.Check The Label has disappered");
		
		
		MouseActivity.rightClick(driver, "Label.Buttonpersonalisieren.xpath");
		Utility.checkElementOr(driver, "ContextMenue.Benutzereinstellungen.xpath").click();
		
		WebElement ele1 = driver.findElement(By.xpath("//span[contains(text(),'Unsichtbare')]"));
		MouseActivity.mouseHoverAdvance(driver, ele1);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, ele1);
		Thread.sleep(5000);
		driver.switchTo().frame(0);
		Utility.checkElementOr(driver, "Frame.Link.TestButtonwiederherstellen.xpath").click();
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		
		Assert.assertEquals(true, Utility.isElementPresentByXpath(driver,"Label.LabelzumInputfield.xpath"));
		List<WebElement> inputLabel1=driver.findElements(By.xpath("//span[text()='Label zum Inputfield']"));
		System.out.println(inputLabel1.size());
		int i3 = inputLabel1.size();
		assertEquals(i1, i3);
		Reporter.log("2.Check the input field has appered.",true);
		test.log(LogStatus.PASS, "2.Check the input field has appered");
		
		
		List<WebElement> inputs=driver.findElements(By.xpath("//span[text()='Datum']/ancestor::td[1]/following-sibling::td//input"));
		int input1 = inputs.size();
		
		MouseActivity.rightClickByElement(driver,inputs.get(0));
		Utility.checkElementOr(driver, "ContextMenue.Benutzereinstellungen.xpath").click();
		WebElement ele3 = driver.findElement(By.xpath("//span[contains(text(),'ausblenden')]"));
		MouseActivity.mouseHoverAdvance(driver, ele3);
		Utility.clickOnElementUsingJavaScriptExecutor(driver, ele3);
		Thread.sleep(5000);
		List<WebElement> inputs1=driver.findElements(By.xpath("//span[text()='Datum']/ancestor::td[1]/following-sibling::td//input"));
		int input2 = inputs1.size();
		assertTrue(input1>input2);
		Reporter.log("3.chceck that, one date input field disappeared",true);
		test.log(LogStatus.PASS, "3.chceck that, one date input field disappeared");
		
		WebElement element = driver.findElement(By.xpath("//span[text()='Datum']/ancestor::td[1]/following-sibling::td//input"));
		element.clear();
		element.sendKeys("text");
		element.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		List<WebElement> inputs2=driver.findElements(By.xpath("//span[text()='Datum']/ancestor::td[1]/following-sibling::td//input"));
		List<WebElement> error=driver.findElements(By.xpath("//span[text()='Datum']/ancestor::td[1]/following-sibling::td//input/parent::span[contains(@class,'lsField--error')]"));
		int errorCount = error.size();
		int input3 = inputs2.size();
		assertEquals(input1, input3);
		assertEquals(input1, errorCount);
		Reporter.log("4.chceck that, again date input field is appeared, two date fields are marked in red color",true);
		test.log(LogStatus.PASS, "4.chceck that, again date input field is appeared, two date fields are marked in red color");
		
		WebElement element1 = driver.findElement(By.xpath("//span[text()='Datum']/ancestor::td[1]/following-sibling::td//input"));
		element1.clear();
		element1.sendKeys("1440/07/18");
		element1.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		List<WebElement> inputsf=driver.findElements(By.xpath("//span[text()='Datum']/ancestor::td[1]/following-sibling::td//input"));
		boolean a;
		try{
				(driver.findElement(By.xpath("//span[text()='Datum']/ancestor::td[1]/following-sibling::td//input/parent::span[contains(@class,'lsField--error')]"))).isDisplayed();
				a=true;
		}
		catch (Exception e) {
			a=false;
		}
		int inputf = inputsf.size();
		assertTrue(input1>inputf);
		assertFalse(a);
		Reporter.log("5.chceck that, only one date input field is appeared, and red error mark disappeared",true);
		test.log(LogStatus.PASS, "5.chceck that, only one date input field is appeared, and red error mark disappeared");
		
		Utility.clickOnElementUsingJavaScriptExecutor(driver, "//*[text()='Personalisierungsdaten zurücksetzen']", "xpath");
		Thread.sleep(3000);
		

		List<WebElement> inputslast=driver.findElements(By.xpath("//span[text()='Datum']/ancestor::td[1]/following-sibling::td//input"));
		int inputlast = inputslast.size();
		assertEquals(input1, inputlast);
		Reporter.log("6.both date input fields are displaying again",true);
		test.log(LogStatus.PASS, "6.both date input fields are displaying again");
		
	
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
