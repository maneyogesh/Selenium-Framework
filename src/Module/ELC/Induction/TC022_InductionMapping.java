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

public class TC022_InductionMapping extends TestBase {

	public static final Logger log = Logger.getLogger(TC022_InductionMapping.class.getName());
	
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

	@Test(priority=0,groups="IMLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		i = new Induction(driver);
		i.Test_Login_Scheduler();
	}
		
	@Test(priority=1,groups="IMTargetPage",dependsOnGroups="IMLogin")
	public void TargetPage() throws Exception{
		i = new Induction(driver);
		i.Test_Open_Target_Page_InductionMapping();
	}
	
	@Test(priority=2,dependsOnGroups="IMTargetPage",dataProvider="InductionSchedule")
	public void SearchFunctionality(String SearchDropdownData,String NOTUSE,String NOTUSE1, String InTime,String OutTime, String Dropdowndata, String SearchBoxData) throws Exception{
		i = new Induction(driver);
		i.Test_Search_RecordMapping(SearchDropdownData);
	}
		
	@Test(priority=3,dependsOnGroups = { "IMTargetPage" },dataProvider="InductionSchedule")
	public void InductionSchedule(String SearchDropdownData,String NOTUSE,String NOTUSE1, String InTime,String OutTime, String Dropdowndata, String SearchBoxData) throws Exception {
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
		  i.Test_Search_RecordMapping(SearchDropdownData);
		  i.Test_EmployeesInductionMapping(InTime, OutTime, Dropdowndata, SearchBoxData);
		  try {
			Assert.assertTrue(bc.isElementPresentXpathLocator(SearchBoxData));
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Induction Map for Perticular Employee", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Induction Mapping");
		/*	WebElement BackButton = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_CancelButton']"));
			bc.click(BackButton, "Back Button");*/
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Induction not map Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Induction not map Successfully");
			Assert.fail("Expected : [Employee Name] But Found : [Employee not Found]");
		}
	}
	
	
	
}

	
	

