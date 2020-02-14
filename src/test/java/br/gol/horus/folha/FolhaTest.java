package br.gol.horus.folha;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "junit:target/cucumber/reports/FolhaTest/junit.xml",
				 "html:target/cucumber/reports/FolhaTest"
                  },
		dryRun = false,
		monochrome=true,
		strict=true
		)
public class FolhaTest {

}