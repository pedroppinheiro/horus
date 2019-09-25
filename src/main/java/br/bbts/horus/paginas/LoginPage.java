package br.bbts.horus.paginas;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.bbts.horus.constantes.Constantes;
import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.base.PsimPage;

public class LoginPage extends PsimPage {

	@FindBy(id = "usuario") WebElement usuario;
	@FindBy(id = "senha") WebElement senha;
	@FindBy(id = "btnEntrar") WebElement btnEntrar;
	@FindBy(className = "ui-messages-error-detail") WebElement errorMessageDetail;
	
	public LoginPage(DriverVO driverVO) {
		super(driverVO);
		acessarURL(Constantes.LOGIN_URL);
		waitUntil(ExpectedConditions.visibilityOf(usuario));
	}

	public PsimPage login(String usuario, String senha) {
		this.usuario.sendKeys(usuario);
		this.senha.sendKeys(senha);
		this.btnEntrar.click();

		HomePage homePage;
		try {
			homePage = new HomePage(getDriverVO());
		} catch(TimeoutException te) {
			return this;
		}
		return homePage;
	}
	
	public PsimPage login() {
		return this.login(Constantes.DEFAULT_USER_LOGIN, Constantes.DEFAULT_PASSWORD);
	}
	
	public String getErrorMessageDetail() {
		return errorMessageDetail.getText();
	}
	
	@Override
	public PsimPage acessarPagina(DominioPaginas dominioPagina) {
		HomePage homePage = (HomePage) this.login();
		return homePage.acessarPagina(dominioPagina);
	}
}
