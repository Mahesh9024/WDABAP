package lib;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File
;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;


public class Utility 
{

	static WebElement element;
    static String lxpath;
    public  static String reportPath;
    
//*******check_check_box_************************************************************************************************************************
	public static void checkCheckbox(WebDriver driver,WebElement Element)
	{
		Element.click();
	}
	
//===========================SelectContextValue========================================
	public static void SelectContextValue( WebDriver ldriver,String loketer,int ArrowDown)
	{
	   Utility.checkElementOr(ldriver, loketer).click();;
	   Actions action = new Actions(ldriver);
	   for(int i=1;i<=ArrowDown;i++)
	   {
		action.sendKeys(Keys.ARROW_DOWN);
	   }
	   action.sendKeys(Keys.ENTER).click().build().perform();
	   
	}
	
//*****Set_the_to_the_input/textbox/_area******************************************************************************************************
	public static void setText(WebDriver driver, WebElement Element,String text) throws InterruptedException
	{
	      Element.sendKeys(text);
	      Thread.sleep(2000);
	}
//==============================================Click_Änderungendurchführen_Button============================
	public static void ClickÄnderungendurchführenButton( WebDriver ldriver) throws InterruptedException
	{
	     	WebElement element=ldriver.findElement(By.xpath("//span[text()='Änderungen durchführen']"));
	     	element.click();
	     	Thread.sleep(5000);
	}
//*********clear_the_text*************************************************************************************************************************************	

