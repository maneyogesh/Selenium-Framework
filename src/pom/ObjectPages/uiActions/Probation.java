package pom.ObjectPages.uiActions;

import java.io.IOException;
import java.time.LocalDate;
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

public class Probation extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	LocalDate localDate = LocalDate.now();
	
	@FindBy(linkText="Human Resource") WebElement HRMS;
	@FindBy(linkText="Employee Life Cycle") WebElement EmployeeLifeCycle;
	@FindBy(linkText="Probation") WebElement Probation;  
	@FindBy(linkText="Probation Request") WebElement ProbationRequest;
	@FindBy(linkText="Probation Review") WebElement ProbationReview;
	@FindBy(linkText="Probation Status") WebElement ProbationStatus;
	String module = "Human Resource";
	String submoduleName = "Employee Life Cycle";
	String subsectionName = "Probation";
	
	String prpageName = "Probation Request";
	String prPageTitle = "Probation Request";
	
	String prvPageName = "Probation Review";
	String prvPageTitle = "Probation Review";
	
	String psPageName = "Probation Status";
	String psPageTitle = "Probation Status";
	
	@FindBy(xpath=".//*[contains(text(),'Probation Request')]/parent::tr/td[@class='page-heading']") WebElement PRPageHeader;
	@FindBy(xpath=".//*[text()='Probation Review']/parent::tr/td[@class='page-heading']") WebElement PRVPageHeader;
	@FindBy(xpath=".//*[contains(text(),'Probation Status')]/parent::tr/td[@class='page-heading']") WebElement PSPageHeader;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpsearch']") WebElement SearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearch']") WebElement SearchTextBox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnSearch']") WebElement SearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnClear']") WebElement ClearSearchButton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpsearch']") WebElement ReviewSearchDropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearch']") WebElement reviewSearchTextBox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnSearch']") WebElement reviewSearchButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnClear']") WebElement reviewClearSearchButton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_UserAccordion']/div/span[text()='PERSONAL DETAILS']") WebElement PersonalDetails;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_UserAccordion']/div/span[text()='SUITABILITY']") WebElement SUITABILITY;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_UserAccordion']/div/span[text()='RATING']") WebElement RATING;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_UserAccordion']/div/span[text()='PROBATION RECOMMENDATION']") WebElement PROBATIONRECOMMENDATION;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtRemarks1']") WebElement Remark;
	
	@FindBy(xpath=".//*[@id='drpstatus']") WebElement Action;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_BtnSubmit']") WebElement SubmitButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_BtnCancel']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtRemarks1']") WebElement ReviewRemark;
	@FindBy(xpath=".//*[@id='drpstatuspar']") WebElement ReviewAction;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_DrpConfirmationType']") WebElement ProbationLetter;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnGenerate']") WebElement GenerateButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_CancelButton']") WebElement StatusCancelButton;
