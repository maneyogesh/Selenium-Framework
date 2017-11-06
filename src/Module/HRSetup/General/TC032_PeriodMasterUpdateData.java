package Module.HRSetup.General;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.PeriodMaster;
import pom.ObjectPages.uiActions.QuestionBank;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC032_PeriodMasterUpdateData extends TestBase {

	public static final Logger log = Logger.getLogger(TC032_PeriodMasterUpdateData.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	QuestionBank qb;
	BaseClass bc;
	PeriodMaster pm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="PeriodMaster")
	public String[][] getTestData(){
		String[][] testRecords = getData("HRSetup/PeriodMaster.xlsx", "PeriodMaster");
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

	@Test(priority=0,groups="pmLogin")
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
		
	@Test(priority=1,groups="pmTargetPage",dependsOnGroups="pmLogin")
	public void TargetPage() throws Exception{
		pm = new PeriodMaster(driver);
		pm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="pmTargetPage",dependsOnGroups="pmLogin",dataProvider="PeriodMaster")
	public void Search_Functionality(String dropdown_Value, String Level, String NoOfDaysdata, String NoticePerioddata, String ProbationPerioddata, String PPAlertdata) throws Exception{
		pm = new PeriodMaster(driver);
		pm.Test_Search_Record(dropdown_Value, Level);
	}
	
	@Test(priority=3,dependsOnGroups = {"pmTargetPage"},dataProvider="PeriodMaster")
	public void Edit_Record(String dropdown_Value, String Level, String NoOfDaysdata, String NoticePerioddata, String ProbationPerioddata, String PPAlertdata) throws Exception{
		bc = new BaseClass(driver);
		pm = new PeriodMaster(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		bc.waitFixedTime(1);
		pm.Test_EditRecord(NoOfDaysdata, NoticePerioddata, ProbationPerioddata, PPAlertdata);
		bc.waitFixedTime(1);
	
		  try {
			pm.Test_VerifyRecord(Level, NoOfDaysdata, NoticePerioddata, ProbationPerioddata, PPAlertdata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Basic Salary Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Period Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Basic Salary not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Basic Salary not Added Successfully");
			Assert.fail("Expected : ["+NoOfDaysdata+"] But Found : [Not Added Recent Record]");
		}
	}
	

	
}

	
	

