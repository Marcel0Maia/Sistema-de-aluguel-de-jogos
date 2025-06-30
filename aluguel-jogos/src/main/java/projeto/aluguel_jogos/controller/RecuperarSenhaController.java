package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.aluguel_jogos.model.Usuario;
import projeto.aluguel_jogos.service.RecuperarSenhaService;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/recuperar-senha")
public class RecuperarSenhaController {

    @Autowired
    private RecuperarSenhaService recuperarSenhaService;

    @PostMapping
    public ResponseEntity<?> recuperarSenha(@RequestBody Map<String, String> body) {
        String nome = body.get("nome");
        String email = body.get("email");
        String dataNascimentoStr = body.get("dataNascimento");

        if (nome == null || email == null || dataNascimentoStr == null) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Dados incompletos"));
        }

        LocalDate dataNascimento;
        try {
            dataNascimento = LocalDate.parse(dataNascimentoStr);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Data de nascimento inválida"));
        }

        Optional<Usuario> usuarioOpt = recuperarSenhaService.recuperarSenhaProvisoria(nome, email, dataNascimento);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            // Retorna também a senha provisória na resposta
            return ResponseEntity.ok(Map.of(
                    "email", usuario.getEmail(),
                    "senhaProvisoria", usuario.getSenha(),
                    "mensagem", "Senha provisória gerada"
            ));
        } else {
            return ResponseEntity.status(404).body(Map.of("erro", "Usuário não encontrado com os dados informados"));
        }
    }
}
