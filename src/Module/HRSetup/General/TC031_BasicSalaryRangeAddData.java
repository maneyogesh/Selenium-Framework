package Module.HRSetup.General;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.BasicSalaryRange;
import pom.ObjectPages.uiActions.QuestionBank;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC031_BasicSalaryRangeAddData extends TestBase {

	public static final Logger log = Logger.getLogger(TC031_BasicSalaryRangeAddData.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	BasicSalaryRange bsr;
	BaseClass bc;
	QuestionBank qb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="BasicSalaryRange")
	public String[][] getTestData(){
		String[][] testRecords = getData("HRSetup/BasicSalaryRange.xlsx", "BasicSalaryRange");
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

	@Test(priority=0,groups="bsrLogin")
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
		
	@Test(priority=1,groups="bsrTargetPage",dependsOnGroups="bsrLogin")
	public void TargetPage() throws Exception{
		bsr = new BasicSalaryRange(driver);
		bsr.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="bsrAddNewButton",dependsOnGroups="bsrTargetPage")
	public void Add_New_Button() throws Exception{
		bsr = new BasicSalaryRange(driver);
		bsr.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"bsrTargetPage"},dataProvider="BasicSalaryRange")
	public void Add_Basic_Salary_Range(String Ddata, String gdata, String mdata, String meddata, String maxdata) throws Exception {
		bc = new BaseClass(driver);
		bsr = new BasicSalaryRange(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		bc.waitFixedTime(1);
		bsr.Test_AddNewRecord(Ddata, gdata, mdata, meddata, maxdata);
		bc.waitFixedTime(1);
	
		  try {
			bsr.Test_VerifyRecord(gdata, Ddata, mdata, meddata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Basic Salary Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Test Setup");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Basic Salary not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Basic Salary not Added Successfully");
			Assert.fail("Expected : ["+Ddata+"] But Found : [Not Added Recent Record]");
		}
	}
	
	@Test(priority=4,dependsOnMethods="Add_Basic_Salary_Range",dataProvider="BasicSalaryRange")
	public void Edit_Record(String Ddata, String gdata, String mdata, String meddata, String maxdata) throws Exception{
		bsr = new BasicSalaryRange(driver);
		bsr.Test_Edit_Record(gdata, Ddata, mdata, meddata);
	}
	
	
}

	
	

