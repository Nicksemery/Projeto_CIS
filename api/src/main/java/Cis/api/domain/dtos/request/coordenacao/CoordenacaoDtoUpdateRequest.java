package Cis.api.domain.dtos.request.coordenacao;

import Cis.api.domain.enums.Cargos;

public record CoordenacaoDtoUpdateRequest(
        String nome,
        String email,
        String matricula
) {
}
