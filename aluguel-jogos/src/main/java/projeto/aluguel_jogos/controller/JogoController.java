package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import projeto.aluguel_jogos.model.Jogo;
import projeto.aluguel_jogos.repository.JogoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jogos")
public class JogoController {
    @Autowired
    private JogoRepository jogoRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping
    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }
    @PostMapping
    public Jogo criarJogo(@RequestBody Jogo jogo) {
        return jogoRepository.save(jogo);
    }
    @GetMapping("/{id}")
    public Jogo buscarJogo(@PathVariable Long id) {
        return jogoRepository.findById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public Jogo atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogoAtualizado) {
        return jogoRepository.findById(id).map(jogo -> {
            jogo.setNome(jogoAtualizado.getNome());
            jogo.setDescricao(jogoAtualizado.getDescricao());
            jogo.setRequisitosSistema(jogoAtualizado.getRequisitosSistema());
            jogo.setDesenvolvedor(jogoAtualizado.getDesenvolvedor());
            jogo.setPublicador(jogoAtualizado.getPublicador());
            jogo.setGenero(jogoAtualizado.getGenero());
            jogo.setPreco(jogoAtualizado.getPreco());
            jogo.setEmDestaque(jogoAtualizado.isEmDestaque());
            jogo.setNoCarrossel(jogoAtualizado.isNoCarrossel());
            return jogoRepository.save(jogo);
        }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void deletarJogo(@PathVariable Long id) {
        jogoRepository.deleteById(id);
    }

    @PostMapping("/historico")
    public ResponseEntity<?> registrarAluguel(@RequestBody Map<String, Object> dados) {
        try {
            Map<String, Object> usuario = (Map<String, Object>) dados.get("usuario");
            Map<String, Object> jogo = (Map<String, Object>) dados.get("jogo");
            int tempo = (int) dados.get("tempoAluguel");

            Long usuarioId = Long.valueOf(usuario.get("id").toString());
            Long jogoId = Long.valueOf(jogo.get("id").toString());

            String dataInicioStr = dados.get("dataInicio").toString();
            String dataFimStr = dados.get("dataFim").toString();

            LocalDate dataInicio = LocalDate.parse(dataInicioStr.substring(0, 10));
            LocalDate dataFim = LocalDate.parse(dataFimStr.substring(0, 10));

            jdbcTemplate.update("""
                INSERT INTO HISTORICO (usuario_id, jogo_id, data_inicio, data_fim)
                VALUES (?, ?, ?, ?)
            """, usuarioId, jogoId, dataInicio, dataFim);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao registrar histórico: " + e.getMessage());
        }
    }
    @GetMapping("/buscar")
    public List<Jogo> buscarPorNome(@RequestParam String nome) {
        return jogoRepository.findByNomeContainingIgnoreCase(nome);
    }

}