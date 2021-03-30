package StepsDefinations;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import IoTSense.Pages.BaseClass;
import IoTSense.Pages.Login_Logout_Page;
import IoTSense.Pages.SAct_TrigAlrt_Page;
import IoTSense.Utilities.ConfigDataProvider;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TriggerTest extends BaseClass{

	public ConfigDataProvider config;
	public Login_Logout_Page logInOut;
	public SAct_TrigAlrt_Page smartActionTriger;

	@Given("Launch The Application")
	public void launch_The_Application() {
		config = new ConfigDataProvider();
		driver.get(config.getStagingURL());
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@When("Login The Application With Sample Credential")
	public void login_The_Application_With_Sample_Credential() {
		logInOut = new Login_Logout_Page(driver);
		logInOut.LoginToApp(config.getUsername(), config.getPassword());
	}

	@Then("Selecting the Smart Action from Side Bar")
	public void selecting_the_Smart_Action_from_Side_Bar() {
		smartActionTriger = new SAct_TrigAlrt_Page(driver);
		smartActionTriger.SmartActionModule();
	}

	@Then("Selecting the Trigger & Alerts Option")
	public void selecting_the_Trigger_Alerts_Option() {
		smartActionTriger.TriggerModule();
		System.out.println(100/0);
	}

	/*
	 * @Then("To Create New Trigger Click on New") public void
	 * to_Create_New_Trigger_Click_on_New() { smartActionTriger.CreateNewTrigger();
	 * }
	 */

	@Then("Create New Trigger & Enter the Following Details")
	public void enter_the_Following_Details(DataTable dataTable) throws Exception   {
		List<Map<String,String>> dataList = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> e: dataList) {
			smartActionTriger.CreateNewTrigger();
			String Name= e.get("Name");
			String Thing= e.get("Thing");
			String Description= e.get("Description");
			String Condititon= e.get("Condititon");
			String Action= e.get("Action");
			smartActionTriger.AddTiggerData(Name, Thing, Description, Condititon, Action);
			smartActionTriger.SubmitData();

		}
		
	}

	/*
	 * @Then("Clicking on Submit to Create Trigger") public void
	 * clicking_on_Submit_to_Create_Trigger() {
	 * System.out.println("Clicked on Submit"); }
	 */

	@Then("Signout the Application")
	public void signout_the_Application() {
		logInOut.LogoutToApp();
	}
}
