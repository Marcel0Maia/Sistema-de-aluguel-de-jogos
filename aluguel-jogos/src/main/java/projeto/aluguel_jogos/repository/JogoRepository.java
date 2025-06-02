package projeto.aluguel_jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.aluguel_jogos.model.Jogo;

import java.util.List;

// Interface de repositório para operações CRUD de Jogo
public interface JogoRepository extends JpaRepository<Jogo, Long> {
    List<Jogo> findByEmDestaqueTrue();
    List<Jogo> findByNoCarrosselTrue();
}
