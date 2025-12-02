package Cis.api.domain.dtos.response;

import Cis.api.domain.enums.Cargos;

public record CoordenacaoDtoResponse(
        Long id,
        String nome,
        String matricula,
        String email
){

}
