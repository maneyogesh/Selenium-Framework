package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.EmployeeAttendanceCalendar;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC013_EmployeeAttendanceCalender extends TestBase {

	public static final Logger log = Logger.getLogger(TC013_EmployeeAttendanceCalender.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	EmployeeAttendanceCalendar eac;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="EmployeeAttendanceCalender")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("EmployeeAttendanceCalender.xlsx", "EmployeeAttendanceCalender");
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

	@Test(priority=0,groups="EACLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		eac = new EmployeeAttendanceCalendar(driver);
		eac.Test_Login_Submitter();
	}
		
	@Test(priority=1,groups="EACTargetPage",dependsOnGroups="EACLogin")
	public void TargetPage() throws Exception{
		eac = new EmployeeAttendanceCalendar(driver);
		eac.Test_Open_Target_Page();
	}
		
	@Test(priority=2,dependsOnGroups = { "EACTargetPage" },dataProvider="EmployeeAttendanceCalender")
	public void AttendanceRegularization(String MonthData, String Date, String AttendanceStatusData,String Remarksdata, String InTimedata, String OutTimedata) throws Exception {
		bc = new BaseClass(driver);
		eac = new EmployeeAttendanceCalendar(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this recoAR as no run");
			}*/
		  bc.waitFixedTime(1);
		
		 eac.Test_Employee_Attendance_Calender(MonthData, Date, AttendanceStatusData, Remarksdata, InTimedata, OutTimedata);
		  try {
			  eac.Test_VerifyAttendance(Date);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("attendance regularized successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Employee Attendance Calender");
			WebElement BackButton = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_tabContainer_TabPanel1_CancelSaveBtn']"));
			bc.click(BackButton, "Back Button");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance not regularized", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Attendance Not Regularized");
			Assert.fail("Expected : [Submitted Record Entry] But Found : [Attendance not regularized : White backgorund colour]");
		}
	}
	
	
	
}

	
	

