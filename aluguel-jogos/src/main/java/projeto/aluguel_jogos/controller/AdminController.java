package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projeto.aluguel_jogos.service.JogoService;
import projeto.aluguel_jogos.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JogoService jogoService;

    // Alterar status admin de um usuário
    @PostMapping("/usuario/{id}/admin")
    public String alterarAdmin(@PathVariable Long id,
                               @RequestParam boolean isAdmin,
                               RedirectAttributes redirectAttributes) {
        try {
            userService.alterarPermissaoAdmin(id, isAdmin);
            redirectAttributes.addFlashAttribute("mensagem", "Permissão atualizada!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/painel-admin/usuarios";
    }

    // Alterar jogo em destaque
    @PostMapping("/jogo/{id}/destaque")
    public String atualizarDestaque(@PathVariable Long id,
                                    @RequestParam boolean emDestaque,
                                    RedirectAttributes redirectAttributes) {
        try {
            jogoService.atualizarDestaque(id, emDestaque);
            redirectAttributes.addFlashAttribute("mensagem", "Jogo atualizado!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/painel-admin/jogos";
    }

    // Alterar jogo no carrossel
    @PostMapping("/jogo/{id}/carrossel")
    public String atualizarCarrossel(@PathVariable Long id,
                                     @RequestParam boolean noCarrossel,
                                     RedirectAttributes redirectAttributes) {
        try {
            jogoService.atualizarCarrossel(id, noCarrossel);
            redirectAttributes.addFlashAttribute("mensagem", "Jogo atualizado!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/painel-admin/jogos";
    }
}