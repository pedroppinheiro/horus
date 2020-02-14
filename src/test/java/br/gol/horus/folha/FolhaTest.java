package br.gol.horus.folha;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "html:target/cucumber/reports/FolhaTest"
                  },
		dryRun = false
		)
public class FolhaTest {

}