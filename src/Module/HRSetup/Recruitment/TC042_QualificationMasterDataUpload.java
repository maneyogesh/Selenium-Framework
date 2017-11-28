package Module.HRSetup.Recruitment;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.QualificationMaster;
import pom.ObjectPages.uiActions.QuestionBank;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC042_QualificationMasterDataUpload extends TestBase {

	public static final Logger log = Logger.getLogger(TC042_QualificationMasterDataUpload.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	BaseClass bc;
	QuestionBank qb;
	QualificationMaster qm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="QualificationMaster")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("HRSetup/QualificationMaster.xlsx", "QualificationMaster");
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

	@Test(priority=0,groups="QMLogin")
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
		
	@Test(priority=1,groups="QMTargetPage",dependsOnGroups="QMLogin")
	public void TargetPage() throws Exception{
		qm = new QualificationMaster(driver);
		qm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="QMAddNewButton",dependsOnGroups="QMTargetPage")
	public void Add_New_Button() throws Exception{
		qm = new QualificationMaster(driver);
		qm.Test_Add_New_Button();
	}
	
	@Test(priority=3,groups="QMAddNewData",dependsOnGroups = { "QMAddNewButton" },dataProvider="QualificationMaster")
	public void Add_Qualification_Data(String DropdownData,String QualificationCodedata, String QualificationNamedata, String Descriptiondata) throws Exception {
		bc = new BaseClass(driver);
		qm = new QualificationMaster(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		bc.waitFixedTime(1);
		qm.Test_AddNew_Data(QualificationCodedata, QualificationNamedata, Descriptiondata);
		bc.waitFixedTime(1);
		qm.Test_Search_Record(DropdownData, QualificationCodedata);
		bc.waitFixedTime(1);
	
		  try {
			qm.Test_VerifyRecord(QualificationCodedata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Qualification Master record Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Qualification Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Qualification Master record not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Test not Added Successfully");
			Assert.fail("Expected : ["+QualificationCodedata+"] But Found : [Not Recently Added Record]");
		}
	}
	
	@Test(priority=4,dependsOnGroups = { "QMAddNewData" })
	public void Edit_Record(){
		bc = new BaseClass(driver);
		qm = new QualificationMaster(driver);
		qm.Test_Edit_Record();
	}
	
}

	
	

