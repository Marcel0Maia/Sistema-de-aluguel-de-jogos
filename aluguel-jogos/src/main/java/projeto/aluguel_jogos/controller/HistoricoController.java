package projeto.aluguel_jogos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.aluguel_jogos.service.HistoricoService;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @PostMapping
    public ResponseEntity<?> registrarHistorico(@RequestBody Map<String, Object> dados) {
        try {
            Map<String, Object> usuario = (Map<String, Object>) dados.get("usuario");
            Map<String, Object> jogo = (Map<String, Object>) dados.get("jogo");

            Long usuarioId = Long.valueOf(usuario.get("id").toString());
            Long jogoId = Long.valueOf(jogo.get("id").toString());

            String dataInicioStr = dados.get("dataInicio").toString();
            String dataFimStr = dados.get("dataFim").toString();
            String tipoAcao = dados.get("tipoAcao").toString(); // deve ser enviado do frontend

            LocalDate dataInicio = LocalDate.parse(dataInicioStr.substring(0, 10));
            LocalDate dataFim = LocalDate.parse(dataFimStr.substring(0, 10));

            historicoService.registrarHistorico(dataFim, dataInicio, jogoId, usuarioId, tipoAcao);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao registrar histórico: " + e.getMessage());
        }
    }


}
