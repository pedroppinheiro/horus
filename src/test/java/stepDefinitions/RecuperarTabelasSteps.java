package stepDefinitions;

import static org.junit.Assert.assertFalse;

import config.Constantes;
import cucumber.ScenarioContext;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import managers.DatabaseManager;
import pageObjects.golPageObjects.paginas.SalvarRecuperarPage;

public class RecuperarTabelasSteps {
	
	ScenarioContext context;
	
	 public RecuperarTabelasSteps(ScenarioContext context) {
        this.context = context;
        DatabaseManager db = DatabaseManager.createDatabaseManager();
		db.execute(
				String.format("truncate table %s.%s.TAB_CNT_4MRelConteudo", Constantes.DATABASE_NAME, Constantes.DATABASE_SCHEMA)
				);
    }

	@Quando("eu preencho o limite de registros para {string}")
	public void eu_preencho_o_limite_de_registros_para(String limiteDeRegistros) throws Exception {
		((SalvarRecuperarPage) context.paginaAtual).informarLimiteDeRegistros(limiteDeRegistros);
	}

	@Quando("recupero tabelas projeto")
	public void clico_no_botão_de_Recuperar_Tabelas_Projeto() throws Exception {
		((SalvarRecuperarPage) context.paginaAtual).recuperarTabelasProjeto();
		((SalvarRecuperarPage) context.paginaAtual).confirmarExecucao();
	}

	@Entao("nenhuma mensagem de erro é exibida")
	public void nenhuma_mensagem_de_erro_é_exibida() {
		assertFalse("A mensagem de erro não deve ser exibida", ((SalvarRecuperarPage) context.paginaAtual).isMensagemErroExibida());
	}
	
}
