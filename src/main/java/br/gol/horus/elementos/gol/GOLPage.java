package br.gol.horus.elementos.gol;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.gol.horus.constantes.Constantes;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.base.PageObjectBase;
import br.gol.horus.elementos.gol.paginas.LoginPage;
import br.gol.horus.helpers.ClientesEnum;
import br.gol.horus.helpers.DominioPaginas;

/**
 * Classe que guarda comportamentos e objetos que são específicas para páginas GOL
 * @author pedro
 *
 */
public abstract class GOLPage extends PageObjectBase {
	
	@FindBy(id = "link-painel-usuario")
	public WebElement lupaPesquisar;
	
	@FindBy(id = "pesquisar-transacao")
	//@FindBy(css = "div.row.menu-usuario span.twitter-typeahead")
	public WebElement pesquisarTransacao;
	
	@FindBy(css = "div.modal.fade.modal-erro.in")
	public WebElement mensagemErro;
	@FindBy(id = "mostrarDetalhes")
	public WebElement mostrarDetalhesErroBtn;
	@FindBy(className = "msg-detalhes")
	public WebElement detalhesErro;

	public GOLPage(DriverVO driverVO) throws Exception {
		this(driverVO, null);
	}

	public GOLPage(DriverVO driverVO, Duration aguardarProcessandoTimeout) throws Exception {
		super(driverVO);

		aguardarProcessando(aguardarProcessandoTimeout);
	}
	
	public GOLPage(DriverVO driverVO, Duration timeoutComecarProcessar, Duration timeoutFinalizarProcessar) throws Exception {
		super(driverVO);
		
		aguardarProcessando(timeoutComecarProcessar, timeoutFinalizarProcessar);
	}

	public GOLPage(DriverVO driverVO, boolean shouldIgnoreExceptions, Duration timeoutComecarProcessar,
			Duration timeoutFinalizarProcessar) throws Exception {
		super(driverVO);
		aguardarProcessando(shouldIgnoreExceptions, timeoutComecarProcessar, timeoutFinalizarProcessar);
	}

	public String getURL() {
		return getWebDriver().getCurrentUrl();
	}

	public void acessarURL(String url) {
		getWebDriver().get(url);
	}

	public GOLPage acessarPagina(String pagina) throws Exception {
		return acessarPagina(DominioPaginas.getDominioPaginasByNome(pagina));
	}

	public GOLPage acessarPagina(DominioPaginas pagina) throws Exception {
		LOGGER.info("Tentando acessar a página " + pagina.getNome());
		lupaPesquisar.click();
		
		limparPesquisa();
		
		pesquisarTransacao.sendKeys(pagina.getNome());

		this.waitUntil(ExpectedConditions.visibilityOfElementLocated(By.className("tt-suggestion")));
		pesquisarTransacao.sendKeys(Keys.DOWN);
		pesquisarTransacao.sendKeys(Keys.ENTER);

		return (GOLPage) pagina.getPaginaClass().getDeclaredConstructor(DriverVO.class).newInstance(this.getDriverVO());
	}

	private void limparPesquisa() {
		pesquisarTransacao.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		pesquisarTransacao.sendKeys(Keys.DELETE);
	}

	/**
	 * Aguarda exibição do modal de processando. Por padrão ignora erros caso o modal não seja encontrado.
	 * @throws Exception
	 */
	public void aguardarProcessando() throws Exception {
		aguardarProcessando(true, null, null);
	}

	public void aguardarProcessando(Duration timeoutInSeconds) throws Exception {
		aguardarProcessando(true, timeoutInSeconds, timeoutInSeconds);
	}
	
	public void aguardarProcessando(boolean shouldIgnoreExceptions) throws Exception {
		aguardarProcessando(shouldIgnoreExceptions, null, null);
	}

	public void aguardarProcessando(boolean shouldIgnoreExceptions, Duration timeout) throws Exception {
		aguardarProcessando(shouldIgnoreExceptions, timeout, timeout);
	}

	public void aguardarProcessando(Duration timeoutComecarProcessar, Duration timeoutFinalizarProcessar) throws Exception {
		aguardarProcessando(true, timeoutComecarProcessar, timeoutFinalizarProcessar);
	}
	
	public void aguardarProcessando(boolean shouldIgnoreExceptions, Duration timeoutComecarProcessar, Duration timeoutFinalizarProcessar) throws Exception {
		
		this.waitUntilPageLoadComplete(10); //PAGE_LOAD_TIMEOUT
		
		if(timeoutComecarProcessar != null && timeoutComecarProcessar.isZero()) {
			return;
		}
		
		LOGGER.info("PROCESSANDO : Aguardando processando" + (!shouldIgnoreExceptions ? ". Serão lançadas falhas caso o processando não seja encontrado corretamente" : ""));
		
		try {
			if(processandoApareceu(timeoutComecarProcessar) && processandoConcluiu(timeoutFinalizarProcessar)) {
				LOGGER.info("Processando detectado e finalizado com sucesso!");
			}
		} catch (Exception e) {
			if(!shouldIgnoreExceptions) {
				throw e;
			}
		}
	}

