package Cis.api.domain.dtos.request.paciente;

import Cis.api.domain.enums.DiaSemana;
import Cis.api.domain.enums.HorarioAgendamento;

public record PacienteDtoUpdateRequest(
        String nome,
        String telefone,
        DiaSemana disponibilidadeData, // Novo campo
        HorarioAgendamento disponibilidadeHorario
        ) {
}
