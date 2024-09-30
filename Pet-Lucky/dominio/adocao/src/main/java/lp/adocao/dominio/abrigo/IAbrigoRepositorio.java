package lp.adocao.dominio.abrigo;

import java.util.List;

public interface IAbrigoRepositorio {
    void salvar(Abrigo abrigo);
    void editar(Abrigo abrigo);
    void remover(IdAbrigo idAbrigo);
    Abrigo obterPorId(IdAbrigo idAbrigo);
    List<Abrigo> listarAbrigos();
    Abrigo obterPorNome(String nome);
}
