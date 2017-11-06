package Module.ELC.Probation;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.Probation;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC027_ProbationStatusGenerateReport extends TestBase {

	public static final Logger log = Logger.getLogger(TC027_ProbationStatusGenerateReport.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Probation p;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	String psPageName = "Probation Status";
	
	@DataProvider(name="ProbationStatus")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("ELC/Probation.xlsx", "ProbationRequestReviewStatus");
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

	@Test(priority=0,groups="PSLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		p = new Probation(driver);
		p.Test_Login_PStatus();
	}
		
	@Test(priority=1,groups="PSTargetPage",dependsOnGroups="PSLogin")
	public void TargetPage() throws Exception{
		p = new Probation(driver);
		p.Test_Open_Target_Page_ProbationStatus();
	}
	
	@Test(priority=2,dependsOnGroups="PSTargetPage",dataProvider="ProbationStatus")
	public void SearchFunctionality(String SearchDropdownData,String SearchValue,String NOTUSE0, String NOTUSE1, String NOTUSE2, String NOTUSE3, String NOTUSE4, String NOTUSE5, String NOTUSE6, String NOTUSE7, String NOTUSE8, String NOTUSE9, String NOTUSE10, String NOTUSE11, String NOTUSE12, String NOTUSE13, String NOTUSE14, String NOTUSE15, String NOTUSE16, String NOTUSE17, String NOTUSE18, String NOTUSE19, String NOTUSE20, String NOTUSE21, String NOTUSE22, String NOTUSE23, String NOTUSE24, String NOTUSE28, String NOTUSE25, String NOTUSE27, String NOTUSE26, String NOTUSE30, String NOTUSE29) throws Exception{
		p = new Probation(driver);
		p.Test_ProbationReview_Search_Functionality(SearchDropdownData, SearchValue);
	}
		
	@Test(priority=3,dependsOnGroups = {"PSTargetPage"},dataProvider="ProbationStatus")
	public void ProbationGenerateReport(String SearchDropdownData,String SearchValue,String NOTUSE0, String NOTUSE1, String NOTUSE2, String NOTUSE3, String NOTUSE4, String NOTUSE5, String NOTUSE6, String NOTUSE7, String NOTUSE8, String NOTUSE9, String NOTUSE10, String NOTUSE11, String NOTUSE12, String NOTUSE13, String NOTUSE14, String NOTUSE15, String NOTUSE16, String NOTUSE17, String NOTUSE18, String NOTUSE19, String NOTUSE20, String NOTUSE21, String NOTUSE22, String NOTUSE23, String NOTUSE24, String NOTUSE28, String NOTUSE25, String NOTUSE27, String NOTUSE26, String NOTUSE30, String NOTUSE29) throws Exception {
		bc = new BaseClass(driver);
		p = new Probation(driver);
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
		p.Test_ProbationStatusGenerateReport(SearchDropdownData, SearchValue);
		  try {
			Assert.assertEquals(driver.getTitle(), psPageName);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Probation report Generated Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("probation review");
	
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("probation report not Generated Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("probation report not Generated Successfully");
			Assert.fail("Expected : ["+psPageName+"] But Found : ["+driver.getTitle()+"]");
		}
	}
	
	
}

	
	

