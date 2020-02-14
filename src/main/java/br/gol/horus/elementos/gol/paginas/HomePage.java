package br.gol.horus.elementos.gol.paginas;

import java.time.Duration;
import java.util.logging.Level;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.GOLPage;

public class HomePage extends GOLPage {

	@FindBy(className = "page-breadcrumb") public WebElement breadcrumbs;
	@FindBy(css = "h4.page-title") public WebElement nomeCliente;
	
	public HomePage(DriverVO driverVO) throws Exception {
		super(driverVO);
	}
	
	public HomePage(DriverVO driverVO, Duration timeoutComecarProcessar, Duration timeoutFinalizarProcessar)
			throws Exception {
		super(driverVO, timeoutComecarProcessar, timeoutFinalizarProcessar);
	}

	public HomePage(DriverVO driverVO, boolean shouldIgnoreExceptions, Duration timeoutComecarProcessar, Duration timeoutFinalizarProcessar) throws Exception {
		super(driverVO, shouldIgnoreExceptions, timeoutComecarProcessar, timeoutFinalizarProcessar);
	}

	public String getNomeCliente() {
		try {
			return this.nomeCliente.getText().trim();
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, "Falha ao obter o nome do cliente na página", e);
			return "";
		}
	}
}
