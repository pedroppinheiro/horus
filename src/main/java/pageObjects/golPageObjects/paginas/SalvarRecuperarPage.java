package pageObjects.golPageObjects.paginas;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.base.Stopwatch;

import pageObjects.golPageObjects.GOLPage;
import pageObjects.golPageObjects.componentes.ModalVgolMensagemComponent;
import valueObjects.DriverVO;

public class SalvarRecuperarPage extends GOLPage {
	
	@FindBy(id = "campo_formulario_maxCountRegs") public WebElement campoLimiteRegistros;
	@FindBy(css = "button[name=salvarBasico]") public WebElement botaoRecuperarTabelasProjeto;

	public SalvarRecuperarPage(DriverVO driverVO) throws Exception {
		super(driverVO);
	}

	public void informarLimiteDeRegistros(String limiteDeRegistros) throws Exception {
		campoLimiteRegistros.sendKeys(limiteDeRegistros);
	}

	public void recuperarTabelasProjeto() {
		botaoRecuperarTabelasProjeto.click();
	}

	public void confirmarExecucao() throws Exception {
		new ModalVgolMensagemComponent(driverVO).confirmar();
		Stopwatch timer = Stopwatch.createStarted();
		this.aguardarProcessando(false, Duration.ofSeconds(35));
		LOGGER.info("Recuperar tabelas finalizou. Tempo de execução de " + timer.stop());
	}

	
}
