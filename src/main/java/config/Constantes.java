package config;

import java.time.Duration;

public final class Constantes {

	public static final boolean IS_HEADLESS = false;
	public static final boolean SHOULD_MAXIMIZE = false;
	
	public static final int CLIENTE_ID = 80;
	
	public static final String SYSTEM_UNDER_TEST_URL = "http://localhost:8080/gol/?cliente=" + CLIENTE_ID;
	
	public static final String DEFAULT_USER_LOGIN = "gol@golsoftware.com.br";
	public static final String DEFAULT_PASSWORD = "condocloud30";
	
	public static final Duration IMPLICIT_WAITING_TIME = Duration.ofSeconds(0);
	public static final Duration SCRIPT_TIMEOUT = Duration.ofSeconds(5);
	
	public static final Duration TIMEOUT_AGUARDAR_EXIBICAO_PROCESSANDO = Duration.ofSeconds(2);
	public static final Duration TIMEOUT_AGUARDAR_FINALIZACAO_PROCESSANDO = Duration.ofSeconds(5);
	
	public static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(10);
	public static final Duration EXPLICIT_WAITING_TIME = Duration.ofSeconds(3);
	public static final Duration EXPLICIT_WAITING_TIME_POLLING_INTERVAL = Duration.ofMillis(250);
	public static final Duration AJAX_ELEMENT_LOCATOR_FACTORY_TIMEOUT = Duration.ofSeconds(2);
	public static final Duration DOWNLOAD_WAITING_TIME = Duration.ofSeconds(60);
	
	public static final String CHROME_DRIVER_NAME = "chromedriver.exe"; //"./src/test/resources/drivers/chromedriver.exe";
	public static final String FIREFOX_DRIVER_NAME = "geckodriver-v0.25.0-win64.exe"; //"./src/test/resources/drivers/geckodriver-v0.25.0-win64.exe";

	public static final String DATABASE_CONNECTION_HOST = "localhost";
	public static final String DATABASE_CONNECTION_PORT = "1433";
	public static final String DATABASE_CONNECTION_USERNAME = "sa";
	public static final String DATABASE_CONNECTION_PASSWORD = "redeinf123";
	public static final String DATABASE_NAME = "SRH0080";
	public static final String DATABASE_SCHEMA = "SRH0080";
	
	public static final String DOWNLOAD_PATH = System.getProperty("user.home") + "\\horus\\DOWNLOADS";
	public static final String SCREENSHOTS_PATH = System.getProperty("user.home") + "\\horus\\SCREENSHOTS";

	public static final String DEFAULT_FILE_ERROR_NAME = "unexpected_error.png";
	
}
