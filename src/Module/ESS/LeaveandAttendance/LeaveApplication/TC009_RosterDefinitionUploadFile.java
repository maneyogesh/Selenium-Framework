package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.RosterDefinition;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;

import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC009_RosterDefinitionUploadFile extends TestBase {

	public static final Logger log = Logger.getLogger(TC009_RosterDefinitionUploadFile.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	RosterDefinition rd;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@BeforeClass
	public void setUp() throws Exception {
	  	sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="RDLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		rd = new RosterDefinition(driver);
		rd.Test_Login_Submitter();
	}
		
	@Test(priority=1,groups="RDTargetPage",dependsOnGroups="RDLogin")
	public void TargetPage() throws Exception{
		rd = new RosterDefinition(driver);
		rd.Test_Open_Target_Page();
	}
		
	@Test(priority=2,dependsOnGroups = { "RDTargetPage" })
	public void DefineRosterUploadFile() throws Exception {
		bc = new BaseClass(driver);
		rd = new RosterDefinition(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		  
		  rd.Test_UploadFileInRosterDefinition();
		  
		  try {
			  Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
			  bc.AlertAcceptIfPresent();
			  WebElement cancelbutton = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_btncancel']"));
				bc.waitForElement(cancelbutton);
				bc.click(cancelbutton, "Cancel Button");
				bc.log("file uploaded successfully : process button is working properly");
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Roster has been define successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Roster file upload");
			bc.AlertAcceptIfPresent();
			 WebElement cancelbutton = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_btncancel']"));
			 bc.waitForElement(cancelbutton);
			bc.click(cancelbutton, "Cancel Button");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Roster Not Define", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("file not uploaded successfully : process button is not working properly");
			Assert.fail("Expected : [alert box with process completed] But Found : [alert box not present]");
		}
	}


	
}

	
	

