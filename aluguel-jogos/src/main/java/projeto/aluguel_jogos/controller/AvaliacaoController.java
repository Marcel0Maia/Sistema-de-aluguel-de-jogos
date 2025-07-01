package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.aluguel_jogos.model.Avaliacao;
import projeto.aluguel_jogos.repository.AvaliacaoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
    }

    @GetMapping("/jogo/{jogoId}")
    public ResponseEntity<List<Avaliacao>> listarPorJogo(@PathVariable Long jogoId) {
        return ResponseEntity.ok(avaliacaoRepository.findByJogoId(jogoId));
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes() {
        return ResponseEntity.ok(avaliacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable Long id) {
        Optional<Avaliacao> optional = avaliacaoRepository.findById(id);
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable Long id, @RequestBody Avaliacao novaAvaliacao) {
        Optional<Avaliacao> optional = avaliacaoRepository.findById(id);

        if (optional.isPresent()) {
            Avaliacao avaliacaoExistente = optional.get();
            avaliacaoExistente.setNota(novaAvaliacao.getNota());
            avaliacaoExistente.setComentario(novaAvaliacao.getComentario());
            avaliacaoExistente.setDataAvaliacao(novaAvaliacao.getDataAvaliacao());
            avaliacaoExistente.setUsuario(novaAvaliacao.getUsuario());
            avaliacaoExistente.setJogo(novaAvaliacao.getJogo());

            avaliacaoRepository.save(avaliacaoExistente);
            return ResponseEntity.ok(avaliacaoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Long id) {
        if (avaliacaoRepository.existsById(id)) {
            avaliacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
