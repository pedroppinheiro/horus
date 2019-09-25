package br.bbts.horus.paginas;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.base.PsimPage;

public class ImportarNivelCriticidadePage extends PsimPage {
	private static final Logger LOGGER = Logger.getLogger(ImportarNivelCriticidadePage.class.getName());
	private final String submenuId = "menuImportarNivelCriticidade";
	
	@FindBy(css = "#dataTable_data tr") List<WebElement> arquivosCadastrados;
	@FindBy(id = "botaoAcaoIncluir") WebElement botaoAcaoIncluir;
	@FindBy(id = "form-manter") WebElement formManter;
	@FindBy(id = "arquivo_input") WebElement arquivoInput;
	@FindBy(id = "botaoAcaoConfirmarInclusao") WebElement btnConfirmaInclusao;
	@FindBy(className = "ui-messages-info-detail") WebElement messageInfo;
	@FindBy(className = "ui-messages-error-detail") WebElement messageError;
	
	public ImportarNivelCriticidadePage(DriverVO driverVO) {
		super(driverVO);
	}
	
	public ImportarNivelCriticidadePage acessarPagina(String usuario, String senha) {
//		new HomePage().acessarPagina(usuario, senha);
		return this.acessarPagina();
	}

	public ImportarNivelCriticidadePage acessarPagina() {
		LOGGER.log(Level.FINE, "Navegando para {0}",submenuId); 
		executeScript("document.getElementById('" + submenuId + "').click();");
		waitUntil(ExpectedConditions.invisibilityOf(modalAguarde));
		LOGGER.log(Level.FINE, "Página carregada");
		return this;
	}

	public List<WebElement> getArquivosCadastrados() {
		return arquivosCadastrados;
	}

	public void incluir() {
		botaoAcaoIncluir.click();
	}

	public void waitUntilFormIsVisible() {
		waitUntil(ExpectedConditions.visibilityOf(formManter));
	}

	public void informarArquivo(File file) {
		LOGGER.log(Level.FINE, "Caminho do arquivo: {0}", file.getAbsolutePath());
		arquivoInput.sendKeys(file.getAbsolutePath());
	}

	public void salvar() {
		btnConfirmaInclusao.click();
	}

	public String getMessageInfo() {
		waitUntil(ExpectedConditions.visibilityOf(messageInfo));
		return messageInfo.getText();
	}

	public String getMessageError() {
		waitUntil(ExpectedConditions.visibilityOf(messageError));
	    return messageError.getText();
	}

}
