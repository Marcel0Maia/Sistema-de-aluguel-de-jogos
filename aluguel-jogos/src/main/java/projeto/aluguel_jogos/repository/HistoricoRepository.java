package projeto.aluguel_jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.aluguel_jogos.model.Historico;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    // Listar histórico de um usuário
    List<Historico> findByUsuarioId(Long usuarioId);
}
