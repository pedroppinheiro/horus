package br.bbts.horus.relatorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Optional;

import br.bbts.horus.driver.DriverFactory;
import br.bbts.horus.driver.DriverVO;
import br.bbts.horus.paginas.DominioPaginas;
import br.bbts.horus.paginas.LoginPage;
import br.bbts.horus.paginas.RelatorioDinamicoPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.Pt;

public class RelatorioDinamicoSteps implements Pt {
	
	private DriverVO driverVO;
	private RelatorioDinamicoPage relatorioDinamicoPage;
	private String extensaoDoArquivo;
	
	@Before
    public void beforeScenario() {
		driverVO = DriverFactory.createDriverVO();
    }
	
	public RelatorioDinamicoSteps() {
		
		Dada("a pagina de Relatorio Dinamico do PSIM", () -> {
			relatorioDinamicoPage = (RelatorioDinamicoPage) new LoginPage(driverVO).acessarPagina(DominioPaginas.RELATORIO_DINAMICO_PAGE);
		});
		
		Quando("eu clico no relatorio de {string}", (String relatorioNome) -> {
			relatorioDinamicoPage.acessarRelatorio(relatorioNome);
		});
		
		Quando("clico no botao de baixar arquivo {string}", (String extensaoDoArquivo) -> {
		    this.extensaoDoArquivo = extensaoDoArquivo.toLowerCase().contains(".") ? extensaoDoArquivo.toLowerCase() : "." + extensaoDoArquivo.toLowerCase();
		    relatorioDinamicoPage.baixarArquivo(this.extensaoDoArquivo);
		});

		Entao("o arquivo e baixado com sucesso", () -> {
			Optional<File> arquivoBaixado = relatorioDinamicoPage.getArquivoBaixado(this.extensaoDoArquivo);
			String nomeArquivoEsperado = relatorioDinamicoPage.getRelatorioAcessado().getNomeArquivoEsperado() + extensaoDoArquivo;
			assertTrue("O arquivo " + nomeArquivoEsperado + " não foi baixado com sucesso no tempo esperado", arquivoBaixado.isPresent());
			assertEquals("O nome do arquivo não é igual ao nome do arquivo esperado", nomeArquivoEsperado, arquivoBaixado.get().getName());
		});
		
		Quando("informo a data inicio {string}", (String dataInicio) -> {
			relatorioDinamicoPage.informarDataInicio(dataInicio);
		});

		Quando("informo a data final {string}", (String dataFinal) -> {
			relatorioDinamicoPage.informarDataFinal(dataFinal);
		});
		
		Quando("informo a data base {string}", (String dataBase) -> {
			relatorioDinamicoPage.informarDataBase(dataBase);
		});
		
		Quando("informo a data de inicio da ocorrencia {string}", (String dataInicioOcorrencia) -> {
			relatorioDinamicoPage.informarDataInicioOcorrencia(dataInicioOcorrencia);
		});
		
		Quando("informo a data de fim da ocorrencia {string}", (String dataFimOcorrencia) -> {
			relatorioDinamicoPage.informarDataFimOcorrencia(dataFimOcorrencia);
		});
		
		Quando("seleciono a dependencia {string}", (String dependenciaNome) -> {
			relatorioDinamicoPage.informarDependencia(dependenciaNome);
		});
		
		Quando("seleciono o usuario {string}", (String usuarioNome) -> {
			relatorioDinamicoPage.informarUsuario(usuarioNome);
		});
		
		Quando("seleciono o grupo de monitoramento {string}", (String grupoMonitoramento) -> {
			relatorioDinamicoPage.informarGrupoMonitoramento(grupoMonitoramento);
		});
	}

	@After
    public void afterScenario(Scenario scenario) {
		
		if(scenario.isFailed()) {
			try {
				relatorioDinamicoPage.tirarScreenshot(scenario.getName());
			} catch (Exception e) {
				System.out.println("Não foi possível tirar uma screenshot do erro");
				e.printStackTrace();
			}
		}
		
//		relatorioDinamicoPage.cleanFolder();
		relatorioDinamicoPage.destroyDriverVO();
    }
}
