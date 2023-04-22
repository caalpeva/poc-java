package team.boolbee.calculator;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
* Acceptance Test
*/
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:acceptance/calculator.feature")
public class AcceptanceTest {

}
