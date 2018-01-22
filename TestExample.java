package com.tests;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestExample {
	@Test(dataProvider="searchData")
	public static void searchInGoogle(String url, String testInput) {
		FirefoxDriver driver= new FirefoxDriver();
		driver.get(url);
		
		driver.findElement(By.id("gbqfq")).click();
		driver.findElement(By.id("gbqfq")).sendKeys(testInput);
		driver.findElement(By.xpath("//*[@id='gbqfb']/span")).click();
		
		//LinkedIn
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Facebook")));
	}
	@DataProvider(name = "searchData")
	public Object[][] LoggedinSearch() throws Exception{
		Object[][] retObjArr = getTableArray("TestData/inputTestData.xls", "testData");
		return(retObjArr);
	}

	public String[][] getTableArray(String xlFilePath, String sheetName) throws Exception
	{
		String[][] tabArray=null;
		int ci,cj;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			ci=0;
			tabArray = new String[sheet.getRows()-1][sheet.getColumns()];
			for (int rowVal = 1; rowVal < sheet.getRows(); rowVal++,ci++) {
				cj=0;
				for (int colVal = 0; colVal < sheet.getColumns(); colVal++,cj++) {
					tabArray[ci][cj]=sheet.getCell(colVal,rowVal).getContents();
					/* System.out.println("Tab Array values: "+tabArray[ci][cj]);*/
				}		 
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Thread.sleep(5000);
		return(tabArray);
	}
}