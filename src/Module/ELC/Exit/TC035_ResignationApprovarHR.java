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

public class TC035_ResignationApprovarHR extends TestBase {

	public static final Logger log = Logger.getLogger(TC035_ResignationApprovarHR.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Exit E;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	String PageTtile = "Resignation Approval";
	
	@DataProvider(name="ResignationApprovarHR")
	public String[][] getTestData(){
		String[][] testRecords = getData("ELC/Exit.xlsx", "ResignationSubmission");
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

	@Test(priority=0,groups="RLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		E = new Exit(driver);
		E.Test_LoginApprovarHR();
	}
		
	@Test(priority=1,groups="RTargetPage",dependsOnGroups="RLogin")
	public void TargetPage() throws Exception{
		E = new Exit(driver);
		E.Test_Open_Target_Page_ResignationApproval();
	}
		
	@Test(priority=2,groups="RSearch",dependsOnGroups="RTargetPage",dataProvider="ResignationApprovarHR")
	public void Search_Functionality(String dropdown_Value, String SearchData, String notuse1, String notuse2, String notuse3, String notuse4, String notuse5, String notuse6, String notuse7, String notuse8, String notuse9, String notuse10, String notuse11) throws Exception{
		E = new Exit(driver);
		E.Test_ResignationApprovalSearch_Record(dropdown_Value, SearchData);
		}
	
	@Test(priority=3,dependsOnGroups = { "RTargetPage" },dataProvider="ResignationApprovarHR")
	public void Resignation_Approvar_HR(String dropdown_Value, String SearchData, String notuse1, String notuse2, String notuse3, String notuse4, String notuse5, String notuse6, String notuse7, String notuse8, String notuse9, String notuse10, String Status) throws Exception {
		bc = new BaseClass(driver);
		E = new Exit(driver);
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
		E.Test_ResgnApprovalEditEmployee(dropdown_Value, SearchData, Status);
	
		  try {
			Assert.assertEquals(driver.getTitle(), PageTtile);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Resignation approved or reject Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Resignation approval");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Resignation not approved neither reject Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Resignation not approved neither reject Successfully");
			Assert.fail("Expected Status: [approved or reject] But Found : [Submitted]");
		}
	}
	
	
	
}

	
	

