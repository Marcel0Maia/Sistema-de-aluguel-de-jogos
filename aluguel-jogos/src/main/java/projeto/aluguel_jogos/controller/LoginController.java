package projeto.aluguel_jogos.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/")
    public String root() {
        return "redirect:/html/login.html";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session) {
        if ("admin".equals(username) && "123".equals(password)) {
            session.setAttribute("usuarioLogado", username);
            return "redirect:/home";
        } else {
            // redireciona para login.html com parâmetro de erro na URL (simples)
            return "redirect:/login.html?error=true";
        }
    }

    @GetMapping("/home")
    @ResponseBody
    public String home(HttpSession session) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "<h3>Você não está logado. <a href='/login.html'>Entrar</a></h3>";
        }
        return "<h1>Bem-vindo, " + session.getAttribute("usuarioLogado") + "!</h1>" +
                "<a href='/logout'>Sair</a>";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }
}
