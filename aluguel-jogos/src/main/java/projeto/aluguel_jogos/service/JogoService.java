package projeto.aluguel_jogos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.aluguel_jogos.model.Jogo;
import projeto.aluguel_jogos.repository.JogoRepository;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public void atualizarDestaque(Long jogoId, boolean emDestaque) throws Exception {
        Jogo jogo = jogoRepository.findById(jogoId)
                .orElseThrow(() -> new Exception("Jogo não encontrado"));

        jogo.setEmDestaque(emDestaque);
        jogoRepository.save(jogo);
    }

    public void atualizarCarrossel(Long jogoId, boolean noCarrossel) throws Exception {
        Jogo jogo = jogoRepository.findById(jogoId)
                .orElseThrow(() -> new Exception("Jogo não encontrado"));

        jogo.setNoCarrossel(noCarrossel);
        jogoRepository.save(jogo);
    }
}