package projeto.aluguel_jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.aluguel_jogos.model.Usuario;

import java.time.LocalDate;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNomeAndEmailAndDataNascimento(String nome, String email, LocalDate dataNascimento);
    Optional<Usuario> findByEmailIgnoreCase(String email);
}