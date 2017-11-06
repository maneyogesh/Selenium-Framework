package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.AttendanceApproval;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;

import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC014_EmployeeAttendanceCalender_Approval extends TestBase {

	public static final Logger log = Logger.getLogger(TC014_EmployeeAttendanceCalender_Approval.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	AttendanceApproval aa;
	BaseClass bc;
	String PageTitle = "Attendance Approval";
	
	@DataProvider(name="AttendanceApproval")
	public String[][] getTestData(){
		String[][] testRecords = getData("EmployeeAttendanceCalender.xlsx", "AttendanceApproval");
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

	@Test(priority=0,groups="aalogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		
		aa = new AttendanceApproval(driver);
		aa.Test_Login_1stlevelApproval();
	}
		
	@Test(priority=1,groups="aatargetpage",dependsOnGroups="aalogin")
	public void TargetPage() throws Exception{
	
		aa = new AttendanceApproval(driver);
		aa.Test_Open_Target_Page();
	}
		
	
	@Test(priority=2,dependsOnGroups = { "aatargetpage" },dataProvider="AttendanceApproval")
	public void LeaveApproveReject(String PayMonthDropdownData, String EmpName, String Date,String StatusData) throws Exception {
		bc = new BaseClass(driver);
		aa = new AttendanceApproval(driver);
	
			/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
	aa.Test_AttendanceApproveReject(PayMonthDropdownData, EmpName, Date, StatusData);
		 try {
			 Assert.assertEquals(driver.getTitle(), PageTitle,"<---Attendance not approved --->");
			log.info("Attendance Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance approved or Rejected successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Approved Attendance");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance Not Approved or Rejected Successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("expected : ["+PageTitle+"] But Found : ["+driver.getTitle()+"] : Attendance not approve or reject");
		}
	}
	
	

	
}

	
	

