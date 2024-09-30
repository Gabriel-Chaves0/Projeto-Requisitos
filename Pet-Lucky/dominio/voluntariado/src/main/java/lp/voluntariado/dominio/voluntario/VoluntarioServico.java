package lp.voluntariado.dominio.voluntario;

import lp.adocao.dominio.pessoa.IdPessoa;

import static org.apache.commons.lang3.Validate.notNull;

public class VoluntarioServico {
    private final IVoluntarioRepositorio voluntarioRepositorio;

    public VoluntarioServico(IVoluntarioRepositorio voluntarioRepositorio) {
        this.voluntarioRepositorio = voluntarioRepositorio;
    }

    public void salvar(Voluntario voluntario) {
        notNull(voluntario, "Voluntario não pode ser nulo");

        voluntarioRepositorio.salvar(voluntario);
    }

    public void editar(Voluntario voluntario) {
        notNull(voluntario, "Voluntario não pode ser nulo");

        voluntarioRepositorio.editar(voluntario);
    }

    public Voluntario obterPorId(IdPessoa idVoluntario) {
        notNull(idVoluntario, "Id do voluntario não pode ser nulo");

        return voluntarioRepositorio.obterVoluntarioPorId(idVoluntario);
    }


}
