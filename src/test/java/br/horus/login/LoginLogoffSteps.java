package br.horus.login;

import static org.junit.Assert.*;

import br.horus.constantes.Constantes;
//import br.horus.database.Database;
import br.horus.driver.DriverFactory;
import br.horus.driver.DriverVO;
import br.horus.paginas.HomePage;
import br.horus.paginas.LoginPage;
import br.horus.paginas.base.BasePage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.Pt;

public class LoginLogoffSteps implements Pt {
	
	DriverVO driverVO;
	
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	
	BasePage tempPage;
	
	@Before
    public void beforeScenario() {
		driverVO = DriverFactory.createDriverVO();
    }
	
	public LoginLogoffSteps() {

		Dado("^a pagina de login$", () -> {
			loginPage = new LoginPage(driverVO);
			loginPage.acessarLogin();
	    });
		
		Quando("^eu entro com minhas credenciais \"([^\"]*)\" e \"([^\"]*)\"$", (String usuario, String senha) -> {
			tempPage = loginPage.login(usuario, senha);
	    });
		
		Entao("^sou direcionado para a home page$", () -> {
			assertTrue(tempPage instanceof HomePage);
			homePage = (HomePage) tempPage;
	    });
		
		Entao("e apresentada a mensagem de sucesso {string}", (String string) -> {
			assertEquals(string, homePage.getMensagemSucesso());
		});
		
		Entao("e apresentada a mensagem de erro {string}", (String string) -> {
			assertEquals(string, loginPage.getMensagemErro());
		});
		
		Quando("eu clico no botao de retornar", () -> {
		    loginPage = homePage.clicarBotaoRetornar();
		});

		Entao("sou redirecionado a pagina inicial", () -> {
		    assertEquals(Constantes.BASE_URL, loginPage.getURL());
		});
	}

	@After
    public void afterScenario(Scenario scenario) {
		//Database database = Database.newConnection();
		//database.executeUpdate(""); // maybe do some cleanup
		
		if(scenario.isFailed()) {
			try {
				loginPage.tirarScreenshot(scenario.getName());
			} catch (Exception e) {
				System.out.println("Não foi possível tirar uma screenshot do erro");
				e.printStackTrace();
			}
		}
		loginPage.destroyDriverVO();
    }
	
}




