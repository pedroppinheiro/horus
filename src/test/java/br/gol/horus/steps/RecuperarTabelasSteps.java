package br.gol.horus.steps;

import static org.junit.Assert.assertFalse;

import br.gol.horus.constantes.Constantes;
import br.gol.horus.driver.DriverFactory;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.paginas.HomePage;
import br.gol.horus.elementos.gol.paginas.LoginPage;
import br.gol.horus.elementos.gol.paginas.SalvarRecuperarPage;
import br.gol.horus.helpers.DatabaseManager;
import br.gol.horus.steps.ScenarioContext;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class RecuperarTabelasSteps {
	
	ScenarioContext context;
	SalvarRecuperarPage salvarRecuperarPage;
	
	 public RecuperarTabelasSteps(ScenarioContext context) {
        this.context = context;
        DatabaseManager db = DatabaseManager.createDatabaseManager();
		db.execute(
				String.format("truncate table %s.%s.TAB_CNT_4MRelConteudo", Constantes.DATABASE_NAME, Constantes.DATABASE_SCHEMA)
				);
		salvarRecuperarPage = (SalvarRecuperarPage) context.paginaAtual;
    }

	@Quando("eu preencho o limite de registros para {string}")
	public void eu_preencho_o_limite_de_registros_para(String limiteDeRegistros) throws Exception {
		salvarRecuperarPage.informarLimiteDeRegistros(limiteDeRegistros);
	}

	@Quando("recupero tabelas projeto")
	public void clico_no_botão_de_Recuperar_Tabelas_Projeto() throws Exception {
		salvarRecuperarPage.recuperarTabelasProjeto();
		salvarRecuperarPage.confirmarExecucao();
	}

	@Entao("nenhuma mensagem de erro é exibida")
	public void nenhuma_mensagem_de_erro_é_exibida() {
		assertFalse("A mensagem de erro não deve ser exibida", salvarRecuperarPage.isMensagemErroExibida());
	}
	
}
