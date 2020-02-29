package converter.core;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(features={"src/test/java/converter/features/"},glue={"converter.core"},plugin={"pretty","html:target/report"})
@RunWith(Cucumber.class)
public class JUnitRunner
{

}