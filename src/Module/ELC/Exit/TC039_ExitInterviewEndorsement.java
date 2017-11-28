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

public class TC039_ExitInterviewEndorsement extends TestBase {

	public static final Logger log = Logger.getLogger(TC039_ExitInterviewEndorsement.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Exit E;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	String pagetitle1 = "Exit Interview Endorsement";
	
	@DataProvider(name="ExitInterviewEndorsement")
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

	@Test(priority=0,groups="EIELogin")
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
		
	@Test(priority=1,groups="EIETargetPage",dependsOnGroups="EIELogin")
	public void TargetPage() throws Exception{
		E = new Exit(driver);
		E.Test_Open_Target_Page_ExitInterview_Endorsement();
	}
		
	@Test(priority=2,groups="EISearch",dependsOnGroups="EIETargetPage",dataProvider="ExitInterviewEndorsement")
	public void Search_Functionality(String dropdown_Value, String SearchData, String notuse1, String notuse2, String notuse3, String notuse4, String notuse5, String notuse6) throws Exception{
		E = new Exit(driver);
		E.TEST_SearchRecord_ExitInterview_Endorsement(dropdown_Value, SearchData);
	}
	
	@Test(priority=3,dependsOnGroups = { "EIETargetPage" },dataProvider="ExitInterviewEndorsement")
	public void Exit_Interview_Endorsement(String dropdown_Value, String SearchData, String notuse1, String notuse2, String notuse3, String notuse4, String notuse5, String notuse6) throws Exception {
		bc = new BaseClass(driver);
		E = new Exit(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		  E.Test_Exit_Interview_Endorsement(dropdown_Value, SearchData);
		  E.Test_VerifyExitInterviewForm();
		  try {
			 Assert.assertEquals(driver.getTitle(), pagetitle1);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Exit Interview Endorsement Form submitted Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Exit Interview Endorsement");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Exit Interview Endorsement Form not submitted Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Exit Interview Endorsement Form not submitted Successfully");
			Assert.fail("Expected Status: ["+pagetitle1+"] But Found : ["+driver.getTitle()+"]");
		}
	}
	
}

	
	

