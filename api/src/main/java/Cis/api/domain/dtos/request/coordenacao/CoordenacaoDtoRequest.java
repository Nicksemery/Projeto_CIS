package Cis.api.domain.dtos.request.coordenacao;

import Cis.api.domain.dtos.request.TokenDtoRequest;
import Cis.api.domain.enums.Cargos;
import org.antlr.v4.runtime.misc.NotNull;

public record CoordenacaoDtoRequest (

        Long idUsuario,
        String nome,

        String email,

        String matricula, // Campo Matricula

        Cargos cargo, // Campo Cargo (Enum)

        TokenDtoRequest dadosUsuario
) {
}
