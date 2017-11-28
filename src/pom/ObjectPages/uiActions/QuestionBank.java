package pom.ObjectPages.uiActions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

public class QuestionBank extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(xpath=".//*[text()='Human Resource']") WebElement HRMS;
	@FindBy(xpath=".//*[text()='HR Setup']") WebElement HRSetup;
	@FindBy(xpath=".//*[text()=' General']") WebElement General;  
	@FindBy(xpath=".//*[text()=' Question Master']") WebElement QuestionMaster;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpsearch']") WebElement searchdropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSearch']") WebElement searchtextbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnSearch']") WebElement searchbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnClear']") WebElement ClearSearchbutton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lnkaddnew']") WebElement AddNewButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtQesCode']") WebElement QuestionCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ddSubject']") WebElement SelectSubject;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtqesDesc']") WebElement Question;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ddqusType']") WebElement QuestionType;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSubMks']") WebElement Marks;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lnksubmit']") WebElement SaveButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lnkback']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtnooption']") WebElement NoOfOption;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtopn0']") WebElement Option1;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtopn1']") WebElement Option2;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtopn2']") WebElement Option3;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtopn3']") WebElement Option4;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSub']") WebElement ExpectedAnswer;
		
	String module = "Human Resource";
	String submoduleName = "HR Setup";
	String subsectionName = "General";
	String pageName = "Question Master";
	String PageTitle = "Question Master";
	String AddNewName = "Add New Button";
	String qcName = "Question Code";
	String ssName = "Select Subject";
	String QName = "Question";
	String QTName = "Question Type";
	String MName = "Marks";
	String SaveName = "Save Button";
	String CName = "Cancel Button";
	String NOOName = "No Of Option";
	String ExpectedAnswerName = "Expected Answer";
	
	
	@FindBy(xpath=".//*[@class='page-heading']/span[text()='Question Master']") WebElement PageHeader;
	
	
	public QuestionBank(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\HRMS_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","HRSetup");
		lp = new LoginPage(driver);
		lp.Login(map.get(1).get("CompanyCode"),map.get(1).get("UserName"), map.get(1).get("Password"));
	}
	
	public void Test_Open_Target_Page() throws Exception{
		cm = new CommonMethods(driver);
		cm.TargetPageClick(HRMS, module, HRSetup, submoduleName, General, subsectionName, QuestionMaster, pageName,PageHeader);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, "Search Dropdown", searchtextbox, SearchData, searchbutton);
	}
	
	public void Test_AddNewRecord(String QuestionCodedata, String SelectSubjectData, String Questiondata, String QTData, String NoOfOptiondata, String Marksdata, String Option1data, String Option2data, String Option3data, String Option4data, String ExpectedAnswerdata){
		bc = new BaseClass(driver);
		bc.waitForElement(AddNewButton);
		bc.click(AddNewButton, AddNewName);
		bc.waitForElement(QuestionCode);
		bc.SendKeys(QuestionCode, QuestionCodedata, qcName);
		bc.selectByVisibleText(SelectSubject, SelectSubjectData, ssName);
		bc.SendKeys(Question, Questiondata, QName);
		bc.selectByVisibleText(QuestionType, QTData, QTName);
		bc.waitFixedTime(1);
		if(QTData.equals("Objective")){
			bc.waitForElement(NoOfOption);
			bc.SendKeys(NoOfOption, NoOfOptiondata, NOOName);
			bc.click(Marks, MName);
			bc.waitForElement(Option1);
			bc.SendKeys(Marks, Marksdata, MName);
			bc.click(NoOfOption, NoOfOptiondata);
			switch (NoOfOptiondata) {
			case "2":
				bc.SendKeys(Option1, Option1data, "Option1");
				bc.SendKeys(Option2, Option2data, "Option2");
				break;
			case "3":
				bc.SendKeys(Option1, Option1data, "Option1");
				bc.SendKeys(Option2, Option2data, "Option2");
				bc.SendKeys(Option3, Option3data, "Option3");
				break;
			case "4":
				bc.SendKeys(Option1, Option1data, "Option1");
				bc.SendKeys(Option2, Option2data, "Option2");
				bc.SendKeys(Option3, Option3data, "Option3");
				bc.SendKeys(Option4, Option4data, "Option4");
				break;
			default:
				bc.SendKeys(Option1, Option1data, "Option1");
			}				
		}else if (QTData.equals("Subjective")){
			bc.waitForElement(ExpectedAnswer);
			bc.SendKeys(Marks, Marksdata, MName);
			bc.click(ExpectedAnswer, ExpectedAnswerName);
			bc.SendKeys(ExpectedAnswer, ExpectedAnswerdata, ExpectedAnswerName);
		}
		bc.click(SaveButton, SaveName);
		bc.waitForAlertBox(5);
		bc.AlertAcceptIfPresent();
		try{
			Assert.assertTrue(bc.isElementPresentSingleLocator(AddNewButton));
		}catch(AssertionError e){
			bc.click(CancelButton, CName);
			bc.waitForElement(AddNewButton);
		}
	}
	
	
	public void Test_VerifyRecord(String QuestionCodedata){
		bc = new BaseClass(driver);
			Assert.assertTrue(bc.isElementPresentXpathLocator(QuestionCodedata), "Record not Added Successfully");
			bc.log("Question added Successfully");
	}
	
}
