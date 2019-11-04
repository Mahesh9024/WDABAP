package com.wdabapsp.page;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import lib.Utility;
import lib.Wait;


public class LoginPage {

    WebDriver driver;
    
    public LoginPage(WebDriver driver){
    	this.driver=driver;
    }

    @FindBy(id="sap-user")
	WebElement Edit_UserName;
	
	@FindBy(id="sap-password")
	WebElement Edit_UserPassWord;
	
	@FindBy(id="LOGON_BUTTON")
	WebElement Button_LogOn;
	
	@FindBy(id="SESSION_QUERY_CONTINUE_BUTTON")
	WebElement Button_Continue;
	
	
	
	//Method to Login

	public void login ( String pNamespace, String pAppName, String pUIElementToStart, String pLanguage ) throws InterruptedException, IOException{
		
		
		File file = new File("C:\\Automation\\WDParameters.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties props = new Properties();
		props.load(fileInput);
		
		
		String Protocol=props.getProperty("Protocol");
		String Host=props.getProperty("Host");
		String Domain=props.getProperty("Domain");
		String Port=props.getProperty("Port");
		String Client=props.getProperty("Client");
		String Language=pLanguage;
		String User=props.getProperty("User");
		String User_Pass=props.getProperty("User_Pass");
		
		
		String loginURL = Protocol + "://" +  Host + "." +  Domain + ":" + Port + "/sap/bc/webdynpro/" + pNamespace + "/" + pAppName + "?sap-language=" + Language  + "&sap-user=" + User + "&sap-client=" + Client;
	      System.out.println("loginURL"+loginURL);

		if (pAppName.toUpperCase().equals("WDR_TEST_UI_ELEMENTS") ){

			if (pUIElementToStart == ""){}
			
			else { 

				loginURL= loginURL + "&VIEW_ELEMENT=" + pUIElementToStart;
			}
			
		}

		driver.get(loginURL);
		System.out.println(loginURL);
		
		
		try {
			   driver.findElement(By.id("sap-user"));			   		
	     } catch (NoSuchElementException e) {		 
	    	   Reporter.log("Log in page didnt upload properly",true);
		 }	
		Edit_UserName.clear();
		   Edit_UserName.sendKeys(User);
		   Thread.sleep(2000);
		   Edit_UserPassWord.sendKeys(User_Pass);
		   Button_LogOn.sendKeys(Keys.ENTER);
		   
		   try {
			   
			   Thread.sleep(2000);
			   Utility.clickOnElementUsingJavaScriptExecutor(driver, driver.findElement(By.id("SESSION_QUERY_CONTINUE_BUTTON")));
			 
			} catch (NoSuchElementException e) {
			   
			}
		   
		   try {
			   Thread.sleep(2000);
			   Utility.clickOnElementUsingJavaScriptExecutor(driver, driver.findElement(By.id("SYSTEM_MESSAGE_CONTINUE_BUTTON")));
			 
			} catch (NoSuchElementException e) {
			   
			}
		   
		   try {
			   driver.findElement(By.xpath("//span[text()='Continue']")).click();;
			 
			} catch (NoSuchElementException e) {
			   
			}
		   
		}
	
	public void loginAsAdmin ( String pNamespace, String pAppName,String pAdminMode ) throws IOException{
		
		
		File file = new File("C:\\Automation\\WDParameters.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties props = new Properties();
		props.load(fileInput);
		
		
		String Protocol=props.getProperty("Protocol");
		String Host=props.getProperty("Host");
		String Domain=props.getProperty("Domain");
		String Port=props.getProperty("Port");
		String Client=props.getProperty("Client");
		String Language=props.getProperty("Language");
		String User=props.getProperty("User");
		String User_Pass=props.getProperty("User_Pass");
		
		
		String loginURL = Protocol + "://" +  Host + "." +  Domain + ":" + Port + "/sap/bc/webdynpro/" + pNamespace + "/" + pAppName + "?sap-language="+ Language+ "&sap-user="+User + "&sap-client="+Client+"?&"+pAdminMode;
		System.out.print(loginURL);
		driver.get(loginURL);
		Wait.sleep(4000);
		try {
			   driver.findElement(By.id("sap-user"));			   		
	     } catch (NoSuchElementException e) {		 
	    	   Reporter.log("Log in page didnt upload properly",true);
		 }	
		  Edit_UserName.clear();
		   Edit_UserName.sendKeys(User);
		   Wait.sleep(2000);
		   Edit_UserPassWord.clear();
		   Edit_UserPassWord.sendKeys(User_Pass);
		   Button_LogOn.click();
		   
		   try {
			   driver.findElement(By.id("SESSION_QUERY_CONTINUE_BUTTON")).click();;
			 
			} catch (NoSuchElementException e) {
			   
			}
		   try {
		   driver.findElement(By.xpath("//span[text()='Continue']")).click();;
		 
		} catch (NoSuchElementException e) {
		   
		}
		   Wait.sleep(2000);
		   
		}

	
	public String browserInfo () throws InterruptedException, IOException{
	
		File file = new File("C:\\Automation\\WDParameters.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties props = new Properties();
		props.load(fileInput);
		
		String Browser=props.getProperty("Browser");
		
		Reporter.log("Test Starts",true);
		Thread.sleep(3000);
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
	    String browserName = cap.getBrowserName().toUpperCase();
	    String ver = cap.getVersion().toString();
	    System.out.println("Browser Name: "+browserName+" Version: "+ver);
	    System.out.println("====================================================================");
	    String BrowserInfo="Browser Name: "+browserName+" Version: "+ver;
		return BrowserInfo;
	}
		   
	}
	
	

