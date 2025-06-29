package projeto.aluguel_jogos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HistoricoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void registrarHistorico(LocalDate data_fim, LocalDate data_inicio, Long jogo_id, Long usuario_id) {
        String sql = "INSERT INTO historico (usuario_id, acao, data) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, data_fim, data_inicio, jogo_id, usuario_id);
    }
}
