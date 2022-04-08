package com.nopcommerce;
import org.testng.annotations.Test;
import com.nopcommerce.pagebaseUI.PageBaseUI;
import common.PageTest;
import pageobject_nopcommerce.GeneratorManager;
import pageobject_nopcommerce.ProductPO;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class ProductList extends PageTest {
	private WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		maximizeWindown(driver);
		settimeOutImplicitWait(driver, 30);
		driver.get(PageBaseUI.URL_NOPCOMMERCE);
	}

	@Test
	public void TC01_SortNameByAscending() {
		productListPage=GeneratorManager.getProductList(driver);
		productListPage.selectItemInSort(driver,"Name: A to Z");
		verifyTrue(productListPage.isNameByAscending());
		threadSecond(3);
	}

//	@Test
	public void TC02_SortNameByDescending() {
		productListPage=GeneratorManager.getProductList(driver);
		productListPage.selectItemInSort(driver,"Name: Z to A");
		verifyTrue(productListPage.isNameByDescending());
		threadSecond(3);
	}
	
//	@Test
	public void TC03_SortPriceByDescending() {
		productListPage=GeneratorManager.getProductList(driver);
		productListPage.selectItemInSort(driver,"Price: High to Low");
		threadSecond(3);
		verifyTrue(productListPage.isPriceByDescending());
		
	}
	@Test
	public void TC04_VerifyDBAndUI() throws ClassNotFoundException, SQLException {
		productListPage=GeneratorManager.getProductList(driver);
		verifyEquals(productListPage.geSizeProductUI(), productListPage.getListProductDB());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	ProductPO productListPage;
}
