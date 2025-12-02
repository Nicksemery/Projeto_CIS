package Cis.api.domain.dtos.response;

import lombok.Data;

public record PsicologoDtoRespons(
        Long id,
        Long idCoordenacao,
        String nome,
        String matricula

) {

}
