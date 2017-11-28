package Module.ESS.LeaveandAttendance.LeaveApplication;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.AttendanceRegularization;

import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC010_AttendanceRegularization extends TestBase {

	public static final Logger log = Logger.getLogger(TC010_AttendanceRegularization.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	AttendanceRegularization ar;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="AttendanceRegularization")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("LeaveAndAttendance/AttendanceRegularization.xlsx", "AttendanceRegularization");
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

	@Test(priority=0,groups="ARLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		ar = new AttendanceRegularization(driver);
		ar.Test_Login_Submitter();
	}
		
	@Test(priority=1,groups="ARTargetPage",dependsOnGroups="ARLogin")
	public void TargetPage() throws Exception{
		ar = new AttendanceRegularization(driver);
		ar.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="ARSearchRecord",dependsOnGroups={"ARTargetPage"})
	public void SearchRecord() throws Exception{
		ar = new AttendanceRegularization(driver);
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\ExcelData\\LeaveAndAttendance\\SearchRecord.xls","AttendanceRegularization");
		ar.Test_Search_Record(map.get(0).get("Department"),map.get(0).get("Year"),map.get(0).get("Month"),map.get(0).get("Search Dropdown"), map.get(0).get("SearchValue(Employee Name)"));
	}
		
	@Test(priority=3,dependsOnGroups = { "ARSearchRecord" },dataProvider="AttendanceRegularization")
	public void AttendanceRegularization(String dropdown_Value, String SearchDataEmployeeName, String Date, String AttendanceStatusData,String Remarksdata, String InTimedata, String OutTimedata) throws Exception {
		bc = new BaseClass(driver);
		ar = new AttendanceRegularization(driver);
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
		
		 ar.Test_Attendance_Regularization(dropdown_Value, SearchDataEmployeeName, Date, AttendanceStatusData, Remarksdata, InTimedata, OutTimedata);
		  try {
		 ar.Test_VerifyAttendance(SearchDataEmployeeName, Date);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("attendance regularized successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Attendance Regularization");
			WebElement BackButton = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_tabContainer_TabPanel1_CancelSaveBtn']"));
			bc.click(BackButton, "Back Button");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance not regularized", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Attendance Not Regularized");
			Assert.fail("Expected : [Submitted Record Entry] But Found : [Attendance not regularized : White backgorund colour]");
		}
	}
	
	
	
}

	
	

