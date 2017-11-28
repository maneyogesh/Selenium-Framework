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

public class OnBehalfLeaveApplication extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Human Resource") WebElement HRMS;
	@FindBy(linkText="Employee Self Service") WebElement ESS;
	@FindBy(linkText="Leave & Attendance") WebElement LeaveandAttendance;  
	@FindBy(linkText="On Behalf Leave Application") WebElement OnBehalfLeaveApplication;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpSearchList']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement searchbutton;
	
	String module = "Human Resource";
	String submoduleName = "ESS";
	String subsectionName = "Leave and Attendance";
	String pageName = "On Behalf Leave Application";
	String PageTitle = "On Behalf Leave Application";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	
	
	public OnBehalfLeaveApplication(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_LoginSubmitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(8).get("CompanyCode"),map.get(8).get("UserName"), map.get(8).get("Password"));
	}
	
	///-----------------------> login leave approvar ------------------------------>
	public void Test_LoginApprovar() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(10).get("CompanyCode"),map.get(10).get("UserName"), map.get(10).get("Password"));
	}
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, OnBehalfLeaveApplication, pageName, PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, "Search Dropdown", searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_EditEmployee(String EmpCode){
		bc = new BaseClass(driver);
		WebElement EditEmp = driver.findElement(By.xpath(".//*[text()='"+EmpCode+"']/parent::tr/td/input"));
		EditEmp.click();
		bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.isElementPresentSingleLocator(PageHeader));		
	}
	
}
