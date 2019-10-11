package br.horus.paginas.base;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.horus.driver.DriverVO;
import br.horus.paginas.DominioPaginas;
import br.horus.paginas.LoginPage;

/**
 * Este classe pode ser usada para armazenar métodos que representam ações
 * em comum a toda a aplicação a ser testada. Todas as classes que representam 
 * páginas ou elementos podem herdar desta classe, 
 * se não deverá herdar da classe PageObjectBase 
 * @author pedro
 *
 */
public abstract class BasePage extends PageObjectBase {
	
	private static final Logger LOGGER = Logger.getLogger(BasePage.class.getName());
	
//	@FindBy(id = "linkSair") public WebElement linkSair;
//	@FindBy(id = "sim") public WebElement simBtn;
//	@FindBy(id = "modalAguarde") public WebElement modalAguarde;
//	
	public BasePage(DriverVO driverVO) {
		super(driverVO);
	}

//	public LoginPage logoff(DriverVO driverVO) {
//		linkSair.click();
//		simBtn.click();
//		return new LoginPage(driverVO);
//	}
	
	public String getURL() {
		return getWebDriver().getCurrentUrl();
	}
	
//	/**
//	 * Disponibiliza o select tradicional para que o selenium consiga interagir corretamente com o elemento
//	 */
//	public void habilitarSelects() {
//		waitAndExecuteScript(1000, "$('select').closest('div').removeClass('ui-helper-hidden-accessible')");
//	}
	
	public void acessarURL(String url) {
		getWebDriver().get(url);
	}
	
	public BasePage acessarPagina(DominioPaginas dominioPagina) {
		LOGGER.log(Level.FINE, "Tentando acessar a página {0}", dominioPagina);
		// lógica para acessar as paginas
		// pode ser colocada aqui se for algo mais genérico 
		// ou pode ser sobrescrita nas subclasses
		LOGGER.log(Level.FINE, "Página carregada");
		return dominioPagina.getPagina(getDriverVO());
	}
}
