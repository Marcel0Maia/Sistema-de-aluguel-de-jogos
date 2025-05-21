package projeto.aluguel_jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.aluguel_jogos.model.Jogo;

// Interface de repositório para operações CRUD de Jogo
public interface JogoRepository extends JpaRepository<Jogo, Long> {
}
