package br.gol.horus.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.gol.horus.helpers.Screenshoter;

/**
 * Classe de Driver Value Object. Esta classe agrega objetos comumente usados a fim de facilitar o uso ao longo do código.
 * 
 * Agrega os objetos WebDriver, WebDriverWait, JavascriptExecutor e AjaxElementLocatorFactory
 * @author pedro
 *
 */
public class DriverVO {

	private WebDriver webDriver;
	private WebDriverWait webDriverWait;
	private JavascriptExecutor javascriptExecutor;
	private AjaxElementLocatorFactory ajaxElementLocatorFactory;
	private Screenshoter screenshoter;

	protected DriverVO(WebDriver webDriver, WebDriverWait webDriverWait, JavascriptExecutor javascriptExecutor,
			AjaxElementLocatorFactory ajaxElementLocatorFactory) {
		this.webDriver = webDriver;
		this.webDriverWait = webDriverWait;
		this.javascriptExecutor = javascriptExecutor;
		this.ajaxElementLocatorFactory = ajaxElementLocatorFactory;
		this.screenshoter = new Screenshoter(this.webDriver);
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriverWait getWebDriverWait() {
		return webDriverWait;
	}

	public void setWebDriverWait(WebDriverWait webDriverWait) {
		this.webDriverWait = webDriverWait;
	}

	public JavascriptExecutor getJavascriptExecutor() {
		return javascriptExecutor;
	}

	public void setJavascriptExecutor(JavascriptExecutor javascriptExecutor) {
		this.javascriptExecutor = javascriptExecutor;
	}

	public AjaxElementLocatorFactory getAjaxElementLocatorFactory() {
		return ajaxElementLocatorFactory;
	}

	public void setAjaxElementLocatorFactory(AjaxElementLocatorFactory ajaxElementLocatorFactory) {
		this.ajaxElementLocatorFactory = ajaxElementLocatorFactory;
	}
	
	public void tirarScreenshot(String fileName) {
		this.screenshoter.tirarScreenshot(fileName);
	}

	public void destroyDriverVO() {
		if (webDriver != null) {
			webDriver.quit();
			webDriver = null;
			webDriverWait = null;
			javascriptExecutor = null;
			screenshoter = null;
		}
	}

}
