package com.wdabapsp.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
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
import lib.DropDown;
import lib.Utility;

public class WDA_FIELD_EXPLANATIONS 
{
	//static String url= "https://ldciu3y.wdf.sap.corp:55001/sap/bc/webdynpro/sap/WDR_TEST_UI_ELEMENTS?sap-language=DE&sap-user=sim_admin&sap-client=200&VIEW_ELEMENT=InputField";
		ExtentReports extent;
		ExtentTest test;
		public WebDriver driver;
		String ScrPath=System.getProperty("user.dir")+"\\target\\"+this.getClass().getSimpleName();	
		String StepDetail;
		
		 @BeforeMethod
		 public void Setup() throws InterruptedException, IOException{

			    //String ClasPath=System.getProperty("user.dir")+"\\target\\"+this.getClass().getSimpleName();
			    File index = new File(ScrPath);
			    if (index.exists()) {
				     FileUtils.deleteDirectory(new File(ScrPath));
				     
			    }
			
			    extent = new ExtentReports(ScrPath+".html", true);
				test = extent.startTest(this.getClass().getSimpleName(), "This test case tests the behaviour of the Web Dynpro LinkToUrl UI element");
				driver = BrowserFactory.typeBrowser();
				LoginPage loginPage=PageFactory.initElements(driver, LoginPage.class);
				
				 //Display information about the  browser
				String StepDetail=loginPage.browserInfo();
				test.log(LogStatus.PASS, StepDetail);
				
				
				//User Logs in
				
				String pNamespace="sap";
				String pAppName="wdr_test_ui_elements";
				String pUIElementToStart="InputField";
				loginPage.login(pNamespace, pAppName,pUIElementToStart,"DE");
				StepDetail="User successfully Logged in";
				test.log(LogStatus.PASS, StepDetail);
				Reporter.log("User successfully Logged in",true);
				
		 }
		 
