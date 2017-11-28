package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.BiometricAttendanceUpload;
import pom.ObjectPages.uiActions.EmployeeAttendanceCalendar;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC018_BiometricAttendanceUploadStatusVerify extends TestBase {

	public static final Logger log = Logger.getLogger(TC018_BiometricAttendanceUploadStatusVerify.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	BiometricAttendanceUpload bau;
	EmployeeAttendanceCalendar eac;
	BaseClass bc;
	
	@DataProvider(name="BiometricAttendanceUploadVerify")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance/BiometricAttendanceUpload.xlsx", "BiometricAttendanceVerify");
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

	@Test(priority=0, groups = "bausvlogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		eac = new EmployeeAttendanceCalendar(driver);
		eac.Test_Login_Submitter();
	}
		
	@Test(priority=1, groups = "bausvtargetpage", dependsOnGroups="bausvlogin")
	public void TargetPage() throws Exception{
		eac = new EmployeeAttendanceCalendar(driver);
		eac.Test_Open_Target_Page();
	}
		
	@Test(priority=2,dependsOnGroups = { "bausvtargetpage" },dataProvider="BiometricAttendanceUploadVerify")
	public void ConfigurationScheduler(String MonthData,String DateData) throws Exception {
		bc = new BaseClass(driver);
		bau = new BiometricAttendanceUpload(driver);
		eac = new EmployeeAttendanceCalendar(driver);
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		eac.Test_Search_Record(MonthData);
		bc.ScrollDown();
		  try {
			 bau.Test_VerifyTXTUploaded(DateData);
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData(" Attendace showing properly", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot(" Biometric Attendance ");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance not Uploaded", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("login -> Open Employee Attendance Page -> not showing Attendance Properly");
			Assert.fail(" Attendance not showing ");
		}
	}

}

	
	

