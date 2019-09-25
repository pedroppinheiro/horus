package br.bbts.horus.vms.manterGrupoPlano;

import static org.junit.Assert.*;

import br.bbts.horus.database.Database;
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

public class ManterGrupoPlanoSteps implements Pt {
	
	DriverVO driverVO;
	
	LoginPage loginPage;
	HomePage homePage;
	VmsPage vmsPage;
	
	String plano = "";
	String planoRenomeado = "";
	
	@Before
    public void beforeScenario() {
		driverVO = DriverFactory.createDriverVO();
    }
	
	public ManterGrupoPlanoSteps() {
		
		Dada("a pagina de VMS do PSIM", () -> {
			vmsPage = (VmsPage) new LoginPage(driverVO).acessarPagina(DominioPaginas.VMS_PAGE);
		});
		
		Dada("eu seleciono um grupo de monitoramento", () -> {
			vmsPage.selecionarGrupo();
		});

		Quando("incluo um novo grupo com nome {string}", (String grupoPlano) -> {
			vmsPage.incluirGrupoPlano(grupoPlano);
		});

		Quando("eu renomeio o grupo de plano {string} para {string}", (String nomeGrupoPlanoAntes, String nomeGrupoPlanoDepois) -> {
			plano = nomeGrupoPlanoAntes;
			planoRenomeado = nomeGrupoPlanoDepois;
			vmsPage.renomearGrupoPlano(nomeGrupoPlanoAntes, nomeGrupoPlanoDepois);
		});

		Quando("eu excluo o grupo de plano {string}", (String grupoPlano) -> {
			vmsPage.excluirGrupoPlano(grupoPlano);
		});

		Entao("o grupo {string} deve aparecer na listagem de grupos de plano", (String grupoPlano) -> {
		    assertEquals(true, vmsPage.isGrupoPlanoNaListagem(grupoPlano));
		});
		
		Entao("o grupo {string} nao deve aparecer na listagem de grupos de plano", (String grupoPlano) -> {
			assertEquals(false, vmsPage.isGrupoPlanoNaListagem(grupoPlano));
		});
		
	}

	@After
    public void afterScenario(Scenario scenario) {
		Database database = Database.newConnection();
		database.executeUpdate("UPDATE GRUPO_PLANO SET SITUACAO = 'DESABILITADO' WHERE NOME = '"+plano+"'");
		database.executeUpdate("UPDATE GRUPO_PLANO SET SITUACAO = 'DESABILITADO' WHERE NOME = '"+planoRenomeado+"'");
		
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
