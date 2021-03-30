package Hooks;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import IoTSense.Pages.BaseClass;
import IoTSense.Utilities.ConfigDataProvider;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class TriggerTestHook {

	private WebDriver driver;
	public ConfigDataProvider config;

	@Before("@Sanity")
	public void setDriver() {
		config = new ConfigDataProvider();
		BaseClass.BrowserFactory(config.getBrowser());
	}
	@After("@Sanity")
	public void closeDriver(Scenario scenario) throws IOException {
		BaseClass.tearDown(scenario);
		BaseClass.BrowserClose();
	}

}

