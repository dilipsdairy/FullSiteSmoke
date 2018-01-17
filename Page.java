package com.datadriven.java.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.logging.Logger;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;

//import org.apache.log4j.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.org.apache.bcel.internal.generic.Select;

/**
 * @author RD
 *
 */
public class Page {

	 public static Logger logger = Logger.getLogger(Page.class.getName());
	 public static  WebDriver driver;
     public static Properties props;
     public static final int MAX_WAIT_TIME_IN_MS = 220000;
     public static String base_url = null;
     public static String browsertype = null;
     
     public static String browserStack_platform= null;
     public static String browserStack_platform_version= null;
     public static String browserStack_browser= null;
     public static String browserStack_browser_version= null;
     
     
     public static  String dataPath=null;
 	 public static boolean isLoggedin=false; 
 	 public static String basicuser = null;
     public static String basicuserpwd = null;
   	 public static String screenShotPackageName=null;
 	 public static String chromeDriverPath=null;
 	 public static String screenShotsPath=null;

 	 public static String specifiPartnerToRun=null;
  	 public static String environmenttoRun=null;
  	 public static String environmentalSheetPickToRun=null;
  	public static String executionStartTime=null;
  	public static double strtTimeMillSec;

 	 @SuppressWarnings("rawtypes")
 	 public static Map screenDetails=null;
 	 public static String testReportsPath;
 	 
