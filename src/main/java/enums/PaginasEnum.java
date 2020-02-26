package enums;

import java.util.Arrays;

import pageObjects.golPageObjects.GOLPage;
import pageObjects.golPageObjects.paginas.AbrirGeracaoFolhaPage;
import pageObjects.golPageObjects.paginas.HomePage;
import pageObjects.golPageObjects.paginas.SalvarRecuperarPage;
import pageObjects.golPageObjects.paginas.TabelasGeracaoFolhaPage;


/**
 * Enum utilizado para guardar as páginas do GOL.
 * O nome no enum deve guardar o texto para facilmente achar a página no "Pesquisar Transação"
 * 
 * @author pedro
 *
 */
public enum PaginasEnum {
	
	SALVAR_E_RECUPERAR_TABELAS("Salvar e Recuperar Tabelas", SalvarRecuperarPage.class), 
	HOMEPAGE("DashBoard", HomePage.class),
	ABRIRGERACAOFOLHA("Abrir Geração de Folha", AbrirGeracaoFolhaPage.class),
	TABELAS_GERACAOFOLHA("Home > Tabelas > Folha de Pagamento > Outros > Geração da Folha", TabelasGeracaoFolhaPage.class);
	
	private String nome;
	private Class<?> paginaClass;
	
	private <T extends GOLPage> PaginasEnum(String nome, Class<T> paginaClass) {
		this.nome = nome;
		this.paginaClass = paginaClass;
	}

	public static PaginasEnum getDominioPaginasByNome(String nome) {
		return Arrays.stream(PaginasEnum.values())
			  .filter(pagina -> pagina.getNome().equalsIgnoreCase(nome))
			  .findFirst()
			  .orElseThrow(() -> new IllegalStateException(String.format("Página não encontrada: \"%s\".", nome)));
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Class<?> getPaginaClass() {
		return paginaClass;
	}

	public void setPaginaClass(Class<?> paginaClass) {
		this.paginaClass = paginaClass;
	}
}
