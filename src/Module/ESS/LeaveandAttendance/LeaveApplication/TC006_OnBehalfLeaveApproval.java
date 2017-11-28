package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.LeaveApproval;
import pom.ObjectPages.uiActions.OnBehalfLeaveApplication;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;

import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC006_OnBehalfLeaveApproval extends TestBase {

	public static final Logger log = Logger.getLogger(TC006_OnBehalfLeaveApproval.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	OnBehalfLeaveApplication ol;
	LeaveApproval la;
	BaseClass bc;
	String PageTitle = "Leave Approval";
	
	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance/OnBehalfLeaveApplication.xlsx", "OnBehalfApproval");
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
		
		ol = new OnBehalfLeaveApplication(driver);
		ol.Test_LoginApprovar();
	}
		
	@Test(priority=1)
	public void TargetPage() throws Exception{
		la = new LeaveApproval(driver);
		la.Test_Open_Target_Page();
	}
		
	
	@Test(priority=2,dependsOnMethods = { "TargetPage" },dataProvider="loginData")
	public void LeaveApproveReject(String dropdown_Value, String SearchData, String Date,String StatusData) throws Exception {
		bc = new BaseClass(driver);
		la = new LeaveApproval(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		
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
			Assert.fail("<---Leave not approved --->");
		}
	}
	
	

	
}

	
	

