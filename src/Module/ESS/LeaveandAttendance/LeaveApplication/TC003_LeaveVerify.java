package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.LeaveApplication;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;

import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC003_LeaveVerify extends TestBase {

	public static final Logger log = Logger.getLogger(TC003_LeaveVerify.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	LeaveApplication la;
	BaseClass bc;
	
	@DataProvider(name="LeaveVerify")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance/LeaveAndAttendance.xlsx", "LeaveVerify");
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

	@Test(priority=0)
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		la = new LeaveApplication(driver);
		la.Test_Login_Submitter();
	}
		
	@Test(priority=1)
	public void TargetPage() throws Exception{
		la = new LeaveApplication(driver);
		la.Test_Open_Target_Page();
	}
		
	
	@Test(priority=2,dependsOnMethods = { "TargetPage" },dataProvider="LeaveVerify")
	public void VerifyLeave(String verifyDate,String VerifyStatus) throws Exception {
		bc = new BaseClass(driver);
		la = new LeaveApplication(driver);
	
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		  try {
		 la.Test_VerifyLeaveApproveReject(verifyDate, VerifyStatus);
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("leave status is showing properly", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("verify Leave");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("leave status is not showing properly", iTestCaseRow, Constant.Col_ActualResult);
			Reporter.log("login with same user those are apply leave -> approve or rejected leave status is not showing properly under status column");
			Assert.fail("Leave status is not showing properly in status column ");
		}
	}
	
	

	
}

	
	

