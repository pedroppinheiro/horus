package br.bbts.horus.paginas.base;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.DominioPaginas;
import br.bbts.horus.paginas.LoginPage;

public abstract class PsimPage extends PageObjectBase {
	
	private static final Logger LOGGER = Logger.getLogger(PsimPage.class.getName());
	
	@FindBy(id = "linkSair") public WebElement linkSair;
	@FindBy(id = "sim") public WebElement simBtn;
	@FindBy(id = "modalAguarde") public WebElement modalAguarde;
	
	public PsimPage(DriverVO driverVO) {
		super(driverVO);
	}

	public LoginPage logoff(DriverVO driverVO) {
		linkSair.click();
		simBtn.click();
		return new LoginPage(driverVO);
	}
	
	public String getURL() {
		return getWebDriver().getCurrentUrl();
	}
	
	/**
	 * Disponibiliza o select tradicional para que o selenium consiga interagir corretamente com o elemento
	 */
	public void habilitarSelects() {
		waitAndExecuteScript(1000, "$('select').closest('div').removeClass('ui-helper-hidden-accessible')");
	}
	
	public void acessarURL(String url) {
		getWebDriver().get(url);
	}
	
	public PsimPage acessarPagina(DominioPaginas dominioPagina) {
		LOGGER.log(Level.FINE, "Tentando acessar a página {0}", dominioPagina);
		try {
			executeScript("var temp = document.getElementById('" + dominioPagina + "') || document.querySelector('." + dominioPagina + "'); temp.click();");
			waitUntil(ExpectedConditions.invisibilityOf(modalAguarde));
		} catch(JavascriptException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, "Não foi possível acessar a página por id ou classe: {0}", dominioPagina);
			return null;
		}
		
		LOGGER.log(Level.FINE, "Página carregada");
		return dominioPagina.getPagina(getDriverVO());
	}
}
