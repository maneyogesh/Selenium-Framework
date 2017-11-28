package pom.ObjectPages.uiActions;

import java.util.List;

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

public class CampusMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(xpath=".//*[text()='Human Resource']") WebElement HRMS;
	@FindBy(linkText="HR Setup") WebElement HRSetup;
	@FindBy(xpath=".//*[text()='HR Setup']//parent::a//parent::li/ul/li/a[@title='Recruitment']") WebElement Recruitment;  
	@FindBy(linkText="Campus Master") WebElement CampusMaster;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpSearchList']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdAddNew']") WebElement AddNewButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImageButton1']") WebElement Excelbutton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCampusCode']") WebElement CampusCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCampus']") WebElement CampusName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtBatch']") WebElement InterviewBatch;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtInterviewFrom']") WebElement InterviewTimeFrom;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtInterviewto']") WebElement InterviewTimeTo;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_chkActive']") WebElement Active;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnFnlAdd']") WebElement OKButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnFnlCan']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgCampus_imgEditButton_0']") WebElement FirstEditButton;
	
	
	String module = "Human Resource";
	String AddNewButtonName = "AddNew Button";
	String submoduleName = "HR Setup";
	String subsectionName = "Recruitment";
	String pageName = "Qualification Master";
	String PageTitle = "Qualification Master";
	String CCodeName = "Campus Code";
	String CName = "Campus Name";
	String IName = "Interview Batch";
	String ITFName = "Interview Time From";
	String ITTName = "Interview Time To";
	String ActiveName = "Active";
	
	String CancelButtonName= "Cancel Button";
	String sdName= "Search Dropdown";
	String SearchBoxButtonName= "SearchBox Button";
	String SearchButtonName= "Search Button";
	String OKButtonName= "OK Button";
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImaInterviewDate']") WebElement CalenderButton;
	@FindBy(id="ContentPlaceHolder_CalendarExtender1_nextArrow") WebElement NextCalender;
	@FindBy(id="ContentPlaceHolder_CalendarExtender1_title") WebElement MiddleCalender;
	@FindBy(id="ContentPlaceHolder_CalendarExtender1_prevArrow") WebElement PrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender1_monthsBody']//descendant::tr/td/div") List<WebElement> AllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender1_daysBody']//tr//td[not(contains(@class,'ajax__calendar_other'))]") List<WebElement> AllDates;
		
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
		
	public CampusMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, HRSetup, submoduleName, Recruitment, subsectionName, CampusMaster, pageName,PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, sdName, searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_Add_New_Button(){
		bc = new BaseClass(driver);
		bc.click(AddNewButton, AddNewButtonName);
		bc.waitFixedTime(1);
		Assert.assertTrue(bc.isElementPresentSingleLocator(CampusCode));
		bc.click(CancelButton, CancelButtonName);
	}
	
	public void Test_AddNew_Data(String CampusCodedata, String CampusNamedata, String ExceldateData, String InterviewBatchdata, String InterviewTimeFromData, String InterviewTimeTodata, String Activeness) throws InterruptedException{
		bc = new BaseClass(driver);
		
		bc.click(AddNewButton, AddNewButtonName);
		bc.waitFixedTime(1);
		bc.waitForElement(CampusCode);
		bc.SendKeys(CampusCode, CampusCodedata, CCodeName);
		bc.waitFixedTime(1);
		bc.SendKeys(CampusName, CampusNamedata, CName);
		bc.waitFixedTime(1);
		bc.DateSelection(ExceldateData, CalenderButton, NextCalender, MiddleCalender, PrevCalender, AllMonth, AllDates);
		bc.SendKeys(InterviewBatch, InterviewBatchdata, IName);
		bc.waitFixedTime(1);
		bc.SendKeys(InterviewTimeFrom, InterviewTimeFromData, ITFName);
		bc.waitFixedTime(1);
		bc.SendKeys(InterviewTimeTo, InterviewTimeTodata, ITTName);
		bc.waitFixedTime(1);
		if(Activeness.equals("Yes")){
			bc.click(Active, ActiveName);
		}
		bc.click(OKButton, OKButtonName);
		bc.AlertAcceptIfPresent();
	}
		
	public void Test_VerifyRecord(String QualificationCodedata){
		bc = new BaseClass(driver);
		WebElement RecentUpdatedRecord = driver.findElement(By.xpath(".//*[text()='"+QualificationCodedata+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(RecentUpdatedRecord), "Record not Found");
			bc.log("Campus master record added Successfully");
	}
	
	public void Test_Edit_Record(){
		bc = new BaseClass(driver);
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(FirstEditButton, PageHeader, InterviewBatch, IName, "test data", OKButton, "Record Updated Successfully");
		
	}

}
