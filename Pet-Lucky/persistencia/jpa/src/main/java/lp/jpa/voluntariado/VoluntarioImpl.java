package lp.jpa.voluntariado;

import lp.adocao.dominio.pessoa.IdPessoa;
import lp.voluntariado.dominio.voluntario.IVoluntarioRepositorio;
import lp.voluntariado.dominio.voluntario.Voluntario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoluntarioImpl implements IVoluntarioRepositorio {
    @Override
    public void salvar(Voluntario voluntario) {

    }

    @Override
    public void editar(Voluntario voluntario) {

    }

    @Override
    public Voluntario obterVoluntarioPorId(IdPessoa idVoluntario) {
        return null;
    }

    @Override
    public List listarVoluntarios() {
        return List.of();
    }
}