 	public static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
     static{
    	 try{
    		 loadProperties();
          }catch(Exception e){
        	  e.printStackTrace(); 
           }
     }
     //BrowserStack credentials
     public static final String USERNAME = "NoelleJansen";
     public static final String AUTOMATE_KEY = "DHkt8eHLDz4rYNxLseR5";
     public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

     
    public static void loadProperties(){
    	strtTimeMillSec = new Date().getTime();
    	
    		props = new Properties();
                     try{
                    	 props.load(new FileInputStream("src/com/datadriven/java/resources/config.properties"));
                     	 	 base_url = props.getProperty("baseUrl");
                     	 	 
                         //browsertype = props.getProperty("browser_type");
                    	 dataPath=props.getProperty("dataPath");
                    	 basicuser=props.getProperty("basic_user");
                    	 basicuserpwd=props.getProperty("basic_user_pwd");
                    	 chromeDriverPath=props.getProperty("Chrome_Driver_location");
                    	 System.out.println("---Properties file loaded successfully---");
                    	 
                     	// specifiPartnerToRun=props.getProperty("specifiPartnerToRun");
                    	 //*************Read Excel**************
                    	 Workbook wrk1 = null;
                   	     Map keyword = new HashMap();

                   	     wrk1 = Workbook.getWorkbook(new File("FullSite_Smoke_Driver.xls"));

                    	 //Obtain the reference to the first sheet in the workbook
                    	 Sheet sheet1 = wrk1.getSheet("EnvironmentSetup");

                    	 //Obtain reference to the Cell using getCell(int col, int row) method of sheet
                    	 Cell colArow1 = sheet1.getCell(0, 1);
                    	 Cell colBrow1 = sheet1.getCell(1, 1);
                    	 Cell colArow2 = sheet1.getCell(2, 1);
                    	 Cell colPlatform = sheet1.getCell(3, 1);
                    	 Cell colPlatformVer = sheet1.getCell(4, 1);
                    	 Cell colBrowser = sheet1.getCell(5, 1);
                    	 Cell colBrowserVersion = sheet1.getCell(6, 1);

                    	 //Read the contents of the Cell using getContents() method, which will return 
                    	 //it as a String
                    	 environmenttoRun = colArow1.getContents();
                    	 specifiPartnerToRun = colBrow1.getContents();
                    	 browsertype = colArow2.getContents();
                    	 browserStack_platform= colPlatform.getContents();
                    	 browserStack_platform_version = colPlatformVer.getContents();
                    	 browserStack_browser= colBrowser.getContents();
                   		 browserStack_browser_version= colBrowserVersion.getContents();
                   		 
                    	 System.out.println("Where to Run: "+browsertype);
                    	 System.out.println("Platform: "+browserStack_platform);
                    	 System.out.println("Platform Version: "+browserStack_platform_version);
                    	 System.out.println("Browser Type: "+browserStack_browser);
                    	 System.out.println("Browser version: "+browserStack_browser_version);
                    	 
                     }catch(Exception e)
                     {
                    	 System.out.println("---Properties file not loaded---");
                    	 e.printStackTrace();
                     }
                	 //*******Ended Reading Excel************
                 	
                	 if(environmenttoRun.equalsIgnoreCase("stg"))
                	 { 
                		 System.out.println("Environment: Staging");
                		 if(specifiPartnerToRun.equalsIgnoreCase("All")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details_Stg_All.xls";
                		 System.out.println("Runinging on STG partner sites");
                		 } else if(specifiPartnerToRun.equalsIgnoreCase("aa")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-AA.xls";
                    		 System.out.println("Runinging on STG AA site");
                		 } else if(specifiPartnerToRun.equalsIgnoreCase("iDine")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-iDine.xls";
                    		 System.out.println("Runinging on STG iDine site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ihg")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-IHG.xls";
                    		 System.out.println("Runinging on STG IHG site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("bb")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-BB.xls";
                    		 System.out.println("Runinging on STG MyBestBuy site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("or")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg_orbitz.xls";
                    		 System.out.println("Runinging on STG Orbitz site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("us")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-US.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ua")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-UA.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("sw")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-SW.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ak")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-AK.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("hh")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-HH.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("dl")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-DL.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("iDine-SW-IHG")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-iD-SW-IHG.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("iDine-US-HH")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-iD-US-HH.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("SPR")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-SPR.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ClubO")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-ClubO.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("eScrip")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-eScrip.xls";
                    		 System.out.println("Runinging on STG eSrip site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("Upromise")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-Upromise.xls";
                    		 System.out.println("Runinging on STG UPromise site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("GD")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-GD.xls";
                    		 System.out.println("Runinging on STG GoodDining site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("FRN")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Stg-FRN.xls";
                    		 System.out.println("Runinging on STG FRN site");
                		 }
                	 } 
                	 else if(environmenttoRun.equalsIgnoreCase("prod")) 
                	 { 
                		 System.out.println("Environment: Prod");
                		 if(specifiPartnerToRun.equalsIgnoreCase("All")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details_Prod_All.xls";
                		 System.out.println("Runinging on STG partner sites");
                		 } else if(specifiPartnerToRun.equalsIgnoreCase("aa")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-AA.xls";
                    		 System.out.println("Runinging on STG AA site");
                		 } else if(specifiPartnerToRun.equalsIgnoreCase("iDine")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-iDine.xls";
                    		 System.out.println("Runinging on STG iDine site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ihg")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-IHG.xls";
                    		 System.out.println("Runinging on STG IHG site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("bb")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-BB.xls";
                    		 System.out.println("Runinging on STG MyBestBuy site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("or")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod_orbitz.xls";
                    		 System.out.println("Runinging on STG Orbitz site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("us")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-US.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ua")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-UA.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("sw")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-SW.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ak")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-AK.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("hh")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-HH.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("dl")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-DL.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("iDine-SW-IHG")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-iD-SW-IHG.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("iDine-US-HH")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-iD-US-HH.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("eScrip")){
                			 environmentalSheetPickToRun="TestData/Stg_TestData/Login_Details-Prod-eScrip.xls";
                    		 System.out.println("Runinging on STG eSrip site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("Upromise")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-Upromise.xls";
                    		 System.out.println("Runinging on Prod UPromise site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("GD")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-GD.xls";
                    		 System.out.println("Runinging on Prod Good Dining site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("SPR")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-SPR.xls";
                    		 System.out.println("Runinging on Prod Good Dining site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ClubO")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-ClubO.xls";
                    		 System.out.println("Runinging on Prod Good Dining site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("FRN")){
                			 environmentalSheetPickToRun="TestData/Prod_TestData/Login_Details-Prod-FRN.xls";
                    		 System.out.println("Runinging on Prod FRN site");
                		 }
                	 }   
                	 else if(environmenttoRun.equalsIgnoreCase("qa")) 
                	 { 
                		 System.out.println("Environment: QA(trw)");
                		 if(specifiPartnerToRun.equalsIgnoreCase("All")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details_QA_All.xls";
                		 System.out.println("Runinging on STG partner sites");
                		 } else if(specifiPartnerToRun.equalsIgnoreCase("aa")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-AA.xls";
                    		 System.out.println("Runinging on STG AA site");
                		 } else if(specifiPartnerToRun.equalsIgnoreCase("iDine")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-iDine.xls";
                    		 System.out.println("Runinging on STG iDine site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ihg")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-IHG.xls";
                    		 System.out.println("Runinging on STG IHG site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("bb")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-BB.xls";
                    		 System.out.println("Runinging on STG MyBestBuy site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("or")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA_orbitz.xls";
                    		 System.out.println("Runinging on STG Orbitz site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("us")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-US.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ua")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-UA.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("sw")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-SW.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ak")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-AK.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("hh")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-HH.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("dl")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-DL.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("iDine-US-HH")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-iD-SW-IHG.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("iDine-SW-IHG")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-iD-US-HH.xls";
                    		 System.out.println("Runinging on STG US site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("SPR")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-SPR.xls";
                    		 System.out.println("Runinging on QA SPR site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("ClubO")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-ClubO.xls";
                    		 System.out.println("Runinging on QA ClubO site");
                		 }
                		 else if(specifiPartnerToRun.equalsIgnoreCase("FRN")){
                			 environmentalSheetPickToRun="TestData/QA_TestData/Login_Details-QA-FRN.xls";
                    		 System.out.println("Runinging on QA FRN site");
                		 }
                	 }
                	 System.out.println("---Properties file loaded successfully---");
            }
     
       				public  WebDriver getDriver(){
       					try{
       					if(browsertype.toLowerCase().contains("safari")){
       						driver = new SafariDriver();
         				}
     					if(browsertype.toLowerCase().contains("InternetExplorer")){
     						System.setProperty("webdriver.ie.driver","src/com/datadriven/java/resources/IEDriverServer.exe");
     						driver = new InternetExplorerDriver();
     					}
     					if(browsertype.toLowerCase().contains("chrome")){
         						System.setProperty("webdriver.chrome.driver","src/com/datadriven/java/resources/chromedriver.exe");
         						ChromeOptions options = new ChromeOptions();
         						options.addArguments("user-data-dir=C:/Users/dilip.siddanthapu/AppData/Local/Google/Chrome/User Data");
         						options.addArguments("--start-maximized");
         						driver = new ChromeDriver();
         						driver.manage().window().maximize();
     					}
     					if(browsertype.toLowerCase().contains("browserstack")){
								if( browserStack_platform .equalsIgnoreCase("Windows") || browserStack_platform .equalsIgnoreCase("OS X")){
		     						DesiredCapabilities caps = new DesiredCapabilities();
		     							caps.setCapability("os", browserStack_platform);
		     							caps.setCapability("os_version", browserStack_platform_version);	     						
		     							caps.setCapability("browser", browserStack_browser);
		     							caps.setCapability("browser_version", browserStack_browser_version);
		     							caps.setCapability("project", "Full_Site_Smoke_Tests");
		     							caps.setCapability("build", "FullSite_SmokeV0.1");
		     							caps.setCapability("acceptSslCert", "true");
		     							caps.setCapability("browserstack.debug", "true");
		     						driver = new RemoteWebDriver(new URL(URL), caps);
 								}
 							}
     					if(browsertype.toLowerCase().contains("firefox")){
             					driver = new FirefoxDriver();
     							/*FirefoxProfile profile = new FirefoxProfile(new File("D:\\SeleniumFirefoxProfiles"));                  
     							WebDriver driver = new FirefoxDriver(profile);*/
             					driver.manage().window().maximize();
    						
     					}
     					System.out.println("--Driver initialized--");
     				}catch(Exception e){
     					System.out.println("--Driver not initialized--");
     				}
     				return driver;
     					}
       			
       			
       					public static  void takeScreenshot(String imagename)
       					{
       						try {
       							File scrnsht =
       									((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       						String Screenshotfilename ="Screenshot/"+imagename+timeStamp+".png";
       					   
       						if(!new File(Screenshotfilename).exists()) {
       							FileUtils.copyFile(scrnsht, new	File(Screenshotfilename));
       							System.out.println("------------------->"+scrnsht);
       						} 
       							else{
       							new File(Screenshotfilename).delete();
       							FileUtils.copyFile(scrnsht, new	File(Screenshotfilename));
       							}
       						}
       						catch (Exception e) {
       							e.printStackTrace();
       						}
       					}		
     				
       					public static void pagerefreshfun()
       					{
       						driver.navigate().refresh();
       					}
       					
      				public  WebDriverWait getWebDriverWait(){
         			
     					return new WebDriverWait(driver,MAX_WAIT_TIME_IN_MS);
				}
      				public static void isTextPresent(String message , String Imagename)
      			    {
      			         try
      			         {
      			        	 if(driver.getPageSource().contains(message)){
      			        		takeScreenshot(Imagename);
      			        		    			                             }
      			             else{
      			            	
      			                 System.out.println("text did not present on the page");
      			                  }
      			         }
      			         catch (Exception e)
      			         {
      			    			           takeScreenshot(Imagename);
      			             			         }
      			    }
     				public String getCurrentMethodName() { 
     					StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace(); 
     					return stackTraceElements[1].getMethodName(); 
     				}
     				public static String getCurrenClassName() { 
     					StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace(); 
     					return stackTraceElements[1].getClassName(); 
   					 }
              }             