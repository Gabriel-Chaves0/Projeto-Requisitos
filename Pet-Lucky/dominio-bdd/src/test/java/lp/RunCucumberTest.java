package lp;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources", // Caminho para os arquivos .feature
        glue = "lp",             // Pacote onde estão as Step Definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"}, // Gera um relatório de saída em HTML
        monochrome = true              // Para melhor legibilidade no console
)
public class RunCucumberTest {
}
