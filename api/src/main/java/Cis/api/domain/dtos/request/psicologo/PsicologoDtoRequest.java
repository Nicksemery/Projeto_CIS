package Cis.api.domain.dtos.request.psicologo;

import Cis.api.domain.dtos.request.TokenDtoRequest;

public record PsicologoDtoRequest(
        String nome,
        String matricula,
        Long idCoordenacao
) {
}
