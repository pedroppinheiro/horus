package br.bbts.horus.notificacao.regras;

import static org.junit.Assert.*;

import br.bbts.horus.driver.DriverFactory;
import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.DominioPaginas;
import br.bbts.horus.paginas.HomePage;
import br.bbts.horus.paginas.LoginPage;
import br.bbts.horus.paginas.NotificacaoRegraPage;
import br.bbts.horus.paginas.base.PsimPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.Pt;

public class ManterNotificacaoRegrasSteps implements Pt {
	
	DriverVO driverVO;
	
	LoginPage loginPage;
	HomePage homePage;
	PsimPage pagina;
	NotificacaoRegraPage notificacaoRegraPage;
	
	@Before
    public void beforeScenario() {
		driverVO = DriverFactory.createDriverVO();
    }
	
	public ManterNotificacaoRegrasSteps() {
		
		Dada("a pagina de Notificacao Regras do PSIM", () -> {
			notificacaoRegraPage = (NotificacaoRegraPage) new LoginPage(driverVO).acessarPagina(DominioPaginas.NOTIFICACAO_REGRA_PAGE);
		});

		Quando("incluo uma nova regra {string} com valores quaisquer", (String regraNotificacaoNome) -> {
			notificacaoRegraPage.incluirRegra(regraNotificacaoNome);
		});

		Quando("pesquiso pelo nome {string}", (String regraNotificacaoNome) -> {
			notificacaoRegraPage.pesquisarPorNome(regraNotificacaoNome);
		});

		Entao("a regra {string} deve aparecer na listagem de regras", (String regraNotificacaoNome) -> {
		    assertTrue(notificacaoRegraPage.isListagemConsta(regraNotificacaoNome));
		});

		Quando("eu renomeio a regra {string} para {string}", (String nomeAntes, String nomeDepois) -> {
			notificacaoRegraPage.editarNome(nomeAntes, nomeDepois);
		});

		Quando("eu excluo a regra {string}", (String regraNotificacaoNome) -> {
			notificacaoRegraPage.excluir(regraNotificacaoNome);
		});

		Entao("a regra {string} nao deve aparecer na listagem de regras", (String regraNotificacaoNome) -> {
			assertFalse(notificacaoRegraPage.isListagemConsta(regraNotificacaoNome));
		});
		
	}

	@After
    public void afterScenario() {
		notificacaoRegraPage.destroyDriverVO();
    }
}
