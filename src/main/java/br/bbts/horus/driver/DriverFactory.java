package br.bbts.horus.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonObject;

import br.bbts.horus.constantes.Constantes;

public class DriverFactory {
	
	private DriverFactory() {
	}

	//private static FirefoxOptions firefoxOptions = new FirefoxOptions(proxyConfig())
	private static FirefoxOptions firefoxOptions = new FirefoxOptions()
			.setLogLevel(FirefoxDriverLogLevel.ERROR)
			.setHeadless(Constantes.IS_HEADLESS);
//			.addArguments("--private")
//			.setPageLoadStrategy(PageLoadStrategy.EAGER); 
			// A value of normal causes the command to return after the load event fires on the new
			// page, a value of eager causes it to return after DOMContentLoaded fires, and
			// a value of none causes it to return immediately.
//			.addArguments("-safe-mode"); //Launches the application with
			// all extensions disabled, for that launch only. (Extensions are not loaded,
			// but are not permanently disabled in the Extension Manager data source).
	
	private static DesiredCapabilities proxyConfig() {

		JsonObject json = new JsonObject();
		json.addProperty("proxyType", "DIRECT");
		
//		String proxy = "proxybem.cobra.com.br";
//		int port = 3128;
//		json.addProperty("proxyType", "MANUAL"); // https://seleniumhq.github.io/selenium/docs/api/rb/Selenium/WebDriver/Proxy.html
//		json.addProperty("httpProxy", proxy + ":" + port);
//		json.addProperty("sslProxy", proxy + ":" + port);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("proxy", json);
		cap.setCapability("acceptInsecureCerts", true);
		
		return cap;
	}
	
	private static FirefoxProfile createFirefoxProfile() {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		
		firefoxProfile.setPreference("browser.download.dir", Constantes.DOWNLOAD_PATH);
		firefoxProfile.setPreference("browser.download.downloadDir", Constantes.DOWNLOAD_PATH);
		firefoxProfile.setPreference("browser.download.defaultFolder", Constantes.DOWNLOAD_PATH);
		
		firefoxProfile.setPreference("pdfjs.disabled", true);
		firefoxProfile.setAcceptUntrustedCertificates(true);
		firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
		
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
				"text/csv,application/x-msexcel,application/excel,"
				+ "application/x-excel,"
				+ "application/vnd.ms-excel,"
				+ "image/png,"
				+ "image/jpeg,"
				+ "text/html,"
				+ "text/plain,"
				+ "application/msword,"
				+ "application/pdf,"
				+ "application/xml");
		return firefoxProfile;
	}

	private static DriverVO firefoxDriver() {
		System.setProperty("webdriver.gecko.driver", Constantes.FIREFOX_DRIVER_LOCATION);

		DriverFactory.firefoxOptions.setProfile(createFirefoxProfile());
		
		WebDriver driver = new FirefoxDriver(DriverFactory.firefoxOptions);
		configureDriver(driver);
		
		if(!Constantes.IS_HEADLESS && Constantes.SHOULD_MAXIMIZE) {
			driver.manage().window().maximize();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Constantes.EXPLICIT_WAITING_TIME);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, Constantes.AJAX_ELEMENT_LOCATOR_FACTORY_TIMEOUT);
		
		return new DriverVO(driver, wait, js, ajaxElementLocatorFactory);
	}

//	private static WebDriver chromeDriver() {
//		System.setProperty("webdriver.chrome.driver", Constantes.CHROME_DRIVER_LOCATION);
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.setHeadless(true); // só funciona com headless = true no chrome
//		driver = new ChromeDriver(chromeOptions);
//		configureDriver();
//		wait = new WebDriverWait(driver, Constantes.EXPLICIT_WAITING_TIME);
//		js = (JavascriptExecutor) driver;
//		return driver;
//	}

	private static void configureDriver(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Constantes.IMPLICIT_WAITING_TIME, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constantes.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Constantes.SCRIPT_TIMEOUT, TimeUnit.SECONDS);
	}
	
	public static DriverVO createDriverVO() {
		return DriverFactory.firefoxDriver();
	}

}
