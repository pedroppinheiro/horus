package br.horus.paginas;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.horus.constantes.Constantes;
import br.horus.driver.DriverVO;
import br.horus.paginas.base.BasePage;

public class LoginPage extends BasePage {

	@FindBy(id = "usr") WebElement usuario;
	@FindBy(id = "pwd") WebElement senha;
	@FindBy(css = "#case_login > form > input[type=submit]:nth-child(5)") WebElement loginBtn;
	
	@FindBy(css = "#case_login > .error") WebElement errorMsg;
	
	public LoginPage(DriverVO driverVO) {
		super(driverVO);	
	}
	
	public void acessarLogin() {
		acessarURL(Constantes.BASE_URL);
		waitUntil(ExpectedConditions.visibilityOf(usuario));
	}
	
	public BasePage login() {
		return this.login(Constantes.DEFAULT_USER_LOGIN, Constantes.DEFAULT_PASSWORD);
	}
	
	public BasePage login(String usuario, String senha) {
		this.usuario.sendKeys(usuario);
		this.senha.sendKeys(senha);
		this.loginBtn.click();

		HomePage homePage;
		try {
			homePage = new HomePage(getDriverVO());
		} catch(TimeoutException te) {
			return this;
		}
		return homePage;
	}
	
	@Override
	public BasePage acessarPagina(DominioPaginas dominioPagina) {
		HomePage homePage = (HomePage) this.login();
		return homePage.acessarPagina(dominioPagina);
	}

	public String getMensagemErro() {
		waitUntil(ExpectedConditions.visibilityOf(errorMsg));
		return errorMsg.getText();
	}
	
}
