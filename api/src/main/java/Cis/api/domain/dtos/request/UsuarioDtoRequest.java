package Cis.api.domain.dtos.request;

public record UsuarioDtoRequest(
        String login,
        String senha,
        String permissao
) {
}
