package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projeto.aluguel_jogos.model.Usuario;
import projeto.aluguel_jogos.repository.UsuarioRepository;
import projeto.aluguel_jogos.service.UserService;

import java.time.LocalDate;

@Controller
public class ConfiguracoesController {
   @Autowired
   private UsuarioRepository usuarioRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/usuario")
    public  String showUsuarioPage() {
        return "redirect:/html/usuario.html"; // "Retorna o nome do arquivo "
    }

    @GetMapping("/configuracoes")
    public String mostrarConfiguracoes() {
       return "redirect:/html/configuracoes.html";

   }
    @PostMapping("/configuracoes")
    public String editarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            userService.atualizarUsuario(usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getSenha(),
                    usuario.getDataNascimento());
            return "redirect:/html/login.html";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/html/configuracoes.html?error=true";
        }
    }
}
