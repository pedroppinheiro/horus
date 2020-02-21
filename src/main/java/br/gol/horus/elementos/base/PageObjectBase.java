package br.gol.horus.elementos.base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.gol.horus.constantes.Constantes;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.GOLPage;
import br.gol.horus.helpers.Screenshoter;

/**
 * Classe que guarda comportamentos e objetos que são utilizados para qualquer
 * tipo de página
 * 
 * @author pedro
 *
 */
public abstract class PageObjectBase {

	protected DriverVO driverVO;
	public static final Logger LOGGER = Logger.getLogger(GOLPage.class.getName());

	public PageObjectBase(DriverVO driverVO) {
		this.driverVO = driverVO;
		PageFactory.initElements(getDriverVO().getAjaxElementLocatorFactory(), this);
	}

	/**
	 * Aguarda até que uma condição seja satisfeita. Tempo de timeout é o padrão
	 * definido nas configurações do projeto Tempo de pooling é o padrão definido
	 * nas configurações do projeto
	 * 
	 * @param condition
	 */
	public void waitUntil(Function<WebDriver, ?> condition) {
		driverVO.getWebDriverWait().until(condition);
	}

	/**
	 * Aguarda até que uma condição seja satisfeita dentro de um timeout específico.
	 * Tempo de pooling é o padrão definido nas configurações do projeto
	 * 
	 * @param condition
	 */
	public void waitUntil(Function<WebDriver, ?> condition, int timeoutInSeconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driverVO.getWebDriver())
				.withTimeout(Duration.ofSeconds(timeoutInSeconds))
				.pollingEvery(Constantes.EXPLICIT_WAITING_TIME_POLLING_INTERVAL);
		// .ignoring(Exception.class);
		wait.until(condition);
	}

	/**
	 * Aguarda até que uma condição seja satisfeita dentro de um timeout específico
	 * e com um tempo de polling.
	 * 
	 * @param condition
	 */
	public void waitUntil(Function<WebDriver, ?> condition, int timeoutInSeconds, int poolingInMilliseconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driverVO.getWebDriver())
				.withTimeout(Duration.ofSeconds(timeoutInSeconds))
				.pollingEvery(Duration.ofMillis(poolingInMilliseconds));
		// .ignoring(Exception.class);
		wait.until(condition);
	}

	/**
	 * Wrapper para o execute script do driver
	 * 
	 * @param script
	 * @param args
	 * @return objeto de retorno do script se tiver algum
	 */
	public Object executeScript(String script, Object... args) {
		return driverVO.getJavascriptExecutor().executeScript(script, args);
	}

	/**
	 * Wrapper para o execute script async do driver
	 * 
	 * @param script
	 * @param args
	 * @return objeto de retorno do script se tiver algum
	 */
	private Object executeAsyncScript(String script, Object... args) {
		return driverVO.getJavascriptExecutor().executeAsyncScript(script, args);
	}

	public Object executeAsyncScript(String script, Duration timeoutInSeconds, Object... args) {
		this.alterarScriptTimeout(timeoutInSeconds);
		Object retorno;
		try {
			retorno = executeAsyncScript(script, args);
		} catch (Exception e) {
			this.alterarScriptTimeoutParaPadrao();
			throw e;
		}
		return retorno;
	}

	public void waitSeconds(int seconds) {
		try {
			Thread.sleep(Duration.ofSeconds(seconds).toMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public WebDriver getWebDriver() {
		return driverVO.getWebDriver();
	}

	public JavascriptExecutor getJavascriptExecutor() {
		return driverVO.getJavascriptExecutor();
	}

	public WebDriverWait getWebDriverWait() {
		return driverVO.getWebDriverWait();
	}

	public void setDriverVO(DriverVO driverVO) {
		this.driverVO = driverVO;
	}

	public DriverVO getDriverVO() {
		return this.driverVO;
	}

	public void destroyDriverVO() {
		this.driverVO.destroyDriverVO();
	}

	/**
	 * Troca para uma janela numeroJanela sendo que e feita uma verificacao por um
	 * numero total de janelas. Lembrando que a primeira janela comeca com 0 (zero)
	 * 
	 * @param numeroJanela   numero da janela para trocar
	 * @param totalDeJanelas total de janelas que e esperado que tenha
	 */
	public void alterarParaJanela(int numeroJanela, int totalDeJanelas) {
		getWebDriverWait().until(ExpectedConditions.numberOfWindowsToBe(totalDeJanelas));
		List<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		getWebDriver().switchTo().window(tabs.get(numeroJanela));
	}

	public void tirarScreenshot(String fileName) {
		this.driverVO.tirarScreenshot(fileName);
	}

	public Object customWaitUntil(long timeout, long pollingEvery, ExpectedCondition<?> condition) throws Exception {
		return new FluentWait<>(this.getWebDriver()).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingEvery)).until(condition);
	}

	public void alterarScriptTimeout(Duration seconds) {
		this.getDriverVO().getWebDriver().manage().timeouts().setScriptTimeout(seconds.getSeconds(), TimeUnit.SECONDS);
	}

	public void alterarScriptTimeoutParaPadrao() {
		this.getDriverVO().getWebDriver().manage().timeouts().setScriptTimeout(Constantes.SCRIPT_TIMEOUT.getSeconds(),
				TimeUnit.SECONDS);
	}

	public void waitUntilJqueryIsDone(int timeoutInSeconds) {
		this.waitUntil((driver) -> {
			Boolean isJqueryCallDone = (Boolean) executeScript("return jQuery.active===0");
			if (!isJqueryCallDone) {
				LOGGER.info("JQuery call is in Progress");
			}
			return isJqueryCallDone;
		}, timeoutInSeconds);
	}

	public void waitUntilPageLoadComplete(int timeoutInSeconds) {
		this.waitUntil((driver) -> {
			Boolean isPageLoaded = (Boolean) executeScript("return document.readyState").equals("complete");
			//if (!isPageLoaded) {
				LOGGER.info("Document is loading");
			//}
			return isPageLoaded;
		}, timeoutInSeconds);
	}

}
