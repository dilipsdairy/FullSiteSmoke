package com.datadriven.java.util;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * @author RD
 *
 */
public class PageActions extends Page{
	
	public static final int MAX_WAIT_TIME_IN_MS = 220000;
	public static final String TEXT = "textField";
	public static final String CLICK = "click" ;
    public static final String SUBMIT = "submit" ;
	public static final String PRESENT = "present" ;
	public static final String PARAMETERNOTREQUIRED = "notrequired" ;
	public static String validation ="failed" ;
	public static boolean flag =false ;
	public static String textValidation ="failed";

	//Simple Search fields 
	@FindBy(id="CityAjax")
	public static  WebElement LocationField;
	@FindBy(id="merchantSearch")
	public static  WebElement LookingForField; 
	@FindBy(id="cszSubmitButton")
	public static  WebElement HomeSearchButton; 
	
	//UP - merchant link on search results page
	@FindBy(xpath="html/body/div[4]/div[5]/div[5]/table[1]/tbody/tr[5]/td[2]/div[1]/a")
	public static  WebElement up_MerchantLink_On_SearchResults; 
	
	//Adv Search functionalities 
	@FindBy(xpath="//*[@id='advlink']/a")
	public static WebElement AdvSearchLinkOnHomePage;
	@FindBy(xpath="//*[@id='CityAjax3']")
	public static WebElement SearchFieldOnAdvSearchPage;
	@FindBy(xpath="//*[@id='proximity']/tbody/tr[2]/td[2]/select")
	public static WebElement ProximityDropdownOnAdvSearchPage;	
	@FindBy(xpath="//*[@id='submitGeneralSearchForm']")
	public static WebElement SearchSubmitOnAdvSearchPage;
	
	//Search Results page
	@FindBy(linkText="Search Results")
	public static WebElement Back2SearchResultsFromMDetailsPage;
	
	
	
	//Merchant Details
	@FindBy(xpath="//*[@id='topspan']/div[1]/a[2]")
	public static  WebElement BreadcrumbLinkOnMerchantDetails;
	
	//Search results 
	@FindBy(xpath="//input[starts-with(@id, 'serialNumber')]")
	public static  WebElement SearchResultsSNumber; 
	@FindBy(xpath="//input[starts-with(@id, 'tableName')]")
	public static  WebElement SearchResultsMerchantLink; 
	//@FindBy(xpath="//*[@id='tableName49587']/a")
	@FindBy(xpath="//input[starts-with(@id, 'tableName')]/a[1]")
	public static  WebElement SearchResultsMerchantHyderLink; 
	//Verify Menu links
	@FindBy(xpath="//*[@id='whyjoin']/a")
	public static  WebElement HomePageLinkOnMenu;
	@FindBy(xpath="//*[@id='home']/a")
	public static  WebElement HomePageLinkOnMenuLoggedIn;
	@FindBy(xpath="//*[@id='howitworks']/a")
	public static  WebElement HIWPageLinkOnMenu;
	@FindBy(xpath="//*[@id='faqs']/a")
	public static  WebElement FAQPageLinkOnMenu;
	@FindBy(xpath="//*[@id='bonuses']/a")
	public static  WebElement BonusPageLinkOnMenu;
	@FindBy(id="bonuses")
	public static  WebElement VerifyBonusPageLinkOnMenu;
	@FindBy(xpath="//*[@id='joinnow']/a")
	public static  WebElement SignUpPageLinkOnMenu;
	@FindBy(xpath="//*[@id='headerlinkhome']")
	public static  WebElement SiteLogoLink;
	
	// Footer links  
	@FindBy(linkText="FAQ")
	public static  WebElement FooterFAQLink;
	@FindBy(linkText="Privacy Policy")
	public static  WebElement FooterPPLink;
	@FindBy(linkText="Terms & Conditions")
	public static  WebElement FooterTCLink;
	@FindBy(linkText="Contact Us")
	public static  WebElement FooterContactUsLink;
	@FindBy(linkText="About Us")
	public static  WebElement FooterAboutUsLink;
	
	//Enrollment fields
	@FindBy(xpath="//*[@id='firstName']")
	public static  WebElement EnrolFirstNameField;
	@FindBy(xpath="//*[@id='lastName']")
	public static  WebElement EnrolLastNameField;
	@FindBy(xpath="//*[@id='zipcode']")
	public static  WebElement EnrolZipCodeField;
	@FindBy(xpath="//*[@id='email']")
	public static  WebElement EnrolEmailfield;
	@FindBy(xpath="//*[@id='emailConfirm']")
	public static  WebElement EnrolConfirmEmailfield;
	
	//Login functionality
	@FindBy(xpath="//*[@id='lbcid']")
	public static WebElement LoginButtonTopRight;
	@FindBy(xpath="//input[@id='loginId' and @value='']")
	public static WebElement LoginPageLoginField;
	@FindBy(xpath="//form[@id='loginform']/input[2]")
	public static WebElement LoginPagePwdField;
	@FindBy(xpath="//input[@value='Login']")  //  //input[@value='Login']
	public static WebElement LoginPageSubmitButton;
	// reset password fields   
			@FindBy(xpath="//input[@id='password' and @value='']")  
			public static WebElement pwdResetField;
			@FindBy(xpath="//input[@id='confirmPassword' and @value='']")  
			public static WebElement ConfpwdResetField;
			@FindBy(xpath="//*[@id='updatePwd']/div[2]/div[2]/input")  
			public static WebElement PwdResetSubmitBtn;
			@FindBy(linkText="Click here to go to your homepage.")  
			public static WebElement AfterPwdResetHomeLinkTxt;	
//Incorrect login: error message  
	@FindBy(xpath="//*[@id='loginErrorMsg']/div")    
	public static WebElement ErrorWhenLoginFailed; 			

//Logged in view(Account Center)
	@FindBy(xpath="//*[@id='myaccountcenter']/a")
	public static WebElement AccountCenterLink;
	@FindBy(xpath="//*[@id='but2_center']/a")
	public static WebElement AccountInfoLink;
	@FindBy(xpath="//*[@id='userUpdate']/table[1]/tbody/tr[5]/td[2]/span")
	public static WebElement AccountInfoChangeLink;
	@FindBy(xpath="//*[@id='firstName']")
	public static WebElement AccountInfoFirstNameField;
	@FindBy(css="input.pad_t10")
	public static WebElement AccountInfoSubmitButton;
	
