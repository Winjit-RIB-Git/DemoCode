package IoTSense.Pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;
import com.paulhammant.ngwebdriver.NgWebDriver;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static NgWebDriver ngWebDriver;
	public static JavascriptExecutor jsDriver;
	public JavascriptExecutor executor;

	public static WebDriver BrowserFactory(String BrowserName) {

		if(BrowserName.equals("Chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito","--disable-notifications");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			jsDriver =(JavascriptExecutor)driver;
			ngWebDriver = new NgWebDriver(jsDriver);
			driver.manage().window().maximize();
		}
		else if(BrowserName.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			jsDriver =(JavascriptExecutor)driver;
			ngWebDriver = new NgWebDriver(jsDriver);
			driver.manage().window().maximize();

		}
		else {
			System.out.println("Driver Not Supported");
		}

		return driver;

	}
	public static  WebDriver tearDown(Scenario scenario) {

		try {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			if (scenario.isFailed()) {
				scenario.log("this is my failure message");
				TakesScreenshot ts = (TakesScreenshot) driver;
				byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", screenshotName);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		driver.close();
		return driver;
	}

	public static WebDriver BrowserClose() {
		driver.close();
		return driver;
	}

	public static NgWebDriver WaitForSynchronized() {
		ngWebDriver.waitForAngularRequestsToFinish();
		return ngWebDriver;
	}
	
	public static String getCurrentDataTime() {
		String dateName = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss").format(new Date());
		return dateName;
	}
}
