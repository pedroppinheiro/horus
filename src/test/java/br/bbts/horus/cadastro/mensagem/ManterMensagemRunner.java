package br.bbts.horus.cadastro.mensagem;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "html:target/cucumber/reports/ManterMensagemTest"
                  },
		dryRun = true
		)
public class ManterMensagemRunner {

}

