package lp.jpa.adocao.abrigo;

import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IAbrigoRepositorio;
import lp.adocao.dominio.abrigo.IdAbrigo;
import lp.jpa.adocao.JpaMapeador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class AbrigoImpl implements IAbrigoRepositorio {
    private static final Logger log = LoggerFactory.getLogger(AbrigoImpl.class);
    @Autowired
    AbrigoJpaRepositorio repositorio;

    @Autowired
    JpaMapeador mapeador = JpaMapeador.getInstance();


    @Override
    public void salvar(Abrigo abrigo) {
        var abrigoJpa = mapeador.map(abrigo, AbrigoJpa.class);
        repositorio.save(abrigoJpa);
    }

    @Override
    public void editar(Abrigo abrigo) {
        var abrigoJpa = mapeador.map(abrigo, AbrigoJpa.class);
        repositorio.save(abrigoJpa);

    }

    @Override
    public void remover(IdAbrigo idAbrigo) {
        repositorio.deleteById(idAbrigo.getId());
    }

    @Override
    public Abrigo obterPorId(IdAbrigo idAbrigo) {
        var abrigoJpa = repositorio.findById(idAbrigo.getId());
        return mapeador.map(abrigoJpa.orElse(null), Abrigo.class);
    }

    @Override
    public List<Abrigo> listarAbrigos() {
        var abrigosJpa = repositorio.findAll();
        return abrigosJpa.stream()
                .map(abrigoJpa -> mapeador.map(abrigoJpa, Abrigo.class))
                .toList();
    }

    @Override
    public Abrigo obterPorNome(String nome) {
        var abrigoJpa = repositorio.findBynomeAbrigo(nome);
        return mapeador.map(abrigoJpa, Abrigo.class);
    }
}
