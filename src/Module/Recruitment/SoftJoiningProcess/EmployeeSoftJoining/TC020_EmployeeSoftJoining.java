package Module.Recruitment.SoftJoiningProcess.EmployeeSoftJoining;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.EmployeeSoftJoining;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC020_EmployeeSoftJoining extends TestBase {

	public static final Logger log = Logger.getLogger(TC020_EmployeeSoftJoining.class.getName());
	
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	EmployeeSoftJoining esj;
	BaseClass bc;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@DataProvider(name="EmployeeSoftJoining")
	public String[][] getTestData(){
		String[][] testRecords = getData("Recruitment/SoftJoiningProcess.xlsx", "EmployeeSoftJoining");
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

	@Test(priority=0,groups="ESJLogin")
	public void Login() throws Exception{
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		
		esj = new EmployeeSoftJoining(driver);
		esj.Test_Login_Submitter();
	}
		
	@Test(priority=1,groups="ESJTargetPage",dependsOnGroups="ESJLogin")
	public void TargetPage() throws Exception{
		esj = new EmployeeSoftJoining(driver);
		esj.Test_Open_Target_Page();
	}
		
	@Test(priority=2,dependsOnGroups = { "ESJTargetPage" },dataProvider="EmployeeSoftJoining")
	public void ADD_Employee(String TitleData, String CandidateFirstNameData, String CandidateMiddleNamedata,String CandidateLastNamedata, String CandidateRegionalNamedata, String CandidatepreferredNamedata, String NationalityData, String OrganizationData, String BranchData, String GenderData, String MaritalData, String dobExceldate, String NationalIDdata, String LevelData, String DivisionData, String DepartmentData, String BasicSalaryRangedata, String PositionData, String CostCenterData, String ReportingManagerdata, String ejExceldate, String MobileNodata, String HomeNodata, String PersonalEmaildata, String PassportNodata, String ResidentTypeDATA, String BudgetedData, String ReasonForRequirementData, String Reasondata, String PORdata, String POHdata, String NPdata, String ppExceldate, String EmployeeTypeData, String FamilyDetails, String FamilyMemberNamedata, String MaritalStatusData, String FamilyGenderData, String FAddressdata, String FPhoneNodata, String RelationshipData, String FExceldate, String BirthcountryData, String BirthStateData, String BirthLocationData, String FamilyTypeData, String Occupationdata, String IdentityTypeData, String IdentityNodata, String ActionData, String Remarkdata) throws Exception {
		bc = new BaseClass(driver);
		esj = new EmployeeSoftJoining(driver);
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
		
		esj.Test_AddNew(TitleData, CandidateFirstNameData, CandidateMiddleNamedata, CandidateLastNamedata, CandidateRegionalNamedata, CandidatepreferredNamedata, NationalityData, OrganizationData, BranchData, GenderData, MaritalData, dobExceldate, NationalIDdata, LevelData, DivisionData, DepartmentData, BasicSalaryRangedata, PositionData, CostCenterData, ReportingManagerdata, ejExceldate, MobileNodata, HomeNodata, PersonalEmaildata, PassportNodata, ResidentTypeDATA, BudgetedData, ReasonForRequirementData, Reasondata, PORdata, POHdata, NPdata, ppExceldate, EmployeeTypeData, FamilyDetails, FamilyMemberNamedata, MaritalStatusData, FamilyGenderData, FAddressdata, FPhoneNodata, RelationshipData, FExceldate, BirthcountryData, BirthStateData, BirthLocationData, FamilyTypeData, Occupationdata, IdentityTypeData, IdentityNodata, ActionData, Remarkdata);
		  try {
			 esj.Test_VerifyEmployee(CandidateFirstNameData, CandidateMiddleNamedata, CandidateLastNamedata);
		 	ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Employee Added successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Employee Soft Joining");
			WebElement BackButton = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_CancelButton']"));
			bc.click(BackButton, "Back Button");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Employee not Added", iTestCaseRow, Constant.Col_ActualResult);
			bc.log("Employee not Added Successfully");
			Assert.fail("Expected : [Recently added employee showing on Grid] But Found : [Employee not showing]");
		}
	}
	
	
	
}

	
	

