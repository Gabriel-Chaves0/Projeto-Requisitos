package lp.adocao.dominio.abrigo;

import java.util.List;

public interface IAbrigoRepository {
    void adicionarAbrigo(Abrigo abrigo);
    void atualizarAbrigo(Abrigo abrigo);
    void removerAbrigo(IdAbrigo idAbrigo);
    Abrigo buscarAbrigo(IdAbrigo idAbrigo);
    List<Abrigo> listarAbrigos();
}
