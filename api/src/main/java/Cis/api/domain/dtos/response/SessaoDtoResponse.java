package Cis.api.domain.dtos.response;

import Cis.api.domain.enums.SessaoStatus;

import java.time.LocalDateTime;

public record SessaoDtoResponse(Long id,
                                Long idPaciente,
                                Long idPsicologo,
                                Long idCoordenacao,
                                LocalDateTime dataHora,
                                SessaoStatus status,
                                LocalDateTime dataAprovacao) {
}
