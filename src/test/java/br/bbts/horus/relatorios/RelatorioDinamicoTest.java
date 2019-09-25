package br.bbts.horus.relatorios;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "html:target/cucumber/reports/RelatorioDinamico"
                  },
		dryRun = false
		)
public class RelatorioDinamicoTest {

}