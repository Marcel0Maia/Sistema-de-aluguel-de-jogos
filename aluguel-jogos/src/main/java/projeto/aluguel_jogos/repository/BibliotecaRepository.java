package projeto.aluguel_jogos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projeto.aluguel_jogos.model.Biblioteca;


import java.util.List;
import java.util.Optional;


public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {


    // Listar todos os itens da biblioteca de um usuário
    List<Biblioteca> findByUsuarioId(Long usuarioId);


    Optional<Biblioteca> findByUsuarioIdAndJogoId(Long usuarioId, Long jogoId);
}
