package projeto.aluguel_jogos.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import projeto.aluguel_jogos.model.Usuario;
import projeto.aluguel_jogos.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    public Optional<Usuario> buscarUsuarioEmail(@PathVariable Long id, String email) {
        return usuarioRepository.findByEmail(email);
    }
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario
            usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            if (usuarioAtualizado.getNome() != null) {
                usuario.setNome(usuarioAtualizado.getNome());
            }
            if (usuarioAtualizado.getEmail() != null) {
                usuario.setEmail(usuarioAtualizado.getEmail());
            }
            if (usuarioAtualizado.getSenha() != null) {
                usuario.setSenha(usuarioAtualizado.getSenha());
            }
// Atualiza isAdmin se enviado
            usuario.setIsAdmin(usuarioAtualizado.getIsAdmin());
            return usuarioRepository.save(usuario);
        }).orElse(null);
    }
    @PutMapping("/{id}/admin")
    public void atualizarIsAdmin(@PathVariable Long id, @RequestBody Map<String, Boolean>
            body) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"));
        Boolean novoValor = body.get("isAdmin");
        if (novoValor != null) {
            usuario.setIsAdmin(novoValor);
            usuarioRepository.save(usuario);
        }
    }
    @GetMapping("/usuario-logado")
    public Usuario usuarioLogado(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        System.out.println("Usuario na sessão no /usuario-logado: " + usuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado na sessão!");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não logado");
        }
        return usuario;
    }
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    @PutMapping("/atualizar-icone")
    public ResponseEntity<?> atualizarIcone(@RequestBody Map<String, String> payload, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String novaFoto = payload.get("foto");
        usuario.setFoto(novaFoto);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/historico")
    @ResponseBody
    public List<Map<String, Object>> getHistoricoDoUsuario(@RequestParam Long usuarioId) {
        String sql = """
            SELECT h.data_inicio, h.data_fim, j.nome AS nome_jogo
            FROM HISTORICO h
            JOIN JOGO j ON h.jogo_id = j.id
            WHERE h.usuario_id = ?
            ORDER BY h.data_inicio DESC
        """;

        return jdbcTemplate.query(sql, new Object[]{usuarioId}, (rs, rowNum) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("nome_jogo", rs.getString("nome_jogo"));
            map.put("data_inicio", rs.getDate("data_inicio").toString());
            map.put("data_fim", rs.getDate("data_fim") != null ? rs.getDate("data_fim").toString() : null);
            return map;
        });
    }
}