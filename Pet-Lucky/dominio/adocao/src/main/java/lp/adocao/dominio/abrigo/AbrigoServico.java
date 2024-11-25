package lp.adocao.dominio.abrigo;

import java.util.List;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class AbrigoServico {
    private final IAbrigoRepositorio abrigoRepositorio;

    public AbrigoServico(IAbrigoRepositorio abrigoRepositorio) {
        this.abrigoRepositorio = abrigoRepositorio;
    }

    public void salvar(Abrigo abrigo) {
        notNull(abrigo, "Abrigo não pode ser nulo");
        abrigoRepositorio.salvar(abrigo);
    }

    public void editar(Abrigo abrigo) {
        notNull(abrigo, "Abrigo não pode ser nulo");
        abrigoRepositorio.editar(abrigo);
    }

    public Abrigo obterPorId(IdAbrigo id) {
        notNull(id, "Id do abrigo não pode ser nulo");
        notEmpty(id.toString(), "Id do abrigo não pode ser vazio");

        return abrigoRepositorio.obterPorId(id);
    }

    public void remover(IdAbrigo id) {
        notNull(id, "Id do abrigo não pode ser nulo");
        notEmpty(id.toString(), "Id do abrigo não pode ser vazio");

        abrigoRepositorio.remover(id);
    }

    public List<Abrigo> listarAbrigos() {
        return abrigoRepositorio.listarAbrigos();
    }

    public Abrigo obterPorNome(String nome) {
        notEmpty(nome, "Nome do abrigo não pode ser vazio");
        return abrigoRepositorio.obterPorNome(nome);
    }
}
