package projeto.aluguel_jogos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.aluguel_jogos.model.Usuario;
import projeto.aluguel_jogos.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Cadastra um novo usuário.
     *
     //* @param newUser O usuário a ser cadastrado.
     * @return O usuário salvo.
     * @throws Exception Se o email já estiver em uso.
     */
    public Usuario cadastrarUsuario(String nome, String email, String senha, LocalDate dataNascimento) throws Exception {
        // Verifica se o email já existe no banco
        Optional<Usuario> existente = usuarioRepository.findByEmail(email);
        if (existente.isPresent()) {
            throw new Exception("Usuário já cadastrado");
        }

        Usuario novoUsuario = new Usuario(nome, email, senha, dataNascimento);
        usuarioRepository.save(novoUsuario);
        return novoUsuario;
    }

    /**
     * Valida as credenciais de login.
     *
     * @param email O email do usuário.
     * @param senha A senha do usuário.
     * @return O usuário autenticado.
     * @throws Exception Se o email não for encontrado ou a senha estiver incorreta.
     */
    public Object usuarioValidarLogin(String email, String senha) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuário não encontrado."));

        // Compara a senha fornecida com a senha armazenada
        if (!usuario.getSenha().equals(senha)) {
            throw new Exception("Senha incorreta.");
        }
        return usuario;
    }

    public void alterarPermissaoAdmin(Long usuarioId, boolean novoValor) throws Exception {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        usuario.setAdmin(novoValor);
        usuarioRepository.save(usuario);
    }
}