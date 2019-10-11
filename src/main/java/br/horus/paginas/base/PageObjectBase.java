package br.horus.paginas.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.horus.constantes.Constantes;
import br.horus.driver.DriverVO;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * Este classe contem metodos que auxiliam em algumas tarefas comuns em testes 
 * @author pedro
 *
 */
public abstract class PageObjectBase implements PageObject {
	
	protected DriverVO driverVO;
	
	public PageObjectBase(DriverVO driverVO) {
		this.driverVO = driverVO;
		PageFactory.initElements(getDriverVO().getAjaxElementLocatorFactory(), this);
	}

	public void waitUntil(ExpectedCondition<?> condition) {
		driverVO.getWebDriverWait().until(condition);
	}
	
	/**
	 * Wrapper para o execute script do driver
	 * @param script
	 * @param args
	 * @return objeto de retorno do script se tiver algum
	 */
	public Object executeScript(String script, Object... args) {
		System.out.println("Executando script: " + script);
		return driverVO.getJavascriptExecutor().executeScript(script, args);
	}
	
	public Object executeScriptAndWait(String script, Object... args) {
		return this.executeAsyncScript(
				"var callback = arguments[arguments.length - 1];" +
				"window.setTimeout(function(){callback("+script+")}, 1000);");
	}
	
	/**
	 * Wrapper para o execute script async do driver
	 * @param script
	 * @param args
	 * @return objeto de retorno do script se tiver algum
	 */
	public Object executeAsyncScript(String script, Object... args) {
		System.out.println("Executando async script: " + script);
		return driverVO.getJavascriptExecutor().executeAsyncScript(script, args);
	}
	
	/**
	 * Espera um tempo e depois executa o script
	 * @param script
	 * @param args
	 * @return objeto de retorno do script se tiver algum
	 */
	public void waitAndExecuteScript(int waitBeforeSeconds, String script) {
		System.out.println("Executando script: " + script);
		waitSeconds(waitBeforeSeconds);
		driverVO.getJavascriptExecutor().executeScript(script);
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
	 * Troca para uma janela numeroJanela sendo que e feita uma verificacao por um numero total de janelas.
	 * Lembrando que a primeira janela comeca com 0 (zero)
	 *  
	 * @param numeroJanela numero da janela para trocar
	 * @param totalDeJanelas total de janelas que e esperado que tenha
	 */
	public void alterarParaJanela(int numeroJanela, int totalDeJanelas) {
		getWebDriverWait().until(ExpectedConditions.numberOfWindowsToBe(totalDeJanelas));
		List<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
		getWebDriver().switchTo().window(tabs.get(numeroJanela));
	}
	
	public void tirarScreenshot(String fileName) throws IOException {
		this.waitSeconds(1);
		fileName = removerCaracteresRuins(fileName);
		String fileNameWithTimestamp = adicionarSufixoTimestamp(fileName);

		getWebDriver().switchTo().defaultContent();
		getWebDriver().manage().window().maximize();
		
		criarArquivoScreenshot(fileNameWithTimestamp);
	}

	private String removerCaracteresRuins(String fileName) {
		return fileName.replaceAll("/", "");
	}
	
	private String adicionarSufixoTimestamp(String fileName) {
		DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss");
		return fileName + " - " + timeStampPattern.format(LocalDateTime.now());
	}
	
	private void criarArquivoScreenshot(String fileName) throws IOException {
		File directory = new File(Constantes.SCREENSHOTS_PATH);
	    if (!directory.exists()){
	        directory.mkdir();
	    }
	    
	    Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(this.getWebDriver()); 
	    ImageIO.write(screenshot.getImage(),"PNG",new File(Constantes.SCREENSHOTS_PATH + File.separator + fileName + ".png"));
	    
	    // old partial page screenshot
//	    File scrFile = ((TakesScreenshot) this.getWebDriver()).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(scrFile, new File(Constantes.SCREENSHOTS_PATH + File.separator + fileName + ".png"));
	}
	
	public Object customWaitUntil(long timeout, long pollingEvery, ExpectedCondition<?> condition) throws Exception {
		return new FluentWait<>(this.getWebDriver())
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingEvery))
				.until(condition);
	}
}
