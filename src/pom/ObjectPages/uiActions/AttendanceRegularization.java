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

public class AttendanceRegularization extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Human Resource") WebElement HRMS;
	@FindBy(linkText="Employee Self Service") WebElement ESS;
	@FindBy(linkText="Leave & Attendance") WebElement LeaveandAttendance;  
	@FindBy(linkText="Attendance Regularization") WebElement AttendanceRegularization;
	String module = "Human Resource";
	String submoduleName = "ESS";
	String subsectionName = "Leave and Attendance";
	String pageName = "Attendance Regularization";
	String PageTitle = "Attendance Regularization";
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='ATTENDANCE REGULARIZATION']") WebElement PageHeader;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropDepts']") WebElement Department;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropPayyear']") WebElement Year;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropPayMonth']") WebElement CalendarMonth;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpSearchList']") WebElement SearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearchText']") WebElement SearchTextBox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdSearch']") WebElement SearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearch;
		
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_imgToDt']") WebElement CalendarButton;	
	@FindBy(xpath=".//*[@id='_content_calToDate_nextArrow']") WebElement NextCalenderButton;
	@FindBy(xpath=".//*[@id='_content_calToDate_prevArrow']") WebElement PrevCalendarButton;
	@FindBy(xpath=".//*[@id='_content_calToDate_title']") WebElement MiddleCalendarButton;
	@FindBy(xpath=".//*[@id='_content_calToDate_daysBody']/tr/td[@class='ajax__calendar_today' OR @class='']") List<WebElement> AllCalendarDays;
	@FindBy(xpath=".//*[@id='_content_calToDate_monthsBody']/tr/td/div") List<WebElement> AllCalendarMonths;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_drpattStatus']") WebElement AttendanceStatus;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_txtRemarks']") WebElement Remarks;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_dgAttRegu_txtintime1_0']") WebElement InTime;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_dgAttRegu_txtouttime1_0']") WebElement OutTime;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_SaveButton']") WebElement SaveButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_CancelSaveBtn']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpSearchList']") WebElement PlannerSearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearchText']") WebElement PlannerSearchTextBox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdSearch']") WebElement PlannerSearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropPayyear']") WebElement PlannerYearDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropPayMonth']") WebElement PlannerMonthDropdown;
		
	String DepartmentName = "Department Dropdown";
	String YearName = "Year Dropdown";
	String CalendarMonthName = "Calendar Month Dropdown";
	String sdName = "Search Dropdown";
	String STName = "Search TextBox";
	String SearchButtonName = "Search Button";
	String ClearSearchButtonName = "Clear Search Button";
	
	String ASName = "Attendance Status";
	String RemarkName = "Remark";
	String ITName = "In Time";
	String OuttName = "Out Time";
	String sbName = "Save Button";
	String cbName = "Cancel Button";
	
	public AttendanceRegularization(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_Submitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(18).get("CompanyCode"),map.get(18).get("UserName"), map.get(18).get("Password"));
		
	}
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, AttendanceRegularization, pageName,PageHeader);
	}
	
	public void Test_Search_Record(String DepartmentData, String YearData, String MonthData, String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		bc.waitForElement(Department);
		bc.selectByVisibleText(Department, DepartmentData, DepartmentName);
		bc.waitFor(8);
		bc.waitForElement(Year);
		bc.selectByIndex(Year, 2, YearName);
		bc.waitForElement(CalendarMonth);
		bc.selectByVisibleText(CalendarMonth, MonthData, CalendarMonthName);
		bc.waitForElement(SearchDropdown);
		cm.Test_Search_Button(SearchDropdown, dropdown_Value, sdName, SearchTextBox, SearchData, SearchButton);
	}
	
	public void Test_Attendance_Regularization(String searchdropdown, String SearchDataEmployeeName, String Date, String AttendanceStatusData,String Remarksdata, String InTimedata, String OutTimedata) throws Exception{
		bc = new BaseClass(driver);
		bc.selectByVisibleText(SearchDropdown, searchdropdown, sdName);
		bc.SendKeys(SearchTextBox, SearchDataEmployeeName, STName);
		bc.click(SearchButton, SearchButtonName);
		bc.waitFor(2);
		WebElement DATEwithNAME = driver.findElement(By.xpath(".//*[text()='"+SearchDataEmployeeName+"']/parent::td/parent::tr/td["+Date+"]/div/div/a"));
		bc.click(DATEwithNAME, "date");
		
		bc.ScrollDown();
		bc.waitForElement(AttendanceStatus);
		bc.selectByVisibleText(AttendanceStatus, AttendanceStatusData, ASName);
		bc.SendKeys(Remarks, Remarksdata, RemarkName);
		bc.SendKeys(InTime, InTimedata, ITName);
		bc.SendKeys(OutTime, OutTimedata, OuttName);
		bc.click(InTime, ITName);
		bc.click(SaveButton, sbName);
		bc.AlertAcceptIfPresent();
		
		
}
	
	public void Test_VerifyAttendance(String SearchDataEmployeeName, String Date){
		bc = new BaseClass(driver);
		try{
			WebElement SubmittedAttendance = driver.findElement(By.xpath(".//*[text()='"+SearchDataEmployeeName+"']/parent::td/parent::tr/td["+Date+"]/div[@style='background-color:#ffd695;']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(SubmittedAttendance), "submitted colour not present");
			bc.log("Attendance Regularization entry submitted successfully");
		}catch(Exception e){
			bc.log("attendance not regularized");
		}
	}
	
	
	public void Test_VerifyAttendanceReject(String SearchDataEmployeeName){
		bc = new BaseClass(driver);
		try{
		WebElement AttendanceDateWithStatusApproveReject = driver.findElement(By.xpath(".//*[text()='"+SearchDataEmployeeName+"']/parent::td/parent::tr/td/div[@style='background-color:#ec9f9c;']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(AttendanceDateWithStatusApproveReject));
		}catch(AssertionError e){
			Assert.fail("attendance neither approve nor reject");
		}
	}

	public void Test_VerifyAttendanceApprove(String SearchDataEmployeeName){
		bc = new BaseClass(driver);
	
		try{
		WebElement AttendanceDateWithStatusApproveReject = driver.findElement(By.xpath(".//*[text()='"+SearchDataEmployeeName+"']/parent::td/parent::tr/td/div[@style='background-color:#95cdac;']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(AttendanceDateWithStatusApproveReject));
		}catch(AssertionError e){
			Assert.fail("attendance neither approve nor reject");
		}
				
	}
	
	public void Test_VerifyAttendanceRejectandApprove(String SearchDataEmployeeName,String SingleDate, String ColourCode ){
		bc = new BaseClass(driver);
	
		try{
		WebElement AttendanceDateWithStatusApproveReject = driver.findElement(By.xpath(".//*[text()='"+SearchDataEmployeeName+"']/parent::td/parent::tr/td["+SingleDate+"]/div[@style='"+ColourCode+"']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(AttendanceDateWithStatusApproveReject));
		}catch(AssertionError e){
			Assert.fail("Attendance Neither Approve nor Reject");
		}
				
	}
	
	public void TestViewPlannerSearch(String MonthData, String PlannerSearchdropdownData, String PlannerSearchTextBoxData) throws Exception{
		bc = new BaseClass(driver);
		cm = new CommonMethods(driver);
			
		bc.waitForElement(PlannerYearDropdown);
		bc.selectByIndex(PlannerYearDropdown, 2, YearName);
		bc.waitForElement(PlannerMonthDropdown);
		bc.selectByVisibleText(PlannerMonthDropdown, MonthData, CalendarMonthName);
		bc.waitFixedTime(3);
		cm.Test_Search_Button(PlannerSearchDropdown, PlannerSearchdropdownData, "Planner Search Dropdown", PlannerSearchTextBox, PlannerSearchTextBoxData, PlannerSearchButton);
		bc.waitFixedTime(2);
	}
	
	
}
	
	

