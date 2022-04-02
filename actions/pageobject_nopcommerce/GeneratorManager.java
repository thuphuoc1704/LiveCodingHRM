package pageobject_nopcommerce;

import org.openqa.selenium.WebDriver;

public class GeneratorManager {
	private WebDriver driver;
		public static ProductPO getProductList(WebDriver driver) {
			return new ProductPO(driver);	
		}
		
}
