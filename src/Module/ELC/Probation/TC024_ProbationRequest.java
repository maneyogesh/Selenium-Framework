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

public class TC024_ProbationRequest extends TestBase {

	public static final Logger log = Logger.getLogger(TC024_ProbationRequest.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Probation p;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="ProbationRequest")
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

	@Test(priority=0,groups="PRLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		p = new Probation(driver);
		p.Test_Login_PRequest();
	}
		
	@Test(priority=1,groups="PRTargetPage",dependsOnGroups="PRLogin")
	public void TargetPage() throws Exception{
		p = new Probation(driver);
		p.Test_Open_Target_Page_ProbationRequest();
	}
		
	@Test(priority=2,dependsOnGroups = { "PRTargetPage" },dataProvider="ProbationRequest")
	public void ProbationRequest(String SearchDropdownData, String SearchValue,String QQ1, String QQ2, String QQ3, String QQ4, String QQ5, String QQ6, String QQ7, String QQ8, String QQ9, String QQ10, String QQ11, String QQ12, String QQ13, String QQ14, String QQ15, String QQ16, String QQ17, String QQ18, String QQ19, String QQ20, String QQ21, String QQ22, String QQ23, String QQ24, String QQ25, String RemarkData, String actiondata, String NOTUSE1, String NOTUSE2, String SubmittedStatus, String NOTUSE29) throws Exception {
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
		p.Test_Search_Record(SearchDropdownData, SearchValue);
		p.Test_AddNew(SearchDropdownData, SearchValue, QQ1, QQ2, QQ3, QQ4, QQ5, QQ6, QQ7, QQ8, QQ9, QQ10, QQ11, QQ12, QQ13, QQ14, QQ15, QQ16, QQ17, QQ18, QQ19, QQ20, QQ21, QQ22, QQ23, QQ24, QQ25, RemarkData, actiondata);
		p.Test_Search_Record(SearchDropdownData, SearchValue);
		bc.waitFixedTime(1);
	
		  try {
			p.Test_VerifyProbationRequestEmployeeStatus(SearchValue, SubmittedStatus);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Probation Request send successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Probation Request");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Probation Request is not send Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Probation Request not send successfully");
			Assert.fail("Expected : [Status = Submited] But Found : [Blank]");
		}
	}
	
	
	
}

	
	

