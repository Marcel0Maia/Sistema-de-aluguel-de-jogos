package projeto.aluguel_jogos.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projeto.aluguel_jogos.model.Usuario;
import projeto.aluguel_jogos.repository.UsuarioRepository;
import projeto.aluguel_jogos.service.UserService;

import java.time.LocalDate;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UserService userService;

    // DTO (Data Transfer Object) simples para o corpo da requisição de login
    record LoginRequest(String email, String senha) {}

    @GetMapping("/")
    public String root() {
        return "redirect:/html/login.html";
    }

    @GetMapping("/cadastro")
    public String showCadastroPage() {
        return "cadastro"; // "Retorna o nome do arquivo "
    }

    @PostMapping("/cadastro")
    public String cadastrar(@RequestParam String nome,
                            @RequestParam String email,
                            @RequestParam String senha,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataNascimento,
                            RedirectAttributes redirectAttributes) {
        try {
            userService.cadastrarUsuario(nome, email, senha, dataNascimento);
            return "redirect:/html/login.html";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cadastro?error=true";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // "Retorna o nome do arquivo "
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session, RedirectAttributes redirectAttributes) throws Exception{
        try {
            userService.usuarioValidarLogin(email, password);
            return "redirect:/html/home.html";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "redirect:/html/home.html"; // Retorna o nome do arquivo "home.html"
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/html/login.html";
    }
}
