package projeto.aluguel_jogos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import projeto.aluguel_jogos.model.Biblioteca;
import projeto.aluguel_jogos.model.Jogo;
import projeto.aluguel_jogos.model.Usuario;
import projeto.aluguel_jogos.repository.BibliotecaRepository;
import projeto.aluguel_jogos.repository.JogoRepository;
import projeto.aluguel_jogos.repository.UsuarioRepository;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BibliotecaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JogoRepository jogoRepository;

    @BeforeEach
    void limparBiblioteca() {
        bibliotecaRepository.deleteAll();

        // Garante que Usuario e Jogo existem
        if (usuarioRepository.findById(1L).isEmpty()) {
            Usuario u = new Usuario();
            u.setId(1L);
            u.setNome("Usuario Teste");
            u.setEmail("teste@email.com");
            u.setSenha("123");
            usuarioRepository.save(u);
        }

        if (jogoRepository.findById(2L).isEmpty()) {
            Jogo j = new Jogo();
            j.setId(2L);
            j.setNome("Jogo Teste");
            j.setDescricao("Descricao Teste");
            j.setGenero("Aventura");
            j.setPreco(99.90);
            j.setImagemUrl("http://exemplo.com/imagem.jpg");
            jogoRepository.save(j);
        }
    }

    @Test
    void deveAlugarJogo() throws Exception {
        String json = """
            {
                "usuario": { "id": 1 },
                "jogo": { "id": 2 },
                "tempoAluguel": 7
            }
        """;

        mockMvc.perform(post("/biblioteca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deveEstenderAluguel() throws Exception {
        // Antes de estender, cria um aluguel
        Usuario usuario = usuarioRepository.findById(1L).get();
        Jogo jogo = jogoRepository.findById(2L).get();

        Biblioteca b = new Biblioteca();
        b.setUsuario(usuario);
        b.setJogo(jogo);
        b.setDataInicio(LocalDate.now());
        b.setDataFim(LocalDate.now().plusDays(7));
        b.setTempoAluguel(7);
        bibliotecaRepository.save(b);

        Long idBiblioteca = b.getId();

        String json = """
            { "dias": 3 }
        """;

        mockMvc.perform(put("/biblioteca/estender/{id}", idBiblioteca)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
