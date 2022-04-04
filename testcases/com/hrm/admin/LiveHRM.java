package com.hrm.admin;
import org.testng.annotations.Test;
import com.hrm.pagebaseUI.PageBaseUI;
import common.DataHelper;
import common.PageTest;
import pageobject_hrm.AddEmployeePO;
import pageobject_hrm.DashBoardPO;
import pageobject_hrm.EmployeeDetailPO;
import pageobject_hrm.EmployeeListPO;
import pageobject_hrm.GeneratorManager;
import pageobject_hrm.LoginPO;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class LiveHRM extends PageTest {
	private WebDriver driver;
	String avatarFilePath=PageBaseUI.UPLOAD_FOLDER_PATH +"image.jpg";
	private String username, password,firstName,lastName, passwordEmp,idEmployee, nickName;
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		maximizeWindown(driver);
		settimeOutImplicitWait(driver, 30);
		driver.get(PageBaseUI.URL_HRM);
		dataHelper=DataHelper.getData();
		firstName=dataHelper.getFirstName();
		lastName=dataHelper.getLastName();
		passwordEmp=dataHelper.getPassWord();
		username = "Admin";
		password = "admin123";
	    nickName="pikachu";
	}

	@Test
	public void TC01_LoginByAdmin() {
		log.info("Login to system by admin");
		loginPage=GeneratorManager.getLoginPage(driver);
		dashboardPage=loginPage.LoginToSystem(driver, username, password);
	}
	@Test
	public void TC02_AddEmployee() {	
		log.info("AddEmployee_Step01: click to link My Info");
		dashboardPage.clickToMenu(driver, "My Info");
		
		log.info("AddEmployee_Step02: click to link PIM");
		dashboardPage.clickToMenu(driver,"PIM");
		
		log.info("AddEmployee_Step03: click to link Add Employee");
		dashboardPage.clickToSubMenu(driver, "Add Employee");
		
		log.info("AddEmployee_Step04: open list employee page");
		addemployeePage=GeneratorManager.getAddEmployeePage(driver);
		
		log.info("AddEmployee_Step05: input lastname field: "+firstName);
		addemployeePage.senkeyToElementDynamicByID(driver, firstName, "firstName");
		
		log.info("AddEmployee_Step06: input lastname field: "+lastName);
		addemployeePage.senkeyToElementDynamicByID(driver, lastName, "lastName");
		
		log.info("AddEmployee_Step07: get employeeId");
	    idEmployee=addemployeePage.getElementAtributeTextBoxByIdDynamic(driver, "value","employeeId");
	    System.out.println("EmployeeID:"+idEmployee);
		
		log.info("AddEmployee_Step08: check in box login detail");
		addemployeePage.CheckBoxDynamicByName(driver, "chkLogin", "Create Login Details");
		
		log.info("AddEmployee_Step09: input username field: "+lastName);
		addemployeePage.senkeyToElementDynamicByID(driver, lastName, "user_name");
		
		log.info("AddEmployee_Step10: input password field: "+passwordEmp);
		addemployeePage.senkeyToElementDynamicByID(driver, passwordEmp, "user_password");
		
		log.info("AddEmployee_Step11: input confPassword field: "+passwordEmp);
		addemployeePage.senkeyToElementDynamicByID(driver, passwordEmp, "re_password");
		
		log.info("AddEmployee_Step12: select enable in dropdownlist field: ");
		addemployeePage.selectItemDefaultDropdownDynamic(driver,"Enabled","status");
		
		log.info("AddEmployee_Step13: click button Save");
		addemployeePage.clickToButtonDynamicByValue(driver, "Save");
	}
//	@Test
	public void TC03_SortLastNameEmployee() {
		employeeDetailPage=GeneratorManager.getEmployeeDetailPage(driver);
		employeeDetailPage.clickToMenu(driver,"PIM");
		employeeDetailPage.clickToSubMenu(driver,"Employee List");
		employeeListPage=GeneratorManager.getEmployeeListPage(driver);
		employeeListPage.clickToMenu(driver, "Last Name");
		employeeListPage.clickToMenu(driver, "Last Name");
		threadSecond(2);
		verifyTrue(employeeListPage.isSortDescending(driver, "resultTable", "Last Name"));
	}
	
//	@Test
	public void TC04_SearchEmployee() {
		log.info("SearchEmployee_Step01: open detail employee");
		employeeDetailPage=GeneratorManager.getEmployeeDetailPage(driver);
		
		log.info("SearchEmployee_Step02: click to link PIM");
		employeeDetailPage.clickToMenu(driver,"PIM");
		
		log.info("SearchEmployee_Step03: click to link Employee List");
		employeeDetailPage.clickToSubMenu(driver,"Employee List");
		
		log.info("SearchEmployee_Step04: Open list employee page");
		employeeListPage=GeneratorManager.getEmployeeListPage(driver);
		
		log.info("SearchEmployee_Step05: search name employee");
		employeeListPage.senkeyToElementDynamicByID(driver, firstName,"empsearch_employee_name_empName");
		
		log.info("SearchEmployee_Step06: search id employee");
		verifyTrue(employeeListPage.isJQueryAndJSLoadedSuccess(driver));
		employeeListPage.senkeyToElementDynamicByID(driver, idEmployee, "empsearch_id");
		verifyTrue(employeeListPage.isJQueryAndJSLoadedSuccess(driver));
		
		log.info("SearchEmployee_Step07: Click to Search button");
		verifyTrue(employeeListPage.isJQueryAndJSLoadedSuccess(driver));
		employeeListPage.clickToButtonDynamicByValue(driver, "Search");
		verifyTrue(employeeListPage.isJQueryAndJSLoadedSuccess(driver));
		
		log.info("SearchEmployee_Step08: Verify result from search");
		String value=employeeListPage.getValueInTableAtRowAndColumn(driver,"resultTable","Last Name","1");
		System.out.println("value"+ value);
		verifyEquals(value, lastName);
	}
