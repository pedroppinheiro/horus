package managers;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import config.Constantes;
import pageObjects.golPageObjects.GOLPage;


/**
 * Alguns testes podem envolver baixar um arquivo de um site e verificar se esse arquivo foi de fato salvo em uma pasta.
 * Esta classe possui métodos que auxiliam nessa atividade de checar se o arquivo foi salvo de fato.
 * 
 * Exemplo: arquivoUtil.obterArquivoBaixado(nomeArquivo);
 * @author pedro
 *
 */
public class ArquivoManager {
	
	private Long creationTime;
	private Long startTime;
	
	private static final Logger LOGGER = Logger.getLogger(GOLPage.class.getName());
	
	public ArquivoManager() {
		this.creationTime = System.currentTimeMillis();
		createDefaultDirectories();
	}

	private void createDefaultDirectories() {
		File downloadFolder = new File(Constantes.DOWNLOAD_PATH);
		File screenshotFolder = new File(Constantes.SCREENSHOTS_PATH);
		
		if(!downloadFolder.exists()) {
			downloadFolder.mkdirs();
		}
		
		if(!screenshotFolder.exists()) {
			screenshotFolder.mkdirs();
		}
	}

	/**
	 * Monitors the changes in the directory and returns the newly created filename
	 * Source: https://www.seleniumeasy.com/selenium-tutorials/verify-file-after-downloading-using-webdriver-java
	 * 
	 */
	public Optional<File> obterArquivoBaixado(String dirPath, String extensaoDoArquivo) {
		
		long timeout = Constantes.DOWNLOAD_WAITING_TIME.getSeconds();
		if(startTime == null) {
			startTime = System.currentTimeMillis();
		}
		
		while(true) {
			long currentTime = (System.currentTimeMillis() - startTime) / 1000;
			if (currentTime > timeout) {
				LOGGER.info("Download operation timed out.. Expected file was not downloaded");
				return Optional.ofNullable(null);
			}
			
			File latestFile = getLatestFilefromDir(dirPath);
			boolean isArquivoPartFound = isFileDownloadedWithExtension(dirPath, extensaoDoArquivo + ".part");
			
			if (latestFile != null && latestFile.getName().endsWith(extensaoDoArquivo) && FileUtils.isFileNewer(latestFile, this.creationTime) && latestFile.length() > 0 && !isArquivoPartFound) {
				LOGGER.info("Arquivo encontrado: " + latestFile.getName());
				return Optional.ofNullable(latestFile);
			}
		}
	}

	/**
	 * Get the latest file from a specific directory
	 * 
	 */
	private File getLatestFilefromDir(String dirPath) {
		
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	/**
	 * Check the file from a specific directory with extension
	 * 
	 */
	private boolean isFileDownloadedWithExtension(String dirPath, String ext) {
		boolean flag = false;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			flag = false;
		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(ext)) {
				flag = true;
			}
		}
		return flag;
	}
	
	public void cleanDirectory(String dirPath) {
		try {
			FileUtils.cleanDirectory(new File(dirPath));
		} catch (IOException e) {
			LOGGER.info("Não foi possível apagar os arquivos da pasta " + dirPath);
			e.printStackTrace();
		}
	}
	
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
}
