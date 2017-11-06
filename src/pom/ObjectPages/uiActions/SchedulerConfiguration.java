package pom.ObjectPages.uiActions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pom.TestBase.BaseClass;
import pom.TestBase.CommonMethods;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;

public class SchedulerConfiguration extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Application Monitor") WebElement ApplicationMonitor;
	@FindBy(linkText="Scheduler Configuration") WebElement SchedulerConfiguration;  
	
	String moduleName = "Enterprise Setup";
	String submoduleName = "Application Monitor";
	String pageName = "Scheduler Configuration";
	String PageTitle = "Scheduler Configuration";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpSearchList']") WebElement SearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearchText']") WebElement SearchTextBox;

	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdSearch']") WebElement SearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchbutton;
				
	String upName = "Upload Button";
	String utName = "Upload Type Dropdown";
	
	String SDName = "Search Dropdown";
	String STName = "Search TextBox";
	String SBName = "Search Button";
	String CSBName = "Clear Search Button";
		
	public SchedulerConfiguration(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_Submitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(28).get("CompanyCode"),map.get(28).get("UserName"), map.get(28).get("Password"));
		
	}
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, ApplicationMonitor, submoduleName, SchedulerConfiguration, pageName, PageHeader);
	}
	
	public void Test_SearchRecord(String SearchDropdownData, String SearchTextBoxData) throws Exception{
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(SearchDropdown, SearchDropdownData, SDName, SearchTextBox, SearchTextBoxData, SearchButton);
	
}
	
	public void Test_RunScheduler(String ScedulerName){
		bc = new BaseClass(driver);
		bc.waitFixedTime(1);
		WebElement ScedulerEntry = driver.findElement(By.xpath(".//*[text()='"+ScedulerName+"']/parent::tr/td/a[text()='Run']"));
		bc.click(ScedulerEntry, ScedulerName);
		bc.waitForAlertBox(20);
	}
	
}
	
	

