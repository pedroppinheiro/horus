package br.bbts.horus.rbac;

import static org.junit.Assert.assertTrue;

import br.bbts.horus.driver.DriverFactory;
import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.HomePage;
import br.bbts.horus.paginas.LoginPage;
import br.bbts.horus.paginas.base.PsimPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.Pt;

public class LoginLogoffSteps implements Pt {
	
	DriverVO driverVO;
	
	LoginPage loginPage;
	HomePage homePage;
	PsimPage pagina;
	
	@Before
    public void beforeScenario() {
		driverVO = DriverFactory.createDriverVO();
    }
	
	public LoginLogoffSteps() {

		Dado("^a pagina de login do PSIM$", () -> {
			loginPage = new LoginPage(driverVO);
	    });
		
		Quando("^eu entro com minhas credenciais \"([^\"]*)\" e \"([^\"]*)\"$", (String usuario, String senha) -> {
			pagina = loginPage.login(usuario, senha);
	    });
		
		Entao("^sou direcionado para a home page$", () -> {
			assertTrue(pagina instanceof HomePage);
			homePage = (HomePage) pagina;
	    });
	}

	@After
    public void afterScenario() {
		loginPage.destroyDriverVO();
    }
}
