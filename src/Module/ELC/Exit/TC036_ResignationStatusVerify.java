package Module.ELC.Exit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.Exit;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC036_ResignationStatusVerify extends TestBase {

	public static final Logger log = Logger.getLogger(TC036_ResignationStatusVerify.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Exit E;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="ResignationVerify")
	public String[][] getTestData(){
		String[][] testRecords = getData("ELC/Exit.xlsx", "ResignationSubmission");
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

	@Test(priority=0,groups="RSLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		E = new Exit(driver);
		E.Test_LoginSubmitter();
	}
		
	@Test(priority=1,groups="RSTargetPage",dependsOnGroups="RSLogin")
	public void TargetPage() throws Exception{
		E = new Exit(driver);
		E.Test_Open_Target_Page_ResignationSubmission();
	}
		

	@Test(priority=3,dependsOnGroups = { "RSTargetPage" },dataProvider="ResignationVerify")
	public void Resignation_FinalStatus_Verification(String dropdown_Value, String SearchData, String HomeAddressdata, String NoticeGivendATA, String ResignationReasonData, String LeavingReasondata, String Remarkdata, String EligibilityData, String Resignationdate, String LastEmployeementdate, String StatusData, String notuse1, String Status) throws Exception {
		bc = new BaseClass(driver);
		E = new Exit(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		  E.Test_ResignationSearch_Record(dropdown_Value, SearchData);
		  
		  try {
			  E.Test_ResignationFinalApprovalVerifyStatus(SearchData, Status);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Resignation submitted Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Resignation");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Resignation not submitted Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Resignation not submitted Successfully");
			Assert.fail("Expected Status: ["+Status+"] But Found : [Not Submitted]");
		}
	}
	
	
	
}

	
	

