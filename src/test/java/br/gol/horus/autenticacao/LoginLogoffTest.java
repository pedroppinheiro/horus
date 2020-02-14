package br.gol.horus.autenticacao;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "junit:target/cucumber/reports/LoginLogoff/junit.xml",
				 "html:target/cucumber/reports/LoginLogoff"
                  },
		dryRun = false,
		monochrome=true,
		strict=true
		)
public class LoginLogoffTest {

}