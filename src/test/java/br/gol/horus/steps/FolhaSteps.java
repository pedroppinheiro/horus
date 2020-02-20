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

public class FolhaSteps {
	
	AbrirGeracaoFolhaPage abrirGeracaoFolhaPage;
	TabelasGeracaoFolhaPage tabelasGeracaoFolhaPage;
	
	String primeiraGeracaoFolhaNome;
	
	private ScenarioContext context;
	
	
	public FolhaSteps(ScenarioContext context) {
        this.context = context;
        abrirGeracaoFolhaPage = (AbrirGeracaoFolhaPage) this.context.paginaAtual;
    }

	@Dado("eu possuo gerações fechadas")
	public void eu_possuo_gerações_fechadas() {
		assertThat("Deve haver gerações abertas",
	    		   abrirGeracaoFolhaPage.getQuantidadeGeracoesFechadas(),
	               greaterThan(1));
	}

	@Quando("eu seleciono a geração de folha fechada mais atual")
	public void eu_seleciono_a_geração_de_folha_fechada_mais_atual() throws Exception {
		primeiraGeracaoFolhaNome = abrirGeracaoFolhaPage.selecionarPrimeiraGeracao();
	}

	@Quando("abro a geracao")
	public void clico_no_botão_de_Abrir_Geração_de_Folha() throws Exception {
		abrirGeracaoFolhaPage.abrirGeracaoFolha();
		abrirGeracaoFolhaPage.clicarOk();
	}

	@Quando("filtro a tabela pela geração")
	public void filtro_a_tabela_pela_geração() throws Exception {
		tabelasGeracaoFolhaPage = (TabelasGeracaoFolhaPage) this.context.paginaAtual;
		tabelasGeracaoFolhaPage.filtrarTabelaGeracaoFolha(primeiraGeracaoFolhaNome);
	}

	@Entao("encontro a geracao pesquisada")
	public void encontro_a_geracao_pesquisada() {
		assertEquals("Quantidade de registros deve ser limita a 1", 1, tabelasGeracaoFolhaPage.obterQuantidadeDeRegistros());
	}
}
