package pageobject_nopcommerce;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
			productNamePrice.add(Float.parseFloat(productPriceElement.getText().substring(1, productPriceElement.getText().length()-3).replace(",", ".")));
		}
		System.out.println("Before sort Descending");
		for (Float product :productNamePrice) {
			System.out.println(product);
		}
		
		System.out.println("After sort Descending");

		List<Float> productNamePriceClone = new ArrayList<Float>(productNamePrice);
		Collections.sort(productNamePriceClone);
		Collections.reverse(productNamePriceClone);
		
		for (Float product : productNamePriceClone) {
			System.out.println(product);
		}
		return productNamePriceClone.equals(productNamePrice);
		
	}
	
	public boolean isProductPriceSortDesc() {
		List<WebElement> productPriceElements = getListElement(driver, ProductPageUI.PRODUCT_PRICE);
		
		List<Float> productPriceValue = new ArrayList<Float>();
		
		for (WebElement productPrice : productPriceElements) {
			productPriceValue.add(Float.parseFloat(productPrice.getText().substring(1, productPrice.getText().length()-3).replace(",", ".")));
		}
		
		System.out.println("Before sort desc: -------------------------------------");
		for(Float productPrice:productPriceValue) {
			System.out.println(productPrice);
		}
		
		List<Float> productNamePriceClone = new ArrayList<Float>(productPriceValue);
		Collections.sort(productNamePriceClone);
		Collections.reverse(productNamePriceClone);
		
		System.out.println("After sort desc: -------------------------------------");
		for(Float productPrice:productNamePriceClone) {
			System.out.println(productPrice);
		}
		
		return productPriceValue.equals(productNamePriceClone);
	}
}
