package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.aluguel_jogos.model.Historico;
import projeto.aluguel_jogos.repository.HistoricoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/historico")
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;

    @GetMapping("/usuario/{usuarioId}")
    public List<Historico> listarPorUsuario(@PathVariable Long usuarioId) {
        return historicoRepository.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public Historico adicionar(@RequestBody Historico historico) {
        return historicoRepository.save(historico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        historicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
