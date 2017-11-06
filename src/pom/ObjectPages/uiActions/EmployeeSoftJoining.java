package pom.ObjectPages.uiActions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pom.TestBase.BaseClass;
import pom.TestBase.CommonMethods;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;

public class EmployeeSoftJoining extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Human Resource") WebElement HRMS;
	@FindBy(linkText="Recruitment") WebElement Recruitment;
	@FindBy(linkText="Soft Joining Process") WebElement SoftJoiningProcess;  
	@FindBy(linkText="Employee Soft Joining") WebElement EmployeeSoftJoining;
	String module = "Human Resource";
	String submoduleName = "Recruitment";
	String subsectionName = "Soft Joining Process";
	String pageName = "Employee Soft Joining";
	String PageTitle = "Employee Soft Joining";
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='Employee Soft Joining']") WebElement PageHeader;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpSearchList']") WebElement SearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearchText']") WebElement SearchTextBox;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdSearch']") WebElement SearchButton;

	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lnkBAddNewD']") WebElement AddNewButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpTitle']") WebElement Title;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtFirstName']") WebElement CandidateFirstName;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtMidName']") WebElement CandidateMiddleName;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtLastName']") WebElement CandidateLastName;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtArabictext']") WebElement CandidateRegionalName;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_TxtCandPreferredName']") WebElement CandidatepreferredName;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpBDNationality']") WebElement Nationality;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_Drpou']") WebElement Organization;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpCompany']") WebElement Company;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpBranch']") WebElement Branch;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpmaritalsts']") WebElement Marital;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_Image4']") WebElement dobCalenderButton;
	@FindBy(id="ctl00_ContentPlaceHolder_CalendarExtender2_nextArrow") WebElement dobNextCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_CalendarExtender2_title") WebElement dobMiddleCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_CalendarExtender2_prevArrow") WebElement dobPrevCalender;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalendarExtender2_monthsBody']//descendant::tr/td/div") List<WebElement> dobAllMonth;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalendarExtender2_daysBody']//tr//td[@class='']") List<WebElement> dobAllDates;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtPANNo']") WebElement NationalID;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpGrade']") WebElement Level;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpDiv']") WebElement Division;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpDepartment']") WebElement Department;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtBasicSal']") WebElement BasicSalaryRange;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpDesg']") WebElement Position;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropCostCenter']") WebElement CostCenter;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtRptManager_UC_txtSearch']") WebElement ReportingManager;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lblRptLevel1']") WebElement ReportingManagerLabel;
	
	@FindBy(xpath=".//*[@id='UC_txtSearch']") WebElement ReportingLevel2;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lblRptLevel2']") WebElement ReportingLevel2label;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_ImageE']") WebElement ejCalenderButton;
	@FindBy(id="ctl00_ContentPlaceHolder_CalendarExtender1_nextArrow") WebElement ejNextCalender;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalendarExtender1_title']") WebElement ejMiddleCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_CalendarExtender1_prevArrow") WebElement ejPrevCalender;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalendarExtender1_monthsBody']/tr/td/div") List<WebElement> ejAllMonth;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalendarExtender1_daysBody']/tr/td[@class='']") List<WebElement> ejAllDates;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtMobileNo']") WebElement MobileNo;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_Txtlandlineno']") WebElement HomeNo;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtOfficialEmail']") WebElement PersonalEmail;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtpassportno']") WebElement PassportNo;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpEmpType']") WebElement ResidentType;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpChangeforRequi']") WebElement ReasonForRequirement;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtReason']") WebElement Reason;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_UCSearchRepatriation_UC_txtSearch']") WebElement POR;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_Label189']") WebElement PORLabel;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_UCSearchHire_UC_txtSearch']") WebElement POH;
	@FindBy(xpath=".//*[text()='Point of Hire']") WebElement POHLabel;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtNoticeperiod']") WebElement NP;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_ImageF']") WebElement PPCalenderButton;
	@FindBy(id="ctl00_ContentPlaceHolder_CalProbationperiod_nextArrow") WebElement PPNextCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_CalProbationperiod_title") WebElement PPMiddleCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_CalProbationperiod_prevArrow") WebElement PPPrevCalender;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalendarExtender1_monthsBody']/tr/td/div") List<WebElement> PPAllMonth;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalendarExtender1_daysBody']/tr/td[@class='']") List<WebElement> PPAllDates;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpEmployeetype']") WebElement EmployeeType;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpAction']") WebElement Action;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtRemarks']") WebElement Remark;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lblAddNew']") WebElement AddNewFamilyMember;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_OkButton']") WebElement OKButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtDependentName']") WebElement FamilyMemberName;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpMaritalStatus']") WebElement MaritalStatus;

	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtAddress']") WebElement FAddress;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_TxtPhoneNo']") WebElement FPhoneNo;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpRelation']") WebElement Relationship;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_imgCalBirthtDate']") WebElement FCalenderButton;
	@FindBy(id="ctl00_ContentPlaceHolder_CalExtBirthDate_nextArrow") WebElement FNextCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_CalExtBirthDate_title") WebElement FMiddleCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_CalExtBirthDate_prevArrow") WebElement FPrevCalender;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalExtBirthDate_monthsBody']/tr/td/div") List<WebElement> FAllMonth;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalExtBirthDate_daysBody']/tr/td[@class='']") List<WebElement> FAllDates;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpCountry']") WebElement Birthcountry;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpState']") WebElement BirthState;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpLocation']") WebElement BirthLocation;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpDepenType']") WebElement FamilyType;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_TxtOccupation']") WebElement Occupation;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpIdtype']") WebElement IdentityType;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtIdnum']") WebElement IdentityNo;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_OkButtondt']") WebElement FOKButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CancelButtondt']") WebElement FCancelButton;
			
	String ClearSearchName = "Clear Search Button";
	String CalendarMonthName = "Calendar Month Dropdown";
	
	public EmployeeSoftJoining(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_Submitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","SoftJoiningProcess");
		lp = new LoginPage(driver);
		lp.Login(map.get(1).get("CompanyCode"),map.get(1).get("UserName"), map.get(1).get("Password"));
	}
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, Recruitment, submoduleName, SoftJoiningProcess, subsectionName, EmployeeSoftJoining, pageName,PageHeader);
	
	}
	
	public void Test_Search_Record(String SearchDropdownData, String searchdata) throws Exception {
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		cm.Test_Search_Button(SearchDropdown, SearchDropdownData, "Search Dropdown", SearchTextBox, searchdata, SearchButton);
}
	
	public void Test_AddNew(String TitleData, String CandidateFirstNameData, String CandidateMiddleNamedata,String CandidateLastNamedata, String CandidateRegionalNamedata, String CandidatepreferredNamedata, String NationalityData, String OrganizationData, String BranchData, String GenderData, String MaritalData, String dobExceldate, String NationalIDdata, String LevelData, String DivisionData, String DepartmentData, String BasicSalaryRangedata, String PositionData, String CostCenterData, String ReportingManagerdata,String ejExceldate, String MobileNodata, String HomeNodata, String PersonalEmaildata, String PassportNodata, String ResidentTypeDATA, String BudgetedData, String ReasonForRequirementData, String Reasondata, String PORdata, String POHdata, String NPdata, String ppExceldate, String EmployeeTypeData, String FamilyDetails, String FamilyMemberNamedata, String MaritalStatusData, String FamilyGenderData, String FAddressdata, String FPhoneNodata, String RelationshipData, String FExceldate, String BirthcountryData, String BirthStateData, String BirthLocationData, String FamilyTypeData, String Occupationdata, String IdentityTypeData, String IdentityNodata, String ActionData, String Remarkdata) throws Exception{
		bc = new BaseClass(driver);
		bc.waitFor(1);
		bc.click(AddNewButton, "Add New Button");
		bc.selectByVisibleText(Title, TitleData, "Title");
		bc.waitFixedTime(3);
		bc.SendKeys(CandidateFirstName, CandidateFirstNameData, "Candidate First Name");
		bc.SendKeys(CandidateMiddleName, CandidateMiddleNamedata, "Candidate Middle Name");
		bc.SendKeys(CandidateLastName, CandidateLastNamedata, "Candidate Last Name");
		bc.SendKeys(CandidateRegionalName, CandidateRegionalNamedata, "Candidate Regional Name");
		bc.click(CandidateLastName, "Last Name");
		bc.waitFixedTime(3);
		bc.SendKeys(CandidatepreferredName, CandidatepreferredNamedata, "Candidate preferred Name");
		bc.click(CandidateLastName, "Last Name");
		bc.waitFixedTime(3);
		bc.selectByVisibleText(Nationality, NationalityData, "Nationality");
		bc.selectByVisibleText(Organization, OrganizationData, "Organization");
		bc.waitFixedTime(3);
	
		bc.selectByVisibleText(Branch, BranchData, "Branch");
		
		WebElement Gender = driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder_RadioButtonGender']/tbody/tr/td/label[text()='"+GenderData+"']"));
		bc.click(Gender, "Gender");
		
		bc.selectByVisibleText(Marital, MaritalData, "Marital Status");
		bc.DateSelection(dobExceldate, dobCalenderButton, dobNextCalender, dobMiddleCalender, dobPrevCalender, dobAllMonth, dobAllDates);
		bc.SendKeys(NationalID, NationalIDdata, "National ID");
		bc.selectByVisibleText(Level, LevelData, "Level");
		bc.waitFixedTime(3);
		bc.selectByVisibleText(Division, DivisionData, "Division");
		bc.waitFixedTime(3);
		bc.selectByVisibleText(Department, DepartmentData, "Department");
		bc.waitFixedTime(3);
		bc.SendKeys(BasicSalaryRange, BasicSalaryRangedata, "Basic Salary Range");
		bc.click(NationalID, "National ID");
		bc.waitFixedTime(2);
		bc.selectByVisibleText(Position, PositionData, "Position");
		bc.waitFixedTime(2);
		bc.AlertAcceptIfPresent();
		bc.selectByVisibleText(CostCenter, CostCenterData, "Cost Center");
		bc.click(ReportingManager, "Reporting Manager");
		bc.waitFixedTime(2);
		bc.SendKeysSearchBoxDropdownValue(ReportingManager, ReportingManager, ReportingManagerdata, "Reporting Manager", 3);
		bc.waitFixedTime(8);
	//	bc.SendKeysSearchBoxDropdownValue(ReportingLevel2, ReportingLevel2label, ReportingLeveldata, "Reporting Level", 3);
		bc.DateSelection(ejExceldate, ejCalenderButton, ejNextCalender, ejMiddleCalender, ejPrevCalender, ejAllMonth, ejAllDates);
		bc.ScrollDown();
		bc.SendKeys(MobileNo, MobileNodata, "Mobile No");
		bc.SendKeys(HomeNo, HomeNodata, "Home No");
		bc.SendKeys(PersonalEmail, PersonalEmaildata, "Personal Email");
		bc.SendKeys(PassportNo, PassportNodata, "Passport No");
		bc.selectByVisibleText(ResidentType, ResidentTypeDATA, "Resident Type");
		
		WebElement Budgeted = driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder_rdblBudgetedPositn']/tbody/tr/td/label[text()='"+BudgetedData+"']"));
		bc.click(Budgeted, "Budgeted");
		
		bc.selectByVisibleText(ReasonForRequirement, ReasonForRequirementData, "Reason For Requirement");
		bc.waitFixedTime(3);
		bc.SendKeys(Reason, Reasondata, "Reason");
		bc.SendKeysSearchBoxDropdownValue(POR, PORLabel, PORdata, "point of repatriation", 3);
		bc.waitFixedTime(2);
		bc.SendKeysSearchBoxDropdownValue(POH, POHLabel, POHdata, "Point Of Hire", 3);
		bc.waitFixedTime(3);
		bc.SendKeys(NP, NPdata, "Notice Period");
		bc.DateSelection(ppExceldate, PPCalenderButton, PPNextCalender, PPMiddleCalender, PPPrevCalender, PPAllMonth, PPAllDates);
		bc.selectByVisibleText(EmployeeType, EmployeeTypeData, "Employee Type");
		if(FamilyDetails=="Yes"){
		bc.click(AddNewFamilyMember, "Add New Family Member");
		bc.waitFixedTime(1);
		bc.SendKeys(FamilyMemberName, FamilyMemberNamedata, "Family Member Name");
		bc.selectByVisibleText(MaritalStatus, MaritalStatusData, "Marital Status");
		
		WebElement FamilyGender = driver.findElement(By.xpath(".//*[@class='col-2-popup']/tbody/tr/td/label[text()='"+FamilyGenderData+"']"));
		bc.click(FamilyGender, "Family Gender");
		
		bc.SendKeys(FAddress, FAddressdata, "Address");
		bc.SendKeys(FPhoneNo, FPhoneNodata, "Phone No");
		bc.selectByVisibleText(Relationship, RelationshipData,"Relationship");
		bc.waitFixedTime(2);
		bc.DateSelection(FExceldate, FCalenderButton, FNextCalender, FMiddleCalender, FPrevCalender, FAllMonth, FAllDates);
		bc.waitFixedTime(3);
		bc.selectByVisibleText(Birthcountry, BirthcountryData, "Birth Country");
		bc.waitFixedTime(2);
		bc.selectByVisibleText(BirthState, BirthStateData, "Birth State");
		bc.waitFixedTime(2);
		bc.selectByVisibleText(BirthLocation, BirthLocationData, "Birth Location");
		bc.selectByVisibleText(FamilyType, FamilyTypeData, "Family Type");
		bc.SendKeys(Occupation, Occupationdata, "Occupation");
		bc.selectByVisibleText(IdentityType, IdentityTypeData, "Identity Type");
		bc.SendKeys(IdentityNo, IdentityNodata, "Identity No");
		bc.click(FOKButton, "Family OK Button");
		}else{
			bc.log("Family Details not required");
		}
		bc.selectByVisibleText(Action, ActionData, "Action");
		bc.SendKeys(Remark, Remarkdata, "Remark");
		bc.click(OKButton, "Ok Button");
		bc.AlertAcceptIfPresent();
				
}
	
	public void Test_VerifyEmployee(String FirstName, String MiddleName, String LastName){
		bc = new BaseClass(driver);
		try{
			WebElement NewEmployee = driver.findElement(By.xpath(".//*[text()='"+FirstName+" "+MiddleName+" "+LastName+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(NewEmployee), "employee not added successfully");
			bc.log("Employee Added Successfully");
		}catch(Exception e){
			bc.log("Employee not Added Successfully");
		}
	}
	
	/*
	public void Test_VerifyAttendanceRejectandApprove(String DateData, String ColourCode ){
		bc = new BaseClass(driver);
	
		try{
		WebElement AttendanceDateWithStatusApproveReject = driver.findElement(By.xpath(".//*[text()='"+DateData+"']/parent::div/parent::td[@style='"+ColourCode+"']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(AttendanceDateWithStatusApproveReject));
		}catch(AssertionError e){
			Assert.fail("Attendance Neither Approve nor Reject");
		}
				
	}*/
	

	
	
}
	
	

