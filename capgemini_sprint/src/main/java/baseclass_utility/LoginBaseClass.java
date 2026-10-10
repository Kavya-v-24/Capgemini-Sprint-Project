package baseclass_utility;

import java.io.IOException;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import generic_utility.CommonData;


public class LoginBaseClass extends CommonData{
	protected WebDriver driver;
	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void launchBrowser(@Optional("chrome") String browser) throws IOException {

	    if (browser.equalsIgnoreCase("chrome")) {
	    	// Avoid Change Password popup
			ChromeOptions settings = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.password_manager_leak_detection", false);
			settings.setExperimentalOption("prefs", prefs);
	        driver = new ChromeDriver(settings);
	    } else if (browser.equalsIgnoreCase("edge")) {
	        driver = new EdgeDriver();
	    } else {
	        throw new IllegalArgumentException("Unsupported browser: " + browser);
	    }

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void navigateApp() throws IOException
	{
		driver.get(getCommonData("url"));
	}
	

	
	@AfterClass(alwaysRun = true)
	public void closeBrowser()
	{
		driver.quit();
	}

	
}

