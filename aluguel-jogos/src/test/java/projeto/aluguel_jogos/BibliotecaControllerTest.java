package projeto.aluguel_jogos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import projeto.aluguel_jogos.model.Biblioteca;
import projeto.aluguel_jogos.model.Jogo;
import projeto.aluguel_jogos.model.Usuario;
import projeto.aluguel_jogos.repository.BibliotecaRepository;
import projeto.aluguel_jogos.repository.JogoRepository;
import projeto.aluguel_jogos.repository.UsuarioRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BibliotecaControllerTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Test
    void deveCadastrarBibliotecaParaUsuarioEJogo() {
        // Criar e salvar um usuário
        Usuario usuario = new Usuario();
        usuario.setNome("Fulano");
        usuario.setEmail("fulano@teste.com");
        usuario = usuarioRepository.save(usuario);

        // Criar e salvar um jogo
        Jogo jogo = new Jogo();
        jogo.setNome("Super Mario");
        jogo.setDescricao("Um jogo clássico.");
        jogo.setGenero("Plataforma");
        jogo.setPreco(49.90);
        jogo.setRequisitosSistema("Windows 10");
        jogo.setDesenvolvedor("Nintendo");
        jogo.setPublicador("Nintendo");
        jogo.setImagemUrl("http://exemplo.com/supermario.png");
        jogo = jogoRepository.save(jogo);

        // Criar e salvar a biblioteca associada ao usuário e ao jogo
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setUsuario(usuario);
        biblioteca.setJogo(jogo);
        biblioteca = bibliotecaRepository.save(biblioteca);

        // Verificar se foi persistido corretamente
        assertThat(biblioteca.getId()).isNotNull();
        assertThat(biblioteca.getUsuario().getId()).isEqualTo(usuario.getId());
        assertThat(biblioteca.getJogo().getId()).isEqualTo(jogo.getId());}
}