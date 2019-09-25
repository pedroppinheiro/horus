package br.bbts.horus.integracao.importar_nivel_de_criticidade;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "html:target/cucumber/reports/ManterArquivoCriticidadeTest"
                  },
		dryRun = true
		)
public class ManterArquivoCriticidadeRunner {
	

}

