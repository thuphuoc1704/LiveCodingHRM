package com.bankguru;
import org.testng.annotations.Test;
import common.PageTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class MultipleEnvorment extends PageTest {
	private WebDriver driver;

	@Parameters({"browser","env","ip","port"})
	@BeforeClass
	public void beforeClass(String browserName, String url, String ip, String port) {
		driver = getBrowserDriverAndEnvMutiple_Grid(browserName, url,ip,port);
	}

	@Test
	public void TC01_SortNameByDescending() {
	
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
}
