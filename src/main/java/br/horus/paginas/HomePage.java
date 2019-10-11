package br.horus.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.horus.driver.DriverVO;
import br.horus.paginas.base.BasePage;

public class HomePage extends BasePage {
	
	@FindBy(css = "#case_login > .success") WebElement successMsg;
	@FindBy(css = "#case_login > a") WebElement goBackBtn;
	
	protected HomePage(DriverVO driverVO) {
		super(driverVO);
		waitUntil(ExpectedConditions.visibilityOf(successMsg));
	}
	
	public String getMensagemSucesso() {
		return successMsg.getText();
	}

	public LoginPage clicarBotaoRetornar() {
		goBackBtn.click();
		LoginPage loginPage = new LoginPage(this.getDriverVO());
		return loginPage;
	}

}
