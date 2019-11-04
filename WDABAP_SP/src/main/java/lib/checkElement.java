package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class checkElement 
{
	public static boolean existElement( WebDriver driver,String lxpath)
	{
	    try 
	    {
	        driver.findElement(By.xpath(lxpath));
	        
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}

}
