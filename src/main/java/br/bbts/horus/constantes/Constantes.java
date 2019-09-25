package br.bbts.horus.constantes;

public final class Constantes {

	public static final boolean IS_HEADLESS = false;
	public static final boolean SHOULD_MAXIMIZE = false;
	
//	public static final String BASE_URL = "http://localhost:8080/psim"; //localhost
	
	public static final String BASE_URL = "http://10.8.1.233:9180/psim"; //teste
	public static final String DEFAULT_USER_LOGIN = "Selenium";
	public static final String DEFAULT_PASSWORD = "1234";
	
//	public static final String BASE_URL = "https://psim.hm.bb.com.br/psim"; // homologação
//	public static final String DEFAULT_USER_LOGIN = "michael.lago";
//	public static final String DEFAULT_PASSWORD = "psim";
	
	public static final long IMPLICIT_WAITING_TIME = 10L;
	public static final long SCRIPT_TIMEOUT = 15L;
	public static final long PAGE_LOAD_TIMEOUT = 10L;
	public static final long EXPLICIT_WAITING_TIME = 10L;
	public static final int AJAX_ELEMENT_LOCATOR_FACTORY_TIMEOUT = 10;

	public static final String CHROME_DRIVER_LOCATION = "./src/test/resources/chromedriver.exe";
	public static final String FIREFOX_DRIVER_LOCATION = "./src/test/resources/geckodriver-v0.23.0-win64.exe";
	
	public static final String LOGIN_URL = BASE_URL + "/login.jsf";
	public static final String HOME_URL = BASE_URL + "/pages/home.jsf";
	
	public static final String DATABASE_CONNECTION_URL = "jdbc:oracle:thin:@10.8.1.212:1521:dsv1";
	public static final String DATABASE_CONNECTION_USERNAME = "psim4_tst_e1";
	public static final String DATABASE_CONNECTION_PASSWORD = "psim";
	
	public static final String DOWNLOAD_PATH = System.getProperty("user.home") + "\\horus\\DOWNLOADS";
	public static final String SCREENSHOTS_PATH = System.getProperty("user.home") + "\\horus\\SCREENSHOTS";
	
	public static final long DOWNLOAD_WAITING_TIME_IN_SECONDS = 60L;

	public static final String DEFAULT_FILE_ERROR_NAME = "unexpected_error.png";
	
	public static final int RELATORIOS_POR_PAGINA = 10;
	
}
