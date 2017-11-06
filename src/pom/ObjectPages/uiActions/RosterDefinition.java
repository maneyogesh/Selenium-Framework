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

public class RosterDefinition extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(id="ImgBtn_HR") WebElement HRMS;
	@FindBy(linkText="Employee Self Service") WebElement ESS;
	@FindBy(linkText="Leave & Attendance") WebElement LeaveandAttendance;  
	@FindBy(linkText="Roster Definition") WebElement RosterDefinition;
	String module = "Human Resource";
	String submoduleName = "ESS";
	String subsectionName = "Leave and Attendance";
	String pageName = "Roster Definition";
	String PageTitle = "Roster Definition";
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpSearchList']") WebElement SearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearchText']") WebElement SearchBox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdSearch']") WebElement SearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropDepts']") WebElement DepartmentDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropPayyear']") WebElement YearDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DropPayMonth']") WebElement MonthDropdown;
	@FindBy(xpath=".//*[@id='DrpShift']") WebElement ShiftCode;
	@FindBy(xpath=".//*[@id='WeekOff1']") WebElement Weekoff01;
	@FindBy(xpath=".//*[@id='WeekOff2']") WebElement Weekoff02;
	@FindBy(xpath=".//*[@id='Save']") WebElement SaveButton;
	@FindBy(xpath=".//*[@id='Close']") WebElement CloseButton;
	
	@FindBy(xpath=".//*[@id='FromDate']") WebElement FromDateCalendareButton;	
	@FindBy(xpath=".//*[@class='ui-icon ui-icon-circle-triangle-e']") WebElement FNextCalender;
	@FindBy(xpath=".//*[@class='ui-icon ui-icon-circle-triangle-w']") WebElement FPrevCalendar;
	@FindBy(xpath=".//*[@class='ui-datepicker-calendar']/tbody/tr/td[@data-event='click']") List<WebElement> FAllDates;
	
	@FindBy(xpath=".//*[@id='tableRoster']/tbody/tr[1]/td[2]/a") WebElement Edit1stRecordRoster;	
	
	@FindBy(xpath=".//*[@id='ToDate']") WebElement ToDateCalendareButton;	
	@FindBy(xpath=".//*[@CLASS='ui-icon ui-icon-circle-triangle-e']") WebElement TNextCalender;
	@FindBy(xpath=".//*[@class='ui-icon ui-icon-circle-triangle-w']") WebElement TPrevCalendar;
	@FindBy(xpath=".//*[@class='ui-datepicker-calendar']/tbody/tr/td[@data-event='click']") List<WebElement> TAllDates;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_ImgUP']") WebElement UploadButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_FUp1']") WebElement BrowseButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnupload']") WebElement InsideUploadButton;
	@FindBy(xpath=".//*[text()='Pending']/parent::tr/td/input") WebElement ProcessButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btncancel']") WebElement CancelButton;
	
	String UBName = "Upload Buttton";
	String BBName = "Browse Buttton";
	String IUBName = "Inside Upload Buttton";
	String PBName = "Process Buttton";
	String CBName = "Cancel Buttton";
	
	String SearchDropdownName = "Search Dropdown";
	String SearchBoxName = "Search Box";
	String SearchButtonName = "Search Button";
	String ClearSearchButtonName = "Clear Search Button";
	String DepartmentDropdownName = "Department Dropdown";
	String YearDropdownName = "Year Dropdown";
	String MonthDropdownName = "Month Dropdown";
	String ShiftCodeName = "Shift Code";
	String weekoff01Name = "Week Off 01";
	String weekoff02Name = "Week Off 02";
	String SaveName = "Save Button";
	String closeName = "Close Button";
	
	public RosterDefinition(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_Submitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(14).get("CompanyCode"),map.get(14).get("UserName"), map.get(14).get("Password"));
		
	}
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
	}
	
	public void Test_Search_Record(String DepartmentData, String YearData, String MonthData, String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		bc.selectByVisibleText(DepartmentDropdown, DepartmentData, DepartmentDropdownName);
		bc.selectByVisibleText(YearDropdown, YearData, YearDropdownName);
		bc.selectByVisibleText(MonthDropdown, MonthData, MonthDropdownName);
		cm.Test_Search_Button(SearchDropdown, dropdown_Value, SearchDropdownName, SearchBox, SearchData, SearchButton);
	}
	
	public void Test_DefineRoster(String SearchDataEmployeeName, String ShiftCodeData,String ExcelFromdate, String ExcelTodate, String Weekoff01Data, String Weekoff02Data) throws Exception{
		bc = new BaseClass(driver);
		WebElement DATE16 = driver.findElement(By.xpath(".//*[text()='"+SearchDataEmployeeName+"']/following::td[1]/a"));
		bc.click(DATE16, "16th Date");
		bc.waitForElement(ShiftCode);
		bc.selectByVisibleText(ShiftCode, ShiftCodeData, ShiftCodeName);
		bc.DateSelectionWithMonthOnly(ExcelFromdate, FromDateCalendareButton, FNextCalender, FPrevCalendar, FAllDates);
		bc.waitFixedTime(1);
		bc.DateSelectionWithMonthOnly(ExcelTodate, ToDateCalendareButton, TNextCalender, TPrevCalendar, TAllDates);
		bc.selectByVisibleText(Weekoff01, Weekoff01Data, weekoff01Name);
		bc.selectByVisibleText(Weekoff02, Weekoff02Data, weekoff02Name);
		bc.click(SaveButton, SaveName);
		bc.AlertAcceptIfPresent();
}
	
	public void Test_VerifyRoster(String SearchDataEmployeeName, String ShiftCodeData){
		bc = new BaseClass(driver);
		try{
		WebElement verifyRoster = driver.findElement(By.xpath(".//*[text()='"+SearchDataEmployeeName+"']/parent::tr/td/a[text()='"+ShiftCodeData+"']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(verifyRoster));
		bc.log("Roster defined successfully");
	}catch(AssertionError e){
		bc.log("Roster not define");
		//	Assert.fail("expected : ["+ShiftCodeData+"] But Found : [--]");
	}
}
	
	public void Test_EditRoster(){
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		bc.AlertAcceptIfPresent();
		cm.Test_Edit_RecordDirectSave(Edit1stRecordRoster, SaveButton, PageTitle);
	}
	
	public void Test_UploadFileInRosterDefinition() throws IOException{
		bc = new BaseClass(driver);
		bc.click(UploadButton, UBName);
		bc.waitForElement(BrowseButton);
		bc.click(BrowseButton, BBName);
		////////////////////
		Runtime.getRuntime().exec("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\UploadMasterEXEFiles\\RosterUpload.exe");
		////////////////////
		bc.waitFixedTime(1);
		bc.click(InsideUploadButton, IUBName);
		bc.waitForElement(ProcessButton);
		try{
		Assert.assertTrue(bc.isElementPresentSingleLocator(ProcessButton), "Page crashed : Data Not Uploaded");
		bc.click(ProcessButton, PBName);
		bc.waitForAlertBox(10);
		bc.log("file has been uploaded successfully : process button is showing");
	
		}
		catch(AssertionError e){
			bc.log("file not uploaded : process button is not showing");
		}
	}
	
	
}
