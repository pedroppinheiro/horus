package br.gol.horus.apoio.suporte;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "html:target/cucumber/reports/RecuperarTabelasTest"
                  },
		dryRun = false
		)
public class RecuperarTabelasTest {

}