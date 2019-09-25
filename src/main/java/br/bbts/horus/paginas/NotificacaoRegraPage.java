package br.bbts.horus.paginas;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.base.PsimPage;
import br.bbts.horus.paginas.elementos.PSelect;

public class NotificacaoRegraPage extends PsimPage {

	private final String BREADCRUMBS_NAME = "Regra Notificação";

	@FindBy(className = "system-breadcrumb")
	WebElement breadcrumbs;
	@FindBy(id = "botaoAcaoIncluir")
	WebElement botaoAcaoIncluir;
	@FindBy(id = "btWizard_next")
	WebElement nextBtn;
	
	@FindBy(id = "nome")
	WebElement nomeInput;
	@FindBy(id = "descricao")
	WebElement descricaoInput;
	@FindBy(id = "condicaoDependencia\\\\:0")
	WebElement condicaoDependenciaCheckbox;
	@FindBy(id = "meio\\\\:0")
	WebElement meioDeNotificacaoCheckbox;
	@FindBy(id = "atraso")
	WebElement atrasoInput;
	@FindBy(id = "intermitencia")
	WebElement intermitenciaSelect;
	@FindBy(id = "objetoNotificacao\\\\:0")
	WebElement objetoNotificacaoRadio;
	
	

	public NotificacaoRegraPage(DriverVO driverVO) {
		super(driverVO);
		waitUntil(ExpectedConditions.textToBePresentInElement(breadcrumbs, BREADCRUMBS_NAME));
	}

	public void incluirRegra(String regraNotificacaoNome) {
		botaoAcaoIncluir.click();
		preencherFormularioInformacoesBasicas(regraNotificacaoNome);
		nextBtn.click();
		preencherFormularioDestinatarios();
		preencherFormularioLayous();
		
	}

	public void pesquisarPorNome(String regraNotificacaoNome) {
		// TODO Auto-generated method stub

	}

	public boolean isListagemConsta(String regraNotificacaoNome) {
		// TODO Auto-generated method stub
		return false;
	}

	public void editarNome(String nomeAntes, String nomeDepois) {
		// TODO Auto-generated method stub

	}

	public void excluir(String regraNotificacaoNome) {
		// TODO Auto-generated method stub

	}

	private void preencherFormularioInformacoesBasicas(String regraNotificacaoNome) {
		nomeInput.sendKeys(regraNotificacaoNome);
		descricaoInput.sendKeys(regraNotificacaoNome);
		condicaoDependenciaCheckbox.clear();
		atrasoInput.sendKeys("1");
		new PSelect(intermitenciaSelect).selectFirst();
		
	}

	private void preencherFormularioDestinatarios() {

	}

	private void preencherFormularioLayous() {

	}

}
