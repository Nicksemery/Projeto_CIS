package Cis.api.domain.dtos.request.sessao;

import Cis.api.domain.enums.DiaSemana;
import Cis.api.domain.enums.HorarioAgendamento;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record SessaoDtoRequest(
        Long idPsicologo,

        Long idPaciente,

        Long idCoordenacao, // Coordenacao que gerencia esta sessão

        // O campo deve ser a data/hora exata do agendamento, não a disponibilidade
        LocalDateTime dataHoraAgendada
) {
}
