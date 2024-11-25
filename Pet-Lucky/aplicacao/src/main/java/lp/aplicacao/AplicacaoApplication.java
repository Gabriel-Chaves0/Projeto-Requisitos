package lp.aplicacao;

import lp.adocao.dominio.abrigo.AbrigoServico;
import lp.adocao.dominio.abrigo.IAbrigoRepositorio;
import lp.adocao.dominio.animal.AnimalServico;
import lp.adocao.dominio.animal.IAnimalRespositorio;
import lp.adocao.dominio.pessoa.IPessoaRepositorio;
import lp.adocao.dominio.pessoa.PessoaServico;
import lp.voluntariado.dominio.voluntario.IVoluntarioRepositorio;
import lp.voluntariado.dominio.voluntario.VoluntarioServico;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"lp.adocao.dominio.abrigo", "lp.adocao.dominio.animal", "lp.adocao.dominio.pessoa", "lp.voluntariado.dominio.voluntario", "lp."})
public class AplicacaoApplication {



    public static void main(String[] args) {
        SpringApplication.run(AplicacaoApplication.class, args);
    }

}
