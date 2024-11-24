package lp.jpa.adocao.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJpaRepositorio extends JpaRepository<PessoaJpa, Integer>{
    PessoaJpa findBycpf(String cpf);
}
