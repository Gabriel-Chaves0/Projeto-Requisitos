package lp.jpa.adocao.abrigo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AbrigoJpaRepositorio extends JpaRepository<AbrigoJpa, Integer>{
    List<AbrigoJpa> findAll();
    AbrigoJpa findBynomeAbrigo(String nome);
}
