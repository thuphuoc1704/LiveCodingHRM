package pageobject_hrm;

import org.openqa.selenium.WebDriver;

public class GeneratorManager {
	private WebDriver driver;
		public static LoginPO getLoginPage(WebDriver driver) {
			return new LoginPO(driver);	
		}
		public static DashBoardPO getDashBoardPage(WebDriver driver) {
			return new DashBoardPO(driver);	
		}
		public static EmployeeDetailPO getEmployeeDetailPage(WebDriver driver) {
			return new EmployeeDetailPO(driver);	
		}
		public static EmployeeListPO getEmployeeListPage(WebDriver driver) {
			return new EmployeeListPO(driver);	
		}
		public static AddEmployeePO getAddEmployeePage(WebDriver driver) {
			return new AddEmployeePO(driver);	
		}
}
