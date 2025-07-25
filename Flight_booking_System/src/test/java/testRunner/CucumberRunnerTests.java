package testRunner;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/Features",
    glue = {"StepDefinitions"},
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true
)
public class CucumberRunnerTests {

}
