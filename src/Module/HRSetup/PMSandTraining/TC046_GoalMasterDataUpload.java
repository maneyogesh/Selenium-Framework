package Module.HRSetup.PMSandTraining;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.GoalMaster;
import pom.ObjectPages.uiActions.QuestionBank;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC046_GoalMasterDataUpload extends TestBase {

	public static final Logger log = Logger.getLogger(TC046_GoalMasterDataUpload.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	BaseClass bc;
	QuestionBank qb;
	GoalMaster gm;
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

	@Test(priority=0,groups="GMLogin")
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
		
	@Test(priority=1,groups="GMTargetPage",dependsOnGroups="GMLogin")
	public void TargetPage() throws Exception{
		gm = new GoalMaster(driver);
		gm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="GMAddNewButton",dependsOnGroups="GMTargetPage")
	public void Add_New_Button() throws Exception{
		gm = new GoalMaster(driver);
		gm.Test_Add_New_Button();
	}
	
	@Test(priority=3,groups="GMAddNewData",dependsOnGroups = { "GMAddNewButton" },dataProvider="FocusMaster")
	public void Add_Goal_Master_Data(String NOTUSE1, String NOTUSE2, String Focusdata, String NOTUSE4, String NOTUSE5, String NOTUSE6,String dropdown_Value, String GoalCodedata, String Goaldata, String Activeness, String Remarkdata) throws Exception {
		bc = new BaseClass(driver);
		gm = new GoalMaster(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		bc.waitFixedTime(1);
		gm.Test_AddNew_Data(Focusdata, GoalCodedata, Goaldata, Activeness, Remarkdata);
		bc.waitFixedTime(1);
		gm.Test_Search_Record(dropdown_Value, GoalCodedata);
		bc.waitFixedTime(1);
		  try {
			gm.Test_VerifyRecord(GoalCodedata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Goal Master record Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Goal Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Goal Master record not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Goal data not Added Successfully");
			Assert.fail("Expected : ["+GoalCodedata+"] But Found : [Recently Added Record]");
		}
	}
	
	@Test(priority=4,dependsOnGroups = { "GMAddNewData" })
	public void Edit_Record(){
		bc = new BaseClass(driver);
		gm = new GoalMaster(driver);
		gm.Test_Edit_Record();
	}
	
}

	
	