	private boolean processandoApareceu(Duration timeoutInSeconds) throws Exception {
		timeoutInSeconds = timeoutInSeconds == null ? Constantes.TIMEOUT_AGUARDAR_EXIBICAO_PROCESSANDO : timeoutInSeconds;
		
		boolean isProcessandoExibido = false;
		try {
			isProcessandoExibido = (Boolean) this.executeAsyncScript("var callback = arguments[arguments.length - 1];\r\n" + "\r\n"
					+ "var interval = setInterval(function(){ if(processando) {\r\n"
					+ "		clearInterval(interval);\r\n" + "		callback(true);\r\n" + "	}\r\n"
					+ "}, 250);", timeoutInSeconds);
			if(isProcessandoExibido) {
				LOGGER.info("PROCESSANDO: Processando exibido");
			}
			return isProcessandoExibido;
		} catch (org.openqa.selenium.JavascriptException je) {
			LOGGER.log(Level.SEVERE, "Processando era esperado, porém ocorreu erro de JavascriptException. Tentando novamente. Detalhes: " + je.getMessage());
			isProcessandoExibido = processandoApareceu(timeoutInSeconds);
		} catch (org.openqa.selenium.ScriptTimeoutException ste) {
			LOGGER.log(Level.SEVERE, "Processando não foi exibido dentro do tempo esperado. Detalhes: " + ste.getMessage());
			throw new Exception("ScriptTimeoutException: Processando não foi exibido dentro do tempo esperado", ste);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Processando era esperado, porém não iniciou. Detalhes: " + e.getMessage());
			throw new Exception("Processando era esperado, porém não iniciou", e);
		}
		
		if(!isProcessandoExibido) {
			LOGGER.severe("Processando era esperado, porém não foi encontrado no timeout de " + timeoutInSeconds.getSeconds() + " segundos");
		}
		
		return isProcessandoExibido;
	}

	private boolean processandoConcluiu(Duration timeoutInSeconds) throws Exception {
		timeoutInSeconds = timeoutInSeconds == null ? Constantes.TIMEOUT_AGUARDAR_FINALIZACAO_PROCESSANDO : timeoutInSeconds;
		
		boolean isProcessandoConcluido = false;
		try {
			isProcessandoConcluido = (Boolean) this.executeAsyncScript("var callback = arguments[arguments.length - 1];\r\n" + "\r\n"
					+ "var interval = setInterval(function(){ if(!processando) {\r\n"
					+ "		clearInterval(interval);\r\n" + "		callback(true);\r\n" + "	}\r\n" + "}, 250);", timeoutInSeconds);
			if(isProcessandoConcluido) {
				LOGGER.info("PROCESSANDO: Processamento finalizado");
			}
			
		
		} catch (org.openqa.selenium.ScriptTimeoutException ste) {
			LOGGER.log(Level.SEVERE, "Processando não finalizou dentro do tempo esperado. Detalhes: " + ste.getMessage());
			throw new Exception("ScriptTimeoutException: Processando não finalizou dentro do tempo esperado", ste);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Processando não finalizou. Detalhes: " + e.getMessage());
			throw new Exception("Processando não finalizou", e);
		}
		
		if(!isProcessandoConcluido) {
			LOGGER.severe("Processando era esperado finalizar, porém ainda sendo exibido. Timeout: " + timeoutInSeconds.getSeconds() + " segundos");
		}
		
		return isProcessandoConcluido;
	}

	public void refresh() {
		this.getDriverVO().getWebDriver().navigate().refresh();
	}

	public boolean isMensagemErroExibida() {
		try {
			return mensagemErro.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException nsee) {
			LOGGER.info("Não foi possível localizar o modal de erro: " + nsee.getMessage());
			return false;
		}
	}

	public boolean isDetalhesErroExibido() {
		try {
			return detalhesErro.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException nsee) {
			LOGGER.info("Não foi possível localizar o texto de detalhes do erro: " + nsee.getMessage());
			return false;
		}
	}

	public void mostrarDetalhesModalErro() {
		if (isMensagemErroExibida() && !isDetalhesErroExibido()) {
			try {
				mostrarDetalhesErroBtn.click();
			} catch (Exception e) {
				LOGGER.info("Não foi possível encontrar o botão de mostrar detalhes: " + e.getMessage());
			}
		}
	}
	
	public LoginPage logout() throws Exception {
		this.executeScript("logout();");
		return new LoginPage(driverVO);
	}
}
