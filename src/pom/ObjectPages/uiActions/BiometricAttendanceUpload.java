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

public class BiometricAttendanceUpload extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Human Resource") WebElement HRMS;
	@FindBy(linkText="Employee Self Service") WebElement ESS;
	@FindBy(linkText="Leave & Attendance") WebElement LeaveandAttendance;  
	@FindBy(linkText="Biometric Attendance Upload") WebElement BiometricAttendanceUpload;
	String module = "Human Resource";
	String submoduleName = "ESS";
	String subsectionName = "Leave and Attendance";
	String pageName = "Biometric Attendance Upload";
	String PageTitle = "Biometric Attendance Upload";
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='Biometric Attendance Upload']") WebElement PageHeader;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lblUpload']") WebElement UploadButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drptype']") WebElement UploadTypeDropdown;

	@FindBy(xpath=".//*[@id='ContentPlaceHolder_FileUploadECS']") WebElement BrowseButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ECS_btnUpload']") WebElement ADDButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnCancel']") WebElement CancelButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lnkDownload']") WebElement DownloadTemplate;
			
	String upName = "Upload Button";
	String utName = "Upload Type Dropdown";
	
	String BName = "Browse Button";
	String AName = "ADD Button";
	String CName = "Cancel Button";
	String DTName = "Download Template";
		
	public BiometricAttendanceUpload(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_Submitter() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(28).get("CompanyCode"),map.get(28).get("UserName"), map.get(28).get("Password"));
		
	}
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
	//	cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, RosterDefinition, pageName, PageTitle);	
		cm.TargetPageClick(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, BiometricAttendanceUpload, pageName,PageHeader);
	}
	
	public void Test_Employee_Attendance_Upload(String FileFormate, String EXEFilePath) throws Exception{
		bc = new BaseClass(driver);
		bc.click(UploadButton, upName);
		bc.selectByVisibleText(UploadTypeDropdown, FileFormate, utName);
		bc.waitForElement(BrowseButton);
		bc.click(BrowseButton, BName);
		////////////////
		Runtime.getRuntime().exec(EXEFilePath);
		////////////////
		bc.click(ADDButton, AName);
		bc.waitFor(2);
}
	
	public void Test_VerifyTXTUploaded(String DateData){
		bc = new BaseClass(driver);
	
		try{
		WebElement AttendanceDate = driver.findElement(By.xpath(".//*[text()='"+DateData+"']/parent::div/parent::td/div/div/div/span[text()='PR']"));
		Assert.assertTrue(bc.isElementPresentSingleLocator(AttendanceDate));
		}catch(AssertionError e){
			Assert.fail("Present status is not showing on this date : "+DateData);
		}
				
	}
	
	
}
	
	

