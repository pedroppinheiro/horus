package br.horus.constantes;

import java.io.File;

public final class Constantes {

	public static final boolean IS_HEADLESS = false;
	public static final boolean SHOULD_MAXIMIZE = false;
	public static final String CURRENT_OS = "LINUX"; // LINUX ou WINDOWS
	public static final String BASE_URL = "http://testing-ground.scraping.pro/login";
	public static final String DEFAULT_USER_LOGIN = "admin";
	public static final String DEFAULT_PASSWORD = "12345";
	
	public static final long IMPLICIT_WAITING_TIME = 5L;
	public static final long SCRIPT_TIMEOUT = 5L;
	public static final long PAGE_LOAD_TIMEOUT = 5L;
	public static final long EXPLICIT_WAITING_TIME = 5L;
	public static final int AJAX_ELEMENT_LOCATOR_FACTORY_TIMEOUT = 5;
	
	public static final String CHROME_DRIVER_LOCATION = "./src/test/resources/chromedriver.exe";
	public static final String FIREFOX_WINDOWS_DRIVER_LOCATION = "./src/test/resources/geckodriver-v0.25.0-win64.exe";
	public static final String FIREFOX_LINUX_DRIVER_LOCATION = "./src/test/resources/geckodriver-v0.25.0-win64";
		
	public static final String DATABASE_CONNECTION_URL = "jdbc:oracle:thin:@<ip>:<port>"; //jdbc:subprotocol:subname
	public static final String DATABASE_CONNECTION_USERNAME = "login";
	public static final String DATABASE_CONNECTION_PASSWORD = "password";
	
	public static final String DOWNLOAD_PATH = System.getProperty("user.home") + "\\horus\\DOWNLOADS";
	public static final String SCREENSHOTS_PATH = System.getProperty("user.home") + File.separator + "horus" + File.separator + "SCREENSHOTS";
	public static final long DOWNLOAD_WAITING_TIME_IN_SECONDS = 60L;

	public static final String DEFAULT_FILE_ERROR_NAME = "unexpected_error.png";
	
	public static final String LINUX_OS = "LINUX";
	public static final String WINDOWS_OS = "WINDOWS";
}
