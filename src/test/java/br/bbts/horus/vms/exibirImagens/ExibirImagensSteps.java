package br.bbts.horus.vms.exibirImagens;

import static org.junit.Assert.*;

import br.bbts.horus.driver.DriverFactory;
import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.DominioPaginas;
import br.bbts.horus.paginas.HomePage;
import br.bbts.horus.paginas.LoginPage;
import br.bbts.horus.paginas.VmsPage;
import br.bbts.horus.paginas.base.PsimPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.Pt;

public class ExibirImagensSteps implements Pt {
	
	DriverVO driverVO;
	
	LoginPage loginPage;
	HomePage homePage;
	PsimPage pagina;
	VmsPage vmsPage;
	
	@Before
    public void beforeScenario() {
		driverVO = DriverFactory.createDriverVO();
    }
	
	public ExibirImagensSteps() {
		
		Dada("a pagina de VMS do PSIM", () -> {
			vmsPage = (VmsPage) new LoginPage(driverVO).acessarPagina(DominioPaginas.VMS_PAGE);
		});
		
		Quando("eu seleciono a dependencia {string}", (String dependencia) -> {
		    vmsPage.selecionarDependencia(dependencia);
		});

		Quando("clico na dependencia {string} na arvore", (String dependencia) -> {
		    vmsPage.clicarNaDependenciaRaizDaArvore();
		});

		Entao("o mosaico deve apresentar todas as cameras da dependencia", () -> {
			assertTrue("Mosaíco não está apresentando câmeras", vmsPage.isVideosCarregados());
		});
		
	}

	@After
    public void afterScenario(Scenario scenario) {
		if(scenario.isFailed()) {
			try {
				vmsPage.tirarScreenshot(scenario.getName());
			} catch (Exception e) {
				System.out.println("Não foi possível tirar uma screenshot do erro");
				e.printStackTrace();
			}
		}
		
		vmsPage.destroyDriverVO();
    }
}
