package pageObjects.golPageObjects.componentes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pageObjects.golPageObjects.GOLComponent;
import valueObjects.DriverVO;

public class ModalVgolMensagemComponent extends GOLComponent {
	
	@FindBy(id = "modalVgolMensagem") public WebElement modalVgolMensagem;
	@FindBy(css = "div#modalVgolMensagem button.btn.btn-sm.blue") public WebElement botaoOkModalVgolMensagem;
	@FindBy(css = "div#modalVgolMensagem button.btn.btn-sm.default") public WebElement botaoCancelarModalVgolMensagem;

	public ModalVgolMensagemComponent(DriverVO driverVO) throws Exception {
		super(driverVO);
	}
	
	public void confirmar() {
		waitUntil(ExpectedConditions.visibilityOf(modalVgolMensagem),5);
		botaoOkModalVgolMensagem.click();
	}
	
	public void cancelar() {
		waitUntil(ExpectedConditions.visibilityOf(modalVgolMensagem));
		botaoCancelarModalVgolMensagem.click();
	}

}
