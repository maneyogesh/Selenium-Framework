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

public class QualificationMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(xpath=".//*[text()='Human Resource']") WebElement HRMS;
	@FindBy(linkText="HR Setup") WebElement HRSetup;
	@FindBy(xpath=".//*[text()='HR Setup']//parent::a//parent::li/ul/li/a[@title='Recruitment']") WebElement Recruitment;  
	@FindBy(linkText="Qualification Master") WebElement QualificationMaster;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpSearchList']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdAddNew']") WebElement AddNewButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSkillId']") WebElement QualificationCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSkillName']") WebElement QualificationName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSkillDescription']") WebElement Description;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_OkButton']") WebElement OKButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgpp_imgEditButton_0']") WebElement FirstEditButton;
	
	
	String module = "Human Resource";
	String AddNewButtonName = "AddNew Button";
	String submoduleName = "HR Setup";
	String subsectionName = "Recruitment";
	String pageName = "Qualification Master";
	String PageTitle = "Qualification Master";
	String QualificationCodeName = "Qualification Code";
	String QualificationNameName = "Qualification Name";
	String DescriptionName = "Description";
	
	String CancelButtonName= "Cancel Button";
	String sdName= "Search Dropdown";
	String SearchBoxButtonName= "SearchBox Button";
	String SearchButtonName= "Search Button";
	String OKButtonName= "OK Button";
	
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='Qualification Master']") WebElement PageHeader;
		
	public QualificationMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, HRSetup, submoduleName, Recruitment, subsectionName, QualificationMaster, pageName,PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, sdName, searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_Add_New_Button(){
		bc = new BaseClass(driver);
		bc.click(AddNewButton, AddNewButtonName);
		bc.waitFixedTime(1);
		Assert.assertTrue(bc.isElementPresentSingleLocator(QualificationCode));
		bc.click(CancelButton, CancelButtonName);
	}
	
	public void Test_AddNew_Data(String QualificationCodedata, String QualificationNamedata, String Descriptiondata){
		bc = new BaseClass(driver);
		
		bc.click(AddNewButton, AddNewButtonName);
		bc.waitFixedTime(1);
		bc.waitForElement(QualificationCode);
		bc.SendKeys(QualificationCode, QualificationCodedata, QualificationCodeName);
		bc.waitFixedTime(1);
		bc.SendKeys(QualificationName, QualificationNamedata, QualificationNameName);
		bc.waitFixedTime(1);
		bc.SendKeys(Description, Descriptiondata, DescriptionName);
		bc.waitFixedTime(1);
		bc.click(OKButton, OKButtonName);
		bc.AlertAcceptIfPresent();
	
	}
		
	public void Test_VerifyRecord(String QualificationCodedata){
		bc = new BaseClass(driver);
		WebElement RecentUpdatedRecord = driver.findElement(By.xpath(".//*[text()='"+QualificationCodedata+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(RecentUpdatedRecord), "Record not Found");
			bc.log("Qualification master record added Successfully");
	}
	
	public void Test_Edit_Record(){
		bc = new BaseClass(driver);
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(FirstEditButton, PageHeader, Description, DescriptionName, "test data", OKButton, "Record Updated Successfully.");
		
	}

}
