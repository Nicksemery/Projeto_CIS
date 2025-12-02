/*
package Cis.api.infra.service.impl;

import Cis.api.domain.dtos.request.TokenDtoRequest;
import Cis.api.domain.entity.Usuario;
import Cis.api.domain.enums.Roles;
import Cis.api.infra.repository.UsuarioRepository;
import Cis.api.infra.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario criarUsuario(TokenDtoRequest dto, Roles role) {

        var senhaCript = passwordEncoder.encode(dto.senha());

        Usuario usuario = new Usuario(dto.login(), senhaCript);

        return usuarioRepository.save(usuario);
    }
}

 */
