package lib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DropDown
{
	public static  void selecFromDropDown(WebDriver ldriver,String xpath,String val) throws InterruptedException
	{
		
		WebElement clickondropdown=ldriver.findElement(By.xpath(xpath));
		clickondropdown.click();
		Thread.sleep(2000);
		List <WebElement> options1 = ldriver.findElements(By.xpath("//td[@class='lsItemlistbox__content urIlb2I urColorTxtStandard']"));
		//System.out.println("option"+options1.size());
		for (WebElement option : options1) 
		{
			//System.out.println("val"+val);
			if((option.getText()).equals(val))
				//if(option.getAttribute("innerHTML").contains(val))	   	
		        option.click();   
		}	
   }
	
	public  void DropDownbyText(WebDriver ldriver,WebElement Element,String val)
	{
		
		Select Drop_Down_option=new Select(Element);
		Drop_Down_option.selectByVisibleText(val);
	}
	
	public static void selecFromDropDownByElement(WebDriver ldriver,WebElement Element ,String val) throws InterruptedException
	{
		
		//WebElement clickondropdown=ldriver.findElement(By.xpath(xpath));
		Element.click();
		Thread.sleep(1000);
		List <WebElement> options1 = ldriver.findElements(By.xpath("//td[contains(@class,'lsItemlistbox__content')]"));	                                                           
		                                                                         
		//System.out.println("option"+options1.size());
		for (WebElement option : options1) 
		{
 
			if((option.getText()).equalsIgnoreCase(val))
		        option.click();
			Thread.sleep(1000);
		}	
   }
	
	public static void selecFromDropDownByElement(WebDriver ldriver,String key ,String val)  
	{
		
		WebElement Element=Utility.checkElementOr(ldriver, key);
		Element.click();		
		List <WebElement> options1 = ldriver.findElements(By.xpath("//td[contains(@class,'lsItemlistbox__content')]"));
		                                                                         
		                                                                         //lsItemlistbox__content urIlb2I urColorTxtStandard
		//td[@class='lsItemlistbox__content urColorTxtStandard']
		
		for (WebElement option : options1) 
		{
			if((option.getText()).equalsIgnoreCase(val))
			{
		        option.click(); 
			  break;
			}
			  
		}	
   }
	
	public static boolean selecFromDropDownByValue(WebDriver ldriver,String key ,String val)  
	{
		
		Utility.clickOnElementUsingJavaScriptExecutor(ldriver, key);		
		List <WebElement> allOptions = ldriver.findElements(By.xpath("//div[@class='lsListbox__value']"));
		System.out.println("options count is "+ allOptions.size());
		boolean dropDown = false;
		for (WebElement option : allOptions) 
		{
			System.out.println(option.getText());
			if((option.getText()).equalsIgnoreCase(val))
			{
				MouseActivity.mouseHoverAdvance(ldriver, option);
		        Utility.clickOnElementUsingJavaScriptExecutor(ldriver, option);
		        dropDown = true;
			  break;
			}
			  
		}	
		return dropDown;
   }
	
	public static boolean selecFromDropDownByValue1(WebDriver ldriver,String key ,String val)  
	{
		WebElement Element=Utility.checkElementOr(ldriver, key);
		Element.click();
		Element.sendKeys(Keys.TAB);	
		List <WebElement> allOptions = ldriver.findElements(By.xpath("//div[@class='lsListbox__value']"));
		System.out.println("options count is "+ allOptions.size());
		boolean dropDown = false;
		for (int i = 0; i< allOptions.size();i++) 
		{
			System.out.println(allOptions.get(i).getAttribute("innerHTML"));
			if((allOptions.get(i).getAttribute("innerHTML")).equalsIgnoreCase(val))
			{
				Element.click();
		        Utility.clickOnElementUsingJavaScriptExecutor(ldriver, allOptions.get(i));
		        dropDown = true;
			  break;
			}
			  
		}	
		return dropDown;
   }
	
	

}
