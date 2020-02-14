package br.gol.horus.helpers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import br.gol.horus.constantes.Constantes;

/**
 * Classe utilizada para realizar o printscreen da tela
 * @author pedro
 *
 */
public class Screenshoter {

	private WebDriver webDriver;
	public static final Logger LOGGER = Logger.getLogger(Screenshoter.class.getName());
	
	public Screenshoter(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void tirarScreenshot(String fileName) {
		try {
			String screenshotFileName = tratarNomeArquivo(fileName);
			criarArquivoScreenshot(screenshotFileName);
		} catch (Exception e) {
			LOGGER.info("Falha em tirar screenshot");
			e.printStackTrace();
		}
	}

	private String tratarNomeArquivo(String fileName) {
		return adicionarPrefixoIdCliente(adicionarSufixoTimestamp(removerCaracteresRuins(fileName)));
	}

	private String adicionarPrefixoIdCliente(String fileName) {
		return Constantes.CLIENTE_ID + " - " + fileName;
	}

	private String adicionarSufixoTimestamp(String fileName) {
		DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("ddMMyyyy-HHmmss");
		return fileName + " - " + timeStampPattern.format(LocalDateTime.now());
	}
	
	private String removerCaracteresRuins(String fileName) {
		return fileName.replaceAll("/", "");
	}
	
	private void criarArquivoScreenshot(String fileName) throws IOException {
		selecionarTelaPrincipal();
		maximizarJanela();
		
		File screenshotOrigem = ((TakesScreenshot) this.webDriver).getScreenshotAs(OutputType.FILE);
		File screenshotDestino = new File(Constantes.SCREENSHOTS_PATH + "\\" + fileName + ".png");
		LOGGER.info("Tentando salvar screenshot em " + screenshotDestino.getAbsolutePath());
		try {
			FileUtils.copyFile(screenshotOrigem, screenshotDestino);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Não foi possível salvar a imagem do screenshot.", e);
		}
	}
	
	private void selecionarTelaPrincipal() {
		this.webDriver.switchTo().defaultContent();
	}
	
	private void maximizarJanela() {
		this.webDriver.manage().window().maximize();
		this.webDriver.manage().window().fullscreen();
	}
}
