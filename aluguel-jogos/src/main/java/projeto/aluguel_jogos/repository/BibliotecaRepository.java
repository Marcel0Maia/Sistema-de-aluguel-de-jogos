package projeto.aluguel_jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.aluguel_jogos.model.Biblioteca;

import java.util.List;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

    // Listar todos os itens da biblioteca de um usuário
    List<Biblioteca> findByUsuarioId(Long usuarioId);
}
