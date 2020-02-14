package br.gol.horus.elementos.gol.componentes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.base.PageObjectBase;
import br.gol.horus.elementos.gol.GOLComponent;

public class ModalVgolMensagemComponente extends GOLComponent {
	
	@FindBy(id = "modalVgolMensagem") public WebElement modalVgolMensagem;
	@FindBy(css = "div#modalVgolMensagem button.btn.btn-sm.blue") public WebElement botaoOkModalVgolMensagem;
	@FindBy(css = "div#modalVgolMensagem button.btn.btn-sm.default") public WebElement botaoCancelarModalVgolMensagem;

	public ModalVgolMensagemComponente(DriverVO driverVO) throws Exception {
		super(driverVO);
	}
	
	public void confirmar() {
		waitUntil(ExpectedConditions.visibilityOf(modalVgolMensagem));
		botaoOkModalVgolMensagem.click();
	}
	
	public void cancelar() {
		waitUntil(ExpectedConditions.visibilityOf(modalVgolMensagem));
		botaoCancelarModalVgolMensagem.click();
	}

}
