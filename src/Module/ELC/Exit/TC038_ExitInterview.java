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

public class TC038_ExitInterview extends TestBase {

	public static final Logger log = Logger.getLogger(TC038_ExitInterview.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Exit E;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	String pagetitle1 = "Exit Interview";
	
	@DataProvider(name="ExitInterview")
	public String[][] getTestData(){
		String[][] testRecords = getData("ELC/Exit.xlsx", "ExitInterview");
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

	@Test(priority=0,groups="EILogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		E = new Exit(driver);
		E.Test_LoginApprovarHR();
	}
		
	@Test(priority=1,groups="EITargetPage",dependsOnGroups="EILogin")
	public void TargetPage() throws Exception{
		E = new Exit(driver);
		E.Test_Open_Target_Page_ExitInterview();
	}
		
	@Test(priority=2,groups="EISearch",dependsOnGroups="EITargetPage",dataProvider="ExitInterview")
	public void Search_Functionality(String dropdown_Value, String SearchData, String notuse1, String notuse2, String notuse3, String notuse4, String notuse5, String notuse6) throws Exception{
		E = new Exit(driver);
		E.TEST_SearchRecord_ExitInterview(dropdown_Value, SearchData);
	}
	

	@Test(priority=3,dependsOnGroups = { "EITargetPage" },dataProvider="ExitInterview")
	public void Exit_Interview(String dropdown_Value, String SearchData, String EIResignationTypeData, String EILeavingReasondata, String EIMajorHighLightsdata, String EIDisappointmentdata, String EICommentdata, String YesNo) throws Exception {
		bc = new BaseClass(driver);
		E = new Exit(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		  E.Test_Exit_InterviewForm(dropdown_Value, SearchData, EIResignationTypeData, EILeavingReasondata, EIMajorHighLightsdata, EIDisappointmentdata, EICommentdata, YesNo);
		  
		  try {
			 E.Test_VerifyExitInterviewForm();
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Exit Interview Form submitted Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Exit Interview");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Exit Interview Form not submitted Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Exit Interview Form not submitted Successfully");
			Assert.fail("Expected Status: ["+pagetitle1+"] But Found : ["+driver.getTitle()+"]");
		}
	}
	
}

	
	

