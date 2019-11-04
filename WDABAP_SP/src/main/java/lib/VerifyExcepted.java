package lib;

import java.io.FileInputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class VerifyExcepted 
{
	    ExtentTest test;
	    FileInputStream fis;
        
	 public static void getAttribute(WebElement Element,String GetAttribute ,String matchcontent,String Passmessage,String Failmessage,ExtentTest test) throws InterruptedException
	 {
		   try 
		   {
			Assert.assertTrue(Element.getAttribute(GetAttribute).contains(matchcontent), Failmessage);
			Thread.sleep(5000);
			test.log(LogStatus.PASS, Passmessage);
			Reporter.log(Passmessage,true);
		    } 
		   catch (Error e) 
		   {
			test.log(LogStatus.FAIL, Failmessage);
			Reporter.log(Failmessage, true);
			
		   }
	   }
	 
	 public static void getText(WebElement Element,String matchcontent,String Passmessage,String Failmessage,ExtentTest test)
	 {
		 try {
		    	 Assert.assertTrue(Element.getText().contains(matchcontent), Failmessage);
				 Reporter.log(Passmessage,true);
				 test.log(LogStatus.PASS,Passmessage);
		    } 
		 catch (Error e) {
		    	 Reporter.log(Failmessage,true);
				 test.log(LogStatus.FAIL,Failmessage);
			}
	 }
	//======================================================
	 public static void getAttribute(WebDriver ldriver,String key,String GetAttribute ,String matchcontent,String Passmessage,String Failmessage,ExtentTest test) 
	 {
		   try 
		   {
			Wait.sleep(3000);
			WebElement ele=Utility.checkElementOr(ldriver, key);
			Assert.assertTrue(ele.getAttribute(GetAttribute).contains(matchcontent), Failmessage);
			test.log(LogStatus.PASS, Passmessage);
			Reporter.log(Passmessage,true);
		    } 
		   catch (Exception e) 
		   {
			test.log(LogStatus.FAIL, Failmessage);
			Reporter.log(Failmessage, true);
			
			
		   }
	   }
//////////////////////////////////////////pass only the key///////////////////////////////////
	 public static void getText(WebDriver ldriver,String key,String matchcontent,String Passmessage,String Failmessage,ExtentTest test) throws InterruptedException
	 {
		 try {
			   WebElement Element=Utility.checkElementOr(ldriver, key);
			   Thread.sleep(5000);
		    	Assert.assertTrue(Element.getText().contains(matchcontent), Failmessage);
		    	Thread.sleep(5000);
				 Reporter.log(Passmessage,true);
				 test.log(LogStatus.PASS,Passmessage);
		    } catch (Error e) {
		    	 Reporter.log(Failmessage,true);
				 test.log(LogStatus.FAIL,Failmessage);
			}
	 }
	 
	 public static void getText1(WebElement Element,String matchcontent,String Passmessage,String Failmessage,ExtentTest test)
	 {
		 try {
		    	 Assert.assertTrue(Element.getText().contains(matchcontent), Failmessage);
				 Reporter.log(Passmessage,true);
				 test.log(LogStatus.PASS,Passmessage);
		    } 
		 catch(AssertionError e){
			 Assert.assertTrue(Element.getAttribute("innerHTML").contains(matchcontent), Failmessage);
			 Reporter.log(Passmessage,true);
			 test.log(LogStatus.PASS,Passmessage);
			 
		 }
		 catch (Error e) {
		    	 Reporter.log(Failmessage,true);
				 test.log(LogStatus.FAIL,Failmessage);
			}
	 }
		 
	 
}


