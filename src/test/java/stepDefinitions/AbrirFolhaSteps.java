package stepDefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;

import cucumber.ScenarioContext;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pageObjects.golPageObjects.paginas.AbrirGeracaoFolhaPage;
import pageObjects.golPageObjects.paginas.TabelasGeracaoFolhaPage;

public class AbrirFolhaSteps {
	String primeiraGeracaoFolhaNome;
	
	private ScenarioContext context;
	
	
	public AbrirFolhaSteps(ScenarioContext context) {
        this.context = context;
    }

	@Dado("existem gerações fechadas")
	public void existem_geracoes_fechadas() {
		assertThat("Deve haver gerações abertas",
					((AbrirGeracaoFolhaPage) context.paginaAtual).getQuantidadeGeracoesFechadas(),
	               greaterThan(1));
	}

	@Quando("abro a geracao de folha mais atual")
	public void eu_seleciono_a_geração_de_folha_fechada_mais_atual() throws Exception {
		primeiraGeracaoFolhaNome = ((AbrirGeracaoFolhaPage) context.paginaAtual).selecionarPrimeiraGeracao();
		((AbrirGeracaoFolhaPage) context.paginaAtual).abrirGeracaoFolha();
		((AbrirGeracaoFolhaPage) context.paginaAtual).clicarOk();
	}

	@Quando("filtro a tabela pela geração")
	public void filtro_a_tabela_pela_geração() throws Exception {
		((TabelasGeracaoFolhaPage) context.paginaAtual).filtrarTabelaGeracaoFolha(primeiraGeracaoFolhaNome);
	}

	@Entao("encontro a geracao pesquisada")
	public void encontro_a_geracao_pesquisada() {
		assertEquals("Quantidade de registros deve ser limita a 1", 1, ((TabelasGeracaoFolhaPage) context.paginaAtual).obterQuantidadeDeRegistros());
	}
}
