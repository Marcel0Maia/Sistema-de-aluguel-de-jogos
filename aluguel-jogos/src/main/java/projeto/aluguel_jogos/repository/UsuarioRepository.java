package projeto.aluguel_jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.aluguel_jogos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
