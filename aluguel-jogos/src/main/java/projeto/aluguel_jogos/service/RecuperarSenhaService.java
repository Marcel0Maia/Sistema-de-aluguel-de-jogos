package projeto.aluguel_jogos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.aluguel_jogos.model.Usuario;
import projeto.aluguel_jogos.repository.UsuarioRepository;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class RecuperarSenhaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public Optional<Usuario> recuperarSenhaProvisoria(String nome, String email, LocalDate dataNascimento) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNomeAndEmailAndDataNascimento(nome, email, dataNascimento);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            String senhaProvisoria = gerarSenhaAleatoria(8);
            usuario.setSenha(senhaProvisoria); // Aqui você pode querer hashear a senha

            usuarioRepository.save(usuario);

            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    private String gerarSenhaAleatoria(int tamanho) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(tamanho);
        for (int i = 0; i < tamanho; i++) {
            int idx = random.nextInt(CARACTERES.length());
            sb.append(CARACTERES.charAt(idx));
        }
        return sb.toString();
    }
}
