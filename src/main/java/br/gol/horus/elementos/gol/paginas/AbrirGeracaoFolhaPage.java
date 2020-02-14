package br.gol.horus.elementos.gol.paginas;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.google.common.base.Stopwatch;

import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.GOLPage;
import br.gol.horus.elementos.gol.componentes.GolTableComponente;
import br.gol.horus.elementos.gol.componentes.ModalVgolMensagemComponente;

public class AbrirGeracaoFolhaPage extends GOLPage {
	
//	@FindBy(css = "table.gol-tab1 tr:nth-child(2) div.w3-checkmark") public WebElement primeiraLinhaGeracaoFolhaCheckbox;
//	@FindBy(css = "table.gol-tab1 tr:nth-child(2) td[data-nome='Geração']") public WebElement nomeGeracao;
//	@FindBy(css = "form[name='tiposfolha'] button.btn.btn-sm.blue") public WebElement abrirFolhaBtn;
//	@FindBy(css = "table.gol-tab1 tbody tr:not(.hidden)") 
//	private List<WebElement> linhasDaTabelaAbrirFolha;
//	
//	public AbrirGeracaoFolhaPage(DriverVO driverVO) throws Exception {
//		super(driverVO);
//	}
//	
//	public int getQuantidadeGeracoesFechadas() {
//		return linhasDaTabelaAbrirFolha.size();
//	}
//	
//	public String selecionarPrimeiraGeracao() throws Exception {
//		primeiraLinhaGeracaoFolhaCheckbox.click();
//		aguardarProcessando();
//		return nomeGeracao.getText();
//	}
//
//	public void abrirGeracaoFolha() throws Exception {
//		abrirFolhaBtn.click();
//	}
//
//	public void clicarOk() throws Exception {
//		new ModalVgolMensagemComponente(driverVO).confirmar();
//		aguardarProcessando();
//	}
	
	
	
	
	
	
	
	@FindBy(css = "form[name='tiposfolha'] button.btn.btn-sm.blue") public WebElement abrirFolhaBtn;
	public GolTableComponente tabela;
	
	public AbrirGeracaoFolhaPage(DriverVO driverVO) throws Exception {
		super(driverVO);
		tabela = new GolTableComponente(driverVO);
	}
	
	public int getQuantidadeGeracoesFechadas() {
		return tabela.getQuantidadeLinhas();
	}
	
	public String selecionarPrimeiraGeracao() throws Exception {
		tabela.getCheckbox(0, 0).click();
		aguardarProcessando();
		return tabela.getCelula(0, "Geração").getText();
	}

	public void abrirGeracaoFolha() throws Exception {
		abrirFolhaBtn.click();
	}

	public void clicarOk() throws Exception {
		new ModalVgolMensagemComponente(driverVO).confirmar();
		aguardarProcessando();
	}
	
}
