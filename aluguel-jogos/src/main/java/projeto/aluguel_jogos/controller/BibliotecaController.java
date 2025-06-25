package projeto.aluguel_jogos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.aluguel_jogos.model.Biblioteca;
import projeto.aluguel_jogos.service.BibliotecaService;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {


    @Autowired
    private BibliotecaService bibliotecaService;


    // Listar biblioteca por usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Biblioteca>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Biblioteca> lista = bibliotecaService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(lista);
    }
    @PostMapping
    public ResponseEntity<?> alugarJogo(@RequestBody Biblioteca biblioteca) {
        biblioteca.setDataInicio(LocalDate.now());


        if (biblioteca.getTempoAluguel() != null) {
            biblioteca.setDataFim(biblioteca.getDataInicio().plusDays(biblioteca.getTempoAluguel()));
        } else {
            biblioteca.setDataFim(biblioteca.getDataInicio().plusDays(7));
        }


        try {
            Biblioteca salva = bibliotecaService.salvar(biblioteca);
            return ResponseEntity.ok(salva);
        } catch (RuntimeException ex) {
            // Retornando JSON com a mensagem de erro
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("erro", ex.getMessage()));
        }
    }






    // Estender aluguel
    @PutMapping("/estender/{id}")
    public ResponseEntity<?> estenderAluguel(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
        Integer dias = body.get("dias");
        if (dias == null || dias <= 0) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Número de dias inválido."));
        }
        try {
            Biblioteca atualizada = bibliotecaService.estenderAluguel(id, dias);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("erro", ex.getMessage()));
        }
    }




    // Deletar item da biblioteca
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        bibliotecaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
