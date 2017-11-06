package Module.HRSetup.General;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.QuestionBank;
import pom.ObjectPages.uiActions.TestSetup;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC030_TestSetupAddTest extends TestBase {

	public static final Logger log = Logger.getLogger(TC030_TestSetupAddTest.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	TestSetup ts;
	BaseClass bc;
	QuestionBank qb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="TestSetup")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("HRSetup/TestSetup.xlsx", "TestSetup");
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

	@Test(priority=0,groups="TSLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
			qb = new QuestionBank(driver);
			qb.Test_Login();
	}
		
	@Test(priority=1,groups="TSTargetPage",dependsOnGroups="TSLogin")
	public void TargetPage() throws Exception{
		ts = new TestSetup(driver);
		ts.Test_Open_Target_Page();
	}
		
	@Test(priority=2,dependsOnGroups = { "TSTargetPage" },dataProvider="TestSetup")
	public void Add_Test(String TestCodedata, String TestNamedata, String SelectSubjectData, String QuestionTypedata, String ppDATA, String QuestionCode) throws Exception {
		bc = new BaseClass(driver);
		ts = new TestSetup(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
	
		bc.waitFixedTime(1);
		ts.Test_AddNewRecord(TestCodedata, TestNamedata, SelectSubjectData, QuestionTypedata, ppDATA, QuestionCode);
		bc.waitFixedTime(1);
	
		  try {
			ts.Test_VerifyRecord(TestCodedata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Test Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Test Setup");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Test not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Test not Added Successfully");
			Assert.fail("Expected : ["+TestCodedata+"] But Found : [Not Recently Added Record]");
		}
	}
	
	
	
}

	
	

