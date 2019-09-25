package br.bbts.horus.paginas;

import java.io.File;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.bbts.horus.constantes.Constantes;
import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.base.PsimPage;
import br.bbts.horus.util.ArquivoUtil;

public class RelatorioDinamicoPage extends PsimPage {
	
	@FindBy(id = "relatorioDinamico")
	WebElement relatorioDinamicoIFrame;
	
	@FindBy(css = "input[value='Baixar XLS']")
	WebElement baixarXLSBtn;
	@FindBy(css = "input[value='Baixar PDF']")
	WebElement baixarPDFBtn;
	@FindBy(id = "formulario:btnBuscar")
	WebElement btnBuscar;
	
	@FindBy(id = "formulario:dinamico_DEPENDENCIA_lupa")
	WebElement lupaDependencia;
	@FindBy(id = "formulario:dinamico_DEPENDENCIA_nomeDependencia")
	WebElement inputDependencia;
	@FindBy(id = "formulario:listagem:0:dinamico_DEPENDENCIA_imagemLov")
	WebElement primeiroResultadoDependencia;
	
	@FindBy(id = "formulario:dinamico_USUARIO_lupa")
	WebElement lupaUsuario;
	@FindBy(id = "formulario:dinamico_USUARIO_nome")
	WebElement inputUsuario;
	@FindBy(id = "formulario:listagem:0:dinamico_USUARIO_imagemLov")
	WebElement primeiroResultadoUsuario;
	
	@FindBy(id = "formulario:dinamico_GRUPO_lupa")
	WebElement lupaGrupoMonitoramento;
	@FindBy(id = "formulario:dinamico_GRUPO_nomeGrupo")
	WebElement inputGrupoMonitoramento;
	@FindBy(id = "formulario:listagem:0:dinamico_GRUPO_imagemLov")
	WebElement primeiroResultadoGrupoMonitoramento;

	@FindBy(id = "formulario:carregando")
	WebElement gifCarregando;
	
	private final String DATA_INICIO_INPUT_ID = "formulario:dinamico_DATA_INICIOInputDate";
	private final String DATA_INICIAL_INPUT_ID = "formulario:dinamico_DATA_INICIALInputDate";
	
	private final String DATA_INPUT_ID = "formulario:dinamico_DATAInputDate";
	private final String DATA_BASE_INPUT_ID = "formulario:dinamico_DATA_BASEInputDate";
	
	private final String DATA_FINAL_INPUT_ID = "formulario:dinamico_DATA_FINALInputDate";
	private final String DATA_FIM_INPUT_ID = "formulario:dinamico_DATA_FIMInputDate";
	
	private final String DATA_INICIO_OCORRENCIA_INPUT_ID = "formulario:dinamico_DATA_INICIO_OCORRENCIAInputDate";
	private final String DATA_FIM_OCORRENCIA_INPUT_ID = "formulario:dinamico_DATA_FIM_OCORRENCIAInputDate";
	
	private final String HORA_INICIO_INPUT_ID = "formulario:dinamico_HORA_INICIO";
	private final String HORA_INICIAL_INPUT_ID = "formulario:dinamico_HORA_INICIAL";
	
	private final String HORA_FIM_INPUT_ID = "formulario:dinamico_HORA_FIM";
	private final String HORA_FINAL_INPUT_ID = "formulario:dinamico_HORA_FINAL";
	
	private final String HORA_INICIO_OCORRENCIA_INPUT_ID = "formulario:dinamico_HORA_INICIO_OCORRENCIA";
	private final String HORA_FIM_OCORRENCIA_INPUT_ID = "formulario:dinamico_HORA_FIM_OCORRENCIA";
	
	private final String PROXIMA_PAGINA = "formulario:pagProxima";
	
	private ArquivoUtil arquivoUtil;
	private RelatoriosEnum relatorioAcessado;
	
	protected RelatorioDinamicoPage(DriverVO driverVO) {
		super(driverVO);
		arquivoUtil = new ArquivoUtil();
		waitUntil(ExpectedConditions.frameToBeAvailableAndSwitchToIt(relatorioDinamicoIFrame));
	}

	public void acessarRelatorio(String relatorioNome) throws Exception {
		setRelatorioAcessado(relatorioNome);
		
		if (getRelatorioAcessado() == null) {
			throw new Exception("Relatorio não encontrado!");
		}
		
		acessarPaginaDoRelatorio(getRelatorioAcessado());
		clicarNoRelatorio(getRelatorioAcessado());
		this.waitUntil(ExpectedConditions.invisibilityOf(gifCarregando));
	}
	
	private void acessarPaginaDoRelatorio(RelatoriosEnum relatorio) {
		int pagina = relatorio.ordinal() / Constantes.RELATORIOS_POR_PAGINA;
		
		for(int i = 0; i < pagina; i++) {
			this.getWebDriver().findElement(By.id(PROXIMA_PAGINA)).click();
		}
	}
	
	private void clicarNoRelatorio(RelatoriosEnum tipoRelatorio) {
		this.getWebDriver().findElement(By.id(tipoRelatorio.getElementId())).click();
	}

	public void baixarArquivo(String extensaoDoArquivo) throws Exception {
		this.arquivoUtil.setStartTime(System.currentTimeMillis());
		if(extensaoDoArquivo.endsWith("pdf")) {
			gerarPDF();
		} else if(extensaoDoArquivo.endsWith("xlsx")) {
			gerarXLS();
		}
		
		if(isMensagemDeErroExibida()) {
			throw new Exception("Mensagem de erro exibida na página do relatório, não foi possível baixar o arquivo.");
		}
	}

