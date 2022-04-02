package pageobject_hrm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hrm.UserPageUI.employeeDetailUI;
import com.hrm.pagebaseUI.PageBaseUI;

import common.PageBase;

public class EmployeeDetailPO extends PageBase {
	private WebDriver driver;

	public EmployeeDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAvatar(WebDriver driver) {
		waitForELementVisible(driver, employeeDetailUI.IMG_AVATAR);
		getElement(driver, employeeDetailUI.IMG_AVATAR).click();
		
	}

	public boolean isImageUploadSuccess(WebDriver driver) {
		waitForELementVisible(driver, employeeDetailUI.IMG_AVATAR);
		int widthImage=Integer.parseInt(getElement(driver, employeeDetailUI.IMG_AVATAR).getAttribute("width"));
		int heightImage=Integer.parseInt(getElement(driver, employeeDetailUI.IMG_AVATAR).getAttribute("height"));
		return  ((widthImage!=200) || ( heightImage!=200));
	}

	public boolean isEnableTextboxInMyInForPage(WebDriver driver, String dynamicLocator) {	
		waitForELementVisible(driver, getLocatorDynamic(employeeDetailUI.TEXTBOX_BY_ID, dynamicLocator));	
		return getElement(driver, getLocatorDynamic(employeeDetailUI.TEXTBOX_BY_ID, dynamicLocator)).isEnabled();	
	}
	public void senkeyToElementDynamicByID(WebDriver driver, String textSenkey, String... dynamicLocator) {
		WebElement element = getElement(driver, getLocatorDynamic(employeeDetailUI.TEXTBOX_BY_ID,dynamicLocator));
		element.clear();
		getElement(driver, getLocatorDynamic(employeeDetailUI.TEXTBOX_BY_ID, dynamicLocator)).sendKeys(textSenkey);
	}
	public void clickSideBarInMyInForPageByText(WebDriver driver, String dynamicLocator) {
		waitForClickToElement(driver, getLocatorDynamic(employeeDetailUI.LINK_SIDEBAR_MYINFOR_BY_TEXT,dynamicLocator ));
		getElement(driver, getLocatorDynamic(employeeDetailUI.LINK_SIDEBAR_MYINFOR_BY_TEXT,dynamicLocator )).click();
	}

	

}
