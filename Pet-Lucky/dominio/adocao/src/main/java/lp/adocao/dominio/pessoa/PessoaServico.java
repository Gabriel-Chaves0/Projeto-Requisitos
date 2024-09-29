package lp.adocao.dominio.pessoa;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class PessoaServico {
    private final PessoaRepositorio pessoaRepositorio;

    public PessoaServico(PessoaRepositorio pessoaRepositorio) {
        this.pessoaRepositorio = pessoaRepositorio;
    }

    public void salvar(Pessoa pessoa) {
        notNull(pessoa, "Pessoa não pode ser nula");

        pessoaRepositorio.salvar(pessoa);
    }

    public void editar(Pessoa pessoa) {
        notNull(pessoa, "Pessoa não pode ser nula");

        pessoaRepositorio.editar(pessoa);
    }

    public Pessoa obterPorId(IdPessoa idPessoa) {
        notNull(idPessoa, "Id da pessoa não pode ser nulo");
        notEmpty(idPessoa.toString(), "Id da pessoa não pode ser vazio");

        return pessoaRepositorio.obterPorId(idPessoa);
    }
}
