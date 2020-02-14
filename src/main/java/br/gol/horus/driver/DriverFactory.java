package br.gol.horus.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonObject;

import br.gol.horus.constantes.Constantes;

public class DriverFactory {
	
	private DriverFactory() {
		
	}
	
	/**
	 * Mais informações em: https://seleniumhq.github.io/selenium/docs/api/rb/Selenium/WebDriver/Proxy.html
	 * 
	 * Permite definir o proxy para ser usado para o driver.
	 * 
	 * Exemplo de configuração MANUAL:
	 * json.addProperty("proxyType", "MANUAL");
	 * json.addProperty("httpProxy", "proxyHost:port");
	 * json.addProperty("sslProxy", "proxyHost:port");
	 * 
	 * @return DesiredCapabilities
	 */
	@SuppressWarnings("unused")
	private static DesiredCapabilities getDesiredCapabilities() {
		JsonObject json = new JsonObject();
		json.addProperty("proxyType", "DIRECT");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("proxy", json);
		cap.setCapability("acceptInsecureCerts", true);
		
		return cap;
	}
	
	/**
	 * 
	 * Para usar o proxy caso necessário: new FirefoxOptions(getDesiredCapabilities())
	 * 
	 * Outras opções para o firefox:
	 * 		- Para definir em modo anônimo: .addArguments("--private")
	 * 
	 * 		- Definir estratégia de load: .setPageLoadStrategy(PageLoadStrategy.EAGER);
	 * 						- PageLoadStrategy.NORMAL: A value of normal causes the command to return after the load event fires on the new page.
	 * 						- PageLoadStrategy.EAGER: A value of eager causes it to return after DOMContentLoaded fires.
	 * 				 		- PageLoadStrategy.NONE: A value of none causes it to return immediately. 
	 * 
	 *  	- Launches the application with all extensions disabled, for that launch only. (Extensions are not loaded, but are not permanently 
	 *  	  disabled in the Extension Manager data source): .addArguments("-safe-mode");
	 * @return FirefoxOptions
	 */
	private static FirefoxOptions createFirefoxOptions() {
		return new FirefoxOptions()
			.setLogLevel(FirefoxDriverLogLevel.ERROR)
			.setHeadless(Constantes.IS_HEADLESS);
	}
	
	/**
	 * Definir configurações do browser firefox
	 * @return FirefoxProfile
	 */
	private static FirefoxProfile createFirefoxProfile() {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		
		/**
		 * The value of browser.download.folderList can be set to either 0, 1, or 2. 
		 * When set to 0, Firefox will save all files downloaded via the browser on the user's desktop.
		 * When set to 1, these downloads are stored in the Downloads folder. 
		 * When set to 2, the location specified for the most recent download is utilized again. 
		 * This path can be modified by selecting a different location the next time you download a file through the browser.
		 */
		firefoxProfile.setPreference("browser.download.folderList", 2);

		//A boolean value that indicates whether or not to show the Downloads window when a download begins.
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false); 
		
		// Definindo todas as configurações de locais de download
		firefoxProfile.setPreference("browser.download.dir", Constantes.DOWNLOAD_PATH);
		firefoxProfile.setPreference("browser.download.downloadDir", Constantes.DOWNLOAD_PATH);
		firefoxProfile.setPreference("browser.download.defaultFolder", Constantes.DOWNLOAD_PATH);
		
		// Disabling internal pdf reader in order to download it instead
		firefoxProfile.setPreference("pdfjs.disabled", true);
		
		firefoxProfile.setAcceptUntrustedCertificates(true);
		firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
		
		// definindo of formatos para serem salvos automaticamente ao invés de perguntas
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

	@SuppressWarnings("unused")
	private static DriverVO firefoxDriver() {
		System.setProperty("webdriver.gecko.driver", Constantes.FIREFOX_DRIVER_LOCATION);
		
		FirefoxOptions firefoxOptions = createFirefoxOptions();

		firefoxOptions.setProfile(createFirefoxProfile());
		
		WebDriver driver = new FirefoxDriver(firefoxOptions);
		configureDriver(driver);
		
		if(!Constantes.IS_HEADLESS && Constantes.SHOULD_MAXIMIZE) {
			driver.manage().window().maximize();
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Constantes.EXPLICIT_WAITING_TIME.getSeconds(), Constantes.EXPLICIT_WAITING_TIME_POLLING_INTERVAL.toMillis());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, (int) Constantes.AJAX_ELEMENT_LOCATOR_FACTORY_TIMEOUT.getSeconds());
		
		return new DriverVO(driver, wait, js, ajaxElementLocatorFactory);
	}

	private static void configureDriver(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Constantes.IMPLICIT_WAITING_TIME.getSeconds(), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constantes.PAGE_LOAD_TIMEOUT.getSeconds(), TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Constantes.SCRIPT_TIMEOUT.getSeconds(), TimeUnit.SECONDS);
	}
	
	public static DriverVO createDriverVO() {
		return DriverFactory.firefoxDriver();
	}

	/**
	 * Este método retorna o driver utilizando o google chrome, porém foram detectadas falhas e instabilidades no uso do navegador.
	 * Devido a isso este método está deprecado até que sejam feitos ajustes e fique mais estável.
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static DriverVO chromeDriver() {
		System.setProperty("webdriver.chrome.driver", Constantes.CHROME_DRIVER_LOCATION);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(true); // só funciona com headless = true no chrome
		WebDriver driver = new ChromeDriver(chromeOptions);
		configureDriver(driver);
		WebDriverWait wait = new WebDriverWait(driver, Constantes.EXPLICIT_WAITING_TIME.getSeconds());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, (int) Constantes.AJAX_ELEMENT_LOCATOR_FACTORY_TIMEOUT.getSeconds());
		
		return new DriverVO(driver, wait, js, ajaxElementLocatorFactory);
	}
}
