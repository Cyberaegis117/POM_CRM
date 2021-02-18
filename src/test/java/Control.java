/*
 *Description: Control Functions library 
'Author :Sunanda Tirunagari & Ankit Kumar
 */



//import static org.testng.Assert.assertTrue;



//import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.net.URL;
import java.net.UnknownHostException;
//import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.HasInputDevices;
//import org.openqa.selenium.interactions.Keyboard;
//import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.RemoteWebDriver;

/*import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;*/

public class Control {

	public static void delete(File file) throws IOException {
		if (file.isDirectory()) { 
			if (file.list().length == 0) {
				file.delete();
			} else {
				// list all the directory contents
				String files[] = file.list();
				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);
					// recursive delete
					delete(fileDelete);
				}
				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					//System.out.println("Directory is deleted : "
						//	+ file.getAbsolutePath());
				}
			}
		} else {
			// if file, then delete it
			file.delete();
		}
	}
	public static void deleteTempFile() throws UnknownHostException {
		String property = "java.io.tmpdir";
		String temp = System.getProperty(property);
		File directory = new File(temp);
		try {
			delete(directory);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	public static void OpenApplication(String browserName, String URL) throws UnknownHostException {
		//deleteTempFile();
		
        if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\geckodriver.exe");
            Constant.driver= new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
		            System.setProperty("webdriver.SafariDriver.driver", "C:\\SeleniumDrivers\\SafariDriver.exe");
		            Constant.driver = new SafariDriver();
		} else if (browserName.equalsIgnoreCase("Chrome")) {
		            System.setProperty("webdriver.chrome.driver", "E:\\Selenium_Java\\FreeCrm_DDF\\src\\test\\resources\\Drivers\\chromedriver.exe");
		            Constant.driver= new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
		            System.setProperty("webdriver.ie.driver", "C:\\SeleniumDrivers\\IEDriverServer.exe");
		            Constant.driver =  new InternetExplorerDriver();
		            Constant.driver.manage().deleteAllCookies();
		} else if (browserName.equalsIgnoreCase("Opera")) {
					OperaOptions options = new OperaOptions();
					options.setBinary("C:\\SeleniumDrivers\\opera.exe");
					System.setProperty("webdriver.opera.driver", "C:\\SeleniumDrivers\\operadriver.exe");
					Constant.driver = new OperaDriver(options);
		} else if (browserName.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\SeleniumDrivers\\MicrosoftWebDriver.exe");
            Constant.driver = new EdgeDriver();

}
        Constant.driver.manage().timeouts().implicitlyWait(Constant.defaultBrowserTimeOut, TimeUnit.SECONDS);
        Constant.driver.manage().deleteAllCookies();
        Constant.driver.manage().deleteAllCookies();
        Constant.driver.manage().window().maximize();

        try {
                        //Thread.sleep(10000);
                        Constant.driver.get(URL);            
                        Generic.WriteTestData("Open the Browser and URL",browserName,URL,"Able to Open the URL '"+URL+"' in Browser '"+browserName+"'","Opened  the URL '"+URL+"' in the Browser '"+browserName+"' successfully","Passed");
                        } catch (Exception e) {    
        }
	}
	public static void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "border: 2px solid DeepPink;");
 	}
	
	
	
	public static WebElement findElement(String PageName, String locatorName) throws Exception   {
		
		String locator,locatorTag,objectLocator;
		//Generic.RepositoryCreation();
		locator= Constant.Map.get(PageName).get(locatorName);
		System.out.println(Constant.Map.get(PageName).get(locatorName));
		if (locatorName != null) {
			System.out.print(locator);
			String[] arrLocator = locator.split("#");
			 locatorTag = arrLocator[0].trim();
			 objectLocator = arrLocator[1].trim();
				System.out.print(locatorTag);
				System.out.println(objectLocator);	
			try {
				if (locatorTag.equalsIgnoreCase("id")) {
					Constant.webelement = Constant.driver.findElement(By.id(objectLocator));
					highlightElement(Constant.driver, Constant.webelement);
				} else if (locatorTag.equalsIgnoreCase("name")) {
					Constant.webelement = Constant.driver.findElement(By.name(objectLocator));
					highlightElement(Constant.driver, Constant.webelement);
				} else if (locatorTag.equalsIgnoreCase("xpath")) {
					Constant.webelement = Constant.driver.findElement(By.xpath(objectLocator));
					highlightElement(Constant.driver, Constant.webelement);
				} else if (locatorTag.equalsIgnoreCase("linkText")) {
					Constant.webelement = Constant.driver.findElement(By.linkText(objectLocator));
					highlightElement(Constant.driver, Constant.webelement);
				} else if (locatorTag.equalsIgnoreCase("Text")) {
					Constant.webelement = Constant.driver.findElement(By.partialLinkText(objectLocator));
					highlightElement(Constant.driver, Constant.webelement);
				} else if (locatorTag.equalsIgnoreCase("class")) {
					Constant.webelement = Constant.driver.findElement(By.className(objectLocator));
					highlightElement(Constant.driver, Constant.webelement);
				} else if (locatorTag.equalsIgnoreCase("tagName")) {
					Constant.webelement = Constant.driver.findElement(By.tagName(objectLocator));
					highlightElement(Constant.driver, Constant.webelement);	
				} else if (locatorTag.equalsIgnoreCase("css")) {
					Constant.webelement = Constant.driver.findElement(By.cssSelector(objectLocator));
					highlightElement(Constant.driver, Constant.webelement);
				} else {
					String error = "Please Check the Given Locator Syntax :"+ locator;
					error = error.replaceAll("'", "\"");
					return null;
				}
			} catch (Exception exception) {
				/*String error = "Please Check the Given Locator Syntax :"
						+ locator;
				error = error.replaceAll("'", "\"");
								exception.printStackTrace();*/
				return null;
			}
		}
		return Constant.webelement;
	}
	public static String getAttribute(String PageName,String locator, String attributeName) {
		String attributeValue = null;
		try {

			WebElement element = findElement( PageName,locator);
			if (element != null)
				attributeValue = element.getAttribute(attributeName);
			element = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return attributeValue;
	}
	public static void enterText(String PageName, String locator, String value) throws Exception {
		Constant.PageName=PageName;
		Constant.locator=locator;
		try {
			WebElement element = findElement(PageName,locator);
			if (element != null) {
				Capabilities caps = ((RemoteWebDriver) Constant.driver).getCapabilities();
				  String browserName = caps.getBrowserName();			
				if (browserName.equalsIgnoreCase("internet explorer")) {	
					element.sendKeys(value);
					//JavascriptExecutor js = (JavascriptExecutor) Constant.driver;	
					//js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, "value",value);
				}
				else
					element.sendKeys(value);
				
					Generic.WriteTestData("Entering text '"+value+"' in '"+locator+"' text field",locator,value,"Should able to enter data'"+value+"' into  '"+locator+"' text field","Entered data'"+value+"' into  '"+locator+"' text field successfully","Passed");
			}else {
				Generic.WriteTestData("Entering text '"+value+"' in '"+locator+"' text field",locator,value,"Unable to identify the '"+locator+"' text field","Not able to identify the  '"+locator+"' text field","Failed");
			}
			
			element = null;
		} catch (Exception e) {
			Generic.WriteTestData("Entering text '"+value+"' in '"+locator+"' text field",locator,value,"Should able to enter data'"+value+"' into  '"+locator+"' text field","Not able to enter data'"+value+"' into  '"+locator+"' text field successfully","Failed");
			e.printStackTrace();
		}
		
	}
	public static void click(String PageName, String locator) throws Exception {
		Constant.PageName=PageName;
		Constant.locator=locator;
		
		try {
			WebElement element = findElement(PageName,locator);
			if (element != null) {
				element.click();	
				Generic.WriteTestData("Click on '"+locator+"'",locator,"","Able to click on '"+locator+"' button.","Clicked on '"+locator+"' button.","Passed");
			}
			else {
				Generic.WriteTestData("Click on '"+locator+"'",locator,"","Element should be available '"+locator+"' button.","Element was not found  '"+locator+"'","Failed");
			}
			element = null;		

		} catch (Exception e) {
			Generic.WriteTestData("Click on '"+locator+"'",locator,"","Able to click on '"+locator+"' button.","Clicking on '"+locator+"' button is unsuccessful.","Failed");
			System.out.println(" Error occured whlie click on the element "
					+ locator + " *** " + e.getMessage());
			
		}
		
	}
	
	public static void hover(String PageName, String locator) throws Exception {
		Constant.PageName=PageName;
		Constant.locator=locator;
		
		try {
			WebElement element = findElement(PageName,locator);
			Actions action = new Actions(Constant.driver);
			if (element != null) {
				action.moveToElement(element).build().perform();
				Generic.WriteTestData("Hover on '"+locator+"'",locator,"","Able to Hover on '"+locator+"' button.","Hovered on '"+locator+"' .","Passed");
			}
			else {
				Generic.WriteTestData("Hover on '"+locator+"'",locator,"","Element should be available '"+locator+"' .","Element was not found  '"+locator+"'","Failed");
			}
			element = null;		

		} catch (Exception e) {
			Generic.WriteTestData("Hover on '"+locator+"'",locator,"","Able to Hover on '"+locator+"' .","Hover on '"+locator+"'  is unsuccessful.","Failed");
			System.out.println(" Error occured whlie Hover on the element "
					+ locator + " *** " + e.getMessage());
			
		}
		
	}
	
	public static void GoToUrl(String url) {
		try {
			Constant.driver.get(url);			
			} catch (Exception e) {

		}
	}
	public static void listSelect(String PageName, String locator, String listValue) {
		Constant.PageName=PageName;
		Constant.locator=locator;
		
		try {
			WebElement element = findElement(PageName,locator);
		
			if (element != null) {
				Select dropdown= new Select(element);
				dropdown.selectByVisibleText(listValue);
				element.click();			
				Generic.WriteTestData("select from '"+locator+"'",locator,"","Able to select '"+listValue+"' from '"+locator+ "'" ,"Able to select '"+listValue+"' from '"+locator ,"Passed");
			}
			else {
				
				Generic.WriteTestData("select from '"+locator+"'",locator,"","Able to select '"+listValue+"' from '"+locator+ "'" ,"Not able to select '"+listValue+"' from '"+locator ,"Failed");
			}
			element = null;		

		} catch (Exception e) {
			System.out.println(" Error occured whlie click on the element "
					+ locator + " *** " + e.getMessage());
			
		}
		
	}
	public static void Checkbox(String PageName, String locator, String CheckBoxVal) {
		Constant.PageName=PageName;
		Constant.locator=locator;
		boolean boxVal ;
		if (CheckBoxVal == "ON") {
			boxVal = true;
		}
		else {
				boxVal = false;
		}
		
		boolean bValue = false;
		try {
			WebElement element = findElement(PageName,locator);
			
			//WebElement element = findElement(PageName,locator);
			if (element != null) {
				bValue =  element.isSelected();
				if (bValue != boxVal) {
					element.click();	
				}
				Generic.WriteTestData("Click on '"+locator+"'",locator,"","Able to click on '"+locator+"' Checkbox/RadioButton.","Clicked on '"+locator+"' Checkbox/RadioButton.","Passed");
			}
			else {
				
				Generic.WriteTestData("Click on '"+locator+"'",locator,"","Able to click on '"+locator+"' Checkbox/RadioButton.","Clicking on '"+locator+"' Checkbox/ is unsuccessful.","Failed");
			}
			element = null;		

		} catch (Exception e) {
			System.out.println(" Error occured whlie click on the element "
					+ locator + " *** " + e.getMessage());
			
		}
		
	}	
	public static void RadioButton(String PageName, String locator) {
		Constant.PageName=PageName;
		Constant.locator=locator;
		//System.out.println("inside radio button Function");
		//System.out.println(locator);
		try {
			WebElement element = findElement(PageName,locator);
			if (element != null) {
				element.click();	
				Generic.WriteTestData("Click on '"+locator+"'",locator,"","Able to click on '"+locator+"' button.","Clicked on '"+locator+"' button.","Passed");
			}
			else {
				
				Generic.WriteTestData("Click on '"+locator+"'",locator,"","Able to click on '"+locator+"' button.","Clicking on '"+locator+"' button is unsuccessful.","Failed");
			}
			element = null;		

		} catch (Exception e) {
			System.out.println(" Error occured whlie click on the element "
					+ locator + " *** " + e.getMessage());
			
		}
	}

	public static void compareText(String PageName,String locator,String ExpectedText) throws Exception{
        Thread.sleep(1000);
         Constant.PageName=PageName;
         Constant.locator=locator;
         String text = null;
        
         WebElement element = findElement(PageName,locator);   
         //
         System.out.println(locator);
         if(element==null)
         {
        	 element = findElement(PageName,locator); 
         }
        try {
            if (element != null)                
                text = element.getText();
          //  System.out.println(text);
            if(ExpectedText.equals(text)) {
                Generic.WriteTestData("Comparing text of field '"+locator+"' in Page '"+PageName,locator,ExpectedText,"The ExpectedText '"+ExpectedText+"' should be equal to Actual text from Application","The ExpectedText '"+ExpectedText+"' is same as the Acutal Text '"+text+"'","Passed");
            }
            else if(text.contains(ExpectedText)) {
                Generic.WriteTestData("Comparing text of field '"+locator+"' in Page '"+PageName,locator,ExpectedText,"The ExpectedText '"+ExpectedText+"' should be equal to Actual text from Application","The ExpectedText '"+ExpectedText+"' is same as the Acutal Text '"+text+"'","Passed");
            }
            else
            {
            	//Control.takeScreenshot();
            	Generic.WriteTestData("Comparing text of field '"+locator+"' in Page '"+PageName,locator,ExpectedText,"The ExpectedText '"+ExpectedText+"' should be equal to Actual text from Application","The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'","Failed");
            }
            
        }catch (Exception e) {
        	//Control.takeScreenshot();
        	Generic.WriteTestData("Comparing text of field '"+locator+"' in Page '"+PageName,locator,ExpectedText,"The ExpectedText '"+ExpectedText+"' should be equal to Actual text from Application","The ExpectedText '"+ExpectedText+"' is not same as the Acutal Text '"+text+"'","Failed");
            e.printStackTrace();
        }
        element = null;
        
        
    }    
    public static void objProperty(String PageName,String locator,String ExpectedPropertyToBeVerified, String ExpectedPropertyValue) throws Exception {
        Constant.PageName=PageName;
        Constant.locator=locator;
        String text = null;
    
        WebElement element = findElement(PageName,locator);
        try {
            if (element != null)    
            
                text = element.getAttribute(ExpectedPropertyToBeVerified);
            if(ExpectedPropertyValue.equalsIgnoreCase(text)) {
                Generic.WriteTestData("Comparing property of field '"+locator+"' in Page '"+PageName+"'",locator,ExpectedPropertyValue,"The Expected property '"+ExpectedPropertyValue+"' should be equal to Actual property from Application","Acutal Property '"+text+"' equals to Expected Property '"+ExpectedPropertyValue+"'","Passed");
            }
            
            else
            {
                takeScreenshot();
                Generic.WriteTestData("Comparing property of field '"+locator+"' in Page '"+PageName+"'",locator,ExpectedPropertyValue,"The Expected property '"+ExpectedPropertyValue+"' should be equal to Actual property from Application","Acutal property '"+text+"' is NOT equals to Expected property '"+ExpectedPropertyValue+"'","Failed");
            }            
        }catch (Exception e) {
            e.printStackTrace();
        }
        element = null;
    }
    public static void objExists(String PageName,String locator,boolean ExistsOnPage) throws Exception {
        
        Constant.PageName=PageName;
        Constant.locator=locator;
        boolean text;
        WebElement element = findElement(PageName,locator);
        try {
            //if (element != null)   
            	text = element.isDisplayed();
                //text = element.getAttribute("aria-disabled");
            if(ExistsOnPage) {
                if(text== true) {
                    Generic.WriteTestData("Check visibility of locator '"+locator+"' in Page '"+PageName+"'",locator,"","'"+locator+"' should be visible on the Application","'"+locator+"' is visbile on the page '"+PageName+"'","Passed");    
                }
                else
                {
                    takeScreenshot();
                    Generic.WriteTestData("Check visibility of locator '"+locator+"' in Page '"+PageName+"'",locator,"","'"+locator+"' should be visible on the Application","'"+locator+"' is Not visbile on the page '"+PageName+"'","Failed");
                }                
            }            
            else
            {
                if(text = true) {
                    takeScreenshot();
                    Generic.WriteTestData("Check visibility of locator '"+locator+"' in Page '"+PageName+"'",locator,"","'"+locator+"' should not be visible on the Application","'"+locator+"' is visbile on the page '"+PageName+"'","Failed");
                }
                else
                {
                    Generic.WriteTestData("Check visibility of locator '"+locator+"' in Page '"+PageName+"'",locator,"","'"+locator+"' should not be visible on the Application","'"+locator+"' is not visbile on the page '"+PageName+"'","Passed");
                }
                
            }        
        }catch (Exception e) {
            e.printStackTrace();
            Generic.WriteTestData("Check visibility of locator '"+locator+"' in Page '"+PageName+"'",locator,"","'"+locator+"' should be visible on the Application","'"+locator+"' is Not visbile on the page '"+PageName+"'","Failed");
            
        }
        element = null;
    }
    public static String HandleWebAlert(boolean Accept) throws Exception{
    	Thread.sleep(20000);
        Alert alert = Constant.driver.switchTo().alert();
        String textonalert = alert.getText();
        if(Accept) { 
            alert.accept();
//            Generic.WriteTestData("Check visibility of locator '"+locator+"' in Page '"+PageName+"'",locator,"","'"+locator+"' should not be visible on the Application","'"+locator+"' is not visbile on the page '"+PageName+"'","Passed");
        }
        else
        {
            alert.dismiss();
        }
        return textonalert;
        
    }
    public static void takeScreenshot()throws Exception {
    	takeScreenshot(Constant.DefaultoptionalFlag);
    }
    public static void takeScreenshot(boolean optionalFlag)throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Date date= new Date();
		String Date1 = dateFormat.format(date);
		DateFormat dateFormat1 = new SimpleDateFormat("HHMMSS");
		Date date2= new Date();
		String screenshotFilePath;
		String Date3 = dateFormat1.format(date2); 		
		 screenshotFilePath=Constant.ScreenshotFolderName+File.separator+Constant.PageName+"_" + Constant.locator + "_"+Date1+"_"+Date3+".png";
		Constant.RecentScreenshot = Constant.PageName+"_" + Constant.locator + "_"+Date1+"_"+Date3+".png";
		try{                                        
	        TakesScreenshot scrShot = ((TakesScreenshot)Constant.driver);
	        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
	        File DestFile = new File(screenshotFilePath);
	        FileHandler.copy(srcFile,DestFile);	        
			    if(optionalFlag) {
			        Generic.setScreenshothyperlink(screenshotFilePath);}
			    else {
			    	Constant.DefaultoptionalFlag = true;
			    	
			    }
	         }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

   /* public static void LaunchMobileApp(String BrowserName,String Version, String deviceName, String platformName, String appPackage, String appActivity,String ServerIP, String HostID, String URL ) throws Exception{
    	DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Version);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        if(!BrowserName.equals("")) {
        	capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserName);
        }

        //capabilities.setCapability("acceptInsecureCerts", true);
       // capabilities.setCapability("acceptSslCerts", true);
        if(!appPackage.equals("")) {
        capabilities.setCapability("appPackage",appPackage );
        capabilities.setCapability("appActivity",appActivity);
        }   
        capabilities.setCapability("noReset", true);
        Constant.driver = new AndroidDriver(new URL("http://"+ServerIP+":"+HostID+"/wd/hub"), capabilities);
        Constant.driver.manage().timeouts().implicitlyWait(Constant.defaultBrowserTimeOut, TimeUnit.SECONDS);    
        if(!BrowserName.equals("")) {
        	Constant.driver.get(URL);
        }
         
    }*/
    
    public static void getMessageContent(String PageName, String locator) throws Exception {
    	 Constant.PageName=PageName;
         Constant.locator=locator;
         String Messagecontent = null;
         WebElement element = findElement(PageName,locator);
         try {
 			if (element != null) {
 				Messagecontent = element.getText();	
 				Generic.WriteTestData("Able to get the text '"+locator+"'",locator,"","Able to fetch  '"+locator+"'","text value is : "+Messagecontent,"Passed");
 			}
 			else {
 				
 				Generic.WriteTestData("Not Able to get the text '"+locator+"'",locator,"","Not Able to fetch  '"+locator+"'","text value is not found :","Failed");
 			}
 			element = null;		

 		} catch (Exception e) {
 			System.out.println(" Error occured whlie click on the element "
 					+ locator + " *** " + e.getMessage());
 			
 		}
    	    	
    }
    
	/*public static void DriverScriptForUSSD(int TotalTestCases) throws Exception {    	
			String InputValue=null;
			String CheckValue=null; 
			String InputValue2 = null;
			for(int RowNo=1; RowNo<=TotalTestCases;RowNo++) {
				Generic.WriteTestCase(Constant.Map2.get("TestCase"+ RowNo).get("TestCaseNo"), Constant.Map2.get("TestCase"+ RowNo).get("TestCaseName"), Constant.Map2.get("TestCase"+ RowNo).get("Expected_result"), Constant.Map2.get("TestCase"+ RowNo).get("Actual Result"));
				click("DialerApp", "btnNumbers");    		
				for(int Col_input= 1; Col_input<=20; Col_input++) {
					InputValue=Constant.Map2.get("TestCase"+ RowNo).get("Input_"+Col_input);
					
					if(InputValue.equals("")) {
						break;
						}
					WebElement element = findElement("DialerApp","tfldDialling");
					if (element != null) { 
							enterText("DialerApp","tfldDialling",InputValue);
							takeScreenshot();
							click("DialerApp","tfldDialling");
							click("DialerApp","btnDialler");
							takeScreenshot();
						}else{
						  	click("DialerApp","Input_field");
							enterText("DialerApp","Input_field",InputValue);
							takeScreenshot();
							click("DialerApp","SendButton");
						    takeScreenshot();
					    }	
					    
						CheckValue=Constant.Map2.get("TestCase"+ RowNo).get("CheckPoint_"+Col_input);
						System.out.println("\n************\n"+CheckValue+"\n************\n");
						compareText("DialerApp_Response","txtOutputMessage",CheckValue);
						takeScreenshot();
					}	  
						WebElement element = findElement("DialerApp","okButton");
					if (element==null) {
						element = findElement("DialerApp","okButton");	
					}
					if (element != null) {
						click("DialerApp","okButton");
					}
					else {
						click("DialerApp","CancelButton");
					     }
					//write 
					
					String Unsubscribe_KW = Constant.Map2.get("TestCase"+ RowNo).get("Unsubscribe_Via_SMS");
					String Notification = Constant.Map2.get("TestCase"+ RowNo).get("SMS_Notification");
					
					if(Unsubscribe_KW != "") {
						Control.LaunchMobileApp("", "8.1.0", "OnePlus5T", "Android", "com.android.mms", "com.android.mms.ui.ConversationList", "10.225.138.136", "4723","");
						Control.click("MessageNotification", "messgeMenu");
						Control.enterText("MessageNotification", "messageEditBox", Unsubscribe_KW);
						Control.click("MessageNotification", "messageSendButton");
						Thread.sleep(1000);
						Control.compareText("MessageNotification", "messagetext", Notification);
					}else {
						Control.LaunchMobileApp("", "8.1.0", "OnePlus5T", "Android", "com.android.mms", "com.android.mms.ui.ConversationList", "10.225.138.136", "4723","");
						Control.click("MessageNotification", "messgeMenu");
						Control.compareText("MessageNotification", "messagetext", Notification);
						//Control.getMessageContent("MessageNotification", "messagetext");
					}
						Generic.TestScriptEnds();
						Thread.sleep(10000);
			}
		}*/
    
	/*public static void DriverScriptForUSSD1(int TotalTestCases) throws Exception {    	
		String InputValue=null;
		String CheckValue=null; 
		String InputValue2 = null;
		for(int RowNo=1; RowNo<=TotalTestCases;RowNo++) {
			Generic.WriteTestCase(Constant.Map2.get("TestCase"+ RowNo).get("TestCaseNo"), Constant.Map2.get("TestCase"+ RowNo).get("TestCaseName"), Constant.Map2.get("TestCase"+ RowNo).get("Expected_result"), Constant.Map2.get("TestCase"+ RowNo).get("Actual Result"));
			String Unsubscribe_KW = Constant.Map2.get("TestCase"+ RowNo).get("Unsubscribe_Via_SMS");
			String Notification = Constant.Map2.get("TestCase"+ RowNo).get("SMS_Notification");
			if(Unsubscribe_KW != "") {
				LaunchMobileApp("",Generic.ReadFromExcel("DeviceDetails","pep",1),Generic.ReadFromExcel("DeviceDetails","pep",2) , "Android", Generic.ReadFromExcel("DeviceDetails","pep",3), Generic.ReadFromExcel("DeviceDetails","pep",4),Generic.ReadFromExcel("DeviceDetails","pep",5) , "4723","");
				Control.click("MessageNotification", "messageSenderIcon");
				Control.enterText("MessageNotification", "noEnterText", "8080");
				Control.enterText("MessageNotification", "messageEditBox", Unsubscribe_KW);
				Control.click("MessageNotification", "messageSendButton");
				takeScreenshot();
//				 TouchAction Ac = new TouchAction(Constant.driver);
//				 Ac.longPress(Constant.driver.findElementByXPath("\\android.widget.TextView[@text='"+Unsubscribe_KW+"']")).release().perform();
				//longPress("MessageNotification", "messagetext");
				Control.click("MessageNotification", "deleteMessage");
				Control.click("MessageNotification", "deleteConfirmation");
				Thread.sleep(6000);
				//Control.click("MessageNotification", "messgeMenu");
				Control.compareText("MessageNotification", "messagetext", Notification);
				takeScreenshot();
				longPress("MessageNotification", "messagetext");
				Control.click("MessageNotification", "deleteMessage");
				Control.click("MessageNotification", "deleteConfirmation");
			}else {
//				Date date= new Date();
//		    	long time1 = date.getTime();
//		    	Timestamp ts = new Timestamp(time1);
				LaunchMobileApp("", Generic.ReadFromExcel("DeviceDetails","pep",1),Generic.ReadFromExcel("DeviceDetails","pep",2), "Android", "com.simpler.dialer", "com.simpler.ui.activities.ContactsAppActivity", Generic.ReadFromExcel("DeviceDetails","pep",5), "4723","");
				click("DialerApp", "DialPad"); 
				for(int Col_input= 1; Col_input<=20; Col_input++) {
					InputValue=Constant.Map2.get("TestCase"+ RowNo).get("Input_"+Col_input);
					if(InputValue.equals("")) {
						break;
						}
					WebElement element = findElement("DialerApp","Dialer");
					if (element != null) { 
							enterText("DialerApp","Dialer",InputValue);
							takeScreenshot();
							click("DialerApp","Dialer");
							click("DialerApp","btnDialler");
							takeScreenshot();
						}else{
						  	click("DialerApp","InputOption");
							enterText("DialerApp","InputOption",InputValue);
							takeScreenshot();
							click("DialerApp","Send");
						    takeScreenshot();
					    }	
						CheckValue=Constant.Map2.get("TestCase"+ RowNo).get("CheckPoint_"+Col_input);
						//System.out.println("\n************\n"+CheckValue+"\n************\n");
						compareText("DialerApp_Response","txtOutputMessage",CheckValue);
						takeScreenshot();
					}	
					WebElement element = findElement("DialerApp","ok");
					if (element==null) {
						element = findElement("DialerApp","ok");	
					}
					if (element != null) {
						click("DialerApp","ok");
					}
					else {
						click("DialerApp","Cancel");
					     }	
				Control.LaunchMobileApp("",Generic.ReadFromExcel("DeviceDetails","pep",1),Generic.ReadFromExcel("DeviceDetails","pep",2), "Android", Generic.ReadFromExcel("DeviceDetails","pep",3), Generic.ReadFromExcel("DeviceDetails","pep",4), Generic.ReadFromExcel("DeviceDetails","pep",5), "4723","");
				Control.click("MessageNotification", "messgeMenu");
				Control.compareText("MessageNotification", "messagetext", Notification);
				takeScreenshot();
				longPress("MessageNotification", "messagetext");
				Control.click("MessageNotification", "deleteMessage");
				Control.click("MessageNotification", "deleteConfirmation");
			}		
			Generic.TestScriptEnds();
			Thread.sleep(10000);
		}
	}*/
	
	public static void scroll(String PageName, String locator) throws Exception {
		 Constant.PageName=PageName;
         Constant.locator=locator;
         WebElement element = findElement(PageName,locator);
         try {
 			if (element != null) {
 				JavascriptExecutor js = (JavascriptExecutor) Constant.driver; 
 				js.executeScript("arguments[0].scrollTop = arguments[1];",element, 250); 
 				Thread.sleep(1000); 
 				Generic.WriteTestData("Able to Scroll '"+locator+"'",locator,"","Able to Scroll  '"+locator+"'","Successfully scroll operation is performed","Passed");
 			}
 			else {
 				
 				Generic.WriteTestData("Not Able to Scroll '"+locator+"'",locator,"","Not Able to Scroll '"+locator+"'","Scroll operation is not performed","Failed");
 			}
 			element = null;		

 		} catch (Exception e) {
 			System.out.println(" Error occured whlie click on the element "
 					+ locator + " *** " + e.getMessage());
 			
 		}
	}

	
	public static void longPress(String PageName, String locator) {
//		Constant.PageName=PageName;
//        Constant.locator=locator;
//        WebElement element = findElement(PageName,locator);
//        //uncomment for mobile device testing
//        TouchAction Ac = new TouchAction(Constant.driver);
//        
//        try {
//			if (element != null) {
//				Ac.longPress(element).release().perform();
//				//Generic.WriteTestData("Able to get the text '"+locator+"'",locator,"","Able to fetch  '"+locator+"'","text value is : "+Messagecontent,"Passed");
//			}
//			else {
//				
//				//Generic.WriteTestData("Not Able to get the text '"+locator+"'",locator,"","Not Able to fetch  '"+locator+"'","text value is not found :","Failed");
//			}
//			element = null;		
//
//		} catch (Exception e) {
//			System.out.println(" Error occured whlie click on the element "
//					+ locator + " *** " + e.getMessage());
//			
//		}
//		
	}
	
	
	public void GeneratePDFReport()
	{
		String excel_path=Constant.ResultFilePath.substring(0,Constant.ResultFilePath.lastIndexOf("\\"))+"\\";
		System.out.println("Path to Result : "+excel_path);
       // JavaReport jr=new JavaReport();
       // jr.GenerateReport(Constant.path_to_python_scripts, Constant.ResultFilePath, excel_path, Constant.ResultFilePath, "Report_Test_Summary"+".pdf");
	}
	
	  
}   


