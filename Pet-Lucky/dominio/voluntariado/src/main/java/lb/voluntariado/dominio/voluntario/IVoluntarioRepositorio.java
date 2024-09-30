package lb.voluntariado.dominio.voluntario;

import lp.adocao.dominio.pessoa.IdPessoa;

public interface IVoluntarioRepositorio {
    void salvar(Voluntario voluntario);
    void editar(Voluntario voluntario);
    Voluntario obterPorId(IdPessoa idVoluntario);

}
