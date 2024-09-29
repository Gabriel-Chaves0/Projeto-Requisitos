package lp;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/lp", // Caminho para os arquivos .feature
        glue = "lp", // Pacote onde os Step Definitions estão
        plugin = {"pretty", "html:target/cucumber-reports.html"} // Opções de output
)
public class RunCucumberTest {
}
