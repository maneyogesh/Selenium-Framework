package pom.ObjectPages.uiActions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class Induction extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	LocalDate localDate = LocalDate.now();
	
	@FindBy(linkText="Human Resource") WebElement HRMS;
	@FindBy(linkText="Employee Life Cycle") WebElement EmployeeLifeCycle;
	@FindBy(linkText="Induction") WebElement Induction;  
	@FindBy(linkText="Induction Schedule") WebElement InductionSchedule;
	@FindBy(linkText="Induction Mapping") WebElement InductionMapping;
	@FindBy(linkText="Induction Feedback") WebElement InductionFeedBack;
	String module = "Human Resource";
	String submoduleName = "Employee Life Cycle";
	String subsectionName = "Induction";
	String pageName = "Induction Schedule";
	String PageTitle = "Induction Schedule";
	
	String FeedbackPageName = "Induction FeedBack";
	String FeedbackTitle = "Induction FeedBack";
	
	String MappingPageName = "Induction Mapping";
	String MappingTitle = "Induction Mapping";
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='INDUCTION SCHEDULE']") WebElement PageHeader;
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='EMPLOYEE INDUCTION MAPPING']") WebElement MappingPageHeader;
	@FindBy(xpath=".//*[@class='page-heading']") WebElement FeedbackHeader;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpSearchList']") WebElement SearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearchText']") WebElement SearchTextBox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnSearch']") WebElement SearchButton;

	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnClearSearch']") WebElement ClearSearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdAddNew']") WebElement AddNewButton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_ImgFd']") WebElement CalenderButton;
	@FindBy(id="ctl00_ContentPlaceHolder_CalendarExtender_nextArrow") WebElement NextCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_CalendarExtender_title") WebElement MiddleCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_CalendarExtender_prevArrow") WebElement PrevCalender;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalendarExtender_monthsBody']//descendant::tr/td/div") List<WebElement> AllMonth;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CalendarExtender_daysBody']/tr/td[not(contains(@class,'ajax__calendar_other'))]") List<WebElement> AllDates;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtvenue']") WebElement Venue;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtTimeIn']") WebElement InTime;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtTimeOut']") WebElement OutTime;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_OkButton']") WebElement OKButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lnkAddNew']") WebElement MappingAddNewButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_chkSelectAll']") WebElement SelectAll;
	@FindBy(id="ctl00_ContentPlaceHolder_drpSearchList") WebElement MappingSearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtNotMappSearch']") WebElement MappingSearchBox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_LnkNotMappSearch']") WebElement MappingSearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnSave123']") WebElement MappingOkButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnSave']") WebElement MappingSaveButton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpSearchList']") WebElement Mapping1stSearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearchText']") WebElement Mapping1stSearchBox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnSearch']") WebElement Mapping1stSearchButton;
	
	
	String ClearSearchName = "Clear Search Button";
	String MappingpageName ="Employee Induction Mapping";
	String ANName = "Add New";
	String SDName = "Search Dropdown";
	String STName = "Search TextBox";
	String SbName = "Search Button";
	String CName = "Calender Button";
	String NCName = "Next Calender Button";
	String MCName = "Middle Calender Button";
	String AllMonthName = "All Month";
	String AllDateName = "All Dates";
	String VenueName = "Venue";
	String InTName = "In Time";
	String OutTName = "Out Time";
	String OKName = "OK Button";
	String SName = "Save Button";
	String CancelName = "Cancel Button";
	String InductionIDName = "Induction ID";
	String c = "Checkbox";
	String sallc = "Select All Checkbox";
	 String CurrentSystemDayOnly = DateTimeFormatter.ofPattern("dd").format(localDate);
	
	public Induction(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_Submitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Induction");
		lp = new LoginPage(driver);
		lp.Login(map.get(1).get("CompanyCode"),map.get(1).get("UserName"), map.get(1).get("Password"));
	}
	
	public void Test_Login_Scheduler() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Induction");
		lp = new LoginPage(driver);
		lp.Login(map.get(2).get("CompanyCode"),map.get(2).get("UserName"), map.get(2).get("Password"));
	}
	
	public void Test_Login_Feedbacker() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Induction");
		lp = new LoginPage(driver);
		lp.Login(map.get(3).get("CompanyCode"),map.get(3).get("UserName"), map.get(3).get("Password"));
	}
	
	public void Test_Open_Target_Page_InductionSchedule() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, EmployeeLifeCycle, submoduleName, Induction, subsectionName, InductionSchedule, pageName,PageHeader);
	
	}
	
	public void Test_Open_Target_Page_InductionMapping() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, EmployeeLifeCycle, submoduleName, Induction, subsectionName, InductionMapping, MappingPageName,MappingPageHeader);
	
	}
	
	public void Test_Open_Target_Page_InductionFeedBack() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, EmployeeLifeCycle, submoduleName, Induction, subsectionName, InductionFeedBack, FeedbackPageName,FeedbackHeader);
	
	}
	
	public void Test_Search_Record(String SearchDropdownData) throws Exception {
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		cm.Test_Search_Button(SearchDropdown, SearchDropdownData, "Search Dropdown", SearchTextBox, CurrentSystemDayOnly, SearchButton);
}
	
	public void Test_Search_RecordMapping(String SearchDropdownData) throws Exception {
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		cm.Test_Search_Button(Mapping1stSearchDropdown, SearchDropdownData, "Search Dropdown", Mapping1stSearchBox, CurrentSystemDayOnly, Mapping1stSearchButton);
}
	
	public void Test_AddNew(String CompanyNameData, String Venuedata,String InTimedata, String OutTimedata) throws Exception{
		bc = new BaseClass(driver);
		 
	     System.out.println(">>>>>>>>>>"+DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate));
	     String CurrentSystemDate = DateTimeFormatter.ofPattern("dd/MM/yyy").format(localDate);
	    
		bc.waitFor(1);
		bc.click(AddNewButton, "Add New Button");
		bc.waitForElement(CalenderButton);
		bc.DateSelection(CurrentSystemDate, CalenderButton, NextCalender, MiddleCalender, PrevCalender, AllMonth, AllDates);
		
		WebElement CompanyName = driver.findElement(By.xpath(".//*[text()='"+CompanyNameData+"']"));
		bc.click(CompanyName, "Company Name");
		bc.SendKeys(Venue, Venuedata, VenueName);
		bc.SendKeys(InTime, InTimedata, InTName);
		bc.waitFixedTime(3);
		bc.SendKeys(OutTime, OutTimedata, OutTName);
		bc.waitFixedTime(3);
		bc.click(OKButton, OKName);
		bc.AlertAcceptIfPresent();
}
	
	public void Test_VerifyInductionSchedule(String InTimedata, String OutTimedata){
		bc = new BaseClass(driver);
			WebElement InductionEntry = driver.findElement(By.xpath(".//*[contains(text(),'"+InTimedata+":')]/parent::tr/td[contains(text(),'"+OutTimedata+":')]"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(InductionEntry), "Induction Schedule Successfully");
			bc.log("Induction Schedule Successfully");
	}
	
	public void Test_EmployeesInductionMapping(String InTime, String OutTime, String Dropdowndata, String SearchBoxData) throws Exception{
		bc = new BaseClass(driver);
		cm = new CommonMethods(driver);
		WebElement InductionId = driver.findElement(By.xpath(".//*[contains(text(),'"+InTime+":')]/parent::tr/td[contains(text(),'"+OutTime+":')]/parent::tr/td[contains(text(),'"+CurrentSystemDayOnly+"')]/parent::tr/td/a"));
		bc.click(InductionId, InductionIDName);
		bc.click(MappingAddNewButton, "Mapping Add New Button");
		bc.waitFixedTime(4);
//		cm.Test_Search_Button(MappingSearchDropdown, Dropdowndata, SDName, MappingSearchBox, SearchBoxData, MappingSearchButton);
		//bc.selectByIndex(MappingSearchDropdown, 1, SDName);
		bc.selectByVisibleText(MappingSearchDropdown, Dropdowndata, SDName);
		bc.SendKeys(MappingSearchBox, SearchBoxData, "Search box");
		bc.click(MappingSearchButton, "Mapping Search Button");
		bc.waitFixedTime(2);
		System.out.println("0");
		WebElement EmployeeCheckbox1 = driver.findElement(By.xpath(".//*[text()='"+SearchBoxData+"']/parent::tr/td/input[@type='checkbox']"));
		bc.click(EmployeeCheckbox1, c);
		System.out.println("1st");
		bc.waitFixedTime(1);
		bc.click(MappingOkButton, OKName);
		System.out.println("2nd");
		bc.waitFixedTime(2);
		bc.click(SelectAll, sallc);
		System.out.println("3rd");
		bc.waitFixedTime(2);
		bc.click(MappingSaveButton, SName);
		System.out.println("4th");
		bc.AlertAcceptIfPresent();
	}
	
	
	/*public void Test_VerifyAttendanceRejectandApprove(String DateData, String ColourCode ){
		bc = new BaseClass(driver);
	
		try{
		WebElement AttendanceDateWithStatusApproveReject = driver.findElement(By.xpath(".//*[text()='"+DateData+"']/parent::div/parent::td[@style='"+ColourCode+"']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(AttendanceDateWithStatusApproveReject));
		}catch(AssertionError e){
			Assert.fail("Attendance Neither Approve nor Reject");
		}
				
	}*/
	

	
}
	
	

