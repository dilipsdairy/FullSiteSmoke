package com.datadriven.java.testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.datadriven.java.pages.SearchFuncationalityPage;
import com.datadriven.java.util.DataProviderClass;
import com.datadriven.java.util.Page;

public class SearchFuncationalityTest extends  DataProviderClass  {
	
		private WebDriver driver;
	   
		@BeforeClass
	public void setUp() throws Exception {
			driver = getDriver();
			}

		@Test(dataProvider = "HomeSearchUrl",description="Search from HOME")
		public void searchFuncationality(String baseurl, String searchCity) throws InterruptedException
		{ 	
			driver.get(baseurl);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			SearchFuncationalityPage sf =PageFactory.initElements(driver,SearchFuncationalityPage.class);
		    sf.searchFuncationality(searchCity);
		    sf.searchWithVenu(searchCity);
         }			

	@AfterClass
	  public void tearDown() throws Exception {
		 // driver.close();
		  driver.quit();
	  }		  
	  
}
