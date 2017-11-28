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

public class Exit extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Human Resource") WebElement HRMS;
	@FindBy(linkText="Employee Life Cycle") WebElement ELC;
	@FindBy(linkText="Exit") WebElement Exit;  
	@FindBy(linkText="Resignation") WebElement Resignation;
	@FindBy(linkText="Resignation Approval") WebElement ResignationApproval;
	@FindBy(linkText="Exit Form") WebElement ExitForm;
	@FindBy(linkText="Exit Interview") WebElement ExitInterview;
	@FindBy(linkText="Exit Interview Endorsement") WebElement ExitInterviewEndorsement;
	@FindBy(linkText="LWD Change") WebElement LWDChange;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpNewSearchList']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtNewSearchText']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdNewSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdNewClearSearch']") WebElement Clearsearchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DrpStatus1']") WebElement DRPStatus;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_submit']") WebElement SubmitButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_BtnCancel']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpDivSearchList']") WebElement Resgnsearchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDivSearchText']") WebElement ResgnSearchbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdDivSearch']") WebElement ResgnSearchButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdDivClearSearch']") WebElement ResgnClearSearchButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_hmadd']") WebElement HomeAddress;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DropDownList1']") WebElement NoticeGiven; 
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpResigType']") WebElement ResignationReason;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtResLeaving']") WebElement LeavingReason;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtRjctnRmrk']") WebElement Remark;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpEliToRehire']") WebElement Eligibility;

	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImgNot']") WebElement rdCalenderButton;
	@FindBy(id="ContentPlaceHolder_CalendarExtender1_nextArrow") WebElement rdNextCalender;
	@FindBy(id="ContentPlaceHolder_CalendarExtender1_title") WebElement rdMiddleCalender;
	@FindBy(id="ContentPlaceHolder_CalendarExtender1_prevArrow") WebElement rdPrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender1_monthsBody']//descendant::tr/td/div") List<WebElement> rdAllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender1_daysBody']/tr/td[not(contains(@class,'ajax__calendar_other'))]") List<WebElement> rdAllDates;
		
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImgLED']") WebElement redCalenderButton;
	@FindBy(id="ContentPlaceHolder_CalendarExtender3_nextArrow") WebElement redNextCalender;
	@FindBy(id="ContentPlaceHolder_CalendarExtender3_title") WebElement redMiddleCalender;
	@FindBy(id="ContentPlaceHolder_CalendarExtender3_prevArrow") WebElement redPrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender3_monthsBody']//descendant::tr/td/div") List<WebElement> redAllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender3_daysBody']/tr/td[not(contains(@class,'ajax__calendar_other'))]") List<WebElement> redAllDates;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DrpStatus1']") WebElement Status;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_FUp1']") WebElement BrowseButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnUpload']") WebElement UploadButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_submit']") WebElement RegSubmitButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_BtnCancel']") WebElement RegCancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtRjctnRmrk']") WebElement RemarkApproval;
	
	////////////////////////////////--Exit--//////////////////////////////////////////////////
	@FindBy(xpath=".//*[text()='Your Views on Your Job and the Hotel']") WebElement SecondTab;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtQS0024642']") WebElement AnySuggestion;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtQS0024653']") WebElement WorkEnvironment;
	
	@FindBy(xpath=".//*[text()='Your Major Reason(s) for Leaving the Company (you may tick more than one box)']") WebElement ThirdTab;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtQS0024701']") WebElement Others;
	
	@FindBy(xpath=".//*[text()='The Company That You Will Be Joining']") WebElement Fourthtab;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtQS0024720']") WebElement DisLike;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtQS0024731']") WebElement LoveWorking;
	
	@FindBy(xpath=".//*[text()='Employee’s Comments']") WebElement fifthTab;
	
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_BtnSubmit']") WebElement ExitFormSubmitButton;
	
	////////////////////////////-------- exit interview ---------//////////////////////////
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DdlSearchList']") WebElement EIDropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement EISearchBox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement EISearchButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdClearSearch']") WebElement EIClearSearchButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ddResgType']") WebElement EIResignationType;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtresReason']") WebElement EILeavingReason;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txthighlight']") WebElement EIMajorHighLights;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtdisappoint']") WebElement EIDisappointment;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtviwercomment']") WebElement EIComment;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_rbComYes']") WebElement EIYes;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_rbComNo']") WebElement EINo;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_BtnSubmit']") WebElement EISubmitButton;
	
	
////////////////////////////-------- exit interview Endorsement---------//////////////////////////
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DrpStatusEn']") WebElement EIEStatusDropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_BtnSubmitExitForm']") WebElement EIESubmitButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DdlSearchList']") WebElement EIEDropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement EIESearchBox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement EIESearchButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdClearSearch']") WebElement EIEClearSearchButton;
	
