package br.gol.horus.apoio.suporte;

import static org.junit.Assert.assertFalse;

import br.gol.horus.constantes.Constantes;
import br.gol.horus.driver.DriverFactory;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.paginas.LoginPage;
import br.gol.horus.elementos.gol.paginas.SalvarRecuperarPage;
import br.gol.horus.helpers.DatabaseManager;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java8.Pt;

public class RecuperarTabelasSteps implements Pt {
	
	DriverVO driverVO;
	
	LoginPage loginPage;
	SalvarRecuperarPage salvarRecuperarPage;
	
	@Before
    public void beforeScenario() {
		DatabaseManager db = DatabaseManager.createDatabaseManager();
		db.execute(
				String.format("truncate table %s.%s.TAB_CNT_4MRelConteudo", Constantes.DATABASE_NAME, Constantes.DATABASE_SCHEMA)
				);
		driverVO = DriverFactory.createDriverVO();
		
    }
	
	public RecuperarTabelasSteps() {

		Dada("a pagina de {string}", (String string) -> {
			loginPage = new LoginPage(driverVO);
			salvarRecuperarPage = (SalvarRecuperarPage) loginPage.acessarPagina(string);
		});

		Quando("eu preencho o limite de registros para {string}", (String limiteDeRegistros) -> {
			salvarRecuperarPage.informarLimiteDeRegistros(limiteDeRegistros);
		});
		
		Quando("clico no botão de Recuperar Tabelas Projeto", () -> {
			salvarRecuperarPage.recuperarTabelasProjeto();
		});

		Quando("clico em Ok no popup de confirmação", () -> {
			salvarRecuperarPage.confirmarExecucao();
		});

		Entao("nenhuma mensagem de erro é exibida", () -> {
			assertFalse("A mensagem de erro não deve ser exibida", salvarRecuperarPage.isMensagemErroExibida());
		});
	}
	
	@After
    public void afterScenario(Scenario scenario) {
		if(scenario.isFailed()) {
			if(loginPage != null) {
				loginPage.mostrarDetalhesModalErro();
			}
			this.driverVO.tirarScreenshot(scenario.getName());
		}
		
		this.driverVO.destroyDriverVO();
    }

	
}
