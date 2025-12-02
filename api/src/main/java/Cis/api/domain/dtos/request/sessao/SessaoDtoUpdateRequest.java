package Cis.api.domain.dtos.request.sessao;

import Cis.api.domain.enums.DiaSemana;
import Cis.api.domain.enums.HorarioAgendamento;
import Cis.api.domain.enums.SessaoStatus;

public record SessaoDtoUpdateRequest(
        SessaoStatus status,
        DiaSemana diaSemana,
        HorarioAgendamento horarioAgendamento
) {
}
