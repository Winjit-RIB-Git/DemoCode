package IoTSense.Utilities;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import IoTSense.Pages.BaseClass;

public class Helper extends BaseClass {
	private WebDriver driver;
	public JavascriptExecutor executor;
	private Select select;


	public Helper(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void JSElement_Click(WebElement element) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	@SuppressWarnings("deprecation")
	public void SelectFromDropDown(WebElement element, String value) {
		select = new Select(element);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		select.selectByVisibleText(value);
	}

	

}
