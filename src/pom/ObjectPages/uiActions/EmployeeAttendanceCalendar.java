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

public class EmployeeAttendanceCalendar extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Human Resource") WebElement HRMS;
	@FindBy(linkText="Employee Self Service") WebElement ESS;
	@FindBy(linkText="Leave & Attendance") WebElement LeaveandAttendance;  
	@FindBy(linkText="Employee Attendance Calendar") WebElement EmployeeAttendanceCalendar;
	String module = "Human Resource";
	String submoduleName = "ESS";
	String subsectionName = "Leave and Attendance";
	String pageName = "Employee Attendance Calender";
	String PageTitle = "Employee Attendance Calender";
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='Employee Attendance Calender']") WebElement PageHeader;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropPayyear']") WebElement Year;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpMonths']") WebElement CalendarMonth;
	
	@FindBy(xpath=".//*[text()='25']") WebElement Date;

	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_drpattStatus']") WebElement AttendanceStatus;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_txtRemarks']") WebElement Remarks;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_dgAttRegu_txtintime1_0']") WebElement InTime;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_dgAttRegu_txtouttime1_0']") WebElement OutTime;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_SaveButton']") WebElement SaveButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_tabContainer_TabPanel1_CancelSaveBtn']") WebElement CancelButton;
			
	String YearName = "Year Dropdown";
	String CalendarMonthName = "Calendar Month Dropdown";
	
	String ASName = "Attendance Status";
	String RemarkName = "Remark";
	String ITName = "In Time";
	String OuttName = "Out Time";
	String sbName = "Save Button";
	String cbName = "Cancel Button";
	
	public EmployeeAttendanceCalendar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_Submitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(23).get("CompanyCode"),map.get(23).get("UserName"), map.get(23).get("Password"));
		
	}
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, EmployeeAttendanceCalendar, pageName,PageHeader);
	}
	
	public void Test_Search_Record(String MonthData) throws Exception {
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
	
		bc.waitForElement(Year);
		bc.selectByIndex(Year, 2, YearName);
		bc.waitForElement(CalendarMonth);
		bc.selectByVisibleText(CalendarMonth, MonthData, CalendarMonthName);
	}
	
	public void Test_Employee_Attendance_Calender(String MonthData, String Date, String AttendanceStatusData,String Remarksdata, String InTimedata, String OutTimedata) throws Exception{
		bc = new BaseClass(driver);
		this.Test_Search_Record(MonthData);
		bc.waitFor(2);
		bc.ScrollDown();
		WebElement DATEwithNAME = driver.findElement(By.xpath(".//*[text()='"+Date+"']"));
		bc.click(DATEwithNAME, "date");
		
		bc.ScrollDown();
		bc.waitForElement(AttendanceStatus);
		bc.selectByVisibleText(AttendanceStatus, AttendanceStatusData, ASName);
		bc.SendKeys(Remarks, Remarksdata, RemarkName);
		InTime.sendKeys(InTimedata);
		OutTime.sendKeys(OutTimedata);
		
		bc.click(InTime, ITName);
		bc.click(SaveButton, sbName);
		bc.AlertAcceptIfPresent();
				
}
	
	public void Test_VerifyAttendance(String Date){
		bc = new BaseClass(driver);
		try{
			WebElement SubmittedAttendance = driver.findElement(By.xpath(".//*[@style='background-color:#ffd695;']/div/a[text()='"+Date+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(SubmittedAttendance), "submitted colour is not present");
			bc.log("Attendance Regularization entry submitted successfully");
		}catch(Exception e){
			bc.log("attendance not regularized");
		}
	}
	
	
	public void Test_VerifyAttendanceRejectandApprove(String DateData, String ColourCode ){
		bc = new BaseClass(driver);
	
		try{
		WebElement AttendanceDateWithStatusApproveReject = driver.findElement(By.xpath(".//*[text()='"+DateData+"']/parent::div/parent::td[@style='"+ColourCode+"']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(AttendanceDateWithStatusApproveReject));
		}catch(AssertionError e){
			Assert.fail("Attendance Neither Approve nor Reject");
		}
				
	}
	

	
	
}
	
	

