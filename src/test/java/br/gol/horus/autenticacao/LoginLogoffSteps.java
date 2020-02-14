package br.gol.horus.autenticacao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.gol.horus.constantes.Constantes;
import br.gol.horus.driver.DriverFactory;
import br.gol.horus.driver.DriverVO;
import br.gol.horus.elementos.gol.GOLPage;
import br.gol.horus.elementos.gol.paginas.HomePage;
import br.gol.horus.elementos.gol.paginas.LoginPage;
import br.gol.horus.helpers.ClientesEnum;
//import io.cucumber.core.gherkin.Scenario;
//import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dada;
import io.cucumber.java.pt.Entao;
//import io.cucumber.java8.Pt;
import io.cucumber.java.pt.Quando;

public class LoginLogoffSteps {
	
	public static final Logger LOGGER = Logger.getLogger(LoginLogoffSteps.class.getName());
	
	DriverVO driverVO;
	
	LoginPage loginPage;
	HomePage homePage;
	GOLPage pagina;
	
	@Before
    public void beforeScenario() {
		driverVO = DriverFactory.createDriverVO();
    }
	
	@Dada("a pagina de login do GOL")
	public void a_pagina_de_login_do_GOL() throws Exception {
		loginPage = new LoginPage(driverVO);
	}

	@Quando("eu entro com minhas credenciais {string} e {string}")
	public void eu_entro_com_minhas_credenciais_e(String usuario, String senha) throws Exception {
		pagina = loginPage.login(usuario, senha);
	}

	@Entao("sou direcionado para a home page")
	public void sou_direcionado_para_a_home_page() {
		assertTrue(pagina instanceof HomePage);
		homePage = (HomePage) pagina;
	}

	@Entao("o header exibe o nome do cliente correto")
	public void o_header_exibe_o_nome_do_cliente_correto() {
		String nomeClienteObtido = homePage.getNomeCliente();
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
	
	@After
    public void afterScenario(Scenario scenario) {
		if(scenario.isFailed()) {
			if(loginPage != null) {
				loginPage.mostrarDetalhesModalErro();
			}
			this.driverVO.tirarScreenshot(scenario.getName());
		}
		
		this.driverVO.destroyDriverVO();
    }

}

