package Module.HRSetup.Recruitment;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.AllowanceDeductionMaster;
import pom.ObjectPages.uiActions.QuestionBank;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC041_AllowanceDeductionMasterDataUpload extends TestBase {

	public static final Logger log = Logger.getLogger(TC041_AllowanceDeductionMasterDataUpload.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	BaseClass bc;
	QuestionBank qb;
	AllowanceDeductionMaster adm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="AllowanceDeductionMaster")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("HRSetup/AllowanceDeductionMaster.xlsx", "AllowanceDeductionMaster");
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

	@Test(priority=0,groups="ADMLogin")
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
		
	@Test(priority=1,groups="ADMTargetPage",dependsOnGroups="ADMLogin")
	public void TargetPage() throws Exception{
		adm = new AllowanceDeductionMaster(driver);
		adm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="ADMAddNew",dependsOnGroups = { "ADMTargetPage" },dataProvider="AllowanceDeductionMaster")
	public void Add_Allowance_Deduction_Data(String dropdown_Value, String AllowanceCodedata, String CategoryData, String AllowanceDescriptiondata, String sortOrderdata, String AllowanceTypeData, String RegisterDisplayNamedata, String GeneralLedgerAccountNumberdata, String PayslipDisplayNamedata, String SectionData, String RefferenceDeductionData, String RoundValuedata) throws Exception {
		bc = new BaseClass(driver);
		adm = new AllowanceDeductionMaster(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
	
		
		bc.waitFixedTime(1);
		adm.Test_AddNew(AllowanceCodedata, CategoryData, AllowanceDescriptiondata, sortOrderdata, AllowanceTypeData, RegisterDisplayNamedata, GeneralLedgerAccountNumberdata, PayslipDisplayNamedata, SectionData, RefferenceDeductionData, RoundValuedata);
		bc.waitFixedTime(1);
		adm.Test_Search_Record(dropdown_Value, AllowanceCodedata);
		bc.waitFixedTime(1);
	
		  try {
			adm.Test_VerifyRecord(AllowanceCodedata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Allowance Deduction Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Allowance Deduction Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Allowance Deduction Data not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Test not Added Successfully");
			Assert.fail("Expected : ["+AllowanceCodedata+"] But Found : [Not Recently Added Record]");
		}
	}
	
	@Test(priority=3,dependsOnGroups = { "ADMAddNew" })
	public void Edit_Record(){
		bc = new BaseClass(driver);
		adm = new AllowanceDeductionMaster(driver);
		adm.Test_Edit_Record();
	}
	
}

	
	