	@FindBy(xpath="//*[@id='but3_center']/a")
	public static WebElement ACMyPrefeTab;
	@FindBy(xpath="//*[@id='but4_center']/a")
	public static WebElement ACBenefitsHistoryTab;
	//Logout
	@FindBy(xpath="//*[@id='logoutid']") //*[@id='logoutid']
	public static WebElement ACLogOutButton;
	@FindBy(linkText="LOGOUT") 
	public static WebElement ACLogOutTxtLink;
	
	//Forgot login id & password
	@FindBy(xpath="//*[@id='loginform']/table[2]/tbody/tr/td/a[1]")
	public static WebElement LoginIDRequestLink;
	@FindBy(xpath="//*[@id='emailAddress']")
	public static WebElement ForgotLoginIDEmailAddress;
	@FindBy(xpath="//*[@id='column1']/table/tbody/tr[5]/td/input")
	public static WebElement ForgotLoginIDContiButton;
	
	@FindBy(xpath="//*[@id='loginform']/table[2]/tbody/tr/td/a[2]")
	public static WebElement PasswordRequestLink;
	@FindBy(xpath="//input[@id='loginId' and @value='']")
	public static WebElement PasswordRequestLoginID;
	@FindBy(xpath="//*[@id='emailAddress' and @value='']")
	public static WebElement PasswordRequestEmailAddress;
	@FindBy(xpath="//*[@id='remindPassword']/div[4]/input")  
	public static WebElement PasswordRequestContiButton;
	
	//Account Center Add Address
	//@FindBy(linkText="Add Search Address »")
	@FindBy(xpath="//*[@id='right_info']/div[4]/a")
	public static WebElement ACAddAddressLink;
	@FindBy(xpath="//form[@id='addressForm']/table/tbody/tr/td/input[1]")
	public static WebElement ACAddressField;
	@FindBy(xpath="//form[@id='addressForm']/table/tbody/tr/td/input[3]")
	public static WebElement ACCityField;
	@FindBy(xpath="//form[@id='addressForm']/table/tbody/tr/td/select")
	public static WebElement ACStateField;
	@FindBy(xpath="//form[@id='addressForm']/table/tbody/tr/td/input[4]")
	public static WebElement ACZipField;
	@FindBy(xpath="//form[@id='addressForm']/table/tbody/tr/td/input[6]") 
	public static WebElement ACAddressSaveButton;
	
	//Delete address
	@FindBy(xpath="//*[@id='right_info']/div[3]/div[2]/div[2]/a[2]") 
	public static WebElement AC_Delete_Address;

	@FindBy(xpath="//*[@id='userUpdate']/span") 
	public static WebElement ACAddressUpdateSuccessMsg;
	

		/**
	 * @param element
	 * @param action
	 * @param Option
	 */

			
	public static String doAction(WebElement element,String action,String parameter){
		           try{
		        	 //  InternalSystemClock.pauseFor(1000);
		        	   if(element!=null && parameter!=null){
		        		   	   if(action.equals(PRESENT)){
		        				   validation="passed";
		        			   }else if(action.equals(TEXT)){
		        				   element.clear();
		        				   element.sendKeys(parameter);
		        				   validation="passed";
		        			   }else if(action.equals(CLICK) || action.equals(SUBMIT) ){
		        				   element.click();
			        	    		validation="passed";
			        	    		  }
		        	   		}
		        	   else{
		        	   			validation="failed";
		        	   		}
		        	   	        	   
		          }catch(Exception e){
		        	  e.printStackTrace();
		        	  validation ="failed";  
		           }
		return validation;
	}

		protected static void getString(WebElement element) {
	    try {
	    	
	    	element.getText();
	      
	    } catch (NoSuchElementException e) {
	     e.printStackTrace();
	    }
	  }	
	public WebDriverWait getWebDriverWait(){
			
			return new WebDriverWait(driver,MAX_WAIT_TIME_IN_MS);
    }  
	public static String  VerifyTxt(WebElement locationPnt, String specifytext)
	{  
		String ElemntTxt = locationPnt.getText().replaceAll("\\s","");
		String expectedTxt = specifytext.replaceAll("\\s","");
		
			if(locationPnt.getText().equalsIgnoreCase(specifytext))
		//if(elementTxt.getText().equalsIgnoreCase(text))   --  WebElement elementTxt
			{
				textValidation="passed";
				//System.out.println("Verification text is Passed: "+specifytext);
				//System.out.println("*******getText*****"+locationPnt.getText());
			}
			else if(expectedTxt.contains(ElemntTxt)){
				textValidation="passed";
				//System.out.println("Element txt: "+ElemntTxt);
				//System.out.println("Expected txt: "+expectedTxt);
			}
			else if(expectedTxt.contains(specifytext)){
				textValidation="passed";
				//System.out.println("Element txt: "+ElemntTxt);
				//System.out.println("Expected txt: "+expectedTxt);
			}
			else
			{
				textValidation="failed";
				//System.out.println("Verification text is Failed: "+specifytext);
				//System.out.println("*******getText*****"+locationPnt.getText());
			}
			return textValidation;
	}  

		
}