//	@FindBy(xpath=".//*[@id='drpstatuspar']") WebElement ReviewSubmit;

	String ClearSearchName = "Clear Search Button";
	String SDName = "Search Dropdown";
	String STName = "Search TextBox";
	String SbName = "Search Button";
	String PDName = "Personal Details";
	String Sname = "Suitability";
	String raName = "Rating";
	String prName = "PROBATION RECOMMENDATION";
	String rrmarkName = "Remark";
	String actionName = "Action";
	String SName = "Submit Button";
	String CancelName = "Cancel Button";
	String GenerateName = "Generate Button";
	
	public Probation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_PRequest() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Probation");
		lp = new LoginPage(driver);
		lp.Login(map.get(1).get("CompanyCode"),map.get(1).get("UserName"), map.get(1).get("Password"));
	}
	
	public void Test_Login_PReview1() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Probation");
		lp = new LoginPage(driver);
		lp.Login(map.get(2).get("CompanyCode"),map.get(2).get("UserName"), map.get(2).get("Password"));
	}
	
	public void Test_Login_PReview2() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Probation");
		lp = new LoginPage(driver);
		lp.Login(map.get(3).get("CompanyCode"),map.get(3).get("UserName"), map.get(3).get("Password"));
	}
	
	public void Test_Login_PStatus() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","Probation");
		lp = new LoginPage(driver);
		lp.Login(map.get(4).get("CompanyCode"),map.get(4).get("UserName"), map.get(4).get("Password"));
	}
	
	public void Test_Open_Target_Page_ProbationRequest() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, EmployeeLifeCycle, submoduleName, Probation, subsectionName, ProbationRequest, prpageName,PRPageHeader);
	
	}
	
	public void Test_Open_Target_Page_ProbationReview() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, EmployeeLifeCycle, submoduleName, Probation, subsectionName, ProbationReview, prvPageName,PRVPageHeader);
	
	}
	
	public void Test_Open_Target_Page_ProbationStatus() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, EmployeeLifeCycle, submoduleName, Probation, subsectionName, ProbationStatus, psPageName,PSPageHeader);
	
	}
	
	public void Test_Search_Record(String SearchDropdownData, String SearchValue) throws Exception {
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		cm.Test_Search_Button(SearchDropdown, SearchDropdownData, "Search Dropdown", SearchTextBox, SearchValue, SearchButton);
}
	
	/*	public void Test_Search_RecordMapping(String SearchDropdownData) throws Exception {
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		cm.Test_Search_Button(Mapping1stSearchDropdown, SearchDropdownData, "Search Dropdown", Mapping1stSearchBox, CurrentSystemDayOnly, Mapping1stSearchButton);
}*/
	
	public void Test_AddNew(String SearchDropdownData, String SearchValue,String QQ1, String QQ2, String QQ3, String QQ4, String QQ5, String QQ6, String QQ7, String QQ8, String QQ9, String QQ10, String QQ11, String QQ12, String QQ13, String QQ14, String QQ15, String QQ16, String QQ17, String QQ18, String QQ19, String QQ20, String QQ21, String QQ22, String QQ23, String QQ24, String QQ25, String RemarkData, String actiondata) throws Exception{
		bc = new BaseClass(driver);
	
		bc.waitFixedTime(1);
		this.Test_Search_Record(SearchDropdownData, SearchValue);
		bc.waitFor(1);
		
		WebElement EditEmployee = driver.findElement(By.xpath(".//*[text()='"+SearchValue+"']/parent::tr/td/input[@accesskey='E']"));
		bc.click(EditEmployee, "Employee Edit");
		bc.waitFixedTime(1);
		bc.click(SUITABILITY, Sname);
		bc.waitFixedTime(1);
		
		WebElement Q1 = driver.findElement(By.xpath(".//*[text()='Q.Quantity of Work']/parent::td/span/label[text()='"+QQ1+"']"));
		bc.click(Q1, "Q1");
		WebElement Q2 = driver.findElement(By.xpath(".//*[text()='Q.Job Knowledge']/parent::td/span/label[text()='"+QQ2+"']"));
		bc.click(Q2, "Q2");
		WebElement Q3 = driver.findElement(By.xpath(".//*[text()='Q.Quality of Work']/parent::td/span/label[text()='"+QQ3+"']"));
		bc.click(Q3, "Q3");
		WebElement Q4 = driver.findElement(By.xpath(".//*[text()='Q.Initiative']/parent::td/span/label[text()='"+QQ4+"']"));
		bc.click(Q4, "Q4");
		WebElement Q5 = driver.findElement(By.xpath(".//*[text()='Q.Adaptability to Changes']/parent::td/span/label[text()='"+QQ5+"']"));
		bc.click(Q5, "Q5");
		WebElement Q6 = driver.findElement(By.xpath(".//*[text()='Q.Co-operation with Supervisors']/parent::td/span/label[text()='"+QQ6+"']"));
		bc.click(Q6, "Q6");
		
		bc.click(RATING, raName);
		bc.waitFixedTime(1);
		WebElement Q7 = driver.findElement(By.xpath(".//*[text()='Q.Learning Ability']/parent::td/parent::tr/td/span/label[text()='"+QQ7+"']"));
		bc.click(Q7, "Q7");
		WebElement Q8 = driver.findElement(By.xpath(".//*[text()='Q.Courtesy to Guests']/parent::td/parent::tr/td/span/label[text()='"+QQ8+"']"));
		bc.click(Q8, "Q8");
		WebElement Q9 = driver.findElement(By.xpath(".//*[text()='Q.Courtesy to Colleagues']/parent::td/parent::tr/td/span/label[text()='"+QQ9+"']"));
		bc.click(Q9, "Q9");
		WebElement Q10 = driver.findElement(By.xpath(".//*[text()='Q.Reliability']/parent::td/parent::tr/td/span/label[text()='"+QQ10+"']"));
		bc.click(Q10, "Q10");
		WebElement Q11 = driver.findElement(By.xpath(".//*[text()='Q.Compliance with Environmental Protection Policy']/parent::td/parent::tr/td/span/label[text()='"+QQ11+"']"));
		bc.click(Q11, "Q11");
		WebElement Q12 = driver.findElement(By.xpath(".//*[text()='Q.With Colleagues']/parent::td/parent::tr/td/span/label[text()='"+QQ12+"']"));
		bc.click(Q12, "Q12");
		WebElement Q13 = driver.findElement(By.xpath(".//*[text()='Q.With Supervisors.']/parent::td/parent::tr/td/span/label[text()='"+QQ13+"']"));
		bc.click(Q13, "Q13");
		WebElement Q14 = driver.findElement(By.xpath(".//*[text()='Q.With Guests.']/parent::td/parent::tr/td/span/label[text()='"+QQ14+"']"));
		bc.click(Q14, "Q14");
		WebElement Q15 = driver.findElement(By.xpath(".//*[text()='Q.With Subordinates']/parent::td/parent::tr/td/span/label[text()='"+QQ15+"']"));
		bc.click(Q15, "Q15");
		WebElement Q16 = driver.findElement(By.xpath(".//*[text()='Q.Personal Grooming']/parent::td/parent::tr/td/span/label[text()='"+QQ16+"']"));
		bc.click(Q16, "Q16");
		WebElement Q17 = driver.findElement(By.xpath(".//*[text()='Q.Punctuality']/parent::td/parent::tr/td/span/label[text()='"+QQ17+"']"));
		bc.click(Q17, "Q17");
		WebElement Q18 = driver.findElement(By.xpath(".//*[text()='Q.Understanding of Shangri-La Culture']/parent::td/parent::tr/td/span/label[text()='"+QQ18+"']"));
		bc.click(Q18, "Q18");
		WebElement Q19 = driver.findElement(By.xpath(".//*[text()='Q.Leadership Ability']/parent::td/parent::tr/td/span/label[text()='"+QQ19+"']"));
		bc.click(Q19, "Q19");
		WebElement Q20 = driver.findElement(By.xpath(".//*[text()='Q.Organization Ability']/parent::td/parent::tr/td/span/label[text()='"+QQ20+"']"));
		bc.click(Q20, "Q15");
		WebElement Q21 = driver.findElement(By.xpath(".//*[text()='Q.Supervisory Ability']/parent::td/parent::tr/td/span/label[text()='"+QQ21+"']"));
		bc.click(Q21, "Q21");
		WebElement Q22 = driver.findElement(By.xpath(".//*[text()='Q.Training Ability']/parent::td/parent::tr/td/span/label[text()='"+QQ22+"']"));
		bc.click(Q22, "Q22");
		WebElement Q23 = driver.findElement(By.xpath(".//*[text()='Q.Please comment on the employee’s training needs.']/parent::td/textarea"));
		bc.SendKeys(Q23, QQ23, "Q23");
		WebElement Q24 = driver.findElement(By.xpath(".//*[text()='Q.In addition to the ratings you have made, please a']/parent::td/textarea"));
		bc.SendKeys(Q24, QQ24, "Q24");
		WebElement Q25 = driver.findElement(By.xpath(".//*[text()='Q.Please outline the main points arising from the in']/parent::td/textarea"));
		bc.SendKeys(Q25, QQ25, "Q25");
		
		bc.click(PROBATIONRECOMMENDATION, prName);
		bc.waitFixedTime(1);
		WebElement Q26 = driver.findElement(By.xpath(".//*[text()='Q.Sense of Responsibility']/parent::td/span/label[text()='Excellent']"));
		bc.click(Q26, "Q26");
		bc.SendKeys(Remark, RemarkData, rrmarkName);
		bc.selectByVisibleText(Action, actiondata, actionName);
		bc.click(SubmitButton, SName);
		bc.AlertAcceptIfPresent();
		try{
			Assert.assertTrue(bc.isElementPresentSingleLocator(SearchButton));
			bc.waitFixedTime(1);
			}catch(AssertionError e){
			bc.click(CancelButton, CancelName);
		}
}
	
	public void Test_VerifyProbationRequestEmployeeStatus(String EmployeeName, String Status){
		bc = new BaseClass(driver);
			WebElement EmployeeStatus = driver.findElement(By.xpath(".//*[text()='"+EmployeeName+"']/parent::tr/td[5][text()='"+Status+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(EmployeeStatus), "Employee Status is not showing properly");
			bc.log("Probation request employee Status showing properly");
	}
	
	public void Test_ProbationReview_Search_Functionality(String SearchDropdownData, String SearchValue) throws Exception{
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		cm.Test_Search_Button(ReviewSearchDropdown, SearchDropdownData, "Search Dropdown", reviewSearchTextBox, SearchValue, reviewSearchButton);
	}
	
	public void Test_ApproveReject(String SearchDropdownData, String SearchValue, String RemarkDATA, String actionData) throws Exception{
		bc = new BaseClass(driver);
		this.Test_ProbationReview_Search_Functionality(SearchDropdownData, SearchValue);
		bc.waitFixedTime(1);
		
		WebElement EditRevieweremployee = driver.findElement(By.xpath(".//*[text()='"+SearchValue+"']/parent::tr/td/input[@accesskey='E']"));
		bc.click(EditRevieweremployee, "Edit Button Employeer Reviewer");
		bc.waitFixedTime(1);
		bc.ScrollDown();
		bc.SendKeys(ReviewRemark, RemarkDATA, rrmarkName);
		
		try{
			bc.selectByVisibleText(ReviewAction, actionData, actionName);
			bc.log("select status");
		}catch(Exception e){
			e.getMessage();
		}
		bc.click(SubmitButton, SName);
		bc.AlertAcceptIfPresent();
		
	}
	
	public void Test_ProbationStatusGenerateReport(String SearchDropdownData, String SearchValue) throws Exception{
		bc = new BaseClass(driver);
		this.Test_ProbationReview_Search_Functionality(SearchDropdownData, SearchValue);
		
		WebElement EmployeeGenerateReport = driver.findElement(By.xpath(".//*[text()='"+SearchValue+"']/parent::tr/td/input[@type='submit']"));
		bc.click(EmployeeGenerateReport, "Generate report");
		bc.waitFixedTime(1);
		bc.selectByVisibleText(ProbationLetter, "Confirmation Letter", "Generate report dropdown");
		bc.click(GenerateButton, GenerateName);
		
	}
	
	
	
	
}

