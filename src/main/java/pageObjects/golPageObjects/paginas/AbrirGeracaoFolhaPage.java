package pageObjects.golPageObjects.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.golPageObjects.GOLPage;
import pageObjects.golPageObjects.componentes.GolTableComponent;
import pageObjects.golPageObjects.componentes.ModalVgolMensagemComponent;
import valueObjects.DriverVO;

public class AbrirGeracaoFolhaPage extends GOLPage {
	
	@FindBy(css = "form[name='tiposfolha'] button.btn.btn-sm.blue") public WebElement abrirFolhaBtn;
	public GolTableComponent tabela;
	
	public AbrirGeracaoFolhaPage(DriverVO driverVO) throws Exception {
		super(driverVO);
		tabela = new GolTableComponent(driverVO);
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
		new ModalVgolMensagemComponent(driverVO).confirmar();
		aguardarProcessando();
	}
	
}
