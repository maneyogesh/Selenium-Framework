package Module.ESS.LeaveandAttendance.LeaveApplication;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.RosterDefinition;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;

import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC008_RosterDefinition extends TestBase {

	public static final Logger log = Logger.getLogger(TC008_RosterDefinition.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	RosterDefinition rd;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="Roster")
	public String[][] getTestData(){
		String[][] testRecords = getData("RosterDefinition.xlsx", "RosterDefinition");
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

	@Test(priority=0,groups="RDLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		rd = new RosterDefinition(driver);
		rd.Test_Login_Submitter();
	}
		
	@Test(priority=1,groups="RDTargetPage",dependsOnGroups="RDLogin")
	public void TargetPage() throws Exception{
		rd = new RosterDefinition(driver);
		rd.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="RDSearchRecord",dependsOnGroups={"RDTargetPage"})
	public void SearchRecord() throws Exception{
		rd = new RosterDefinition(driver);
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\ExcelData\\RosterDefinitionSearchRecord.xls","RosterDefinition");
		rd.Test_Search_Record(map.get(0).get("Department"),map.get(0).get("Year"),map.get(0).get("Month"),map.get(0).get("Search Dropdown"), map.get(0).get("SearchValue(Employee Name)"));
	}
		
	@Test(priority=3,dependsOnGroups = { "RDSearchRecord" },dataProvider="Roster")
	public void DefineRoster(String DepartmentData, String YearData, String MonthData, String dropdown_Value, String SearchDataEmployeeName, String ShiftCodeData, String ExcelFromdate,String ExcelTodate, String Weekoff01Data, String Weekoff02Data) throws Exception {
		bc = new BaseClass(driver);
		rd = new RosterDefinition(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		  bc.waitFixedTime(1);
		 rd.Test_Search_Record(DepartmentData, YearData, MonthData, dropdown_Value, SearchDataEmployeeName);
		 rd.Test_DefineRoster(SearchDataEmployeeName, ShiftCodeData, ExcelFromdate, ExcelTodate, Weekoff01Data, Weekoff02Data);
		  try {
		 rd.Test_VerifyRoster(SearchDataEmployeeName, ShiftCodeData);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Roster has been define successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Roster Define");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Roster Not Define", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("");
			Assert.fail("Expected : ["+ShiftCodeData+"] But Found : [--][Roster Not Define : Shift not showing ]");
		}
	}
	
	@Test(priority=4,dependsOnGroups = { "RDTargetPage" })
	public void EditDefinedRoster(){
		rd = new RosterDefinition(driver);
		rd.Test_EditRoster();
	}

	
}

	
	

