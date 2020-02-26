package runners;

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
		dryRun = false, //checks if all the steps have step definition
		monochrome=true, //display the console output in much readable way
		strict=true, //will fail execution if there are undefined or pending steps
		features = "classpath:features",//{"src/test/resources/features/"},
		glue = {"br.gol.horus.steps"},
		tags = {""}
		)
public class TestRunner {

}