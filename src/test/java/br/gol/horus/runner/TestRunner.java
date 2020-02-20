package br.gol.horus.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				 "junit:target/cucumber-reports/report.xml",
				 "html:target/cucumber-reports/",
				 "json:target/cucumber-reports/report.json",
                  },
		dryRun = false,
		monochrome=true,
		strict=true,
		features = "classpath:features",//{"src/test/resources/features/"},
		glue = {"br.gol.horus.steps"}
		)
public class TestRunner {

}