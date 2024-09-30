package lp.voluntariado.dominio.voluntario;

import lp.adocao.dominio.pessoa.IdPessoa;

import java.util.Arrays;
import java.util.List;

public interface IVoluntarioRepositorio {
    void salvar(Voluntario voluntario);
    void editar(Voluntario voluntario);
    Voluntario obterVoluntarioPorId(IdPessoa idVoluntario);

    List listarVoluntarios();
}
