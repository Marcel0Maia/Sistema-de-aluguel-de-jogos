package projeto.aluguel_jogos.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import projeto.aluguel_jogos.model.Biblioteca;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {


    // Listar todos os itens da biblioteca de um usuário
    List<Biblioteca> findByUsuarioId(Long usuarioId);


    Optional<Biblioteca> findByUsuarioIdAndJogoId(Long usuarioId, Long jogoId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Biblioteca b WHERE b.dataFim < :dataAtual")
    void deleteByDataFimBefore(LocalDate dataAtual);
}
