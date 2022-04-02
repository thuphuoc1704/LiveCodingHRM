package pageobject_nopcommerce;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.nopcommerce.pagebaseUI.ProductPageUI;

import common.PageBase;

public class ProductPO extends PageBase {
	private WebDriver driver;

	public ProductPO(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSort(WebDriver driver, String textItem) {
			Select select = new Select(getElement(driver, ProductPageUI.SELECT_BY_ID));
			select.selectByVisibleText(textItem);
	}
	public boolean isNameByAscending() {
		List<WebElement> productNameElements=getListElement(driver, ProductPageUI.PRODUCT_NAME);
		
		List<String> productNameText=new ArrayList<String>();
		for (WebElement productNameElement : productNameElements) {
			productNameText.add(productNameElement.getText());
		}
		
		System.out.println("Before sort Ascending ");
		for (String product : productNameText) {
			System.out.println(product);
		}
		
		ArrayList<String> productNameTextClone=new ArrayList<String>();
		for (String product : productNameText) {
			productNameTextClone.add(product);
		}
		
		Collections.sort(productNameTextClone);
		
		System.out.println("After sort Ascending");
		for (String product : productNameTextClone) {
			System.out.println(product);
		}
		return productNameTextClone.equals(productNameText);
		
	}
	public boolean isNameByDescending() {
		List<WebElement> productNameElements=getListElement(driver, ProductPageUI.PRODUCT_NAME);
		
		List<String> productNameText=new ArrayList<String>();
		for (WebElement productNameElement : productNameElements) {
			productNameText.add(productNameElement.getText());
		}
		
		System.out.println("Before sort Descending");
		for (String product : productNameText) {
			System.out.println(product);
		}
		
		List<String> productNameTextClone=new ArrayList<String>();
		for (String product : productNameText) {
			productNameTextClone.add(product);
		}
		
		System.out.println("After sort Descending");
		Collections.sort(productNameTextClone);
		Collections.reverse(productNameTextClone);
		for (String product : productNameTextClone) {
			System.out.println(product);
		}
		return productNameTextClone.equals(productNameText);
		
	}
	public boolean isPriceByDescending() {
		List<WebElement> productNamePrices=getListElement(driver, ProductPageUI.PRODUCT_PRICE);
		
		List<Float> productNamePrice=new ArrayList<Float>();
		for (WebElement productPriceElement : productNamePrices) {
			productNamePrice.add(Float.parseFloat(productPriceElement.getText().replace("$", "").replace(",", ".")));
		}
		//Float.parseFloat(productPriceElement.getText().replace("$", "").trim())
		System.out.println("Before sort Descending");
		for (Float product :productNamePrice) {
			System.out.println(product);
		}
		
		List<Float> productPriceClone=new ArrayList<Float>();
		for (Float product : productNamePrice) {
			productPriceClone.add(product);
		}
		
		System.out.println("After sort Descending");
		Collections.sort(productPriceClone);
		Collections.reverse(productPriceClone);
		
		for (Float product : productPriceClone) {
			System.out.println(product);
		}
		return productPriceClone.equals(productNamePrice);
		
	}
}
