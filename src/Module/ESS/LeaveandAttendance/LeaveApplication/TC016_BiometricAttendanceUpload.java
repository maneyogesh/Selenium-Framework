package Module.ESS.LeaveandAttendance.LeaveApplication;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.BiometricAttendanceUpload;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC016_BiometricAttendanceUpload extends TestBase {

	public static final Logger log = Logger.getLogger(TC016_BiometricAttendanceUpload.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	BiometricAttendanceUpload bau;
	BaseClass bc;
	
	@DataProvider(name="AttendanceUpload")
	public String[][] getTestData(){
		String[][] testRecords = getData("LeaveAndAttendance/BiometricAttendanceUpload.xlsx", "BiometricAttendanceUpload");
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

	@Test(priority=0, groups = "baulogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		bau = new BiometricAttendanceUpload(driver);
		bau.Test_Login_Submitter();
	}
		
	@Test(priority=1, groups = "bautargetpage", dependsOnGroups="baulogin")
	public void TargetPage() throws Exception{
		bau = new BiometricAttendanceUpload(driver);
		bau.Test_Open_Target_Page();
	}
		
	@Test(priority=2,dependsOnGroups = { "bautargetpage" },dataProvider="AttendanceUpload")
	public void BiometricAttendanceUpload(String FileFormate,String EXEFilePath) throws Exception {
		bc = new BaseClass(driver);
		bau = new BiometricAttendanceUpload(driver);
	
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		bau.Test_Employee_Attendance_Upload(FileFormate, EXEFilePath);
		  try {
			  WebElement UploadButton = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_lblUpload']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(UploadButton));
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance Uploaded Successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Biometric Attendance Upload");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Attendance not Uploaded", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("login -> open biometric attendance upload -> click on upload -> select txt and browse txt file -> click on add button-> page crashed");
			Assert.fail("Attendance not Uploaded Successfully : upload button not found");
		}
	}

}

	
	

