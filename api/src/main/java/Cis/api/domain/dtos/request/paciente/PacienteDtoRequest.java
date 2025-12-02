package Cis.api.domain.dtos.request.paciente;

import Cis.api.domain.dtos.request.TokenDtoRequest;
import Cis.api.domain.enums.DiaSemana;
import Cis.api.domain.enums.HorarioAgendamento;

public record PacienteDtoRequest(
        String nome,
        String telefone,
        Long idCoordenacao,
        DiaSemana disponibilidadeData, // Novo campo
        HorarioAgendamento disponibilidadeHorario
) {
}
