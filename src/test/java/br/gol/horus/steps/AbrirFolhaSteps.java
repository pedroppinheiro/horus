package br.gol.horus.steps;

import static org.junit.Assert.assertEquals;

import br.gol.horus.driver.DriverFactory;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.paginas.AbrirGeracaoFolhaPage;
import br.gol.horus.elementos.gol.paginas.HomePage;
import br.gol.horus.elementos.gol.paginas.LoginPage;
import br.gol.horus.elementos.gol.paginas.TabelasGeracaoFolhaPage;
import br.gol.horus.steps.ScenarioContext;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
