package br.bbts.horus.util;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.io.FileUtils;

import br.bbts.horus.constantes.Constantes;

public class ArquivoUtil {
	
	private Long creationTime;
	private Long startTime;
	
	public ArquivoUtil() {
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
	 * Check the file from a specific directory
	 * 
	 */
	public boolean isFileDownloaded(String dirPath, String fileName) {
		boolean flag = false;
		File dir = new File(dirPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}

		return flag;
	}

	/**
	 * Check the file from a specific directory with extension
	 * 
	 */
	public boolean isFileDownloadedWithExtension(String dirPath, String ext) {
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

	/**
	 * Get the latest file from a specific directory
	 * 
	 */
	public File getLatestFilefromDir(String dirPath) {
		
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
	 * Monitors the changes in the directory and returns the newly created filename
	 * Source: https://www.seleniumeasy.com/selenium-tutorials/verify-file-after-downloading-using-webdriver-java
	 * 
	 */
	public Optional<File> obterArquivoBaixado(String dirPath, String extensaoDoArquivo) {
		
		long timeout = Constantes.DOWNLOAD_WAITING_TIME_IN_SECONDS;
		if(startTime == null) {
			startTime = System.currentTimeMillis();
		}
		
		while(true) {
			long currentTime = (System.currentTimeMillis() - startTime) / 1000;
			if (currentTime > timeout) {
				System.out.println("Download operation timed out.. Expected file was not downloaded");
				return Optional.ofNullable(null);
			}
			
			File latestFile = getLatestFilefromDir(dirPath);
			boolean isArquivoPartFound = isFileDownloadedWithExtension(dirPath, extensaoDoArquivo + ".part");
			
			if (latestFile != null && latestFile.getName().endsWith(extensaoDoArquivo) && FileUtils.isFileNewer(latestFile, this.creationTime) && latestFile.length() > 0 && !isArquivoPartFound) {
				System.out.println("Arquivo encontrado: " + latestFile.getName());
				return Optional.ofNullable(latestFile);
			}
		}
	}

	public void cleanFolder(String dirPath) {
		try {
			FileUtils.cleanDirectory(new File(dirPath));
		} catch (IOException e) {
			System.out.println("Não foi possível apagar os arquivos da pasta " + dirPath);
			e.printStackTrace();
		}
	}
	
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
}
