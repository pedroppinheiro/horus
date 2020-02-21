package br.gol.horus.elementos.gol.paginas;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jboss.jandex.ThrowsTypeTarget;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.GOLPage;
import br.gol.horus.helpers.ClientesEnum;
import br.gol.horus.helpers.DominioPaginas;
import br.gol.horus.constantes.Constantes;

public class LoginPage extends GOLPage {

	@FindBy(id = "login") WebElement loginInput;
	@FindBy(id = "senha") WebElement senhaInput;
	@FindBy(id = "btnEntrar") WebElement btnEntrar;
	@FindBy(className = "login-form") WebElement formularioLogin;
	
	private HomePage homePage = null;
	
	public LoginPage(DriverVO driverVO) throws Exception {
		super(driverVO, Duration.ZERO);
		if(!isInLoginPage()) {
			acessarURL(Constantes.SYSTEM_UNDER_TEST_URL);
		}
		waitUntil(ExpectedConditions.visibilityOf(formularioLogin));
	}

	public GOLPage login(String usuario, String senha) throws Exception {
		this.loginInput.sendKeys(usuario);
		this.senhaInput.sendKeys(senha);
		this.btnEntrar.click();
		GOLPage homePage = null;
		try {
			homePage = new HomePage(this.getDriverVO(), Duration.ofSeconds(7), Duration.ofSeconds(10));
		} catch(TimeoutException te) {
			te.printStackTrace();
			return this;
		}
		return homePage;
	}
	
	public GOLPage login() throws Exception {
		return this.login(Constantes.DEFAULT_USER_LOGIN, Constantes.DEFAULT_PASSWORD);
	}
	
	/**
	 * Checa se a página atual está na página de login
	 * @return
	 */
	final String regex = "gol\\/(index\\.jsp)?\\?cliente=";
	final Pattern pattern = Pattern.compile(regex);
	
	public boolean isInLoginPage() {
		
		final Matcher matcher = pattern.matcher(this.driverVO.getWebDriver().getCurrentUrl());
		return matcher.find();
		//System.out.println("CURRENT URL: " + this.driverVO.getWebDriver().getCurrentUrl());
		//return this.driverVO.getWebDriver().getCurrentUrl().matches(regex);
	}
	
	public boolean isFormularioExibido() {
		return this.formularioLogin.isDisplayed();
	}
	
	@Override
	public GOLPage acessarPagina(DominioPaginas pagina) throws Exception {
		if(homePage == null) {
			homePage = (HomePage) login();
		}
		
		return homePage.acessarPagina(pagina);
	}
}
