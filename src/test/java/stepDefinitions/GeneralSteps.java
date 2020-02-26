package stepDefinitions;

import java.util.logging.Logger;

import cucumber.ScenarioContext;
import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.Quando;
import pageObjects.golPageObjects.GOLPage;
import pageObjects.golPageObjects.paginas.LoginPage;

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
	    context.paginaAtual = new LoginPage(context.driverVO);
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
			if(context.paginaAtual != null) {
				context.paginaAtual.mostrarDetalhesModalErro();
			}
			context.driverVO.tirarScreenshot(scenario.getName());
		}
		
		context.driverVO.destroyDriverVO();
    }
}
