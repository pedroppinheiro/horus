package stepDefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.Constantes;
import cucumber.ScenarioContext;
import enums.ClientesEnum;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import pageObjects.golPageObjects.paginas.HomePage;
import pageObjects.golPageObjects.paginas.LoginPage;

public class LoginLogoffSteps {
	
	public static final Logger LOGGER = Logger.getLogger(LoginLogoffSteps.class.getName());
	
	private ScenarioContext context;
	 
    public LoginLogoffSteps(ScenarioContext context) {
        this.context = context;
    }
	
	@Dada("a pagina de login do GOL")
	public void a_pagina_de_login_do_GOL() throws Exception {
		assertNotNull(context.paginaAtual);
	}

	@Quando("eu entro com minhas credenciais {string} e {string}")
	public void eu_entro_com_minhas_credenciais(String usuario, String senha) throws Exception {
		context.paginaAtual = ((LoginPage) context.paginaAtual).login(usuario, senha);
	}

	@Entao("sou direcionado para a home page")
	public void sou_direcionado_para_a_home_page() {
		assertTrue(context.paginaAtual instanceof HomePage);
	}

	@Entao("o header exibe o nome do cliente correto")
	public void o_header_exibe_o_nome_do_cliente_correto() {
		String nomeClienteObtido = ((HomePage) context.paginaAtual).getNomeCliente();
		Optional<ClientesEnum> clienteEsperado = ClientesEnum.getById(Constantes.CLIENTE_ID);
		Optional<ClientesEnum> clienteObtido = ClientesEnum.getByNome(nomeClienteObtido);
		try {
			assertThat("Nome do cliente encontrado na homepage deve ser igual ao nome do cliente armazenado no ClientesEnum", clienteEsperado.get().getNome(), equalToIgnoringCase(clienteObtido.get().getNome()));
		} catch (NoSuchElementException nsee) {
			String clienteEsperadoMensagem = clienteEsperado.isPresent() ? clienteEsperado.get().getNome() : "Enum de cliente ainda não definido para o ID " + Constantes.CLIENTE_ID;
			String clienteEncontradoMensagem = clienteObtido.isPresent() ? clienteObtido.get().getNome() : nomeClienteObtido;
			LOGGER.log(Level.SEVERE, "Falha ao comparar os nomes de cliente esperado com o obtido", nsee);
			fail("Cliente esperado: " + clienteEsperadoMensagem + "\nCliente encontrado: " + clienteEncontradoMensagem);
		}
	}
	
	@Quando("eu realizo o logoff")
	public void realizarLogoff() throws Exception {
		context.paginaAtual = context.paginaAtual.logout();
	}
	
	@Entao("sou redirecionado para a página de login")
	public void checarPaginaDeLogin() {
		assertTrue(((LoginPage) context.paginaAtual).isFormularioExibido());
		assertTrue(((LoginPage) context.paginaAtual).isInLoginPage());
	}

}

