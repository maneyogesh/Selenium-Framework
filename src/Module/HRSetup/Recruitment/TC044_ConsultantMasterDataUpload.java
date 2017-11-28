package Module.HRSetup.Recruitment;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.ConsultantMaster;
import pom.ObjectPages.uiActions.QuestionBank;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC044_ConsultantMasterDataUpload extends TestBase {

	public static final Logger log = Logger.getLogger(TC044_ConsultantMasterDataUpload.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	BaseClass bc;
	QuestionBank qb;
	ConsultantMaster cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="ConsultantMaster")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("HRSetup/ConsultantMaster.xlsx", "ConsultantMaster");
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

	@Test(priority=0,groups="CCMLogin")
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
		
	@Test(priority=1,groups="CCMTargetPage",dependsOnGroups="CCMLogin")
	public void TargetPage() throws Exception{
		cm = new ConsultantMaster(driver);
		cm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="CCMAddNewButton",dependsOnGroups="CCMTargetPage")
	public void Add_New_Button() throws Exception{
		cm = new ConsultantMaster(driver);
		cm.Test_Add_New_Button();
	}
	
	@Test(priority=3,groups="CCMAddNewData",dependsOnGroups = { "CCMAddNewButton" },dataProvider="ConsultantMaster")
	public void Add_Consultant_Master_Data(String dropdown_Value,String CompanyNamedata, String UserNamedata, String Passworddata, String FirstNamedata, String MiddleNameData, String LastNamedata, String Emaildata, String MobileNumberdata, String TelephoneNumberdata, String Addressdata, String Activeness) throws Exception {
		bc = new BaseClass(driver);
		cm = new ConsultantMaster(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		bc.waitFixedTime(1);
		cm.Test_AddNew_Data(CompanyNamedata, UserNamedata, Passworddata, FirstNamedata, MiddleNameData, LastNamedata, Emaildata, MobileNumberdata, TelephoneNumberdata, Addressdata, Activeness);
		bc.waitFixedTime(1);
		cm.Test_Search_Record(dropdown_Value, CompanyNamedata);
		bc.waitFixedTime(1);
		  try {
			cm.Test_VerifyRecord(CompanyNamedata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Consultant Master record Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Consultant Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Consaltant Master record not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Test not Added Successfully");
			Assert.fail("Expected : ["+CompanyNamedata+"] But Found : [Recently Added Record]");
		}
	}
	
	@Test(priority=4,dependsOnGroups = { "CCMAddNewData" })
	public void Edit_Record(){
		bc = new BaseClass(driver);
		cm = new ConsultantMaster(driver);
		cm.Test_Edit_Record();
	}
	
}

	
	

