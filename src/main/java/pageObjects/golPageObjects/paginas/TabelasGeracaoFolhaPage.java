package pageObjects.golPageObjects.paginas;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.golPageObjects.GOLPage;
import pageObjects.golPageObjects.componentes.GolTableComponent;
import valueObjects.DriverVO;

public class TabelasGeracaoFolhaPage extends GOLPage {

	GolTableComponent tabela;
	
	@FindBy(id = "campo_formulario_filtrarColunasGeracao")
	public WebElement inputFiltrarGeracaoFolha;
	
	public TabelasGeracaoFolhaPage(DriverVO driverVO) throws Exception {
		super(driverVO);
		tabela = new GolTableComponent(driverVO);
	}

	public void filtrarTabelaGeracaoFolha(String primeiraGeracaoFolhaNome) throws Exception {
		inputFiltrarGeracaoFolha.sendKeys(primeiraGeracaoFolhaNome);
		inputFiltrarGeracaoFolha.sendKeys(Keys.TAB);
		//waitUntil(ExpectedConditions.elementToBeClickable(By.cssSelector("table.gol-tab1.table.table-bordered.table-hover")));
		aguardarProcessando();
	}

	public Object obterQuantidadeDeRegistros() {
		return tabela.getQuantidadeLinhas();
	}
	
}
