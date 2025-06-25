package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.aluguel_jogos.model.Biblioteca;
import projeto.aluguel_jogos.repository.BibliotecaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @GetMapping("/usuario/{usuarioId}")
    public List<Biblioteca> listarPorUsuario(@PathVariable Long usuarioId) {
        return bibliotecaRepository.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public Biblioteca adicionar(@RequestBody Biblioteca biblioteca) {
        return bibliotecaRepository.save(biblioteca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        bibliotecaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
