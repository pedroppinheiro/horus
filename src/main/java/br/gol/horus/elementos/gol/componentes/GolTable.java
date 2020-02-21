package br.gol.horus.elementos.gol.componentes;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.GOLComponent;

public class GolTable extends GOLComponent {
	
	private List<List<WebElement>> tabela = new ArrayList<List<WebElement>>();
	
	public GolTable(DriverVO driverVO, By localizadorUnico) throws Exception {
		super(driverVO);
		List<WebElement> linhas = driverVO.getWebDriver().findElements(localizadorUnico);
		for (WebElement linha : linhas) {
			
			celulas = linha.findElements(By.cssSelector(""));
			tabela.add(new )
		}
	}
	
	public int getQuantidadeLinhas() {
		return linhas.size();
	}
	
	/**
	 * 
	 * @param linha Posição da linha (Começa em zero)
	 * @param nomeColuna Nome da coluna
	 * @return
	 */
	public WebElement getCelula(int linha, String nomeColuna) {
		linha = linha + 2; 
		return linhas.get(linha).get
	}
	
	/**
	 * 
	 * @param linha Posição da linha (Começa em zero)
	 * @param indexhead Posição da coluna (Começa em zero)
	 * @return
	 */
	public WebElement getCelula(int linha, int indexhead) {
		linha = linha + 2; 
		return tabela.findElement(By.cssSelector("tbody tr:nth-child("+linha+") td[indexhead='"+indexhead+"'"));
	}
	
	public WebElement getCheckbox(int linha, int indexhead) {
		return getCelula(linha, indexhead).findElement(By.cssSelector("div.w3-checkmark"));
	}
	
	public WebElement getBotao(int linha, int indexhead) {
		return getCelula(linha, indexhead).findElement(By.tagName("button"));
	}
}
