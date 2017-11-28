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

public class AllowanceDeductionMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(xpath=".//*[text()='Human Resource']") WebElement HRMS;
	@FindBy(linkText="HR Setup") WebElement HRSetup;
	@FindBy(xpath=".//*[text()='HR Setup']//parent::a//parent::li/ul/li/a[@title='Recruitment']") WebElement Recruitment;  
	@FindBy(linkText="Allowance Deduction Master") WebElement AllowanceDeductionMaster;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpSearchList']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnExcel']") WebElement ExcelDownload;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdAddNew']") WebElement AddNewButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtadcode']") WebElement AllowanceCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ddlcategory']") WebElement Category;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtaddesc']") WebElement AllowanceDescription;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtsort']") WebElement sortOrder;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ddlalldedtype']") WebElement AllowanceType;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtregdisname']") WebElement RegisterDisplayName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtglno']") WebElement GeneralLedgerAccountNumber;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtpaydisname']") WebElement PayslipDisplayName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ddlsection']") WebElement Section;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ddlrefad']") WebElement RefferenceDeduction;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtround']") WebElement RoundValue;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_chkprojection']") WebElement Projection;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgallded_imgEditButton_0']") WebElement FirstEditButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lblHeader']") WebElement EditButtonPageHeader;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_chkprojection']") WebElement IcludeForProjection;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_OkButton']") WebElement OKButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	String module = "Human Resource";
	String AddNewButtonName = "AddNew Button";
	String submoduleName = "HR Setup";
	String subsectionName = "Recruitment";
	String pageName = "Allowance Deduction Master";
	String PageTitle = "Allowance Deduction Master";
	String ExcelDownloadName = "Excel Download";
	String AddNewName = "Add New Button";
	String AllowanceCodeName = "Allowance Code";
	String CategoryName = "Category";
	String AllowanceDescriptionName = "Allowance Description";
	String sortOrderName = "sort Order";
	String AllowanceTypeName = "Allowance Type";
	String RegisterDisplayNameName = "Register Display Name";
	String GeneralLedgerAccountNumberName = "General Ledger Account Number";
	String PayslipDisplayNameName= "Payslip Display Name";
	String SectionName= "Section";
	String RefferenceDeductionName= "Refference Deduction";
	String RoundValueName= "Round Value";
	String ProjectionName= "Projection";
	String CancelButtonName= "Cancel Button";
	String sdName= "Search Dropdown";
	String SearchBoxButtonName= "SearchBox Button";
	String SearchButtonName= "Search Button";
	String OKButtonName= "OK Button Name";
	
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='Allowance Deduction Master']") WebElement PageHeader;
		
	public AllowanceDeductionMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, HRSetup, submoduleName, Recruitment, subsectionName, AllowanceDeductionMaster, pageName,PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, sdName, searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_AddNew(String AllowanceCodedata, String CategoryData, String AllowanceDescriptiondata, String sortOrderdata, String AllowanceTypeData, String RegisterDisplayNamedata, String GeneralLedgerAccountNumberdata, String PayslipDisplayNamedata, String SectionData, String RefferenceDeductionData, String RoundValuedata){
		bc = new BaseClass(driver);
		
		bc.click(AddNewButton, AddNewButtonName);
		bc.waitFixedTime(1);
		bc.waitForElement(AllowanceCode);
		bc.SendKeys(AllowanceCode, AllowanceCodedata, AllowanceCodeName);
		bc.selectByVisibleText(Category, CategoryData, CategoryName);
		bc.waitFixedTime(2);
		bc.SendKeys(AllowanceDescription, AllowanceDescriptiondata, AllowanceDescriptionName);
		bc.waitFixedTime(2);
		bc.SendKeys(sortOrder, sortOrderdata, sortOrderName);
		bc.selectByVisibleText(AllowanceType, AllowanceTypeData, AllowanceTypeName);
		bc.waitFixedTime(1);
		bc.SendKeys(RegisterDisplayName, RegisterDisplayNamedata, RegisterDisplayNameName);
		bc.waitFixedTime(1);
		bc.SendKeys(GeneralLedgerAccountNumber, GeneralLedgerAccountNumberdata, GeneralLedgerAccountNumberName);
		bc.waitFixedTime(1);
		bc.SendKeys(PayslipDisplayName, PayslipDisplayNamedata, PayslipDisplayNameName);
		bc.selectByVisibleText(Section, SectionData, SectionName);
		bc.waitFixedTime(1);
		bc.selectByVisibleText(RefferenceDeduction, RefferenceDeductionData, RefferenceDeductionName);
		bc.waitFixedTime(1);
		bc.SendKeys(RoundValue, RoundValuedata, RoundValueName);
		bc.click(IcludeForProjection, "Iclude For Projection Name");
		bc.click(OKButton, OKButtonName);
		bc.AlertAcceptIfPresent();
	
	}
	
	
	public void Test_VerifyRecord(String AllowanceCode){
		bc = new BaseClass(driver);
		WebElement RecentUpdatedRecord = driver.findElement(By.xpath(".//*[text()='"+AllowanceCode+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(RecentUpdatedRecord), "Record not Updated Successfully");
			bc.log("Allowance Deduction master record updated Successfully");
	}
	
	public void Test_Edit_Record(){
		bc = new BaseClass(driver);
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(FirstEditButton, EditButtonPageHeader, RoundValue, RoundValueName, "3", OKButton, "Record Updated Successfully.");
		
	}

}
