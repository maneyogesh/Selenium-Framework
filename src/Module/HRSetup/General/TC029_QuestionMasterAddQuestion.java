package Module.HRSetup.General;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.QuestionBank;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC029_QuestionMasterAddQuestion extends TestBase {

	public static final Logger log = Logger.getLogger(TC029_QuestionMasterAddQuestion.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	QuestionBank qb;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="QuestionMaster")
	public String[][] getTestData(){
		String[][] testRecoARs = getData("HRSetup/QuestionMaster.xlsx", "QuestionMaster");
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
		qb = new QuestionBank(driver);
		qb.Test_Open_Target_Page();
	}
		
	@Test(priority=2,dependsOnGroups = { "QMTargetPage" },dataProvider="QuestionMaster")
	public void Add_Questions(String QuestionCodedata, String SelectSubjectData, String Questiondata, String QTData, String NoOfOptiondata, String Marksdata, String Option1data, String Option2data, String Option3data, String Option4data, String ExpectedAnswerdata) throws Exception {
		bc = new BaseClass(driver);
		qb = new QuestionBank(driver);
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
		qb.Test_AddNewRecord(QuestionCodedata, SelectSubjectData, Questiondata, QTData, NoOfOptiondata, Marksdata, Option1data, Option2data, Option3data, Option4data, ExpectedAnswerdata);
		bc.waitFixedTime(1);
	
		  try {
			qb.Test_VerifyRecord(QuestionCodedata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Question Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Question Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Question not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Question not Added Successfully");
			Assert.fail("Expected : ["+QuestionCodedata+"] But Found : [Not Found "+QuestionCodedata+"]");
		}
	}
	
	
	
}

	
	

