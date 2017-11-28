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

public class ConsultantMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(xpath=".//*[text()='Human Resource']") WebElement HRMS;
	@FindBy(linkText="HR Setup") WebElement HRSetup;
	@FindBy(xpath=".//*[text()='HR Setup']//parent::a//parent::li/ul/li/a[@title='Recruitment']") WebElement Recruitment;  
	@FindBy(linkText="Consultant Master") WebElement ConsultantMaster;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpSearchList']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdAddNew']") WebElement AddNewButton;
		
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCompanyName']") WebElement CompanyName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtUsername']") WebElement UserName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtpassword']") WebElement Password;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtFirstName']") WebElement FirstName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtmiddleName']") WebElement MiddleName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtLastName']") WebElement LastName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtEmailId']") WebElement Email;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtMobileNo']") WebElement MobileNumber;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtTelephoneNO']") WebElement TelephoneNumber;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtAddress']") WebElement Address;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_chkActive']") WebElement Active;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_OkButton']") WebElement OKButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgConsultant_imgEditButton_0']") WebElement FirstEditButton;
	
	
	String module = "Human Resource";
	String AddNewButtonName = "AddNew Button";
	String submoduleName = "HR Setup";
	String subsectionName = "Recruitment";
	String pageName = "Consultant Master";
	String PageTitle = "Consultant Master";
	String CName = "Company Name";
	String UName = "User Name";
	String PName = "Password";
	String FName = "First Name";
	String MName = "Middle Name";
	String LName = "Last Name";
	String EIdName = "Email id";
	String MobileNoName = "Mobile Number";
	String TelephoneNOName = "Telephone Number";
	String AddressName = "Address";
	String ActivenessName = "Active";
	
	String CancelButtonName= "Cancel Button";
	String sdName= "Search Dropdown";
	String SearchBoxButtonName= "SearchBox Button";
	String SearchButtonName= "Search Button";
	String OKButtonName= "OK Button";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
		
	public ConsultantMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, HRSetup, submoduleName, Recruitment, subsectionName, ConsultantMaster, pageName,PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, sdName, searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_Add_New_Button(){
		bc = new BaseClass(driver);
		bc.click(AddNewButton, AddNewButtonName);
		bc.waitFixedTime(1);
		Assert.assertTrue(bc.isElementPresentSingleLocator(CompanyName));
		bc.click(CancelButton, CancelButtonName);
	}
	
	public void Test_AddNew_Data(String CompanyNamedata, String UserNamedata, String Passworddata, String FirstNamedata, String MiddleNameData, String LastNamedata, String Emaildata, String MobileNumberdata, String TelephoneNumberdata, String Addressdata, String Activeness) throws InterruptedException{
		bc = new BaseClass(driver);
		
		bc.click(AddNewButton, AddNewButtonName);
		bc.waitFixedTime(1);
		bc.waitForElement(CompanyName);
		bc.SendKeys(CompanyName, CompanyNamedata, CName);
		bc.waitFixedTime(1);
		bc.SendKeys(UserName, UserNamedata, UName);
		bc.waitFixedTime(1);
		bc.SendKeys(Password, Passworddata, PName);
		bc.waitFixedTime(1);
		bc.SendKeys(FirstName, FirstNamedata, FName);
		bc.waitFixedTime(1);
		bc.SendKeys(MiddleName, MiddleNameData, MName);
		bc.waitFixedTime(1);
		bc.SendKeys(LastName, LastNamedata, LName);
		bc.waitFixedTime(1);
		bc.SendKeys(Email, Emaildata, EIdName);
		bc.waitFixedTime(1);
		bc.SendKeys(MobileNumber, MobileNumberdata, MobileNoName);
		bc.waitFixedTime(1);
		bc.SendKeys(TelephoneNumber, TelephoneNumberdata, TelephoneNOName);
		bc.waitFixedTime(1);
		bc.SendKeys(Address, Addressdata, AddressName);
		if(Activeness.equals("Yes")){
			bc.click(Active, ActivenessName);
		}
		bc.click(OKButton, OKButtonName);
		bc.AlertAcceptIfPresent();
	}
		
	public void Test_VerifyRecord(String CompanyNamedata){
		bc = new BaseClass(driver);
		WebElement RecentAddedRecord = driver.findElement(By.xpath(".//*[text()='"+CompanyNamedata+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(RecentAddedRecord), "Record not Found");
			bc.log("Consultant master record added Successfully");
	}
	
	public void Test_Edit_Record(){
		bc = new BaseClass(driver);
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(FirstEditButton, PageHeader, Address, AddressName, "test data", OKButton, "Record Updated Successfully.");
		
	}

}