////////////////////////////-------- LWD Change ---------//////////////////////////
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpsearch']") WebElement LWDDropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearch']") WebElement LWDSearchBox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnSearch']") WebElement LWDSearchButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnClear']") WebElement LWDClearSearchButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DtgSlfRegn']/tbody/tr[2]/td[1]/a") WebElement LWDFirstRecord;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImgLWDdt']") WebElement LWDCalenderButton;
	@FindBy(id="ContentPlaceHolder_CalendarExtender2_nextArrow") WebElement LWDNextCalender;
	@FindBy(id="ContentPlaceHolder_CalendarExtender2_title") WebElement LWDMiddleCalender;
	@FindBy(id="ContentPlaceHolder_CalendarExtender2_prevArrow") WebElement LWDPrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender2_monthsBody']//descendant::tr/td/div") List<WebElement> LWDAllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender2_daysBody']/tr/td[not(contains(@class,'ajax__calendar_other'))]") List<WebElement> LWDAllDates;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_submit']") WebElement LWDSubmitButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_BtnCancel']") WebElement LWDCancelButton;
	
	
	String ExitFormName = "Exit Form";
	String module = "Human Resource";
	String submoduleName = "ELC";
	String subsectionName = "Exit";
	String rapageName = "Resignation Approval";
	String raPageTitle = "Resignation Approval";
	String rpageName = "Resignation";
	String rPageTitle = "Resignation";
	String DrpStatusName = "Dropdown Status";
	String SearchDrpButtonName = "Search Dropdown Button";
	String SearchBoxButtonName = "Search Text box Button";
	String SearchButtonName = "Search Button";
	String ClearSearchlButtonName = "Clear Search Button";
	String CancelButtonName = "Cancel Button";
	String HomeAddressName = "Home Address";
	String UploadButtonName = "Upload Button";
	String LWDChangeName="LWD Change";
	
	String NGName = "Notice Given";
	String RRName = "Resignation Reason";
	String LRName = "Leaving Reason";
	String RName = "Remark";
	String EName = "Eligibility";
	String ExitInterviewName = "Exit Interview";
	String ExitInterviewEndorsementName = "Exit Interview Endorsement";
		
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DIV1']") WebElement ExitPageTable;
		
	public Exit(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_LoginSubmitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Exit");
		lp = new LoginPage(driver);
		lp.Login(map.get(1).get("CompanyCode"),map.get(1).get("UserName"), map.get(1).get("Password"));
	}
	
	///-----------------------> login  approvar ------------------------------>
	public void Test_LoginApprovarRP() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Exit");
		lp = new LoginPage(driver);
		lp.Login(map.get(2).get("CompanyCode"),map.get(2).get("UserName"), map.get(2).get("Password"));
	}
	
	///-----------------------> login  approvar hr------------------------------>
	public void Test_LoginApprovarHR() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Exit");
		lp = new LoginPage(driver);
		lp.Login(map.get(3).get("CompanyCode"),map.get(3).get("UserName"), map.get(3).get("Password"));
	}
	
	///-----------------------> login  Exit Form------------------------------>
		public void Test_LoginExitEmployee() throws IOException{
			List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Exit");
			lp = new LoginPage(driver);
			lp.Login(map.get(4).get("CompanyCode"),map.get(4).get("UserName"), map.get(4).get("Password"));
		}
		
	
	public void Test_Open_Target_Page_ResignationApproval() throws Exception{
		cm = new CommonMethods(driver);
			
		cm.TargetPageClick(HRMS, module, ELC, submoduleName, Exit, subsectionName, ResignationApproval, rapageName, PageHeader);
	}
	
	public void Test_Open_Target_Page_ResignationSubmission() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, ELC, submoduleName, Exit, subsectionName, Resignation, rpageName, PageHeader);	
	}
	
	public void Test_Open_Target_Page_ExitForm() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, ELC, submoduleName, Exit, subsectionName, ExitForm, ExitFormName, ExitPageTable);	
	}
	
	public void Test_Open_Target_Page_ExitInterview() throws Exception{
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		bc.waitFixedTime(1);
		cm.TargetPageClick(HRMS, module, ELC, submoduleName, Exit, subsectionName, ExitInterview, ExitInterviewName, PageHeader);	
	}
	
	public void Test_Open_Target_Page_ExitInterview_Endorsement() throws Exception{
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		bc.waitFixedTime(1);
		cm.TargetPageClick(HRMS, module, ELC, submoduleName, Exit, subsectionName, ExitInterviewEndorsement, ExitInterviewEndorsementName, PageHeader);	
	}
	
	public void Test_Open_Target_Page_LWD_Change() throws Exception{
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		bc.waitFixedTime(1);
		cm.TargetPageClick(HRMS, module, ELC, submoduleName, Exit, subsectionName, LWDChange, LWDChangeName, PageHeader);	
	}
	
	
	public void Test_ResignationApprovalSearch_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, SearchDrpButtonName, searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_ResignationSearch_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(Resgnsearchdropdown, dropdown_Value, SearchDrpButtonName, ResgnSearchbox, SearchData, ResgnSearchButton);
	}
	
	public void Test_ResgnApprovalEditEmployee(String dropdown_Value, String SearchData, String Status) throws Exception{
		bc = new BaseClass(driver);
		this.Test_ResignationApprovalSearch_Record(dropdown_Value, SearchData);
		WebElement EditEmp = driver.findElement(By.xpath(".//*[text()='"+SearchData+"']"));
		bc.click(EditEmp, "Employee Name for Edit");
		bc.waitFixedTime(1);
		if(Status.contentEquals("Approve By RP")){
			bc.SendKeys(RemarkApproval, "Testing Data", "Remark Approval");
		}else if(Status.contentEquals("Approve By HR")){
			bc.SendKeys(RemarkApproval, "Testing Data", "Remark Approval");
		}
		bc.ScrollDown();
		bc.selectByVisibleText(DRPStatus, Status, DrpStatusName);
		bc.click(RegSubmitButton, "Resignation approval Submit Button");
		
		bc.AlertAcceptIfPresent();
		try{
			Assert.assertTrue(bc.isElementPresentSingleLocator(CancelButton));
			bc.click(CancelButton, CancelButtonName);
		}catch(AssertionError e){
					}
	}
	
	public void Test_ResignationFinalApprovalVerifyStatus(String EmpName, String Status){
		bc = new BaseClass(driver);
		bc.waitFixedTime(1);
		WebElement EditEmp = driver.findElement(By.xpath(".//*[text()='"+EmpName+"']/parent::tr/td[text()='"+Status+"']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(EditEmp));
	}
	
	public void Test_ResignationSubmition(String dropdown_Value, String SearchData, String HomeAddressdata, String NoticeGivendATA, String ResignationReasonData, String LeavingReasondata, String Remarkdata, String EligibilityData, String Resignationdate, String LastEmployeementdate, String StatusData) throws Exception{
		bc = new BaseClass(driver);
		this.Test_ResignationSearch_Record(dropdown_Value, SearchData);
		bc.waitFixedTime(1);
		WebElement edit = driver.findElement(By.xpath(".//*[text()='"+SearchData+"']/parent::tr/td/input[@accesskey='E']"));
		bc.click(edit, "Edit Button");
		bc.waitFixedTime(1);
		bc.waitForElement(NoticeGiven);
	//	bc.SendKeys(HomeAddress, HomeAddressdata, HomeAddressName);
		bc.selectByVisibleText(NoticeGiven, NoticeGivendATA, NGName);
		bc.selectByVisibleText(ResignationReason, ResignationReasonData, RRName);
		bc.SendKeys(LeavingReason, LeavingReasondata, LRName);
		bc.SendKeys(Remark, Remarkdata, RName);
		bc.selectByVisibleText(Eligibility, EligibilityData, EName);
		bc.ScrollDown();
		bc.click(BrowseButton, "Browse Button");
		//////////////////////////////////////////
		Runtime.getRuntime().exec("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\UploadMasterEXEFiles\\resignation.exe");
		//////////////////////////////////////////
		bc.waitFixedTime(1);
		bc.click(UploadButton, UploadButtonName);
		bc.waitFixedTime(1);
		bc.ScrollDown();
		bc.DateSelection(Resignationdate, rdCalenderButton, rdNextCalender, rdMiddleCalender, rdPrevCalender, rdAllMonth, rdAllDates);
		bc.DateSelection(LastEmployeementdate, redCalenderButton, redNextCalender, redMiddleCalender, redPrevCalender, redAllMonth, redAllDates);
		bc.ScrollDown();
		bc.selectByVisibleText(Status, StatusData, "Status");
		bc.click(RegSubmitButton, "Submit button");
	
		bc.AlertAcceptIfPresent();
		try{
			Assert.assertTrue(bc.isElementPresentSingleLocator(RegCancelButton));
			bc.click(RegCancelButton, "Cancel Button");
		}catch(AssertionError e){
			bc.log("record submited successfully");
		}
		
	}
	
	public void Test_ResignationSubmitionVerification(String EmpName){
		bc = new BaseClass(driver);
		bc.waitFixedTime(1);
		WebElement EditEmp = driver.findElement(By.xpath(".//*[text()='"+EmpName+"']/parent::tr/td[text()='Submit']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(EditEmp));
	}
	
	public void Test_ExitForm(String answer1, String answer2, String AnySuggestiondata, String WorkEnvironmentdata, String answer3, String Othersdata, String DisLikedata, String LoveWorkingdata, String answer4){
		bc = new BaseClass(driver);
		bc.click(SecondTab, "2nd Tab");
		bc.waitFixedTime(1);
		WebElement FirstQ = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_td0']/span/label[text()='"+answer1+"']"));
		bc.click(FirstQ, "First Question");
		WebElement SecondQ = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_lblQuestionObj1']//parent::td/span/label[text()='"+answer2+"']"));
		bc.click(SecondQ, "Second Question");
		bc.SendKeys(AnySuggestion, AnySuggestiondata, "Any Suggestion");
		bc.SendKeys(WorkEnvironment, WorkEnvironmentdata, "Work Environment");
		
		bc.click(ThirdTab, "Third Tab");
		bc.waitFixedTime(1);
		WebElement ThirdQ = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_lblMarksR0']//parent::td/span/label[text()='"+answer3+"']"));
		bc.click(ThirdQ, "Third Question");
		bc.SendKeys(Others, Othersdata, "Others");
		
		bc.click(Fourthtab, "Fourth tab");
		bc.waitFixedTime(1);
		bc.SendKeys(DisLike, DisLikedata, "DisLike Question");
		bc.SendKeys(LoveWorking, LoveWorkingdata, "LoveWorking Question");
		
		bc.click(fifthTab, "fifth Tab");
		WebElement ForthQ = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_Panel4']/table/tbody/tr/td/label[text()='"+answer4+"']"));
		bc.click(ForthQ, "Forth Q");
		
		bc.click(ExitFormSubmitButton, "Exit Form Submit Button");
		bc.AlertAcceptIfPresent();
	}	
	
	/////////////////////// exit interview //////////////////////////////////////
	
	public void TEST_SearchRecord_ExitInterview(String dropdown_Value, String SearchData) throws Exception{
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(EIDropdown, dropdown_Value, SearchDrpButtonName, EISearchBox, SearchData, EISearchButton);
	}
		
	public void  Test_Exit_InterviewForm(String dropdown_Value, String SearchData, String EIResignationTypeData, String EILeavingReasondata, String EIMajorHighLightsdata, String EIDisappointmentdata, String EICommentdata, String YesNo) throws Exception{
		bc = new BaseClass(driver);
		this.TEST_SearchRecord_ExitInterview(dropdown_Value, SearchData);
		WebElement EditEmployee = driver.findElement(By.xpath(".//*[text()='"+SearchData+"']//parent::tr/td/a"));
		bc.click(EditEmployee, "Employee code - "+SearchData);
		
		bc.AlertAcceptIfPresent();
		bc.waitFixedTime(2);
		bc.selectByVisibleText(EIResignationType, EIResignationTypeData, "Resignation Type");
		bc.SendKeys(EILeavingReason, EILeavingReasondata, "Leaving Reason");
		bc.SendKeys(EIMajorHighLights, EIMajorHighLightsdata, "Major HighLights");
		bc.SendKeys(EIDisappointment, EIDisappointmentdata, "Disappointment");
		bc.SendKeys(EIComment, EICommentdata, "Comment");
		if(YesNo.equals("Yes")){
			bc.click(EIYes, "Yes");
		}else if(YesNo.equals("No")){
			bc.click(EINo, "Yes");
		}
		bc.click(EISubmitButton, "Submit Button");
		bc.AlertAcceptIfPresent();
	}
	
	public void Test_VerifyExitInterviewForm(){
		bc = new BaseClass(driver);
		Assert.assertTrue(bc.isElementPresentSingleLocator(PageHeader));
		bc.log("Exit Interview Submitted Successfully");
		
	}
	
	///////////////////////--------> Exit Interview Endorsement -----> ////////////////////////////////
	

	public void TEST_SearchRecord_ExitInterview_Endorsement(String dropdown_Value, String SearchData) throws Exception{
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(EIEDropdown, dropdown_Value, SearchDrpButtonName, EIESearchBox, SearchData, EIESearchButton);
	}
	
	
	public void Test_Exit_Interview_Endorsement(String dropdown_Value, String SearchData) throws Exception{
		bc = new BaseClass(driver);
		this.TEST_SearchRecord_ExitInterview_Endorsement(dropdown_Value, SearchData);
		bc.waitFixedTime(1);
		WebElement EditEmployee = driver.findElement(By.xpath(".//*[text()='"+SearchData+"']/parent::tr/td/a"));
		bc.click(EditEmployee, "Edit Employee");
		bc.waitFixedTime(1);
		bc.ScrollDown();
		bc.click(EIESubmitButton, "Submit Button");
		bc.AlertAcceptIfPresent();
		
	}
	
///////////////////////--------> LWD Change -----> ////////////////////////////////
	
	public void TEST_SearchRecord_LWDChange(String dropdown_Value, String SearchData) throws Exception{
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(LWDDropdown, dropdown_Value, SearchDrpButtonName, LWDSearchBox, SearchData, LWDSearchButton);
	}
	
	public void Test_LWDChange(String dropdown_Value, String SearchData, String Exceldate) throws Exception{
		bc = new BaseClass(driver);
		this.TEST_SearchRecord_LWDChange(dropdown_Value, SearchData);
		bc.click(LWDFirstRecord, "First Record");
		bc.waitForElement(LWDCalenderButton);
		bc.DateSelection(Exceldate, LWDCalenderButton, LWDNextCalender, LWDMiddleCalender, LWDPrevCalender, LWDAllMonth, LWDAllDates);
		bc.click(LWDSubmitButton, "Submit Button");
		bc.AlertAcceptIfPresent();
	}
	
	public void Test_Verify_LWD_Change(String SearchData, String VerificationDate){
		bc = new BaseClass(driver);
		WebElement VerifyDate = driver.findElement(By.xpath(".//*[text()='"+SearchData+"']//parent::td//parent::tr/td[text()='"+VerificationDate+"']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyDate));
		bc.log("LWD Changed Successfully");
		
	}
	
}
