package IoTSense.Pages;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import IoTSense.Utilities.Helper;

public class SAct_TrigAlrt_Page extends BaseClass {
	private WebDriver driver;
	public Actions action;
	public JavascriptExecutor executor;
	private Helper helper;
	Properties pro;
	private String ele;

	public SAct_TrigAlrt_Page(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String Action_DataProvider(String element) {

		File src = new File("./Config/TrigActionConfig.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
			ele = pro.getProperty(element);
		} catch (Exception e) {
			System.out.println("Not able to load Trigger Action config file");		
		}
		return ele;
	}

	/*Smart Action Section------------------------------------------*/
	
	@FindBy(xpath = "//a[normalize-space()='Smart Actions']") WebElement SmartActClk;
	@FindBy(xpath = "//a[normalize-space()='Triggers & Alerts']") WebElement TrigAltClk;


	public void SmartActionModule() {
		ngWebDriver.waitForAngularRequestsToFinish();
		helper = new Helper(driver);
		helper.JSElement_Click(SmartActClk);
	}
	
	public void TriggerModule() {
		ngWebDriver.waitForAngularRequestsToFinish();
		helper.JSElement_Click(TrigAltClk);

	}

	/*Create New Trigger---------------------------------------------*/
	
	@FindBy(xpath = "//a[normalize-space()='New']") WebElement NewClk;

	public void CreateNewTrigger() {
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		helper.JSElement_Click(NewClk);

	}

	/*Add Trigger Page-----------------------------------------------------*/
	
	@FindBy(xpath = "//input[@id='name']") WebElement nameIn;
	@FindBy(xpath = "//select[@id='deviceName']") WebElement ThingSelect;
	@FindBy(xpath = "//textarea[@id='desc']") WebElement descriptionIn;
	@FindBy(xpath = "//input[@id='condition']") WebElement conditionIn;

	/*Action ---> REST*/
	@FindBy(xpath = "//select[@id='method']") WebElement mtdSelect;
	@FindBy(xpath = "//input[@id='url']") WebElement urlIn;

	/*Action ---> MQTT*/
	@FindBy(xpath = "//input[@value='MQTT']") WebElement mqttClk;
	@FindBy(xpath = "//input[@id='topic']") WebElement topicIn;
	@FindBy(xpath = "//select[@id='qos']") WebElement QosSelect;
	/*--->Retain*/
	@FindBy(xpath = "//label[normalize-space()='TRUE']") WebElement trueClk;
	@FindBy(xpath = "//label[normalize-space()='FALSE']") WebElement falseClk;
	/*--->Payload Type*/
	@FindBy(xpath = "//label[normalize-space()='JSON']") WebElement JsonClk;
	@FindBy(xpath = "//label[normalize-space()='MESSAGE']") WebElement msgClk;	
	@FindBy(xpath = "//textarea[@placeholder='message...']") WebElement msgIn_1;

	/*Action ---> EMAIL*/
	@FindBy(xpath = "//input[@value='EMAIL']") WebElement emailClk;
	@FindBy(xpath = "//input[@id='to']") WebElement emailAddIn;
	@FindBy(xpath = "//input[@id='subject']") WebElement subjectIn;
	@FindBy(xpath = "//textarea[@id='message']") WebElement msgIn_2;

	/*Action ---> SYNC*/
	@FindBy(xpath = "//input[@value='SYNC']") WebElement syncClk;
	@FindBy(xpath = "//select[@id='syncOption']") WebElement Tgt_Syn_Opt_Select;
	@FindBy(css= "#includeDeviceId") WebElement thingIDChk;
	@FindBy(css= "#includeTimestamp") WebElement timestempChk;
	@FindBy(css= "#includeAllTags") WebElement inclAllTagChk;

	/*Action ---> SMS*/
	@FindBy(xpath = "//input[@value='SMS']") WebElement smsClk;
	@FindBy(xpath = "//input[@name='mobileNumber']") WebElement MobNumIn;
	@FindBy(xpath = "//textarea[@id='smsMessage']") WebElement smsMsgIn;

	/*Action ---> ModBus*/
	@FindBy(xpath = "//input[@value='Modbus']") WebElement modBusClk;
	@FindBy(xpath = "//select[@id='modbusSlave']") WebElement ModBusSlv_Select;
	@FindBy(xpath = "//input[@type='number']") WebElement coilAddIn;
	@FindBy(xpath = "//label[normalize-space()='TRUE']") WebElement trueClk_2;
	@FindBy(xpath = "//label[normalize-space()='FALSE']") WebElement falseClk_2;


	public void AddTiggerData(String Name, String Thing, String Description, String Condititon, String Action) throws Exception {
		nameIn.sendKeys(Name);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		helper.SelectFromDropDown(ThingSelect, Thing);
		descriptionIn.sendKeys(Description);
		conditionIn.sendKeys(Condititon);
		switch (Action.toUpperCase()) {
		case "REST": 
		{
			Thread.sleep(5000);
			helper.SelectFromDropDown(mtdSelect, Action_DataProvider("method"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(5000);
			urlIn.sendKeys(Action_DataProvider("url"));
			Thread.sleep(5000);
			IncludeInData(Action_DataProvider("IncludeInData"));
			Thread.sleep(5000);
			break;

		}
		case "MQTT":
		{
			mqttClk.click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			topicIn.sendKeys(Action_DataProvider("Topic"));
			helper.SelectFromDropDown(QosSelect, Action_DataProvider("QoS"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(Action_DataProvider("Retain").equalsIgnoreCase("TRUE")) {
				trueClk.click();
			}
			else {
				falseClk.click();
			}
			if(Action_DataProvider("PayloadType").equalsIgnoreCase("JSON")) {
				JsonClk.click();
			}
			else {
				msgClk.click();
			}
			IncludeInData(Action_DataProvider("IncludeInData"));
			break;
		}
		case "EMAIL":
		{
			emailClk.click();
			emailAddIn.sendKeys(Action_DataProvider("EmailAdd"));
			subjectIn.sendKeys(Action_DataProvider("Subject"));
			msgIn_2.sendKeys(Action_DataProvider("Message"));
			break;	
		}
		case "SYNC":
		{
			syncClk.click();
			helper.SelectFromDropDown(Tgt_Syn_Opt_Select,Action_DataProvider("TargetSynOpt"));
			IncludeInData(pro.getProperty("IncludeInData"));
			break;	
		}

		case "SMS":
		{
			smsClk.click();
			MobNumIn.sendKeys(Action_DataProvider("MobNumber"));
			smsMsgIn.sendKeys(Action_DataProvider("Message_1"));
		}

		case "Modbus":
		{
			System.out.println("Not Designed yet,..!");
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + Action.toUpperCase());
		}
	}

	public void IncludeInData(String value) {

		switch (value) {
		case "ThingID": {
			thingIDChk.click();
			break;
		}
		case "Timestamp":
			timestempChk.click();
			break;
		case "Specify Datatags":
			inclAllTagChk.click();
			break;		
		default:
			throw new IllegalArgumentException("Unexpected value: " + value);
		}

	}

	@FindBy(xpath = "//button[normalize-space()='Submit']") WebElement submitClk;

	public void SubmitData() {
		submitClk.click();
	}







}
