package br.bbts.horus.rbac;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "html:target/cucumber/reports/LoginLogoff"
                  },
		dryRun = true
		)
public class LoginLogoffTest {

}