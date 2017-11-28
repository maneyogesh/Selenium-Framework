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

public class PeriodMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(xpath=".//*[text()='Human Resource']") WebElement HRMS;
	@FindBy(linkText="HR Setup") WebElement HRSetup;
	@FindBy(linkText="General") WebElement General;  
	@FindBy(linkText="Period Master") WebElement PeriodMaster;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpSearchList']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearchText']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cmdClearSearch']") WebElement ClearSearchbutton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgDesgn']/tbody/tr[2]/td[1]/a") WebElement FirstRecord;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtNoticePeriod']") WebElement NoOfDays;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtnoticeperiodalert']") WebElement NoticePeriod;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_Txtprobationperiod']") WebElement ProbationPeriod;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_Txtprobationperiodalert']") WebElement PPAlert;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_OkButton']") WebElement SaveButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	String module = "Human Resource";
	String submoduleName = "HR Setup";
	String subsectionName = "General";
	String pageName = "Period Master";
	String PageTitle = "Period Master";
	String AddNewName = "Add New Button";
	String FName = "First Record";
	String nodName = "No. Of Day";
	String npName = "Notice Period";
	String ppName = "Probation Period";
	String ppalertName = "Probation Period (Alert)";
	String SaveName = "OK Button";
	String canName = "Cancel Button";
	String sdName= "Search Dropdown";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
		
	public PeriodMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, HRSetup, submoduleName, General, subsectionName, PeriodMaster, pageName,PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, sdName, searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_EditRecord(String NoOfDaysdata, String NoticePerioddata, String ProbationPerioddata, String PPAlertdata){
		bc = new BaseClass(driver);
		bc.waitForElement(FirstRecord);
		bc.click(FirstRecord, AddNewName);
		bc.waitForElement(NoOfDays);
		bc.SendKeys(NoOfDays, NoOfDaysdata, nodName);
		bc.SendKeys(NoticePeriod, NoticePerioddata,npName);
		bc.SendKeys(ProbationPeriod, ProbationPerioddata, ppName);
		bc.SendKeys(PPAlert, PPAlertdata, ppalertName);
		bc.click(SaveButton, SaveName);
		
		bc.waitForAlertBox(5);
		bc.AlertAcceptIfPresent();
		try{
			Assert.assertTrue(bc.isElementPresentSingleLocator(NoOfDays));
		}catch(AssertionError e){
			
			try{
				Assert.assertTrue(bc.isElementPresentSingleLocator(CancelButton));
				bc.click(CancelButton, canName);
				bc.waitForElement(FirstRecord);
				
			}catch(AssertionError t){
				Assert.fail("Expected : [Add New Button] But Found : ["+driver.getTitle()+"]");
			}
			
		}
	}
	
	
	public void Test_VerifyRecord(String Level, String NoticePeriod, String NPAlert, String ProbationPeriod, String ProbationPeriodAlert){
		bc = new BaseClass(driver);
		WebElement RecentUpdatedRecord = driver.findElement(By.xpath(".//*[text()='"+Level+"']/parent::td/parent::tr/td[text()='"+NoticePeriod+"']/parent::tr/td[text()='"+NPAlert+"']/parent::tr/td[text()='"+ProbationPeriod+"']/parent::tr/td[text()='"+ProbationPeriodAlert+"']"));
			Assert.assertTrue(bc.isElementPresentSingleLocator(RecentUpdatedRecord), "Record not Updated Successfully");
			bc.log("Period master record updated Successfully");
	}

}
