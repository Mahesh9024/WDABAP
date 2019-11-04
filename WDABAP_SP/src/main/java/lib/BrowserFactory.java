package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {
	
	static WebDriver driver;
	public static String browserName;

	
	public static WebDriver typeBrowser() throws IOException
	{
		
		File file = new File("C:\\Automation\\WDParameters.properties");
		FileInputStream fileInput = new FileInputStream(file);
		Properties props = new Properties();
		props.load(fileInput);
		browserName=props.getProperty("Browser");
		
		
		if (browserName.equalsIgnoreCase("firefox"))
		{
			String DRIVER_PATH = Utility.class.getClassLoader().getResource("geckodriver.exe").getFile(); 
			System.setProperty("webdriver.gecko.driver",DRIVER_PATH);
			FirefoxProfile profile=new FirefoxProfile();
			 
			// This will set the true value
			profile.setAcceptUntrustedCertificates(true);
			 
			// This will open  firefox browser using above created profile
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);
			driver=new FirefoxDriver(options);
	
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 1);
			// prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			 //   prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			
			ChromeOptions option = new ChromeOptions();
			option.setExperimentalOption("prefs", prefs);
			//option.addArguments("--disable-notifications");
			String DRIVER_PATH = Utility.class.getClassLoader().getResource("chromedriver.exe").getFile();
			System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
			driver=new ChromeDriver(option);
		}
		
		else if(browserName.equalsIgnoreCase("IE"))
		{
			String DRIVER_PATH = Utility.class.getClassLoader().getResource("IEDriverServer.exe").getFile();
			System.setProperty("webdriver.ie.driver", DRIVER_PATH);
			/*DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			dc.setCapability("nativeEvents", false);
			dc.setCapability("unexpectedAlertBehaviour", "accept");
			dc.setCapability("ignoreProtectedModeSettings", true);
			dc.setCapability("disable-popup-blocking", true);
			dc.setCapability("enablePersistentHover", true);
			dc.setCapability("ignoreZoomSetting", true);*/
			
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true); 
			driver = new InternetExplorerDriver(capabilities);
			
			//driver = new InternetExplorerDriver(capabilities);
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
//**********************************************************************************************************

public static void killBrowserProcess(String browser){
		
		if (browser.equalsIgnoreCase("chrome")) {
			try {
				WindowsUtils.killByName("chromedriver(2.23).exe");
				System.out.println("Chrome Driver process killed forcefully");
			} catch (Exception e) {
			} 
		}
		
		if (browser.equalsIgnoreCase("firefox")) {
			try {
				WindowsUtils.killByName("firefox.exe");
				System.out.println("Firefox Driver process killed forcefully");
			} catch (Throwable e) {
			} 
		}
		
		
		if (browser.equalsIgnoreCase("ie")) {
			try {
				WindowsUtils.killByName("IEDriverServer.exe");
				System.out.println("IE Driver process killed forcefully");
			} catch (Exception e) {

			} 
		}
	}
//============================================================================

		

}
