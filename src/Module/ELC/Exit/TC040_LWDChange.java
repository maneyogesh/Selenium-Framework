package Module.ELC.Exit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.Exit;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC040_LWDChange extends TestBase {

	public static final Logger log = Logger.getLogger(TC040_LWDChange.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Exit E;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	String pagetitle1 = "LWD Change";
	
	@DataProvider(name="LWDChange")
	public String[][] getTestData(){
		String[][] testRecords = getData("ELC/Exit.xlsx", "LWDChange");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="LWDLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		E = new Exit(driver);
		E.Test_LoginApprovarHR();
	}
		
	@Test(priority=1,groups="LWDTargetPage",dependsOnGroups="LWDLogin")
	public void TargetPage() throws Exception{
		E = new Exit(driver);
		E.Test_Open_Target_Page_LWD_Change();
	}
		
	@Test(priority=2,groups="LWDSearch",dependsOnGroups="LWDTargetPage",dataProvider="LWDChange")
	public void Search_Functionality(String dropdown_Value, String SearchData, String notuse1, String notuse2) throws Exception{
		E = new Exit(driver);
		E.TEST_SearchRecord_LWDChange(dropdown_Value, SearchData);
	}
	
	@Test(priority=3,dependsOnGroups = { "LWDTargetPage" },dataProvider="LWDChange")
	public void LWD_Change(String dropdown_Value, String SearchData, String ExcelDate, String VerificationDate) throws Exception {
		bc = new BaseClass(driver);
		E = new Exit(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		  E.Test_LWDChange(dropdown_Value, SearchData, ExcelDate);
		  E.Test_Verify_LWD_Change(SearchData, VerificationDate);
		  try {
			 Assert.assertEquals(driver.getTitle(), pagetitle1);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("LWD Changed Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("LWD Change");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("LWD Changed Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("LWD not Change Successfully");
			Assert.fail("Expected Status: ["+pagetitle1+"] But Found : ["+driver.getTitle()+"]");
		}
	}
	
}

	
	

