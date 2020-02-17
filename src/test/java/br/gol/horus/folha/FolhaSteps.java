package br.gol.horus.folha;

import static org.junit.Assert.assertEquals;

import br.gol.horus.driver.DriverFactory;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.paginas.AbrirGeracaoFolhaPage;
import br.gol.horus.elementos.gol.paginas.HomePage;
import br.gol.horus.elementos.gol.paginas.LoginPage;
import br.gol.horus.elementos.gol.paginas.TabelasGeracaoFolhaPage;
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
	
	DriverVO driverVO;
	
	LoginPage loginPage;
	AbrirGeracaoFolhaPage abrirGeracaoFolhaPage;
	TabelasGeracaoFolhaPage tabelasGeracaoFolhaPage;
	
	String primeiraGeracaoFolhaNome;
	
	@Before
    public void beforeScenario() throws Exception {
		driverVO = DriverFactory.createDriverVO();	
		loginPage = new LoginPage(driverVO);
    }
	
	@Dada("a pagina de {string}")
	public void a_pagina_de(String string) throws Exception {
		abrirGeracaoFolhaPage = (AbrirGeracaoFolhaPage) loginPage.acessarPagina(string);
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

	@Quando("clico no botão de Abrir Geração de Folha")
	public void clico_no_botão_de_Abrir_Geração_de_Folha() throws Exception {
		abrirGeracaoFolhaPage.abrirGeracaoFolha();
	}

	@Quando("clico em Ok")
	public void clico_em_Ok() throws Exception {
		abrirGeracaoFolhaPage.clicarOk();	}

	@Quando("acesso a pagina de {string}")
	public void acesso_a_pagina_de(String string) throws Exception {
		tabelasGeracaoFolhaPage = (TabelasGeracaoFolhaPage) abrirGeracaoFolhaPage.acessarPagina(string);
	}

	@Quando("filtro a tabela pela geração")
	public void filtro_a_tabela_pela_geração() throws Exception {
		tabelasGeracaoFolhaPage.filtrarTabelaGeracaoFolha(primeiraGeracaoFolhaNome);
	}

	@Entao("encontro a geracao pesquisada")
	public void encontro_a_geracao_pesquisada() {
		assertEquals("Quantidade de registros deve ser limita a 1", 1, tabelasGeracaoFolhaPage.obterQuantidadeDeRegistros());
	}
	
	/**
	public FolhaSteps() {
		
		Dada("a pagina de {string}", (String string) -> {
			loginPage = new LoginPage(driverVO);
			abrirGeracaoFolhaPage = (AbrirGeracaoFolhaPage) loginPage.acessarPagina(string);
		});
		
		Dado("eu possuo gerações fechadas", () -> {
		    assertThat("Deve haver gerações abertas",
		    		   abrirGeracaoFolhaPage.getQuantidadeGeracoesFechadas(),
		               greaterThan(1));
		});

		Quando("eu seleciono a geração de folha fechada mais atual", () -> {
			primeiraGeracaoFolhaNome = abrirGeracaoFolhaPage.selecionarPrimeiraGeracao();
		});

		Quando("clico no botão de Abrir Geração de Folha", () -> {
			abrirGeracaoFolhaPage.abrirGeracaoFolha();
		});

		Quando("clico em Ok", () -> {
			abrirGeracaoFolhaPage.clicarOk();
		});

		Quando("acesso a pagina de {string}", (String string) -> {
			tabelasGeracaoFolhaPage = (TabelasGeracaoFolhaPage) abrirGeracaoFolhaPage.acessarPagina(string);
		});

		Quando("filtro a tabela pela geração", () -> {
			tabelasGeracaoFolhaPage.filtrarTabelaGeracaoFolha(primeiraGeracaoFolhaNome);
		});

		Entao("encontro a geracao pesquisada", () -> {
			assertEquals("Quantidade de registros deve ser limita a 1", 1, tabelasGeracaoFolhaPage.obterQuantidadeDeRegistros());
		});

	}
	**/
	
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
