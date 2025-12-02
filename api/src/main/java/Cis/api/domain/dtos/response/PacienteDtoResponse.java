package Cis.api.domain.dtos.response;

public record PacienteDtoResponse(
        Long id,
        Long idCoordenacao,
        String nome,
        String telefone

) {
}
