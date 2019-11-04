package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseActivity 
{
public static void mouseHoverAdvance(WebDriver driver,WebElement ele){
		
		Actions act=new Actions(driver);
		act.moveToElement(ele).build().perform();
		
	}
public static void mouseClickAdvance(WebDriver driver,WebElement ele) throws InterruptedException{
	
	Actions act=new Actions(driver);
	act.moveToElement(ele).click();
	Thread.sleep(3000);
	
}
//=====================================================================================================
public static void pressEnter(WebDriver driver,WebElement ele)
   {
	Actions act=new Actions(driver);
	act.moveToElement(ele).click();
	act.sendKeys(Keys.ENTER).click().build().perform();
	}
//===========================================rightClick===========================================
public static void rightClick(WebDriver driver,WebElement ele)
   {
	//WebElement elee = driver.findElement(By.xpath("//span[contains(text(),'FlowLayout 2')]/preceding::input[2]"));
	Actions action = new Actions(driver);
	action.contextClick(ele).build().perform();
	//action.moveToElement(elee, elee.getLocation().x, elee.getLocation().y).contextClick().build().perform();
	//action.contextClick(driver.findElement(By.xpath("//span[contains(text(),'FlowLayout 2')]/preceding::input[2]"))).build().perform();
	//action.moveToElement(ele).sendKeys(Keys.ENTER);
	//action.contextClick();
	//action.build().perform();
	
   }
public static void rightClick(WebDriver driver,String key) 
{
	Actions action = new Actions(driver).contextClick(Utility.checkElementOr(driver, key));
	action.build().perform();
	
	try {
		Thread.sleep(2000);
	} catch (StaleElementReferenceException e) 
	{
	
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
//=========================ByElement======================
public static void rightClickByElement(WebDriver driver,WebElement ele) 
{
	Actions action = new Actions(driver).contextClick(ele);
	action.build().perform();
	
	try {
		Thread.sleep(2000);
	} catch (StaleElementReferenceException e) 
	{
	
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void doubleClick(WebDriver driver,WebElement ele)
{
	Actions act=new Actions(driver);
	act.doubleClick(ele).build().perform();
	}

}
