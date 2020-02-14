package br.gol.horus.folha;

import static org.junit.Assert.assertEquals;

import br.gol.horus.driver.DriverFactory;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.paginas.AbrirGeracaoFolhaPage;
import br.gol.horus.elementos.gol.paginas.LoginPage;
import br.gol.horus.elementos.gol.paginas.TabelasGeracaoFolhaPage;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java8.Pt;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FolhaSteps implements Pt {
	
	DriverVO driverVO;
	
	LoginPage loginPage;
	AbrirGeracaoFolhaPage abrirGeracaoFolhaPage;
	TabelasGeracaoFolhaPage tabelasGeracaoFolhaPage;
	
	String primeiraGeracaoFolhaNome;
	
	@Before
    public void beforeScenario() {
		driverVO = DriverFactory.createDriverVO();
    }
	
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
	
	@After
    public void afterScenario(Scenario scenario) {
		System.out.println("STAAAAAAAAAAATUS: " + scenario.getStatus());
		
		if(scenario.isFailed()) {
			if(loginPage != null) {
				loginPage.mostrarDetalhesModalErro();
			}
			this.driverVO.tirarScreenshot(scenario.getName());
		}
		
		this.driverVO.destroyDriverVO();
    }

}
