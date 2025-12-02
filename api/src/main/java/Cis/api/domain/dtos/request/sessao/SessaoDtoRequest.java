package Cis.api.domain.dtos.request.sessao;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record SessaoDtoRequest(
        @NotNull
        Long idPsicologo,

        @NotNull
        Long idCoordenacao, // Coordenacao que gerencia esta sessão

        @NotNull
        // Use @JsonFormat no Controller para garantir o formato ISO
        LocalDateTime dataHora
) {
}
