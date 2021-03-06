package cucumberOption;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features ="src/test/java/features/",
		dryRun =false,
//		tags={"@a"},
		glue = "stepDefinition", 
		monochrome = true,
		plugin = { "pretty",
				"html:target/html",
				"json:target/json/cucumber.json"}
		)

public class Run_cucumber_net_phptravels {

}
