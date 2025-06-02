package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.aluguel_jogos.model.Jogo;
import projeto.aluguel_jogos.repository.JogoRepository;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private JogoRepository jogoRepository;

    @GetMapping("/destaque")
    public List<Jogo> getJogosEmDestaque() {
        return jogoRepository.findByEmDestaqueTrue();
    }

    @GetMapping("/carrossel")
    public List<Jogo> getJogosNoCarrossel() {
        return jogoRepository.findByNoCarrosselTrue();
    }

}
