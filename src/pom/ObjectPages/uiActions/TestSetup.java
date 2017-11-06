package pom.ObjectPages.uiActions;

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

public class TestSetup extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(xpath=".//*[text()='Human Resource']") WebElement HRMS;
	@FindBy(linkText="HR Setup") WebElement HRSetup;
	@FindBy(linkText="General") WebElement General;  
	@FindBy(linkText="Test Setup") WebElement TestSetup;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_drpsearch']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtSearch']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnClear']") WebElement ClearSearchbutton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_btnAddNew']") WebElement AddNewButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtTestCode']") WebElement TestCode;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_txtTestname']") WebElement TestName;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_ddsubject']") WebElement SelectSubject;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_ddquesType']") WebElement QuestionType;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_TXT_PASS_PER']") WebElement PassingPercentage;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lnkSave']") WebElement SaveButton;
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lnkback']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lnkSelect']") WebElement SelectAll;
	
	@FindBy(xpath=".//*[@id='ctl00_ContentPlaceHolder_lnkDeselect']") WebElement DeSelectAll;
			
	String module = "Human Resource";
	String submoduleName = "HR Setup";
	String subsectionName = "General";
	String pageName = "Test Setup";
	String PageTitle = "Test Setup";
	String AddNewName = "Add New Button";
	String tcName = "Test Code";
	String tName = "Test Name";
	String QName = "Question";
	String SelectSubjectName = "Select Subject";
	String qtName = "Question Type";
	String ppName = "Passing Percentage";
	String SaveName = "Save Button";
	String canName = "Cancel Button";
	String SelectAllName = "Select All";
	String DeSelectAllName = "DeSelect All";
	String sdName="Search Dropdown";
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='Test Setup']") WebElement PageHeader;
		
	public TestSetup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, HRSetup, submoduleName, General, subsectionName, TestSetup, pageName,PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, sdName, searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_AddNewRecord(String TestCodedata, String TestNamedata, String SelectSubjectData, String QuestionTypedata, String ppDATA, String QuestionCode){
		bc = new BaseClass(driver);
		bc.waitForElement(AddNewButton);
		bc.click(AddNewButton, AddNewName);
		bc.waitForElement(TestCode);
		bc.SendKeys(TestCode, TestCodedata, tcName);
		bc.SendKeys(TestName, TestNamedata, tName);
		bc.selectByVisibleText(SelectSubject, SelectSubjectData, SelectSubjectName);
		bc.waitFixedTime(2);
		bc.selectByVisibleText(QuestionType, QuestionTypedata, qtName);
		bc.waitFixedTime(2);
		bc.SendKeys(PassingPercentage, ppDATA, ppName);
		bc.waitFixedTime(1);
		if(QuestionCode.equals("SELECT ALL")){
			bc.click(SelectAll, SelectAllName);
			}				
		else{
			WebElement questionCheckbox = driver.findElement(By.xpath(".//*[text()='"+QuestionCode+"']/parent::tr/td/input[@type='checkbox']"));
			bc.click(questionCheckbox, "Question Code Checkbox");
		}
		bc.click(SaveButton, SaveName);
		bc.waitForAlertBox(5);
		bc.AlertAcceptIfPresent();
		try{
			Assert.assertTrue(bc.isElementPresentSingleLocator(AddNewButton));
		}catch(AssertionError e){
			
			try{
				Assert.assertTrue(bc.isElementPresentSingleLocator(CancelButton));
				bc.click(CancelButton, canName);
				bc.waitForElement(AddNewButton);
				
			}catch(AssertionError t){
				Assert.fail("Expected : [Add New Button] But Found : ["+driver.getTitle()+"]");
			}
			
		}
	}
	
	
	public void Test_VerifyRecord(String TestCodedata){
		bc = new BaseClass(driver);
			Assert.assertTrue(bc.isElementPresentXpathLocator(TestCodedata), "Record not Added Successfully");
			bc.log("Test added Successfully");
	}
	
}
