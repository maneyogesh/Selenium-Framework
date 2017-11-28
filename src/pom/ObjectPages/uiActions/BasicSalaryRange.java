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

public class BasicSalaryRange extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(xpath=".//*[text()='Human Resource']") WebElement HRMS;
	@FindBy(linkText="HR Setup") WebElement HRSetup;
	@FindBy(linkText="General") WebElement General;  
	@FindBy(linkText="Basic Salary Range") WebElement BasicSalaryRange;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpSearchList']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchbutton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdAddNew']") WebElement AddNewButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpdept']") WebElement Department;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpgrade']") WebElement Grade;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtMinRange']") WebElement Minrange;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtMedRange']") WebElement medrange;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtMaxRange']") WebElement maxrange;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_OkButton']") WebElement SaveButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	String module = "Human Resource";
	String submoduleName = "HR Setup";
	String subsectionName = "General";
	String pageName = "Basic Salary Range";
	String PageTitle = "Basic Salary Range";
	String AddNewName = "Add New Button";
	String DName = "Department";
	String gName = "Grade";
	String mName = "Minimum";
	String medName = "Medium";
	String maxName = "Maximum";
	String SaveName = "OK Button";
	String canName = "Cancel Button";
	String sdName= "Search Dropdown";
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='Basic Salary Range']") WebElement PageHeader;
		
	public BasicSalaryRange(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, HRSetup, submoduleName, General, subsectionName, BasicSalaryRange, pageName,PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, sdName, searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButton, AddNewName, PageHeader, CancelButton);
	}
	
	public void Test_AddNewRecord(String Ddata, String gdata, String mdata, String meddata, String maxdata){
		bc = new BaseClass(driver);
		bc.waitForElement(AddNewButton);
		bc.click(AddNewButton, AddNewName);
		bc.waitForElement(Department);
		bc.selectByVisibleText(Department, Ddata, DName);
		bc.selectByVisibleText(Grade, gdata, gName);
		bc.SendKeys(Minrange, mdata, mName);
		bc.SendKeys(medrange, meddata, medName);
		bc.SendKeys(maxrange, maxdata, maxName);
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
	
	
	public void Test_VerifyRecord(String gdata, String dData, String mdata, String meddata){
		bc = new BaseClass(driver);
		WebElement RecentAddedRecord = driver.findElement(By.xpath(".//*[text()='"+gdata+"']/parent::tr/td[text()='"+dData+"']/parent::tr/td[text()='"+mdata+"']/parent::tr/td[text()='"+meddata+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(RecentAddedRecord), "Record not Added Successfully");
			bc.log("Test added Successfully");
	}
	
	public void Test_Edit_Record(String gdata, String dData, String mdata, String meddata){
		cm = new CommonMethods(driver);
		
		WebElement EditRecord = driver.findElement(By.xpath(".//*[text()='"+gdata+"']/parent::tr/td[text()='"+dData+"']/parent::tr/td[text()='"+mdata+"']/parent::tr/td[text()='"+meddata+"']/parent::tr/td/input[@accesskey='E']"));
		cm.Test_Edit_Record(EditRecord, PageHeader, Minrange, "Minimum", mdata, SaveButton, "Record Updated Successfully.");
	}
	
}
