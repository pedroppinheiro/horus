package pageObjects.golPageObjects.componentes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pageObjects.golPageObjects.GOLComponent;
import valueObjects.DriverVO;

public class GolTableComponent extends GOLComponent {
	
	public WebElement tabela;
	
	public GolTableComponent(DriverVO driverVO) throws Exception {
		this(driverVO, By.cssSelector("table.gol-tab1"));
	}
	
	public GolTableComponent(DriverVO driverVO, By localizadorUnico) throws Exception {
		super(driverVO);
		tabela = driverVO.getWebDriver().findElement(localizadorUnico);
	}
	
	public int getQuantidadeLinhas() {
		return tabela.findElements(By.cssSelector("tbody tr:not(.hidden)")).size();
	}
	
	/**
	 * 
	 * @param linha Posição da linha (Começa em zero)
	 * @param nomeColuna Nome da coluna
	 * @return
	 */
	public WebElement getCelula(int linha, String nomeColuna) {
		linha = linha + 2; 
		return tabela.findElement(By.cssSelector("tbody tr:nth-child("+linha+") td[data-nome='"+nomeColuna+"'"));
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
