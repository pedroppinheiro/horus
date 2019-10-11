package br.horus.login;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "html:target/cucumber/reports/LoginLogoff"
                  },
		dryRun = false
		)
public class LoginLogoffTest {

}