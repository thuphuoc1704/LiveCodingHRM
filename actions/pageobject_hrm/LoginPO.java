package pageobject_hrm;

import org.openqa.selenium.WebDriver;
import common.PageBase;

public class LoginPO extends PageBase {
	private WebDriver driver;

	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}
}
