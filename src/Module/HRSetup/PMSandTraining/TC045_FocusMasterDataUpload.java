package Module.HRSetup.PMSandTraining;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.FocusMaster;
import pom.ObjectPages.uiActions.QuestionBank;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC045_FocusMasterDataUpload extends TestBase {

	public static final Logger log = Logger.getLogger(TC045_FocusMasterDataUpload.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	BaseClass bc;
	QuestionBank qb;
	FocusMaster fm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="FocusMaster")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("HRSetup/FocusMaster.xlsx", "FocusMaster");
		return testRecoARs;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="FMLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		  qb = new QuestionBank(driver);
		  qb.Test_Login();
	}
		
	@Test(priority=1,groups="FMTargetPage",dependsOnGroups="FMLogin")
	public void TargetPage() throws Exception{
		fm = new FocusMaster(driver);
		fm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="FMAddNewButton",dependsOnGroups="FMTargetPage")
	public void Add_New_Button() throws Exception{
		fm = new FocusMaster(driver);
		fm.Test_Add_New_Button();
	}
	
	@Test(priority=3,groups="FMAddNewData",dependsOnGroups = { "FMAddNewButton" },dataProvider="FocusMaster")
	public void Add_Focus_Master_Data(String dropdown_Value,String FocusCodedata, String Focusdata, String Activeness, String Remarkdata, String NOTUSE1, String NOTUSE2, String NOTUSE3, String NOTUSE4, String NOTUSE5, String NOTUSE6) throws Exception {
		bc = new BaseClass(driver);
		fm = new FocusMaster(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		bc.waitFixedTime(1);
		fm.Test_AddNew_Data(FocusCodedata, Focusdata, Activeness, Remarkdata);
		bc.waitFixedTime(1);
		fm.Test_Search_Record(dropdown_Value, FocusCodedata);
		bc.waitFixedTime(1);
		  try {
			fm.Test_VerifyRecord(FocusCodedata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Focus Master record Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Focus Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Focus Master record not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Focus data not Added Successfully");
			Assert.fail("Expected : ["+FocusCodedata+"] But Found : [Recently Added Record]");
		}
	}
	
	@Test(priority=4,dependsOnGroups = { "FMAddNewData" })
	public void Edit_Record(){
		bc = new BaseClass(driver);
		fm = new FocusMaster(driver);
		fm.Test_Edit_Record();
	}
	
}

	
	

