package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.AttendanceRegularization;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC012_AttendanceVerify extends TestBase {

	public static final Logger log = Logger.getLogger(TC012_AttendanceVerify.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	AttendanceRegularization ar;
	BaseClass bc;
	
	@DataProvider(name="AttendanceVerify")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance/AttendanceRegularization.xlsx", "AttendanceVerify");
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

	@Test(priority=0, groups = "avlogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		ar = new AttendanceRegularization(driver);
		ar.Test_Login_Submitter();
	}
		
	@Test(priority=1, groups = "avtargetpage", dependsOnGroups="avlogin")
	public void TargetPage() throws Exception{
		ar = new AttendanceRegularization(driver);
		ar.Test_Open_Target_Page();
	}
		
	
	@Test(priority=2,dependsOnGroups = { "avtargetpage" },dataProvider="AttendanceVerify")
	public void VerifyAttendanceApprove(String DepartmentData,String YearData,String MonthData,String dropdown_Value,String SearchDataEmployeeName) throws Exception {
		bc = new BaseClass(driver);
		ar = new AttendanceRegularization(driver);
	
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		ar.Test_Search_Record(DepartmentData, YearData, MonthData, dropdown_Value, SearchDataEmployeeName);
		
		  try {
			 ar.Test_VerifyAttendanceApprove(SearchDataEmployeeName);
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance status is showing properly", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("verify Attendance");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance status is not showing properly", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("login with same user those are apply Attendance -> approve or rejected Attendance status is not showing properly under status column");
			Assert.fail("Attendance not approved");
		}
	}
	
	@Test(priority=3,dependsOnGroups = { "avtargetpage" },dataProvider="AttendanceVerify")
	public void VerifyAttendanceReject(String DepartmentData,String YearData,String MonthData,String dropdown_Value,String SearchDataEmployeeName) throws Exception {
		bc = new BaseClass(driver);
		ar = new AttendanceRegularization(driver);
	
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		ar.Test_Search_Record(DepartmentData, YearData, MonthData, dropdown_Value, SearchDataEmployeeName);
		
		  try {
			 ar.Test_VerifyAttendanceReject(SearchDataEmployeeName);
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance status is showing properly", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("verify Attendance");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance status is not showing properly", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("login with same user those are apply Attendance -> approve or rejected Attendance status is not showing properly under status column");
			Assert.fail("Attendance not rejected");
		}
	}

	
}

	
	

