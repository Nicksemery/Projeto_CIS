package Cis.api.controllers;

import Cis.api.domain.dtos.request.TokenDtoRequest;
import Cis.api.domain.dtos.response.TokenDtoResponse;
import Cis.api.domain.dtos.response.UsuarioDtoResponse;
import Cis.api.domain.entity.Usuario;
import Cis.api.infra.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid TokenDtoRequest dados, UriComponentsBuilder uriBuilder){
        if (usuarioRepository.findByLogin(dados.login()).isPresent()){
            return ResponseEntity.badRequest().body("login já cadastrado");
        }
        //criptografar a senha
        var senhaCriptografada = passwordEncoder.encode(dados.senha());


        // Criar o usuário com o login e senha criptografada
        // foi criado um construtor en Usuario
        var usuario = new Usuario(dados.login(), senhaCriptografada);

        // Salvar o usuário no banco de dados
        usuarioRepository.save(usuario);

        // Criar o URI para o novo usuário
        var uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        // Retornar resposta com status 201 e os dados do usuário criado
        return ResponseEntity.created(uri).body(new UsuarioDtoResponse(usuario));
    }
}
