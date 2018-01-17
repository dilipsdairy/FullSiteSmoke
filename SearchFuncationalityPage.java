package com.datadriven.java.pages;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.datadriven.java.util.PageActions;

public class SearchFuncationalityPage extends PageActions {
	
	
	public void searchFuncationality(String parameter) throws InterruptedException{
		if(driver.getCurrentUrl().contains("upromise")){
			validation=doAction(LocationField,TEXT,parameter);
			if(validation.equals("failed")){
				takeScreenshot("FTC-Location");
				Assert.fail(getCurrentMethodName()+" -Failed to enter Location.");
			}	
				validation=doAction(HomeSearchButton,CLICK,"No Param Req");
				if(validation.equals("failed")){
					takeScreenshot("FTC-SearchBtn");
					Assert.fail(getCurrentMethodName()+" -Failed to click Search button.");
				}
			//Wait for results to load
			WebDriverWait wait=new WebDriverWait(driver, 1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingImage")));		
			WebElement tabElement=driver.findElement(By.id("srTable"));
			wait.until(ExpectedConditions.visibilityOf(tabElement));
			
			validation=doAction(up_MerchantLink_On_SearchResults,CLICK,"No Param Req");
			if(validation.equals("failed")){
				takeScreenshot("FTC-MerchantLink");
				Assert.fail(getCurrentMethodName()+" -Failed to click on Merchant link on Search results(Upromise) page.");
			}
			
		} else{
			validation=doAction(LocationField,TEXT,parameter);
			if(validation.equals("failed")){
				takeScreenshot("FTC-Location");
				Assert.fail(getCurrentMethodName()+" -Failed to enter Location.");
			}	
				validation=doAction(HomeSearchButton,CLICK,"No Param Req");
				if(validation.equals("failed")){
					takeScreenshot("FTC-SearchBtn");
					Assert.fail(getCurrentMethodName()+" -Failed to click Search button.");
				}
			//Wait for results to load
			WebDriverWait wait=new WebDriverWait(driver, 1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingImage")));		
			WebElement tabElement=driver.findElement(By.id("srTable"));
			wait.until(ExpectedConditions.visibilityOf(tabElement));
			
			List tableLinks=tabElement.findElements(By.tagName("a"));
			
			//wait.until(ExpectedConditions.elementToBeClickable(((WebElement)tableLinks.get(0))));
			//((WebElement)tableLinks.get(0)).click();
			for(int i=0;i<tableLinks.size();i++)
			{
				WebElement linkElement=(WebElement)tableLinks.get(i);
				String hrefValue=linkElement.getAttribute("href");
				
				//System.out.println("hrefValue---->"+hrefValue);
				if(hrefValue!=null)
				{
					if(hrefValue.contains(parameter.split(",")[1].toUpperCase()))
						{
							WebElement linkElementsfp=(WebElement)tableLinks.get(i+1);
							//System.out.println("*********hrefValue---->"+linkElementsfp.getAttribute("href"));
							wait.until(ExpectedConditions.elementToBeClickable((linkElementsfp)));
							linkElementsfp.click();
							validation=doAction(BreadcrumbLinkOnMerchantDetails,CLICK,"No Param Req");
							if(validation.equals("failed")){
								takeScreenshot("FTC-breadcrumb");
								Assert.fail(getCurrentMethodName()+" -Failed to click Breadcrumb on Merchant Details.");
							}
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingImage")));	
							WebElement tabElement1=driver.findElement(By.id("srTable"));
							wait.until(ExpectedConditions.visibilityOf(tabElement1));
							break;
						}
				}
			}
		}
		}
	public void searchWithVenu(String parameter) throws InterruptedException{
		
		if(driver.getCurrentUrl().contains("upromise")){
			validation=doAction(LocationField,TEXT,parameter);
			if(validation.equals("failed")){
				takeScreenshot("FTC-Location");
				Assert.fail(getCurrentMethodName()+" -Failed to enter Location.");
			}	
				validation=doAction(HomeSearchButton,CLICK,"No Param Req");
				if(validation.equals("failed")){
					takeScreenshot("FTC-SearchBtn");
					Assert.fail(getCurrentMethodName()+" -Failed to click Search button.");
				}
			//Wait for results to load
			WebDriverWait wait=new WebDriverWait(driver, 1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingImage")));		
			WebElement tabElement=driver.findElement(By.id("srTable"));
			wait.until(ExpectedConditions.visibilityOf(tabElement));
			
			validation=doAction(up_MerchantLink_On_SearchResults,CLICK,"No Param Req");
			if(validation.equals("failed")){
				takeScreenshot("FTC-MerchantLink");
				Assert.fail(getCurrentMethodName()+" -Failed to click on Merchant link on Search results(Upromise) page.");
			}
		} else{
		validation=doAction(LocationField,TEXT,"New York,NY");
		if(validation.equals("failed")){
			takeScreenshot("FTE-Location");
			Assert.fail(getCurrentMethodName()+" -Failed to enter Location");
			}
				//need to add this parameter on datasheet later
				String venueName="Akbar";
				validation=doAction(LookingForField,TEXT,venueName);
				if(validation.equals("failed")){
					takeScreenshot("FTE-LookingFor");
					Assert.fail(getCurrentMethodName()+" -Failed to enter Looking for field.");
				}	
					validation=doAction(HomeSearchButton,CLICK,"No Param Req");
					if(validation.equals("failed")){
						takeScreenshot("FTC-Search");
						Assert.fail(getCurrentMethodName()+" -Failed to click Search submit button.");
					}
				//Wait for results to load
				WebDriverWait wait=new WebDriverWait(driver, 30000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loadingImage")));		
				WebElement tabElement=driver.findElement(By.id("srTable"));
				wait.until(ExpectedConditions.visibilityOf(tabElement));
				
				List tableLinks=tabElement.findElements(By.tagName("a"));
				
				//wait.until(ExpectedConditions.elementToBeClickable(((WebElement)tableLinks.get(0))));
				//((WebElement)tableLinks.get(0)).click();
				for(int i=0;i<tableLinks.size();i++)
				{
					WebElement linkElement=(WebElement)tableLinks.get(i);
					String hrefValue=linkElement.getAttribute("href");
					
					//System.out.println("hrefValue---->"+hrefValue);
					if(hrefValue!=null)
					{
						if(hrefValue.contains(parameter.split(",")[1].toUpperCase()))
						{
							WebElement linkElement1=(WebElement)tableLinks.get(i+1);
							//System.out.println("hrefValue---->"+linkElement1.getAttribute("href"));
							wait.until(ExpectedConditions.elementToBeClickable((linkElement1)));
							linkElement1.click();
							//takeScreenshot("ForSpecificMerchant");
							break;
						}
					}
				}
		   }
		}
}
