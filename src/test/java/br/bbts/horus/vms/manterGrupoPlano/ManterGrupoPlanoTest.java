package br.bbts.horus.vms.manterGrupoPlano;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "html:target/cucumber/reports/ManterGrupoPlano"
                  },
		dryRun = false
		)
public class ManterGrupoPlanoTest { 

}