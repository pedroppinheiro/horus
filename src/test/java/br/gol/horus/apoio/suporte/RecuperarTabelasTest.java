package br.gol.horus.apoio.suporte;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "junit:target/cucumber/reports/RecuperarTabelasTest/junit.xml",
				 "html:target/cucumber/reports/RecuperarTabelasTest"
                  },
		dryRun = false,
		monochrome=true,
		strict=true
		)
public class RecuperarTabelasTest {

}