package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.SchedulerConfiguration;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC017_SchedulerConfiguration extends TestBase {

	public static final Logger log = Logger.getLogger(TC017_SchedulerConfiguration.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	SchedulerConfiguration sc;
	BaseClass bc;
	
	@DataProvider(name="SchedulerConfiguration")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance/BiometricAttendanceUpload.xlsx", "SchedulerConfiguration");
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

	@Test(priority=0, groups = "sclogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		sc = new SchedulerConfiguration(driver);
		sc.Test_Login_Submitter();
	}
		
	@Test(priority=1, groups = "sctargetpage", dependsOnGroups="sclogin")
	public void TargetPage() throws Exception{
		sc = new SchedulerConfiguration(driver);
		sc.Test_Open_Target_Page();
	}
		
	@Test(priority=2,dependsOnGroups = { "sctargetpage" },dataProvider="SchedulerConfiguration")
	public void ConfigurationScheduler(String SearchDropdownData,String SearchTextBoxData) throws Exception {
		bc = new BaseClass(driver);
		sc = new SchedulerConfiguration(driver);
	
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		sc.Test_SearchRecord(SearchDropdownData, SearchTextBoxData);
		sc.Test_RunScheduler(SearchTextBoxData);
		String alertText = bc.closeAlertAndGetItsText();
		  try {
			  Assert.assertEquals(alertText, "Executed Successfully.");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Scheduler has been run Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Scheduler configuration");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance not Uploaded", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("login -> Enterprise Setup ->  Open Application Monitor -> Search no. 16 scheduler -> Click on Run Button -> page crashed");
			Assert.fail("Scheduler Configuration is not working ");
		}
	}

}

	
	

