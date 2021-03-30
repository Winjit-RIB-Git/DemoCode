package IoTSense.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import IoTSense.Utilities.Helper;
public class Login_Logout_Page extends BaseClass {
	private WebDriver driver;
	private Helper helper;

	public Login_Logout_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#username") WebElement unameIn;
	@FindBy(css = "#password") WebElement pwordIn;
	@FindBy(css ="button[type='submit']")  WebElement LoginClk;

	public void LoginToApp(String username, String password) {
		try {

			unameIn.sendKeys(username);
			pwordIn.sendKeys(password);
			BaseClass.WaitForSynchronized();
			helper = new Helper(driver);
			helper.JSElement_Click(LoginClk);
		}
		catch (Exception e) {
			System.out.println("Unable to Login==> "+e);		
		}
	}

	@FindBy(xpath= "//a[@aria-haspopup='true']") WebElement SuperAdminClk;
	@FindBy(xpath = "//a[normalize-space()='Signout']")  WebElement SignoutClk;

	public void LogoutToApp() {
		try {
			Thread.sleep(6000);
			SuperAdminClk.click();
			ngWebDriver.waitForAngularRequestsToFinish();
			SignoutClk.click();
		}
		catch (Exception e) {
			System.out.println("Unable to logout from appplication:==> "+e);
		}
	}
}
