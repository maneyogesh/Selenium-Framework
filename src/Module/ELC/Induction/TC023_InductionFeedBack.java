package Module.ELC.Induction;

import org.apache.log4j.Logger;
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

public class TC023_InductionFeedBack extends TestBase {

	public static final Logger log = Logger.getLogger(TC023_InductionFeedBack.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	Induction i;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="InductionFeedback")
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

	@Test(priority=0,groups="IFLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		i = new Induction(driver);
		i.Test_Login_Feedbacker();
	}
		
	@Test(priority=1,groups="IFTargetPage",dependsOnGroups="IFLogin")
	public void TargetPage() throws Exception{
		bc = new BaseClass(driver);
		i = new Induction(driver);
		bc.waitFixedTime(2);
		i.Test_Open_Target_Page_InductionFeedBack();
	}
	
	
	
}

	
	

