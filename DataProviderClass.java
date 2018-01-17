package com.datadriven.java.util;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import org.testng.annotations.DataProvider;


public class DataProviderClass extends Page {

	//final String environmentalSheetPickToRun = "src/com/datadriven/java/resources/Login_Details-Stg.xls";  //Login_Details-Stg-iD-US-Club.xls Login_Details-Prod_Patch

	@DataProvider(name = "AdvSearch")
	public Object[][] AdvSearch() throws Exception{
		Object[][] retObjArr = getTableArray(environmentalSheetPickToRun, "AdvSearch");
		return(retObjArr);
	}
	@DataProvider(name = "MerchantDetailsLoggedIn")
	public Object[][] MerchantDetails() throws Exception{
		Object[][] retObjArr = getTableArray(environmentalSheetPickToRun, "MerchantDetailsLoggedIn");
		return(retObjArr);
	}
	@DataProvider(name = "HomeSearchUrl")
	public Object[][] SimpleSearch() throws Exception{
		Object[][] retObjArr = getTableArray(environmentalSheetPickToRun, "HomeSearchUrl");
		return(retObjArr);
	}
	@DataProvider(name = "LinksNContent")
	public Object[][] MainMenuLinks() throws Exception{
		Object[][] retObjArr = getTableArray(environmentalSheetPickToRun, "LinksNContent");
		return(retObjArr);
	}
	@DataProvider(name = "LoginFunctionality")
	public Object[][] LoginLogout() throws Exception{
		Object[][] retObjArr = getTableArray(environmentalSheetPickToRun, "LoginFunctionality");
		return(retObjArr);
	}
	@DataProvider(name = "AccountCenter")
	public Object[][] AccountCenterPage() throws Exception{
		Object[][] retObjArr = getTableArray(environmentalSheetPickToRun, "AccountCenter");
		return(retObjArr);
	}
	@DataProvider(name = "LoggedinSearch")
	public Object[][] LoggedinSearch() throws Exception{
		Object[][] retObjArr = getTableArray(environmentalSheetPickToRun, "LoggedinSearch");
		return(retObjArr);
	}

	public String[][] getTableArray(String xlFilePath, String sheetName) throws Exception
	{
		String macroString = "aa,idine";
		String macro2String[] =macroString.split(",");
		String[][] tabArray=null;
		int ci,cj;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			ci=0;
			tabArray = new String[macro2String.length][sheet.getColumns()];
			for(int k=0;k<macro2String.length;k++){
				for (int rowVal = 1; rowVal < sheet.getRows(); rowVal++,ci++) {
					cj=0;


					System.out.println("&&&&&&&&&&&&&&&&%%%%%%%%%%%>: ----:"+macro2String[k]+"<-------->"+sheet.getCell(0,rowVal).getContents());


					if(macro2String[k].equals(sheet.getCell(0,rowVal).getContents())){


						for (int colVal = 0; colVal < sheet.getColumns(); colVal++,cj++) {
							System.out.println("colVal--->"+colVal+"----rowVal--->"+rowVal);
							tabArray[ci][cj]=sheet.getCell(colVal,rowVal).getContents();               
							System.out.println("Tab Array values: "+tabArray[ci][cj]);

						}           
					}


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