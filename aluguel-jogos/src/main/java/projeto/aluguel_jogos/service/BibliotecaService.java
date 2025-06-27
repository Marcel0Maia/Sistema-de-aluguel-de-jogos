package projeto.aluguel_jogos.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.aluguel_jogos.model.Biblioteca;
import projeto.aluguel_jogos.repository.BibliotecaRepository;


import java.util.List;
import java.util.Optional;


@Service
public class BibliotecaService {


    @Autowired
    private BibliotecaRepository bibliotecaRepository;


    // Salvar nova entrada na biblioteca (alugar jogo)
    public Biblioteca salvar(Biblioteca biblioteca) {
        Long usuarioId = biblioteca.getUsuario().getId();
        Long jogoId = biblioteca.getJogo().getId();


        Optional<Biblioteca> existente = bibliotecaRepository.findByUsuarioIdAndJogoId(usuarioId, jogoId);


        if (existente.isPresent()) {
            throw new RuntimeException("Jogo já está cadastrado para este usuário.");
        }


        return bibliotecaRepository.save(biblioteca);
    }


    // Listar todos os itens da biblioteca de um usuário
    public List<Biblioteca> listarPorUsuario(Long usuarioId) {
        return bibliotecaRepository.findByUsuarioId(usuarioId);
    }


    // Estender o aluguel (exemplo: +7 dias)
    public Biblioteca estenderAluguel(Long id, int dias) {
        Biblioteca b = bibliotecaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Biblioteca não encontrada com id: " + id));
        b.setDataFim(b.getDataFim().plusDays(dias));
        return bibliotecaRepository.save(b);
    }


    // Deletar um item da biblioteca pelo id
    public void deletar(Long id) {
        bibliotecaRepository.deleteById(id);
    }
}
