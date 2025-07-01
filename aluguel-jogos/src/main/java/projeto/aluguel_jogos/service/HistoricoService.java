package projeto.aluguel_jogos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HistoricoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void registrarHistorico(LocalDate dataFim, LocalDate dataInicio, Long jogoId, Long usuarioId, String tipoAcao) {
        String sql = "INSERT INTO historico (usuario_id, jogo_id, data_inicio, data_fim, tipo_acao) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, usuarioId, jogoId, dataInicio, dataFim, tipoAcao);
    }


}