	public  static void clearText(WebDriver driver,WebElement Element)
	{
		Element.click();
		Element.clear();
	}
	
//********take_the_Xpath_from_OR_****************************************************************************************************

public static WebElement checkElementOr1(WebDriver ldriver,String key){
	try {
		FileInputStream fis = new FileInputStream(new File(
				"./OR/object_repository.properties"));
		Properties pro = new Properties();
		pro.load(fis);
		lxpath=(String) pro.get(key);
		Thread.sleep(2000);
	    element=ldriver.findElement(By.xpath(lxpath));
	    } 
	catch(FileNotFoundException e)
	{	
		System.out.println("OR File is missing.");
	}
	catch (InterruptedException e) {		
	}
	catch (IOException e) {
		System.out.println("file not loaded.");		
	}	
	catch (NoSuchElementException e) {
		
		//System.out.println(e.getMessage());
		//System.out.println("xpath not find/changed/");
		}

	return element;
}
//*****
public static WebElement checkElementOr(WebDriver ldriver,String key){
	
	WebElement element=null;
	boolean val_status=false;
	boolean val_status1=false;
	Wait.sleep(2000);
	int time=5;
	int TimeOut = time*1000;
	int Interval = 5000;
	int Temp = 0;
	do {
	   	try{
	   		FileInputStream fis = new FileInputStream(new File(
					"./OR/object_repository.properties"));
			Properties pro = new Properties();
			pro.load(fis);
			lxpath=(String) pro.get(key);
			Thread.sleep(2000);
		   // element=ldriver.findElement(By.xpath(lxpath));	   		
			val_status = ldriver.findElement(By.xpath(lxpath)).isDisplayed();
			val_status1 = ldriver.findElement(By.xpath(lxpath)).isEnabled();
           
			//ElementHighlight.higtLight(ldriver, lxpath);
			element=ldriver.findElement(By.xpath(lxpath));
		    } 
		catch(StaleElementReferenceException e){
		}
		catch (Throwable e) {       
		}
	   
		if(val_status&&val_status1) {
			break;
		}
		else {
			try {
				Thread.sleep(Interval);
			} catch (InterruptedException e) {
				
			}
			System.out.println("Waiting for element to appear");
			Temp = Interval + Temp;
		}
           
		if(Temp>=TimeOut){
			break;
		}
	} while(val_status!=true);

	return element;
}


//*************Select_From_Dropdown*****************************************************************
public static void selecFromDropDown(WebDriver ldriver,String xpath,String val) throws InterruptedException
{
	
	WebElement clickondropdown=ldriver.findElement(By.xpath(xpath));
	clickondropdown.click();
	Thread.sleep(2000);
	List <WebElement> options1 = ldriver.findElements(By.xpath("//td[@class='lsItemlistbox__content']"));
	                                                                         
	System.out.println("option"+options1.size());
	for (WebElement option : options1) 
	{
		//System.out.println("val"+val);
			if(option.getAttribute("innerHTML").contains(val))	   	
	        option.click();   
	}	
}

public  void DropDownbyText(WebDriver ldriver,WebElement Element,String val)
{
	
	Select Drop_Down_option=new Select(Element);
	Drop_Down_option.selectByVisibleText(val);
}
//*****************Wait************************************************************************
public static boolean isElementPresentByXpath(WebDriver ldriver,String key) 
{
		try 
		{
			FileInputStream fis = new FileInputStream(new File(
					"./OR/object_repository.properties"));
			Properties pro = new Properties();
			try {
				pro.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lxpath=(String) pro.get(key);
			//System.out.println("lxpath="+lxpath);
			Wait.sleep(3000);
		    element=ldriver.findElement(By.xpath(lxpath));	

		} catch (Exception e)
		{
			return false;
			//System.out.println("xpath not find/changed/");
		}
		
		return true;
	}

//*******************************************************************************************************************
public static String getScreenshot(WebDriver ldriver,String ScrPath) throws Exception 
{
        File scrFile = ((TakesScreenshot) ldriver).getScreenshotAs(OutputType.FILE);
     //The below method will save the screen shot in d drive with name "screenshot.png"
        Date date = new Date();
        String  destPath=ScrPath+"//"+date.getTime()+".png";     
        FileUtils.copyFile(scrFile, new File(destPath));
        return destPath;
}
//========================initReportPath=================================
public static ExtentReports initReportPath(String testName)
{ 

	    reportPath=System.getProperty("user.dir")+"\\target\\"+testName;
	    File index = new File(reportPath);
	    if (index.exists()) 
	    {
		     try 
		     {
				FileUtils.deleteDirectory(new File(System.getProperty("user.dir")+"//target//"+testName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	    }
		return (new ExtentReports(System.getProperty("user.dir")+"//target//"+testName+".html", true));
	 
}
//********************************************clickArrowButton**************************
public static void ClickArrowButton( WebDriver ldriver,int ArrowDown) 
{
	 Actions action = new Actions(ldriver);
	   for(int i=1;i<=ArrowDown;i++)
	   {
		action.sendKeys(Keys.ARROW_DOWN);
	   }
	   action.sendKeys(Keys.ENTER).click().build().perform();
	   
	    Wait.sleep(2000);
	   
   
}
//========================ResizeTheWindow======================================
public static void resizeWindow(WebDriver ldriver, int x,int y)
{
	Dimension dimension1 = new Dimension(x, y);
	ldriver.manage().window().setSize(dimension1);     	
}
public static WebElement checkElementPresense(WebDriver ldriver,WebElement element) throws InterruptedException
{
	Thread.sleep(1000);
    WebDriverWait wait=new WebDriverWait(ldriver, 60);
 
    wait.until(ExpectedConditions.visibilityOf(element));
    wait.until(ExpectedConditions.elementToBeClickable(element));
	
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	//return ldriver.findElement(By.xpath(xpath));
    return element;
}
//========================ResetThePage==================================================
public static void resetPage(WebDriver ldriver )
{
	clickOnElementUsingJavaScriptExecutor(ldriver, "//*[text()='Personalisierungsdaten zurücksetzen']", "xpath");
	ldriver.switchTo().frame("URLSPW-0");
	clickOnElementUsingJavaScriptExecutor(ldriver, "//*[text()='Kein Transportauftrag']/preceding::img[1]", "xpath");
	clickOnElementUsingJavaScriptExecutor(ldriver, "//*[text()='OK']", "xpath");
	ldriver.navigate().refresh();
	}

public static String getLocator(String key) throws IOException{
	FileInputStream fis = new FileInputStream(new File(
				"./OR/object_repository.properties"));
		Properties pro = new Properties();
		pro.load(fis);
		String locator = (String) pro.get(key);
		return locator;
	
}

public static void clickOnElementUsingJavaScriptExecutor(WebDriver ldriver, String key) {

	WebElement element = checkElementOr(ldriver, key);
	JavascriptExecutor jse = (JavascriptExecutor) ldriver;
	jse.executeScript("arguments[0].click();", element);

}

public static void clickOnElementUsingJavaScriptExecutor(WebDriver ldriver, WebElement ele) {
	
	JavascriptExecutor jse = (JavascriptExecutor) ldriver;
	jse.executeScript("arguments[0].click();", ele);

}

public static void clickOnElementUsingJavaScriptExecutor(WebDriver ldriver, String locator, String locatortype) {
	
    Wait.sleep(1000);
	WebElement element = ldriver.findElement(By.xpath(locator));
	JavascriptExecutor jse = (JavascriptExecutor) ldriver;
	jse.executeScript("arguments[0].click();", element);

}

/**It checks the display/visibility of an element on web page
 * @throws Exception */
public static boolean isElementVisible(WebDriver driver,String locator, String locatorType){
	boolean value;
	WebDriverWait wait = new WebDriverWait(driver, 20);
	try{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		driver.findElement(By.xpath(locator));
		return value = true;
	}
	catch(Exception exception){
		return value = false;
	}
}

public static void waitForelement(WebDriver driver, String locator, String locatortype){
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
	
}

public static void waitForelementToDisappear(WebDriver driver, String locator, String locatortype){
	WebDriverWait wait = new WebDriverWait(driver, 50);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
	
}

public static void waitForAttributeValueContains(WebDriver driver, String locator, String locatortype, String attribute, String value){
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.attributeContains(By.xpath(locator), attribute, value));
	
}

public static WebElement webElement(WebDriver ldriver,String locator, String locatortype){
	
	    try{
           
			element=ldriver.findElement(By.xpath(locator));
	    }
	    
	    catch(Exception e){
	    }
		    

	return element;
}

public static void enterText(WebDriver driver, String locator, String text, String locatortype){
	WebElement element = webElement(driver, locator, locatortype);
	element.clear();
	element.sendKeys(text);
		
}

public static void clickSomewhereOnWebpage(WebDriver driver, int x, int y){
	try{
	Actions action = new Actions(driver);
	Robot robot = new Robot();
	robot.mouseMove(x, y);
	action.sendKeys(Keys.ENTER);
	action.click().build().perform();
	}
	catch(AWTException e){
		
	}	
}

public static boolean isElementPresent(WebDriver driver, String locator, String locatortype){
	WebDriverWait wait = new WebDriverWait(driver, 20);
	try{
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		driver.findElement(By.xpath(locator)).isDisplayed();
		return true;
	}
	catch(Exception e){
		return false;
	}
	
}
public static void selectRadioButtonOrCheckbox(WebDriver driver,String locator, String locatortype){
	
	if((webElement(driver, locator, locatortype)).isSelected()==false){
		clickOnElementUsingJavaScriptExecutor(driver, locator, locatortype);
	}
	
}

public static void waitForFrameToAvoilableAndSwitchToIt(WebDriver driver, String frame) {
	WebDriverWait wait = new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
}

public static void clickEnterUsingRobotClass() throws Exception {
	Robot r = new Robot();
	r.keyPress(KeyEvent.VK_ENTER);
	r.keyRelease(KeyEvent.VK_ENTER);
}

public static void clickEnterUsingActionsClass(WebDriver driver) throws Exception {
	Actions a = new Actions(driver);
	a.sendKeys(Keys.ENTER);
}


}
