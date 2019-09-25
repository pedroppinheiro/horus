package br.bbts.horus.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverVO {

	private WebDriver webDriver;
	private WebDriverWait webDriverWait;
	private JavascriptExecutor javascriptExecutor;
	private AjaxElementLocatorFactory ajaxElementLocatorFactory;

	protected DriverVO(WebDriver webDriver, WebDriverWait webDriverWait, JavascriptExecutor javascriptExecutor,
			AjaxElementLocatorFactory ajaxElementLocatorFactory) {
		this.webDriver = webDriver;
		this.webDriverWait = webDriverWait;
		this.javascriptExecutor = javascriptExecutor;
		this.ajaxElementLocatorFactory = ajaxElementLocatorFactory;
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

	public void destroyDriverVO() {
		if (webDriver != null) {
			webDriver.quit();
			webDriver = null;
			webDriverWait = null;
			javascriptExecutor = null;
		}
	}

}
