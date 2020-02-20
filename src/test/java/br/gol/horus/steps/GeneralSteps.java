package br.gol.horus.steps;

import java.util.logging.Logger;

import br.gol.horus.constantes.Constantes;
import br.gol.horus.driver.DriverFactory;
import br.gol.horus.elementos.gol.GOLPage;
import br.gol.horus.elementos.gol.paginas.AbrirGeracaoFolhaPage;
import br.gol.horus.elementos.gol.paginas.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;

public class GeneralSteps {

	private ScenarioContext context;
	public static final Logger LOGGER = Logger.getLogger(GOLPage.class.getName());
	
	public GeneralSteps(ScenarioContext context) {
        this.context = context;
    }
	
	@Before
    public void beforeScenario(Scenario scenario) throws Exception {
		LOGGER.info("------------------------------");
		LOGGER.info("Starting - " + scenario.getName());
		LOGGER.info("------------------------------");
	    
		context.driverVO = DriverFactory.createDriverVO();
	    context.loginPage = new LoginPage(context.driverVO);
	    context.paginaAtual = context.loginPage;
    }
	
	@Dada("a pagina de {string}")
	@Dada("a pagina {string}")
	@Quando("acesso a pagina {string}")
	@Quando("acesso a pagina de {string}")
	@Quando("eu acesso a pagina {string}")
	@Quando("eu acesso a pagina de {string}")
	public void acessarPagina(String pagina) throws Exception {
		context.paginaAtual = context.paginaAtual.acessarPagina(pagina);
	}
	
	@After
    public void afterScenario(Scenario scenario) {
		LOGGER.info("------------------------------");
		LOGGER.info(scenario.getName() + " Status - " + scenario.getStatus());
		LOGGER.info("------------------------------");
		if(scenario.isFailed()) {
			if(context.loginPage != null) {
				context.loginPage.mostrarDetalhesModalErro();
			}
			context.driverVO.tirarScreenshot(scenario.getName());
		}
		
		context.driverVO.destroyDriverVO();
    }
}
