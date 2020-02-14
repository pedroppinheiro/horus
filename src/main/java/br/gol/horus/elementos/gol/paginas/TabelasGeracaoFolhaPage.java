package br.gol.horus.elementos.gol.paginas;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.GOLPage;
import br.gol.horus.elementos.gol.componentes.GolTableComponente;

public class TabelasGeracaoFolhaPage extends GOLPage {

//	@FindBy(id = "campo_formulario_filtrarColunasGeracao")
//	public WebElement inputFiltrarGeracaoFolha;
//	
//	@FindBy(css = "table.gol-tab1.table.table-bordered.table-hover tbody tr:not(.hidden)") 
//	private List<WebElement> linhasDaTabelaGeracaoFolha;
//	
//	public TabelasGeracaoFolhaPage(DriverVO driverVO) throws Exception {
//		super(driverVO);
//	}
//
//	public void filtrarTabelaGeracaoFolha(String primeiraGeracaoFolhaNome) throws Exception {
//		inputFiltrarGeracaoFolha.sendKeys(primeiraGeracaoFolhaNome);
//		inputFiltrarGeracaoFolha.sendKeys(Keys.TAB);
//		//waitUntil(ExpectedConditions.elementToBeClickable(By.cssSelector("table.gol-tab1.table.table-bordered.table-hover")));
//		aguardarProcessando();
//	}
//
//	public Object obterQuantidadeDeRegistros() {
//		return linhasDaTabelaGeracaoFolha.size();
//	}

	GolTableComponente tabela;
	
	@FindBy(id = "campo_formulario_filtrarColunasGeracao")
	public WebElement inputFiltrarGeracaoFolha;
	
	public TabelasGeracaoFolhaPage(DriverVO driverVO) throws Exception {
		super(driverVO);
		tabela = new GolTableComponente(driverVO);
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
