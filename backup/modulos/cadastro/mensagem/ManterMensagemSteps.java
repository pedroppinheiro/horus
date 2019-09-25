package modulos.cadastro.mensagem;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import br.bbts.horus.database.Database;
import br.bbts.horus.driver.DriverFactory;
import br.bbts.horus.paginas.LoginPage;
import br.bbts.horus.paginas.MensagemPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.Pt;


public class ManterMensagemSteps implements Pt {
	WebDriver driver;
	Database database;
	
	MensagemPage mensagemPage;
	
	String nomeMensagem;
	
	public ManterMensagemSteps() {
		Dada("^a pagina de Mensagem$", () -> {
			mensagemPage = new MensagemPage();
			mensagemPage.acessarPagina();
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
	
	@Before
    public void beforeScenario(){
		new LoginPage().login();
    }

	@After
    public void afterScenario(){
		driver.quit();
		database = Database.newConnection();
		database.executeUpdate("DELETE FROM MENSAGEM WHERE NOME = '"+this.nomeMensagem+"'");
		database.close();
    }
}
