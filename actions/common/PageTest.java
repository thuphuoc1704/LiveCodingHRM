package common;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.hrm.pagebaseUI.PageBaseUI;
import com.hrm.pagebaseUI.PageBaseUI.BROWSERLIST;
import com.hrm.pagebaseUI.PageBaseUI.ENVIROMENT;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject_hrm.LoginPO;

public class PageTest {
	protected final Log log;

	protected PageTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver GetDriverInstance() {
		return this.driver;
	}

	private WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	protected WebDriver getBrowserDriver(String browserName) {
		BROWSERLIST browser= BROWSERLIST.valueOf(browserName.toUpperCase());
		if (browser.equals(BROWSERLIST.CHROME)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals(BROWSERLIST.FIREFOX)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals(BROWSERLIST.EDGE)) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equals(BROWSERLIST.COCCOC)) {
			WebDriverManager.chromedriver().driverVersion("97.0.4692").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\phuoc\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		} else {
			throw new RuntimeException("Browser Name Invalid");
		}
		return driver;
	}
	
	protected WebDriver getBrowserDriverAndEnvMutiple(String browserName,String url) {
		BROWSERLIST browser= BROWSERLIST.valueOf(browserName.toUpperCase());
		if (browser.equals(BROWSERLIST.CHROME)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals(BROWSERLIST.FIREFOX)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals(BROWSERLIST.EDGE)) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equals(BROWSERLIST.COCCOC)) {
			WebDriverManager.chromedriver().driverVersion("97.0.4692").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\phuoc\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		} else {
			throw new RuntimeException("Browser Name Invalid");
		}
		driver.get(getEnv(url));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	protected WebDriver getBrowserDriverAndEnvMutiple_Grid(String browserName,String url,String ip,String port) {
		BROWSERLIST browser= BROWSERLIST.valueOf(browserName.toUpperCase());
		DesiredCapabilities cap=null;
		if (browser.equals(BROWSERLIST.CHROME)) {
			WebDriverManager.chromedriver().setup();
			cap=DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WINDOWS);
			ChromeOptions chromeOption= new ChromeOptions();
			chromeOption.merge(cap);
		} else if (browser.equals(BROWSERLIST.FIREFOX)) {
			WebDriverManager.firefoxdriver().setup();
			cap=DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.WINDOWS);
			FirefoxOptions firefoxOption= new FirefoxOptions();
			firefoxOption.merge(cap);
		} else if (browser.equals(BROWSERLIST.EDGE)) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equals(BROWSERLIST.COCCOC)) {
			WebDriverManager.chromedriver().driverVersion("97.0.4692").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\phuoc\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
		} else {
			throw new RuntimeException("Browser Name Invalid");
		}
		try {
			driver=new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ip,port)),cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.get(getEnv(url));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}
	protected String getEnv(String envName) {
		String url=null;
		if (envName.equals("dev")) {
			url=PageBaseUI.URL_DEV;
		} else if (envName.equals("release")) {
			url=PageBaseUI.URL_RELEASE;
		} else if (envName.equals("live")) {
			url=PageBaseUI.URL_LIVE;
		} else {
			throw new RuntimeException("Enviroment Name Invalid");
		}
		return url;
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	public void settimeOutImplicitWait(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void maximizeWindown(WebDriver driver) {
		driver.manage().window().maximize();
	}
	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}
	
	public void threadSecond(long second) {
		try {
			Thread.sleep(1000 * second);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	LoginPO loginPage;
}
