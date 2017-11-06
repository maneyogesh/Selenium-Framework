package pom.ObjectPages.uiActions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;

import pom.TestBase.BaseClass;
import pom.TestBase.CommonMethods;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;

public class AttendanceApproval extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Human Resource") WebElement HRMS;
	@FindBy(linkText="Employee Self Service") WebElement ESS;
	@FindBy(linkText="Leave & Attendance") WebElement LeaveandAttendance;  
	@FindBy(linkText="Attendance Approval") WebElement AttendanceApproval;
	String module = "Human Resource";
	String submoduleName = "ESS";
	String subsectionName = "Leave and Attendance";
	String pageName = "Attendance Approval";
	String PageTitle = "Attendance Approval";
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpMonths']") WebElement PayMonthDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_bttsubmit']") WebElement OKButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_bttcancel']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='Attendance Approval']") WebElement PageHeader;
	
	String PMDName ="Pay Month Dropdown";
	String OKBNAME="OK Button";
	String CancelBName ="Cancel Button";
		
	public AttendanceApproval(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_1stlevelApproval() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(19).get("CompanyCode"),map.get(19).get("UserName"), map.get(19).get("Password"));
	}
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, LeaveApproval, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, AttendanceApproval, pageName, PageHeader);
	}
	
	public void Test_AttendanceApproveReject(String PayMonthDropdownData, String EmpName,String Date, String StatusData) throws Exception{
		bc = new BaseClass(driver);
		bc.selectByVisibleText(PayMonthDropdown, PayMonthDropdownData, PMDName);
		bc.log("Select Pay Month");
		try{
		WebElement checkboxwithdate = driver.findElement(By.xpath(".//*[text()='"+EmpName+"']/parent::tr/td/span[text()='"+Date+"']/parent::td/parent::tr/td/input[@type='checkbox']"));
		bc.waitForElement(checkboxwithdate);
		checkboxwithdate.click();
		bc.log("click on "+Date+"check box");
		}catch(Exception e){
			throw new SkipException(Date+" checkbox is not found");
		}
		bc.waitFixedTime(1);
		try{
			WebElement ActionDropdownwithdate = driver.findElement(By.xpath(".//*[text()='"+EmpName+"']/parent::tr/td/span[text()='"+Date+"']/parent::td/parent::tr/td/select[contains(@id,'DrpDwnAction_row')]"));
			Select s = new Select(ActionDropdownwithdate);
			s.selectByVisibleText(StatusData);
			bc.log("select status");
		}catch(Exception e){
			throw new SkipException(Date+"dropdown is not found");
		}
				
		try{
			Assert.assertEquals(StatusData, "Reject By RM");
			try{
				WebElement remarkBoxwithDate = driver.findElement(By.xpath(".//*[text()='"+EmpName+"']/parent::tr/td/span[text()='"+Date+"']/parent::td/parent::tr/td/textarea"));
				remarkBoxwithDate.sendKeys("Test Data");
				bc.log("Enter Remark : Test Data");
				}catch(Exception e){
					throw new SkipException(Date+"Remark box is not found");
				}
			bc.ScrollDown();
			bc.click(OKButton, OKBNAME);
			bc.log("clicked on submit button");
		}catch(AssertionError e){
			bc.ScrollDown();
			bc.click(OKButton, OKBNAME);
			bc.log("clicked on submit button");
		}
				
	}
}
