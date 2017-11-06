package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.AttendanceRegularization;
import pom.ObjectPages.uiActions.LeaveApproval;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;

import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC019_LeaveApprovalPlannerViewer extends TestBase {

	public static final Logger log = Logger.getLogger(TC019_LeaveApprovalPlannerViewer.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp; 
	LeaveApproval la;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	String PageTitle = "Leave Planner";
	AttendanceRegularization ar;
	
		
	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance.xlsx", "LeavePlannerViewer");
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
		
	
	@Test(priority=2,dependsOnGroups = "Ltargetpage",dataProvider="loginData")
	public void LeavePlannerViewer(String dropdown_Value, String SearchData,String EmpNameData, String DateData, String MonthData, String PlannerSearchdropdownData, String EmpCodeEmpNameData, String SingleDate, String ColourCode) throws Exception {
		bc = new BaseClass(driver);
		la = new LeaveApproval(driver);
		ar = new AttendanceRegularization(driver);
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		la.Test_Search_Record(dropdown_Value, SearchData);
		bc.waitFixedTime(1);
		WebElement viewButton = driver.findElement(By.xpath(".//*[text()='"+EmpNameData+"']/parent::td/parent::tr/td/span[text()='"+DateData+"']/parent::td/parent::tr/td/a[text()='View']"));
		bc.click(viewButton, "View Button");
		ar.TestViewPlannerSearch(MonthData, PlannerSearchdropdownData, EmpCodeEmpNameData);
		ar.Test_VerifyAttendanceRejectandApprove(EmpCodeEmpNameData, SingleDate,ColourCode);
		 try {
			 Assert.assertEquals(driver.getTitle(), PageTitle);
			log.info("showing correct leave status");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record approved or Rejected successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Leave Planner" );
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record Not Approved or Rejected Successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Wrong Leave Status has been shown");
		}
	}
	
	

	
}

	
	

