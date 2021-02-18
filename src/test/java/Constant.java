

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;*/

public class Constant {
	
	public static final String TestDataFilePath = "E:\\Selenium_Java\\FreeCrm_DDF\\src\\test\\resources\\TestData\\pep.xlsx";		
	public static final String Environment = "SIT";
//	public static final String PropertiesFilePath = TestDataFilePath;
	public static int SeqID = 1;
	public static int StepIndex = 0;
	public static int TestStepIndex = 0;
	public static int StepStatus = 0;
	public static int TestCaseIndex = 0;
	public static int TestCaseNumber = 0;
	public static int PassedCases = 0;
	public static int FailedCases = 0;
	public static HashMap<String, HashMap<String, String>> Map = new HashMap<String,HashMap<String,String>>();
	public static HashMap<String, HashMap<String, String>> Map2 = new HashMap<String,HashMap<String,String>>();
	public static final int defaultBrowserTimeOut = 30;
	public static String UserStoryName = null;
	public static String ResultFilePath = null;
	public static String ScreenshotFolderName = null;
	public static String strScenarioDesc=null;
	public static String strExpectedResult=null;
	public static String strActualResult=null;
	public static String PageName=null;
	public static String locator=null;
	public static String RecentScreenshot=null;
	public static WebDriver driver = null;
//	public static AppiumDriver driver = null;
//	public static AndroidDriver<MobileElement> driver = null;
	public static WebElement webelement;	
	public static boolean DefaultoptionalFlag = true;
	
    public static final String path_to_python_scripts="D:\\PDF\\Python27_Excel_PDF\\Python27_Excel_PDF\\";
	public static final String Device_Type="PC";
	
}
