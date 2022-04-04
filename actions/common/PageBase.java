package common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.hrm.pagebaseUI.PageBaseUI;

import pageobject_hrm.DashBoardPO;
import pageobject_hrm.GeneratorManager;

public class PageBase {

	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourcerCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshpage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 3);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public void getAlertText(WebDriver driver) {
		waitForAlertPresence(driver).getText();
	}

	public void sendkeyToAlert(WebDriver driver, String textSenkey) {
		waitForAlertPresence(driver).sendKeys(textSenkey);
	}

	public boolean isElementDisplay(WebDriver driver, String locator) {
		waitForELementVisible(driver, locator);
		return getElement(driver, locator).isDisplayed();
	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public By getLocator(String locator) {
		By by = null;
		if (locator.startsWith("id=")) {
			by = by.id(locator.substring(3));
		} else if (locator.startsWith("name=")) {
			by = by.name(locator.substring(5));
		} else if (locator.startsWith("class=")) {
			by = by.className(locator.substring(6));
		} else if (locator.startsWith("css=")) {
			by = by.cssSelector(locator.substring(4));
		} else if (locator.startsWith("xpath=")) {
			by = by.xpath(locator.substring(6));

		} else {
			throw new RuntimeException("Locator invalid");
		}
		return by;
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getLocator(locator));
	}

	public WebElement getElementDynamic(WebDriver driver, String locator, String... dynamicLocator) {
		locator = getLocatorDynamic(locator, dynamicLocator);
		return driver.findElement(getLocator(locator));
	}

	public String getLocatorDynamic(String variableLocator, String... dynamicValues) {
		if (variableLocator.startsWith("xpath=")) {
			variableLocator = String.format(variableLocator, (Object[]) dynamicValues);
		}
		return variableLocator;
	}

	public String getElementAtribute(WebDriver driver, String locator, String nameAttribute) {
		return getElement(driver, locator).getAttribute(nameAttribute);
	}

	public String getElementAtributeTextBoxByIdDynamic(WebDriver driver, String nameAttribute,
			String... dynamicLocator) {
		return getElementDynamic(driver, PageBaseUI.TEXTBOX_BY_ID, dynamicLocator).getAttribute(nameAttribute);
	}

	public String getElementValueCss(WebDriver driver, String locator, String propertyName) {
		return getElement(driver, locator).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public List<WebElement> getListElement(WebDriver driver, String locator) {
		return driver.findElements(getLocator(locator));
	}

	private List<WebElement> getListElementDynamic(WebDriver driver, String locator) {
		return driver.findElements(getLocator(locator));
	}

	public List<WebElement> getListElementDynamic(WebDriver driver, String locator, String... dynamicLocator) {
		return driver.findElements(getLocator(getLocatorDynamic(locator, dynamicLocator)));
	}

	public int getElementSize(WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}

	private int getElementSizeDynamic(WebDriver driver, String locator, String... dynamicLocator) {
		locator = getLocatorDynamic(locator, dynamicLocator);
		return getListElementDynamic(driver, locator).size();
	}

	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}

	public void clickToButtonDynamicByValue(WebDriver driver, String... dynamicLocator) {
		waitForELementVisible(driver, PageBaseUI.BUTTON_BY_VALUE, dynamicLocator);
		getElement(driver, getLocatorDynamic(PageBaseUI.BUTTON_BY_VALUE, dynamicLocator)).click();
	}

	public void clickToButtonByIdFormAndByValueButton(WebDriver driver, String idForm, String valueButton) {
		waitForELementVisible(driver, PageBaseUI.BUTTON_BY_ID_FORM_AND_BY_ID_VALUEBUTTON, idForm, valueButton);
		getElement(driver, getLocatorDynamic(PageBaseUI.BUTTON_BY_ID_FORM_AND_BY_ID_VALUEBUTTON, idForm, valueButton))
				.click();
	}

	public void clickToMenu(WebDriver driver, String menu) {
		waitForClickToElement(driver, PageBaseUI.LINK_HEADER_BY_TEXT, menu);
		getElement(driver, getLocatorDynamic(PageBaseUI.LINK_HEADER_BY_TEXT, menu)).click();
	}

	public void clickToLink(WebDriver driver, String dynamicLocator) {
		waitForClickToElement(driver, PageBaseUI.LINK_BY_ID, dynamicLocator);
		getElement(driver, getLocatorDynamic(PageBaseUI.LINK_BY_ID, dynamicLocator)).click();
	}

	public void clickToSubMenu(WebDriver driver, String subMenu) {
		waitForClickToElement(driver, PageBaseUI.LINK_HEADER_BY_TEXT, subMenu);
		getElement(driver, getLocatorDynamic(PageBaseUI.LINK_HEADER_BY_TEXT, subMenu)).click();
	}

	public void clickToChildSubMenu(WebDriver driver, String childSubMenu) {
		waitForClickToElement(driver, PageBaseUI.LINK_HEADER_BY_TEXT, childSubMenu);
		getElement(driver, getLocatorDynamic(PageBaseUI.LINK_HEADER_BY_TEXT, childSubMenu)).click();
	}

	public void senkeyToElement(WebDriver driver, String locator, String textSenkey) {
		WebElement element = getElement(driver, locator);
		element.clear();
		getElement(driver, locator).sendKeys(textSenkey);
	}

	public void senkeyToElementDynamicByID(WebDriver driver, String textSenkey, String... dynamicLocator) {
		WebElement element = getElement(driver, getLocatorDynamic(PageBaseUI.TEXTBOX_BY_ID, dynamicLocator));
		element.clear();
		getElement(driver, getLocatorDynamic(PageBaseUI.TEXTBOX_BY_ID, dynamicLocator)).sendKeys(textSenkey);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	public String getElementTextDynamic(WebDriver driver, String variableLocator, String... dynamicLocator) {
		variableLocator = getLocatorDynamic(variableLocator, dynamicLocator);
		return getElement(driver, variableLocator).getText().trim();
	}

	public String getElementAttributeDynamicByValue(WebDriver driver, String nameAttribute, String... dynamicLocator) {
		return getElement(driver, getLocatorDynamic(PageBaseUI.TEXTBOX_BY_ID, dynamicLocator))
				.getAttribute(nameAttribute);
	}

	public String getElementTextBoxValue(WebDriver driver, String dynamicLocator) {
		waitForELementVisible(driver, PageBaseUI.TEXTBOX_BY_ID, dynamicLocator);
		return getElementText(driver, dynamicLocator);
	}

	public String getValueInTableAtRowAndColumn(WebDriver driver, String tableId, String columnName, String rowIndex) {
		int columnIndex = getElementSizeDynamic(driver, PageBaseUI.TABLE_HEADER_BY_ID_AND_ROW, tableId, columnName) + 1;
		System.out.println("INDEX COLUMN:" + columnIndex);
		waitForELementVisible(driver, PageBaseUI.TABLE_ROW_BY_COLUMNINDEX_AND_ROWINDEX, tableId, rowIndex,
				String.valueOf(columnIndex));
		System.out.println(getLocatorDynamic(PageBaseUI.TABLE_ROW_BY_COLUMNINDEX_AND_ROWINDEX, tableId, rowIndex,
				String.valueOf(columnIndex)));
		return getElementTextDynamic(driver, PageBaseUI.TABLE_ROW_BY_COLUMNINDEX_AND_ROWINDEX, tableId, rowIndex,
				String.valueOf(columnIndex));
	}

	public boolean isSortDescending(WebDriver driver, String tableId, String columnName) {
		int columnIndex = getElementSizeDynamic(driver, PageBaseUI.TABLE_HEADER_BY_ID_AND_ROW, tableId, columnName) + 1;
		waitForELementVisible(driver, PageBaseUI.COLUMN_FOR_SORT, String.valueOf(columnIndex));
		List<WebElement> lastNameElements = getListElementDynamic(driver, PageBaseUI.COLUMN_FOR_SORT,
				String.valueOf(columnIndex));
		List<String> lastNameText = new ArrayList<String>();
		for (WebElement lastNameElement : lastNameElements) {
			lastNameText.add(lastNameElement.getText());
		}

		System.out.println("Before sort Descending");
		for (String product : lastNameText) {
			System.out.println(product);
		}

		List<String> lastNameTextClone = new ArrayList<String>();
		for (String lastname : lastNameText) {
			lastNameTextClone.add(lastname);
		}

		Collections.sort(lastNameTextClone);
		Collections.reverse(lastNameTextClone);
		System.out.println("After sort Descending");
		for (String product : lastNameTextClone) {
			System.out.println(product);
		}
		return lastNameText.equals(lastNameTextClone);

	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String textItem) {
		Select select = new Select(getElement(driver, locator));
		select.selectByValue(textItem);
	}

	public void selectItemDefaultDropdownDynamic(WebDriver driver, String textValue, String... dynamicLocator) {
		Select select = new Select(
				getElement(driver, getLocatorDynamic(PageBaseUI.DROPDOWNLIST_BY_ID, dynamicLocator)));
		select.selectByVisibleText(textValue);
	}

	public void clickDropDownList(WebDriver driver, String selectID) {
		waitForELementVisible(driver, getLocatorDynamic(PageBaseUI.DROPDOWNLIST_BY_ID, selectID));
		clickToElement(driver, getLocatorDynamic(PageBaseUI.DROPDOWNLIST_BY_ID, selectID));
	}

	public String getSelectedItemInDefaultDropdown(WebDriver driver, String locator) {
		Select select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();

	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemCustomDropDown(WebDriver driver, String xpathParent, String xpathChild, String itemChossed) {
		getElement(driver, xpathParent).click();
		threadSecond(2);
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(getLocator(xpathChild)));
		List<WebElement> allItem = driver.findElements(getLocator(xpathChild));
		for (WebElement item : allItem) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
			item.click();
			threadSecond(3);
			break;
		}
	}

	public void checkToDefaultCheckBox(WebDriver driver, String locator) {
		WebElement element = getElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void CheckBoxDynamicByName(WebDriver driver, String... dynamicLocator) {
		WebElement element = getElementDynamic(driver, PageBaseUI.CHECKBOX_BY_NAME, dynamicLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToDefaultCheckBox(WebDriver driver, String locator) {
		WebElement element = getElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean elementIsSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public boolean elementIsDisplayed(WebDriver driver, String locator) {
		return getElement(driver, locator).isDisplayed();
	}

	public boolean elementIsEnable(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public void switchToFrameIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver, String locator) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", "border: 2px solid red; border-style: dashed;");
		threadSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element,
				"style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String dynamicLocator1, String dynamicLocator2) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getElement(driver, getLocatorDynamic(PageBaseUI.OPTION_OFF_SELECT, dynamicLocator1, dynamicLocator2)));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')",
				getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public boolean isJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active==0); ");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;",
				getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForELementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
	}

	public void waitForELementVisible(WebDriver driver, String variableLocator, String... dynamicLocator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions
				.visibilityOfElementLocated(getLocator(getLocatorDynamic(variableLocator, dynamicLocator))));
	}

	public void waitForAllELementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getLocator(locator)));
	}

	public void waitForELementInVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(getLocator(locator)));
	}

	public void waitForAllELementInVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
	}

	public void waitForClickToElement(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions.elementToBeClickable(getLocator(locator)));
	}

	public void waitForClickToElement(WebDriver driver, String variableLocator, String... dynamicLocator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		explicitwait.until(ExpectedConditions
				.elementToBeClickable(getLocator(getLocatorDynamic(variableLocator, dynamicLocator))));
	}

	public DashBoardPO LoginToSystem(WebDriver driver, String username, String password) {
		waitForELementVisible(driver, PageBaseUI.USERNAME_TEXTBOX);
		senkeyToElement(driver, PageBaseUI.USERNAME_TEXTBOX, username);
		waitForELementVisible(driver, PageBaseUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, PageBaseUI.PASSWORD_TEXTBOX, password);
		clickToButtonDynamicByValue(driver, "LOGIN");
		return GeneratorManager.getDashBoardPage(driver);
	}

	public boolean isMessageUploadSuccess(WebDriver driver) {
		waitForELementVisible(driver, PageBaseUI.MESSAGE_UPLOAD_SUCCESS);
		return isElementDisplay(driver, PageBaseUI.MESSAGE_UPLOAD_SUCCESS);

	}

	public void upLoadImage(WebDriver driver, String filepath) {
		getElement(driver, PageBaseUI.UPLOAD_FILE).sendKeys(filepath);
	}

	public boolean isAdminMenuDisplay(WebDriver driver) {
		waitForELementVisible(driver, PageBaseUI.ADMIN_MENU);
		return getElement(driver, PageBaseUI.ADMIN_MENU).isDisplayed();
	}

	public void threadSecond(long second) {
		try {
			Thread.sleep(1000 * second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
