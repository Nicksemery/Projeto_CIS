package Cis.api.domain.dtos.response;

import Cis.api.domain.entity.Usuario;

public record UsuarioDtoResponse(Long id, String login) {
    public UsuarioDtoResponse(Usuario usuario){
        this(usuario.getId(), usuario.getLogin());
    }
}
