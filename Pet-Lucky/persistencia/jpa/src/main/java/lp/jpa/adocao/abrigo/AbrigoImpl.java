package lp.jpa.adocao.abrigo;

import lp.adocao.dominio.abrigo.Abrigo;
import lp.adocao.dominio.abrigo.IAbrigoRepositorio;
import lp.adocao.dominio.abrigo.IdAbrigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AbrigoImpl implements IAbrigoRepositorio {
    @Autowired
    AbrigoJpaRepositorio repositorio;

    @Override
    public void salvar(Abrigo abrigo) {

    }

    @Override
    public void editar(Abrigo abrigo) {

    }

    @Override
    public void remover(IdAbrigo idAbrigo) {

    }

    @Override
    public Abrigo obterPorId(IdAbrigo idAbrigo) {
        return null;
    }

    @Override
    public List<Abrigo> listarAbrigos() {
        return List.of();
    }

    @Override
    public Abrigo obterPorNome(String nome) {
        return null;
    }
}
