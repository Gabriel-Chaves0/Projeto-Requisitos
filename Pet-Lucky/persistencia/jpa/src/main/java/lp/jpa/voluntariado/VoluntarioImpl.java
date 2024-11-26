package lp.jpa.voluntariado;

import lp.adocao.dominio.pessoa.IdPessoa;
import lp.jpa.adocao.JpaMapeador;
import lp.voluntariado.dominio.voluntario.IVoluntarioRepositorio;
import lp.voluntariado.dominio.voluntario.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class VoluntarioImpl implements IVoluntarioRepositorio {

    @Autowired
    private VoluntarioJpaRepositorio repositorio;

    @Autowired
    JpaMapeador mapeador = JpaMapeador.getInstance();


    @Override
    public void salvar(Voluntario voluntario) {
        var voluntarioJpa = mapeador.map(voluntario, VoluntarioJpa.class);
        repositorio.save(voluntarioJpa);
    }

    @Override
    public void editar(Voluntario voluntario) {
        var voluntarioJpa = mapeador.map(voluntario, VoluntarioJpa.class);
        repositorio.save(voluntarioJpa);
    }

    @Override
    public Voluntario obterVoluntarioPorId(IdPessoa idVoluntario) {
        var voluntarioJpa = repositorio.findById((long) idVoluntario.getId());
        return mapeador.map(voluntarioJpa.orElse(null), Voluntario.class);
    }

    @Override
    public List<Voluntario> listarVoluntarios() {
        return repositorio.findAll().stream()
                .map(voluntarioJpa -> mapeador.map(voluntarioJpa, Voluntario.class))
                .toList();

    }
}
