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

public class TC037_ExitForm extends TestBase {

	public static final Logger log = Logger.getLogger(TC037_ExitForm.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Exit E;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	String pagetitle1 = "Exit Form";
	
	@DataProvider(name="ExitForm")
	public String[][] getTestData(){
		String[][] testRecords = getData("ELC/Exit.xlsx", "ExitForm");
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

	@Test(priority=0,groups="EFLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		E = new Exit(driver);
		E.Test_LoginExitEmployee();
	}
		
	@Test(priority=1,groups="EFTargetPage",dependsOnGroups="EFLogin")
	public void TargetPage() throws Exception{
		E = new Exit(driver);
		E.Test_Open_Target_Page_ExitForm();
	}
		

	@Test(priority=3,dependsOnGroups = { "EFTargetPage" },dataProvider="ExitForm")
	public void Exit_Form_Submission(String answer1, String answer2, String AnySuggestiondata, String WorkEnvironmentdata, String answer3, String Othersdata, String DisLikedata, String LoveWorkingdata, String answer4) throws Exception {
		bc = new BaseClass(driver);
		E = new Exit(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		  E.Test_ExitForm(answer1, answer2, AnySuggestiondata, WorkEnvironmentdata, answer3, Othersdata, DisLikedata, LoveWorkingdata, answer4);
		  
		  try {
			 Assert.assertEquals(driver.getTitle(), pagetitle1);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Exit form submitted Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Exit Form");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Exit Form not submitted Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Exit Form not submitted Successfully");
			Assert.fail("Expected Status: ["+pagetitle1+"] But Found : ["+driver.getTitle()+"]");
		}
	}
	
	
	
}

	
	

