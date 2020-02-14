package br.gol.horus.autenticacao;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "html:target/cucumber/reports/LoginLogoff"
                  },
		dryRun = true
		)
public class LoginLogoffTest {

}