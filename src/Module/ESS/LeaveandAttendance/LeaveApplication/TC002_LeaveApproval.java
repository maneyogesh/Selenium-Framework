package Module.ESS.LeaveandAttendance.LeaveApplication;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.LeaveApproval;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;

import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC002_LeaveApproval extends TestBase {

	public static final Logger log = Logger.getLogger(TC002_LeaveApproval.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	LeaveApproval la;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	String PageTitle = "Leave Approval";
	
	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance/LeaveAndAttendance.xlsx", "Approval");
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

	@Test(priority= 0, groups="Llogin")
	public void Login() throws Exception{
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		
		la = new LeaveApproval(driver);
		la.Test_Login_1stlevelApproval();
	}
		
	@Test(priority=1, groups="Ltargetpage", dependsOnGroups="Llogin")
	public void TargetPage() throws Exception{
	
		la = new LeaveApproval(driver);
		la.Test_Open_Target_Page();
	}
		
	@Test(priority=2, groups="LSearchRecord",dependsOnGroups={"Ltargetpage"})
	public void SearchRecord() throws Exception{
		la = new LeaveApproval(driver);
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\ExcelData\\LeaveAndAttendance\\SearchRecord.xls","LeaveApproval");
		la.Test_Search_Record(map.get(0).get("DropdownValue"), map.get(0).get("SearchRecord"));
	}
	
	@Test(priority=3,dependsOnGroups = { "Ltargetpage" },dataProvider="loginData")
	public void LeaveApproveReject(String dropdown_Value, String SearchData, String Date,String StatusData) throws Exception {
		bc = new BaseClass(driver);
		la = new LeaveApproval(driver);
	
			/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
	la.Test_LeaveApproveReject(dropdown_Value, SearchData, Date,StatusData);
		 try {
			 Assert.assertEquals(driver.getTitle(), PageTitle);
			log.info("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record approved or Rejected successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Approved Leave");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record Not Approved or Rejected Successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("<--- Leave not approved --->");
		}
	}
	
	

	
}

	
	

