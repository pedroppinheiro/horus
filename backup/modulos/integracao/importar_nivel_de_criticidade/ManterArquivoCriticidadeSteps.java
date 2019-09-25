package modulos.integracao.importar_nivel_de_criticidade;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.openqa.selenium.WebDriver;

import br.bbts.horus.database.Database;
import br.bbts.horus.driver.DriverFactory;
import br.bbts.horus.paginas.ImportarNivelCriticidadePage;
import br.bbts.horus.paginas.LoginPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.Pt;


public class ManterArquivoCriticidadeSteps implements Pt {
	WebDriver driver;
	Database database;
	private final String fileLocation = "src\\test\\java\\modulos\\integracao\\importar_nivel_de_criticidade\\arquivos_teste\\";
	ImportarNivelCriticidadePage criticidadePage;
	int countElements;
	
	public ManterArquivoCriticidadeSteps() {
		
		Entao("^o sistema deve exibir exibir respectivamente as mensagens de erro \"([^\"]*)\"$", (String mensagem) -> {
			assertEquals(mensagem, criticidadePage.getMessageError());
		});
		Dada("^a pagina de Importar Nível de Criticidade$", () -> {
			criticidadePage = new ImportarNivelCriticidadePage();
			criticidadePage.acessarPagina();
			countElements = criticidadePage.getArquivosCadastrados().size();
		});

		Quando("^eu clico no botão de incluir$", () -> {
			criticidadePage.incluir();
			criticidadePage.waitUntilFormIsVisible();
		});

		Quando("^faço upload do arquivo \"(.*)\"$", (String arquivo) -> {
			File file = new File(fileLocation + arquivo);
			criticidadePage.informarArquivo(file);
		});

		Quando("^clico em salvar$", () -> {
			criticidadePage.salvar();
		});
		
		Entao("^o sistema deve exibir a mensagem \"([^\"]*)\"$", (String mensagem) -> {
			assertEquals(mensagem, criticidadePage.getMessageInfo());
		});

		Entao("^a listagem irá incluir o novo registro incluído$", () -> {
			assertEquals(countElements, criticidadePage.getArquivosCadastrados().size());
		});
		
		Quando("^faço upload dos arquivos inválidos \"([^\"]*)\"$", (String arquivo) -> {
			File file = new File(fileLocation + arquivo);
			criticidadePage.informarArquivo(file);
		});

		
	}
	
	@Before
    public void beforeScenario(){
		new LoginPage().login();
    }

	@After
    public void afterScenario(){
		driver.quit();
    }
	
	@After("@inclusao_criticidade")
	public void cleanUpDatabase() {
		database = Database.newConnection();
		database.executeUpdate("DELETE FROM CRITICIDADE_DETALHE");
		database.executeUpdate("DELETE FROM CRITICIDADE_ARQUIVO");
		database.close();
	}
}
