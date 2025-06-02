package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import projeto.aluguel_jogos.model.Jogo;
import projeto.aluguel_jogos.repository.JogoRepository;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoRepository jogoRepository;

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
            return jogoRepository.save(jogo);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarJogo(@PathVariable Long id) {
        jogoRepository.deleteById(id);
    }
}
