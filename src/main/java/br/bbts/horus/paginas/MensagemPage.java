package br.bbts.horus.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.base.PsimPage;
import br.bbts.horus.paginas.elementos.PSelect;
import br.bbts.psim.dominio.DominioCategoriaDaMensagem;
import br.bbts.psim.dominio.DominioOrigemMensagem;
import br.bbts.psim.modelo.Mensagem;

public class MensagemPage extends PsimPage {

	private final String submenuId = "menuMensagem";

	@FindBy(id = "botaoAcaoIncluir")
	WebElement botaoAcaoIncluir;
	@FindBy(id = "form-manter")
	WebElement formManter;
	@FindBy(id = "botaoAcaoConfirmarInclusao")
	WebElement btnConfirmaInclusao;
	@FindBy(className = "ui-messages-info-detail")
	WebElement messageInfo;
	@FindBy(className = "ui-messages-error-detail")
	WebElement messageError;
	@FindBy(id = "form-pesquisar")
	WebElement formPesquisar;
	
	@FindBy(id = "nome")
	WebElement campoNome;
	@FindBy(id = "toleranciaTratamento")
	WebElement toleranciaTratamento;
	
	@FindBy(id = "origem")
	WebElement campoOrigem;
	@FindBy(id = "categoria")
	WebElement campoCategoria;
	@FindBy(id = "prioridade")
	WebElement campoPrioridade;
	@FindBy(id = "sistema")
	WebElement campoSistema;
	@FindBy(id = "natureza")
	WebElement campoNatureza;
	@FindBy(id = "qualificador")
	WebElement campoQualificador;
	@FindBy(id = "codigo")
	WebElement campoCodigo;
	
	public MensagemPage(DriverVO driverVO) {
		super(driverVO);
	}

	public MensagemPage acessarPagina(String usuario, String senha) {
//		new HomePage().acessarPagina(usuario, senha);
		return this.acessarPagina();
	}

	public MensagemPage acessarPagina() {
		System.out.println("Navegando para " + submenuId);
		executeScript("document.getElementById('" + submenuId + "').click();");
		waitUntil(ExpectedConditions.invisibilityOf(modalAguarde));
		System.out.println("Página carregada");
		return this;
	}

	public void incluir() {
		botaoAcaoIncluir.click();
	}

	public void waitUntilFormIsVisible() {
		waitUntil(ExpectedConditions.visibilityOf(formManter));
	}

	public void salvar() {
		btnConfirmaInclusao.click();
		waitUntil(ExpectedConditions.visibilityOf(formPesquisar));
	}

	public String getMessageInfo() {
		waitUntil(ExpectedConditions.visibilityOf(messageInfo));
		return messageInfo.getText();
	}

	public String getMessageError() {
		waitUntil(ExpectedConditions.visibilityOf(messageError));
		return messageError.getText();
	}
	
	public void preencherFormulario(String nomeMensagem) {
		
		Mensagem mensagem = new Mensagem();
		mensagem.setOrigem(DominioOrigemMensagem.MANUAL);
		mensagem.setCategoria(DominioCategoriaDaMensagem.EVENTO);
		
		
		campoNome.sendKeys(nomeMensagem);

//		DriverUtil.habilitarSelects();
		new PSelect(campoOrigem).selectByValue("MANUAL");
		
//		DriverUtil.habilitarSelects();
		new PSelect(campoCategoria).selectByValue("EVENTO");
		
//		DriverUtil.habilitarSelects();
		new PSelect(campoPrioridade).selectByValue("MÉDIA");
		
//		DriverUtil.habilitarSelects();
		new PSelect(campoSistema).selectByValue("ATM");
		
//		DriverUtil.habilitarSelects();
		new PSelect(campoNatureza).selectByValue("OUTROS");
		
//		DriverUtil.habilitarSelects();
		new PSelect(campoQualificador).selectByValue("EXECUÇÃO");
		
		toleranciaTratamento.sendKeys("10");
	}

}
