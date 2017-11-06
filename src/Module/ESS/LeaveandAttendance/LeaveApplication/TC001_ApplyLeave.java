package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.testng.Assert;
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

public class TC001_ApplyLeave extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_ApplyLeave.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	LeaveApplication la;
	BaseClass bc;
	
	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance.xlsx", "ApplyLeave");
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
		
	
	@Test(priority=2,dependsOnMethods = { "TargetPage" },dataProvider="loginData")
	public void ApplyLeave(String leaveTypeData,String Exceldate, String NoOfLeaveData, String ContactNoData, String MobileNumberData, String ReasonData, String AddressData, String ActionData, String VerifyDate) throws Exception {
		bc = new BaseClass(driver);
		la = new LeaveApplication(driver);
	
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		 la.Test_ApplyLeave(leaveTypeData, Exceldate, NoOfLeaveData, ContactNoData, MobileNumberData, ReasonData, AddressData, ActionData);
		 try {
			  Assert.assertTrue(bc.isElementPresentXpathLocator(VerifyDate));
			 log.info("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Apply Leave");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Leave not submitted", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("<---Leave not submitted --->");
		}
	}
	
	

	
}

	
	

