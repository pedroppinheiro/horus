package br.bbts.horus.cadastro.mensagem;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import br.bbts.horus.database.Database;
import br.bbts.horus.driver.DriverFactory;
import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.DominioPaginas;
import br.bbts.horus.paginas.LoginPage;
import br.bbts.horus.paginas.MensagemPage;
import br.bbts.horus.paginas.RelatorioDinamicoPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.Pt;


public class ManterMensagemSteps implements Pt {
	WebDriver driver;
	Database database;
	
	MensagemPage mensagemPage;
	
	String nomeMensagem;
	
	private DriverVO driverVO;
	
	@Before
    public void beforeScenario() {
		driverVO = DriverFactory.createDriverVO();
    }
	
	public ManterMensagemSteps() {
		Dada("^a pagina de Mensagem$", () -> {
			mensagemPage = (MensagemPage) new LoginPage(driverVO).acessarPagina(DominioPaginas.MENSAGEM_PAGE);
		});
		
		Quando("^eu clico no botão de incluir$", () -> {
			mensagemPage.incluir();
			mensagemPage.waitUntilFormIsVisible();
		});
		
		Quando("^preencho o formulario para incluir a nova mensagem de nome \"(.*)\"$", (String nomeMensagem) -> {
			this.nomeMensagem = nomeMensagem;
			mensagemPage.preencherFormulario(nomeMensagem);
		});
		
		Quando("^clico em salvar$", () -> {
			mensagemPage.salvar();
		});
		
		Entao("^o sistema deve exibir a mensagem \"([^\"]*)\"$", (String mensagem) -> {
			assertEquals(mensagem, mensagemPage.getMessageInfo());
		});
	}

	@After
    public void afterScenario(){
		driver.quit();
		database = Database.newConnection();
		database.executeUpdate("DELETE FROM MENSAGEM WHERE NOME = '"+this.nomeMensagem+"'");
		database.close();
    }
}
