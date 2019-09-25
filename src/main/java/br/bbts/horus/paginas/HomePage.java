package br.bbts.horus.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.base.PsimPage;

public class HomePage extends PsimPage {
	
	@FindBy(id = "material-menu") WebElement menu;

	protected HomePage(DriverVO driverVO) {
		super(driverVO);
		waitUntil(ExpectedConditions.visibilityOf(menu));
	}
	
//	public PageObject acessarPagina() {
//		getWebDriver().get(Constantes.HOME_URL);
//		return this;
//	}

}
