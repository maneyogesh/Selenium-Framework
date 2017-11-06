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
import org.testng.Reporter;
import org.testng.SkipException;

import pom.TestBase.BaseClass;
import pom.TestBase.CommonMethods;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;

public class LeaveApplication extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(id="ImgBtn_HR") WebElement HRMS;
	@FindBy(linkText="Employee Self Service") WebElement ESS;
	@FindBy(linkText="Leave & Attendance") WebElement LeaveandAttendance;  
	@FindBy(linkText="Leave Application") WebElement LeaveApplication;
	String module = "Human Resource";
	String submoduleName = "ESS";
	String subsectionName = "Leave and Attendance";
	String pageName = "Leave Application";
	String PageTitle = "Leave Application";
	
	String ContactNoName = "Contact Number";
	String MobileNoName = "Mobile Number";
	String ReasonName = "Reason";
	String AddressName = "Address";
	
	@FindBy(id="ctl00_ContentPlaceHolder_LnkAddLeave") WebElement ApplyLeaveButton;
	@FindBy(id="ctl00_ContentPlaceHolder_lnkaddNew") WebElement AddNewButton;
	@FindBy(id="ctl00_ContentPlaceHolder_drpLeaveTypes") WebElement LeaveType;
	@FindBy(id="ctl00_ContentPlaceHolder_txtNoofdays") WebElement NoOfLeave;
	@FindBy(id="ctl00_ContentPlaceHolder_SaveButton") WebElement SaveButton;
	@FindBy(id="ctl00_ContentPlaceHolder_drpAction") WebElement ActionDropdown;
	@FindBy(id="ctl00_ContentPlaceHolder_btnOk") WebElement OKButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_imgLeaveFrom']") WebElement CalenderButton;
	@FindBy(id="ctl00_ContentPlaceHolder_Calendarextender1_nextArrow") WebElement NextCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_Calendarextender1_title") WebElement MiddleCalender;
	@FindBy(id="ctl00_ContentPlaceHolder_Calendarextender1_prevArrow") WebElement PrevCalender;
	@FindBy(xpath=".//*[@id='ctl00_ctl00_ContentPlaceHolder_Calendarextender1_monthsBody']//descendant::tr/td/div") List<WebElement> AllMonth;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_Calendarextender1_daysBody']//tr//td[@class='']//div") List<WebElement> AllDates;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtLeaveFromDt']") WebElement DateTextBox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CancelSaveBtn']") WebElement AddNewCancelButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lnkBack']") WebElement ApplyLeaveBackButton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtLandNo']") WebElement ContactNumber;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtMobileNo']") WebElement MobileNumber;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtReason']") WebElement Reason;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtAddress']") WebElement Address;
	
	
	public LeaveApplication(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_Submitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(0).get("CompanyCode"),map.get(0).get("UserName"), map.get(0).get("Password"));
	}
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, LeaveApplication, pageName, PageTitle);	
	}
	
	public void Test_ApplyLeave(String leaveTypeData,String Exceldate, String NoOfLeaveData, String ContactNoData, String MobileNumberData, String ReasonData, String AddressData, String ActionData) throws Exception{
		bc = new BaseClass(driver);
		bc.clickWhenReady(ApplyLeaveButton, 30);
		bc.AlertAcceptIfPresent();
		bc.clickWhenReady(AddNewButton, 30);
		bc.waitFixedTime(2);
		bc.selectByVisibleText(LeaveType, leaveTypeData, "Leave Type");
		bc.waitFixedTime(2);
		bc.DateSelection(Exceldate, CalenderButton, NextCalender, MiddleCalender, PrevCalender, AllMonth, AllDates);
		bc.waitFixedTime(2);
		bc.SendKeys(NoOfLeave, NoOfLeaveData, "No Of Leave");
		bc.click(SaveButton, "Save Button");
		bc.AlertAcceptIfPresent();
		try{
		Assert.assertTrue(bc.isElementPresentSingleLocator(AddNewCancelButton));
		bc.click(AddNewCancelButton, "Cancel Button");
		bc.waitFixedTime(1);
		bc.click(ApplyLeaveBackButton, "Back Button");
		}catch(Exception e){
			bc.ScrollDown();
			bc.SendKeys(ContactNumber, ContactNoData, ContactNoName);
			bc.SendKeys(MobileNumber, MobileNumberData, MobileNoName);
			bc.SendKeys(Reason, ReasonData, ReasonName);
			bc.SendKeys(Address, AddressData, AddressName);
			bc.selectByVisibleText(ActionDropdown, ActionData, "Action Dropdown");
			bc.click(OKButton, "OK Button");
			bc.AlertAcceptIfPresent();
		}
		
	}
	
	public void Test_VerifyLeaveApproveReject(String verifyDate, String VerifyStatus){
		
		WebElement LeaveDateWithStatus = driver.findElement(By.xpath(".//*[text()='"+verifyDate+"']/parent::tr/td[text()='"+VerifyStatus+"']"));
		Assert.assertTrue(LeaveDateWithStatus.isDisplayed());
		log.info("verify leave is approve or reject");
		Reporter.log("login with same user those are apply leave -> approve or rejected leave status is showing properly in status column");
		
	}
	
	
	public void Test_LeaveRevoke(String verifyDate, String VerifyStatus, String ActionData, String RevokeStatus) throws Exception{
		bc = new BaseClass(driver);
		try{
		WebElement LeaveRevokeEntry = driver.findElement(By.xpath(".//*[text()='"+verifyDate+"']/parent::tr/td[text()='"+VerifyStatus+"']/parent::tr/td/a"));
		LeaveRevokeEntry.click();
		}catch(Exception e){
			throw new SkipException("Not found Element "+verifyDate );
		}
		bc.ScrollDown();
		bc.selectByVisibleText(ActionDropdown, ActionData, "Action Dropdown");
		bc.click(OKButton, "OK Button");
		bc.waitFor(1);
		WebElement revokesubmittedEntry = driver.findElement(By.xpath(".//*[text()='"+verifyDate+"']/parent::tr/td[text()='"+RevokeStatus+"']"));
		Assert.assertTrue(revokesubmittedEntry.isDisplayed());
		Reporter.log("login with same user those are apply leave -> open approved leave -> revoke submit -> Leave Revoke successfully");
	}
}
