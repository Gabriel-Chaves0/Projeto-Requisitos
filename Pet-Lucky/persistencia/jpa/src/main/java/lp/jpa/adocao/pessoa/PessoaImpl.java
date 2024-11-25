package lp.jpa.adocao.pessoa;

import lp.adocao.dominio.pessoa.IPessoaRepositorio;
import lp.adocao.dominio.pessoa.IdPessoa;
import lp.adocao.dominio.pessoa.Pessoa;
import lp.jpa.adocao.JpaMapeador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaImpl implements IPessoaRepositorio {
    @Autowired
    private PessoaJpaRepositorio repositorio;

    @Autowired
    private JpaMapeador mapeador;

    @Override
    public void salvar(Pessoa pessoa) {
        // Mapeia o domínio para JPA
        var pessoaJpa = mapeador.map(pessoa, PessoaJpa.class);


        System.out.println("Salvando pessoa: " + pessoaJpa);

        // Salva a entidade no banco de dados
        repositorio.save(pessoaJpa);
    }


    @Override
    public void editar(Pessoa pessoa) {
        PessoaJpa pessoaJpa = mapeador.map(pessoa, PessoaJpa.class);
        repositorio.save(pessoaJpa);
    }

    @Override
    public Pessoa obterPorId(IdPessoa idPessoa) {
        return repositorio.findById(idPessoa.getId()).map(pessoaJpa -> mapeador.map(pessoaJpa, Pessoa.class)).orElse(null);
    }

    @Override
    public Pessoa buscarPorCPF(String cpf) {
        System.out.println("Buscando pessoa com CPF: " + cpf);

        // Busca direta pelo CPF, que é o identificador primário
        var pessoaJpa = repositorio.findByCpf(cpf);
        System.out.println("Pessoa encontrada: " + pessoaJpa);

        // Mapeia a entidade JPA para o domínio
        return mapeador.map(pessoaJpa, Pessoa.class);
    }
}
