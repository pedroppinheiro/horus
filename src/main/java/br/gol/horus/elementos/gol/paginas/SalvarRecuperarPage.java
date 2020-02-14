package br.gol.horus.elementos.gol.paginas;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Stopwatch;

import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.GOLPage;
import br.gol.horus.elementos.gol.componentes.ModalVgolMensagemComponente;

public class SalvarRecuperarPage extends GOLPage {
	
	@FindBy(id = "campo_formulario_maxCountRegs") public WebElement campoLimiteRegistros;
	@FindBy(css = "button[name=salvarBasico]") public WebElement botaoRecuperarTabelasProjeto;
	//@FindBy(id = "modalVgolMensagem") public WebElement modalVgolMensagem;
	//@FindBy(css = "div#modalVgolMensagem button.btn.btn-sm.blue") public WebElement botaoOkModalVgolMensagem;
	
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
		new ModalVgolMensagemComponente(driverVO).confirmar();
		Stopwatch timer = Stopwatch.createStarted();
		this.aguardarProcessando(false, Duration.ofSeconds(35));
		LOGGER.info("Recuperar tabelas finalizou. Tempo de execução de " + timer.stop());
	}

	
}
