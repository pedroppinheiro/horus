package br.bbts.horus.paginas;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.base.PsimPage;

public class VmsPage extends PsimPage {

	@FindBy(id = "matriz")
	WebElement matriz;
	@FindBy(id = "grupo")
	WebElement campoGrupo;
	@FindBy(id = "filtrarDependencia")
	WebElement filtrarDependenciaBtn;
	@FindBy(id = "dataTableDependencias:filtroNomeDependencia:filter")
	WebElement filtroNomeDependencia;
	@FindBy(id = "dlgFiltrarDependencia")
	WebElement dlgFiltrarDependencia;
	
	///// Grupo Plano
	@FindBy(id = "acaoNovoGrupoPlano")
	WebElement acaoNovoGrupoPlanoBtn;
	@FindBy(id = "nomeGrupoPlano")
	WebElement nomeGrupoPlanoInput;
	@FindBy(css = "#dlgGrupoPlano .botao.primario")
	WebElement botaoSalvarDialogGrupoPlano;
	@FindBy(id = "icnEditarGrupo")
	WebElement icnEditarGrupoBtn;
	@FindBy(id = "icnExcluirGrupo")
	WebElement icnExcluirGrupoBtn;
	@FindBy(id = "dlgGrupoPlano")
	WebElement dlgGrupoPlano;
	@FindBy(css = ".ui-confirm-dialog .ui-confirmdialog-yes")
	WebElement confirmacaoDialogSimBtn;
	
	private final String TABELA_GRUPO_PLANOS = "tabelaGruposPlanos";
	
	protected VmsPage(DriverVO driverVO) {
		super(driverVO);
		alterarParaJanela(1, 2);
		waitUntil(ExpectedConditions.visibilityOf(matriz));
	}

	public void selecionarDependencia(String dependencia) {

		selecionarGrupo();
		abrirDialogSelecionarDependencia();
		
		filtroNomeDependencia.sendKeys(dependencia);
		waitUntil(ExpectedConditions.invisibilityOf(modalAguarde));
		waitUntil(ExpectedConditions
				.numberOfElementsToBe(By.cssSelector("#dataTableDependencias_data .ui-widget-content"), 1));
		this.executeScriptAndWait("$('#dataTableDependencias_data .ui-chkbox .ui-widget').click()");
		this.executeScriptAndWait("document.getElementById('botaoSelecionarDependencia').click()");
		
		waitUntil(ExpectedConditions.invisibilityOf(dlgFiltrarDependencia));
	}
	
	public void selecionarGrupo() {
		waitUntil(ExpectedConditions.elementToBeClickable(campoGrupo));
		waitUntil(ExpectedConditions.invisibilityOf(modalAguarde));
		this.executeScriptAndWait("PF('grupo').selectValue('[TESTE] GRUPO PEDRO')");
		waitUntil(ExpectedConditions.invisibilityOf(modalAguarde));
	}
	
	private void abrirDialogSelecionarDependencia() {
		filtrarDependenciaBtn.click();
		waitUntil(ExpectedConditions.visibilityOf(dlgFiltrarDependencia));
	}

	public void clicarNaDependenciaRaizDaArvore() {

		this.executeScript(
				"document.querySelector('#arvoreCameras\\\\:0 > span:nth-child(1) > span:nth-child(3)').click();");
	}

	public Boolean isVideosCarregados() {
		
		return (Boolean) this.executeAsyncScript(
				"var callback = arguments[arguments.length - 1];\r\n" + 
				"var polling = function() {\r\n" + 
				"    \r\n" + 
				"    if($('svg[id^=\"loader_regiaoVideo\"]').length == 0) {\r\n" + 
				"    	callback(true);\r\n" + 
				"    	return;\r\n" + 
				"    } else {\r\n" +
				"    	window.setTimeout(polling, 500);\r\n" + 
				"    }\r\n" + 
				"};window.setTimeout(polling, 2000);"
		);
	}
	
	///////////////////////// GRUPO PLANO

	public void incluirGrupoPlano(String grupoPlano) {
		this.abrirDialogGrupoPlano();
		this.inserirNomeGrupoPlanoESalvar(grupoPlano);
		
	}
	
	private void abrirDialogGrupoPlano() {
		acaoNovoGrupoPlanoBtn.click();
		waitUntil(ExpectedConditions.visibilityOf(dlgGrupoPlano));
	}
	
	private void inserirNomeGrupoPlanoESalvar(String grupoPlano) {
		this.nomeGrupoPlanoInput.clear();
		this.nomeGrupoPlanoInput.sendKeys(grupoPlano);
		this.botaoSalvarDialogGrupoPlano.click();
		waitUntil(ExpectedConditions.invisibilityOf(dlgGrupoPlano));
	}

	public boolean isGrupoPlanoNaListagem(String grupoPlano) {
		String verificationScript =
				"var callback = arguments[arguments.length - 1];\r\n" +
				"callback(" +
					"PF('"+TABELA_GRUPO_PLANOS+"').getTbody().find('td').filter(function() {\r\n" + 
					"    return $(this).text() === '"+grupoPlano+"';\r\n" + 
					"}).size() === 1"
				+ ")";
		
		return (Boolean) this.executeAsyncScript(verificationScript);
	}
	
	private void selecionarGrupoDePlano(String grupoPlano) {
		String script = "PF('"+TABELA_GRUPO_PLANOS+"').getTbody().find('td').filter(function() {\r\n" + 
						"    return $(this).text() === '"+grupoPlano+"';\r\n" + 
						"}).click()";
		
		this.executeScriptAndWait(script);
	}

	public void renomearGrupoPlano(String nomeGrupoPlanoAntes, String nomeGrupoPlanoDepois) {
		this.selecionarGrupoDePlano(nomeGrupoPlanoAntes);
		waitUntil(ExpectedConditions.invisibilityOf(modalAguarde));
		icnEditarGrupoBtn.click();
		waitUntil(ExpectedConditions.visibilityOf(dlgGrupoPlano));
		nomeGrupoPlanoInput.clear();
		nomeGrupoPlanoInput.sendKeys(nomeGrupoPlanoDepois);
		botaoSalvarDialogGrupoPlano.click();
		waitUntil(ExpectedConditions.invisibilityOf(dlgGrupoPlano));
	}
	
	public void excluirGrupoPlano(String grupoPlano) {
		this.selecionarGrupoDePlano(grupoPlano);
		waitUntil(ExpectedConditions.invisibilityOf(modalAguarde));
		icnExcluirGrupoBtn.click();
		waitUntil(ExpectedConditions.visibilityOf(confirmacaoDialogSimBtn));
		confirmacaoDialogSimBtn.click();
		waitUntil(ExpectedConditions.invisibilityOf(confirmacaoDialogSimBtn));
	}

}