//	@Test
	public void TC05_Logout() {
		employeeDetailPage=GeneratorManager.getEmployeeDetailPage(driver);
		verifyTrue(employeeDetailPage.isJQueryAndJSLoadedSuccess(driver));
		
		log.info("Logout_Step01: click the account icon");
		employeeDetailPage.clickToLink(driver, "welcome");
		
		log.info("Logout_Step01: click to 'Logout' link");
		employeeDetailPage.clickToMenu(driver, "Logout");	
	}
	
//	@Test
	public void TC05_LoginByUser() {
		loginPage=GeneratorManager.getLoginPage(driver);
		log.info("LoginByUse_Step01: login to system by user");
		dashboardPage=loginPage.LoginToSystem(driver, lastName, passwordEmp);
	}
//	@Test
	public void TC06_UploadAvatar() {
		log.info("UploadAvatar_Step01: click My Infor link");
		dashboardPage.clickToMenu(driver, "My Info");
		employeeDetailPage=GeneratorManager.getEmployeeDetailPage(driver);
		
		log.info("UploadAvatar_Step02: click avatar");
		employeeDetailPage.clickToAvatar(driver);
		
		log.info("UploadAvatar_Step03: click Choose file");
		employeeDetailPage.upLoadImage(driver, avatarFilePath);
		
		log.info("UploadAvatar_Step04: click Upload button");
		employeeDetailPage.clickToButtonDynamicByValue(driver, "Upload");
		
		log.info("UploadAvatar_Step05: verify message upload successfull");
		verifyTrue(employeeDetailPage.isMessageUploadSuccess(driver));
		
		log.info("UploadAvatar_Step06: verify image upload successfull");
		verifyTrue(employeeDetailPage.isImageUploadSuccess(driver));
	}
//	@Test
	public void TC07_EditEmployee() {
		log.info("EditEmployee_Step01: Click to My Infor");
		employeeDetailPage.clickSideBarInMyInForPageByText(driver, "Personal Details");
		
		log.info("EditEmployee_Step02: Verify field disable");
		verifyFalse(employeeDetailPage.isEnableTextboxInMyInForPage(driver, "personal_txtEmpFirstName"));
		verifyFalse(employeeDetailPage.isEnableTextboxInMyInForPage(driver, "personal_txtEmpLastName"));
		verifyFalse(employeeDetailPage.isEnableTextboxInMyInForPage(driver, "personal_txtEmployeeId"));
		verifyFalse(employeeDetailPage.isEnableTextboxInMyInForPage(driver, "personal_txtLicExpDate"));
		
		log.info("EditEmployee_Step03: Click to Edit button");
		employeeDetailPage.isJQueryAndJSLoadedSuccess(driver);
		employeeDetailPage.clickToButtonByIdFormAndByValueButton(driver, "frmEmpPersonalDetails", "Edit");
		
		log.info("EditEmployee_Step04: Edit firstname field");
		employeeDetailPage.senkeyToElementDynamicByID(driver, firstName, "personal_txtEmpFirstName");
		
		log.info("EditEmployee_Step05: Edit lastname field");
		employeeDetailPage.senkeyToElementDynamicByID(driver, lastName, "personal_txtEmpLastName");
		
		
		log.info("EditEmployee_Step06: Input nickname field");
		employeeDetailPage.senkeyToElementDynamicByID(driver, nickName, "personal_txtEmpNickName");
		
		log.info("EditEmployee_Step07: Click to Save button");
		employeeDetailPage.clickToButtonDynamicByValue(driver, "Save");
		
		log.info("EditEmployee_Step08: Verify message upload successfull");
		verifyTrue(employeeDetailPage.isMessageUploadSuccess(driver));
	}
//	@Test
	public void TC08_ContactDetail() {
		log.info("UploadAvatar_Step01: click My Infor link");
		dashboardPage.clickToMenu(driver, "My Info");
		GeneratorManager.getEmployeeDetailPage(driver);
		
		log.info("ContactDetail_Step01: Click to Contact Detail");
		employeeDetailPage.clickSideBarInMyInForPageByText(driver, "Contact Details");
		
		log.info("ContactDetail_Step02: Click to Edit button");
		verifyTrue(employeeDetailPage.isJQueryAndJSLoadedSuccess(driver));
		employeeDetailPage.clickToButtonByIdFormAndByValueButton(driver, "frmEmpContactDetails", "Edit");
		
		log.info("ContactDetail_Step03: Select 'Viet Nam' country");
		employeeDetailPage.selectItemDefaultDropdownDynamic(driver, "Viet Nam","contact_country");
		employeeDetailPage.clickToButtonDynamicByValue(driver, "Save");
		verifyTrue(employeeDetailPage.isMessageUploadSuccess(driver));

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	LoginPO loginPage;
	DashBoardPO dashboardPage;
	EmployeeListPO employeeListPage;
	EmployeeDetailPO employeeDetailPage;
	AddEmployeePO addemployeePage;
	DataHelper dataHelper;

}
