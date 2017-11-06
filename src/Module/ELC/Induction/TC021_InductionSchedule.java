package Module.ELC.Induction;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.Induction;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC021_InductionSchedule extends TestBase {

	public static final Logger log = Logger.getLogger(TC021_InductionSchedule.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Induction i;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="InductionSchedule")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("ELC/Induction.xlsx", "InductionSchedule");
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

	@Test(priority=0,groups="ISLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		i = new Induction(driver);
		i.Test_Login_Submitter();
	}
		
	@Test(priority=1,groups="ISTargetPage",dependsOnGroups="ISLogin")
	public void TargetPage() throws Exception{
		i = new Induction(driver);
		i.Test_Open_Target_Page_InductionSchedule();
	}
		
	@Test(priority=2,dependsOnGroups = { "ISTargetPage" },dataProvider="InductionSchedule")
	public void InductionSchedule(String SearchDropdownData, String CompanyNameData,String Venuedata, String InTimedata, String OutTimedata, String NotUse1, String NotUse2) throws Exception {
		bc = new BaseClass(driver);
		i = new Induction(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this recoAR as no run");
			}*/
		  bc.waitFixedTime(1);
		i.Test_AddNew(CompanyNameData, Venuedata, InTimedata, OutTimedata);
		i.Test_Search_Record(SearchDropdownData);
	
		  try {
			i.Test_VerifyInductionSchedule(InTimedata, OutTimedata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Induction Schedule successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Induction Schedule");
		/*	WebElement BackButton = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_CancelButton']"));
			bc.click(BackButton, "Back Button");*/
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Induction not Schedule Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Employee not Added Successfully");
			Assert.fail("Expected : [Induction Date] But Found : [Induction Date not Found]");
		}
	}
	
	
	
}

	
	