	public void gerarPDF() {
		baixarPDFBtn.click();
	}

	public void gerarXLS() {
		baixarXLSBtn.click();
	}
	
	public void informarDataInicio(String dataInicio) {
		this.executeScript("let data_inicial = document.getElementById('" + DATA_INICIO_INPUT_ID + "') || document.getElementById('" + DATA_INICIAL_INPUT_ID + "'); data_inicial.value = '" + dataInicio + "'");
	}
	
	public void informarDataBase(String dataBase) {
		this.executeScript("let data_inicial = document.getElementById('" + DATA_INPUT_ID + "') || document.getElementById('" + DATA_BASE_INPUT_ID + "'); data_inicial.value = '" + dataBase + "'");
	}
	
	public void informarDataInicioOcorrencia(String dataInicioOcorrencia) {
		this.executeScript("document.getElementById('" + DATA_INICIO_OCORRENCIA_INPUT_ID + "').value = '" + dataInicioOcorrencia + "'");
	}

	public void informarDataFinal(String dataFinal) {
		this.executeScript("let data_inicial = document.getElementById('" + DATA_FIM_INPUT_ID + "') || document.getElementById('" + DATA_FINAL_INPUT_ID + "'); data_inicial.value = '" + dataFinal + "'");
	}
	
	public void informarDataFimOcorrencia(String dataFimOcorrencia) {
		this.executeScript("document.getElementById('" + DATA_FIM_OCORRENCIA_INPUT_ID + "').value = '" + dataFimOcorrencia + "'");
	}
	
	public void informarHoraInicio(String horaInicio) {
		this.executeScript("let data_inicial = document.getElementById('" + HORA_INICIO_INPUT_ID + "') || document.getElementById('" + HORA_INICIAL_INPUT_ID + "'); data_inicial.value = '" + horaInicio + "'");
	}
	
	public void informarHoraInicioOcorrencia(String horaInicioOcorrencia) {
		this.executeScript("document.getElementById('" + HORA_INICIO_OCORRENCIA_INPUT_ID + "').value = '" + horaInicioOcorrencia + "'");
	}
	
	public void informarHoraFim(String horaFim) {
		this.executeScript("let data_inicial = document.getElementById('" + HORA_FIM_INPUT_ID + "') || document.getElementById('" + HORA_FINAL_INPUT_ID + "'); data_inicial.value = '" + horaFim + "'");
	}
	
	public void informarHoraFimOcorrencia(String horaFimOcorrencia) {
		this.executeScript("document.getElementById('" + HORA_FIM_OCORRENCIA_INPUT_ID + "').value = '" + horaFimOcorrencia + "'");
	}

	public void informarDependencia(String dependenciaNome) {
		lupaDependencia.click();
		
		alterarParaJanela(1, 2);
		waitUntil(ExpectedConditions.visibilityOf(btnBuscar));
		
		inputDependencia.sendKeys(dependenciaNome);
		btnBuscar.click();
		primeiroResultadoDependencia.click();
		
		alterarParaJanela(0, 1);
		
		waitUntil(ExpectedConditions.visibilityOf(baixarPDFBtn));
	}
	
	public void informarUsuario(String usuarioNome) {
		lupaUsuario.click();
		
		alterarParaJanela(1, 2);
		waitUntil(ExpectedConditions.visibilityOf(btnBuscar));
		
		inputUsuario.sendKeys(usuarioNome);
		btnBuscar.click();
		primeiroResultadoUsuario.click();
		
		alterarParaJanela(0, 1);
		
		waitUntil(ExpectedConditions.visibilityOf(baixarPDFBtn));
	}
	
	public void informarGrupoMonitoramento(String grupoMonitoramento) {
		lupaGrupoMonitoramento.click();
		
		alterarParaJanela(1, 2);
		waitUntil(ExpectedConditions.visibilityOf(btnBuscar));
		
		inputGrupoMonitoramento.sendKeys(grupoMonitoramento);
		btnBuscar.click();
		primeiroResultadoGrupoMonitoramento.click();
		
		alterarParaJanela(0, 1);
		
		waitUntil(ExpectedConditions.visibilityOf(baixarPDFBtn));
	}
	
	public Optional<File> getArquivoBaixado(String extensaoDoArquivo) throws Exception {
		String nomeArquivo = this.relatorioAcessado != null ? this.relatorioAcessado.getNomeArquivoEsperado() + extensaoDoArquivo : extensaoDoArquivo;
		return arquivoUtil.obterArquivoBaixado(Constantes.DOWNLOAD_PATH, nomeArquivo);
	}
	
	public void cleanFolder() {
		arquivoUtil.cleanFolder(Constantes.DOWNLOAD_PATH);
	}
	
	public boolean isMensagemDeErroExibida() {
		boolean found = false;
		
		try {
			this.customWaitUntil(1, 300, ExpectedConditions.visibilityOfElementLocated(By.id("painelMensagem")));
			found = true;
		} catch (Exception e) {
			System.out.println("Não foi possível encontrar o elemento \"painelMensagem\", provavelmente ele não foi exibido na página.");
		}
		
		return found;
	}
	
	public void setRelatorioAcessado(String relatorioNome) {
		this.relatorioAcessado = RelatoriosEnum.getRelatorioPorNome(relatorioNome);
	}
	
	public RelatoriosEnum getRelatorioAcessado() {
		return this.relatorioAcessado;
	}
}
