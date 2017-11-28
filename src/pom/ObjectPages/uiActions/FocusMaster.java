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

public class FocusMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(xpath=".//*[text()='Human Resource']") WebElement HRMS;
	@FindBy(linkText="HR Setup") WebElement HRSetup;
	@FindBy(xpath=".//*[text()='HR Setup']//parent::a//parent::li/ul/li/a[@title='PMS & Training']") WebElement PMSandTraining;  
	@FindBy(linkText="Focus Master") WebElement FocusMaster;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpSearchList']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdAddNew']") WebElement AddNewButton;
		
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtFocusCode']") WebElement FocusCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtFocus']") WebElement Focus;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtRemrks']") WebElement Remark;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkAutoApprove']") WebElement Active;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_OkButton']") WebElement OKButton; 	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgFocus_imgEditButton_0']") WebElement FirstEditButton;
	
	
	String module = "Human Resource";
	String AddNewButtonName = "AddNew Button";
	String submoduleName = "HR Setup";
	String subsectionName = "PMS and Training";
	String pageName = "Focus Master";
	String PageTitle = "Focus Master";
	String FCName = "Focus Code";
	String FName = "Focus";
	String RName = "Remark";
	String ActiveName = "Active";
		
	String CancelButtonName= "Cancel Button";
	String sdName= "Search Dropdown";
	String SearchBoxButtonName= "SearchBox Button";
	String SearchButtonName= "Search Button";
	String OKButtonName= "OK Button";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
		
	public FocusMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, HRSetup, submoduleName, PMSandTraining, subsectionName, FocusMaster, pageName,PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, sdName, searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_Add_New_Button(){
		bc = new BaseClass(driver);
		bc.click(AddNewButton, AddNewButtonName);
		bc.waitFixedTime(1);
		Assert.assertTrue(bc.isElementPresentSingleLocator(FocusCode));
		bc.click(CancelButton, CancelButtonName);
	}
	
	public void Test_AddNew_Data(String FocusCodedata, String Focusdata, String Activeness, String Remarkdata) throws InterruptedException{
		bc = new BaseClass(driver);
		
		bc.click(AddNewButton, AddNewButtonName);
		bc.waitFixedTime(1);
		bc.waitForElement(FocusCode);
		bc.SendKeys(FocusCode, FocusCodedata, FCName);
		bc.waitFixedTime(1);
		bc.SendKeys(Focus, Focusdata, FName);
		bc.waitFixedTime(1);
	/*	if(Activeness.equals("Yes")){
			bc.click(Active, ActiveName);
		}*/

		bc.SendKeys(Remark, Remarkdata, RName);
		bc.click(OKButton, OKButtonName);
		bc.AlertAcceptIfPresent();
	}
		
	public void Test_VerifyRecord(String FocusCodedata){
		bc = new BaseClass(driver);
		WebElement RecentAddedRecord = driver.findElement(By.xpath(".//*[text()='"+FocusCodedata+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(RecentAddedRecord), "Record not Found");
			bc.log("Focus master record added Successfully");
	}
	
	public void Test_Edit_Record(){
		bc = new BaseClass(driver);
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(FirstEditButton, PageHeader, Remark, RName, "test data", OKButton, "Record Updated Successfully.");
		
	}

}