		  @Test
		  public void WDA_WD_FIELD_EXPLANATIONS() throws Exception {

				 // 1. Change the value property to  'Test'.
			    Utility.checkElementOr(driver,"TextBox.Value.xpath").click();
				Utility.checkElementOr(driver,"TextBox.Value.xpath").clear();
				Utility.checkElementOr(driver,"TextBox.Value.xpath").sendKeys("Test");
				Utility.checkElementOr(driver,"TextBox.Value.xpath").sendKeys(Keys.ENTER);
				StepDetail="Change the value property to  'Test'.";
				
				
				StepDetail="Activate the button 'Änderungen durchführen'";
				test.log(LogStatus.PASS, StepDetail);
				Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.Änderungendurchführen.xpath");
				test.log(LogStatus.INFO, StepDetail);
			    
				//Check : 1. The sample input field displays 'Test'  leftadjusted.
						
				String EinLabelVal=Utility.checkElementOr(driver,"TextBox.EinLabel.xpath").getAttribute("value");
				
				if (EinLabelVal.contains("Test"))
				{
					
					StepDetail="The sample input field displays 'Test'  leftadjusted";
					test.log(LogStatus.PASS, StepDetail);
					Reporter.log(StepDetail,true);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.PASS, test.addScreenCapture(ResScreenPath));	
									
				}
				else
				{	
					
					StepDetail="Fail : The sample input field displays 'Test'  leftadjusted";
					test.log(LogStatus.FAIL, StepDetail);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));	
					
				}	
				
				//2. Change the alignment property to  'center'.
				
				DropDown.selecFromDropDownByValue(driver, "DropDown.Alignment.xpath.Field", "center");
				StepDetail="Change the alignment property to  'center'";
				test.log(LogStatus.PASS, StepDetail);
				Reporter.log("Change the alignment property to  'center'",true);
				
				StepDetail="Activate the button 'Änderungen durchführen'";
				test.log(LogStatus.PASS, StepDetail);
				Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.Änderungendurchführen.xpath");
				test.log(LogStatus.INFO, StepDetail);
				
				
				
				//2. The sample input field displays 'Test' centered.
				EinLabelVal=Utility.checkElementOr(driver,"TextBox.EinLabel.xpath").getAttribute("style"); 	
				
				if (EinLabelVal.contains("center"))
				{
					
					StepDetail="The sample input field displays 'Test' centered.";
					test.log(LogStatus.PASS, StepDetail);
					Reporter.log(StepDetail,true);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.PASS, test.addScreenCapture(ResScreenPath));	
									
				}
				else
				{	
					
					StepDetail="Fail : The sample input field displays 'Test' centered.";
					test.log(LogStatus.FAIL, StepDetail);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));	
					
				}	
				
				 //4. Change the alignment property to  'endOfLine'.
				
				DropDown.selecFromDropDownByValue(driver, "DropDown.Alignment.xpath.Field", "endOfLine");
				StepDetail="Change the alignment property to  'endOfLine'";
				test.log(LogStatus.PASS, StepDetail);
				Reporter.log("Change the alignment property to  'endOfLine'",true);
				
				StepDetail="Activate the button 'Änderungen durchführen'";
				test.log(LogStatus.PASS, StepDetail);
				Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.Änderungendurchführen.xpath");
				test.log(LogStatus.INFO, StepDetail);
				
						
				EinLabelVal=Utility.checkElementOr(driver,"TextBox.EinLabel.xpath").getAttribute("lsdata"); 	
				
				if (EinLabelVal.contains(("endOfLine").toUpperCase()))
				{
					
					StepDetail="Alignment property changed to  'endOfLine'";
					test.log(LogStatus.PASS, StepDetail);
					Reporter.log(StepDetail,true);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.PASS, test.addScreenCapture(ResScreenPath));	
									
				}
				else
				{	
					
					StepDetail="Fail : Change the alignment property not changed to  'endOfLine'";
					test.log(LogStatus.FAIL, StepDetail);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));	
					
				}	
				
				
				//5. Change the alignment property to  'forcedRight'.
				
				DropDown.selecFromDropDownByValue(driver, "DropDown.Alignment.xpath.Field", "forcedRight");
				StepDetail="Change the alignment property to  'forcedRight'";
				test.log(LogStatus.PASS, StepDetail);
				Reporter.log("Change the alignment property to  'forcedRight'",true);
				
				StepDetail="Activate the button 'Änderungen durchführen'";
				test.log(LogStatus.PASS, StepDetail);
				Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.Änderungendurchführen.xpath");
				test.log(LogStatus.INFO, StepDetail);
				
				
				EinLabelVal=Utility.checkElementOr(driver,"TextBox.EinLabel.xpath").getAttribute("lsdata"); 	
				if (EinLabelVal.contains(("Right").toUpperCase()))
				{
					
					StepDetail="Alignment property changed to 'forcedRight'";
					test.log(LogStatus.PASS, StepDetail);
					Reporter.log(StepDetail,true);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.PASS, test.addScreenCapture(ResScreenPath));	
									
				}
				else
				{	
					
					StepDetail="Fail : Alignment property doesnt not change to  'forcedRight'";
					test.log(LogStatus.FAIL, StepDetail);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));	
					
				}	
				
				
				//6. Change the alignment property to  'beginOfLine'.
				
				DropDown.selecFromDropDownByValue(driver, "DropDown.Alignment.xpath.Field", "forcedLeft");
				StepDetail="Change the alignment property to  'forcedLeft'";
				test.log(LogStatus.PASS, StepDetail);
				Reporter.log("Change the alignment property to  'forcedLeft'",true);
				
				StepDetail="Activate the button 'Änderungen durchführen'";
				test.log(LogStatus.PASS, StepDetail);
				Utility.clickOnElementUsingJavaScriptExecutor(driver,"Button.Änderungendurchführen.xpath");
				test.log(LogStatus.INFO, StepDetail);
				
				
				EinLabelVal=Utility.checkElementOr(driver,"TextBox.EinLabel.xpath").getAttribute("lsdata"); 	
				
				if (EinLabelVal.contains(("forcedLeft").toUpperCase()))
				{
					
					StepDetail="Alignment property changed to 'forcedLeft'";
					test.log(LogStatus.PASS, StepDetail);
					Reporter.log(StepDetail,true);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.PASS, test.addScreenCapture(ResScreenPath));	
					 test.log(LogStatus.PASS, "=====================Finish========================================");
									
				}
				else
				{	
					
					StepDetail="Fail : Change the alignment property to  'beginOfLine'";
					test.log(LogStatus.FAIL, StepDetail);
					String ResScreenPath=Utility.getScreenshot(driver,ScrPath);
					test.log(LogStatus.FAIL, test.addScreenCapture(ResScreenPath));	
					
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
