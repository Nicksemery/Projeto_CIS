/*
package Cis.api.controllers;

import Cis.api.domain.dtos.request.TokenDtoRequest;
import Cis.api.domain.dtos.response.TokenDtoResponse;
import Cis.api.domain.entity.Usuario;
import Cis.api.infra.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {


    private final AuthenticationManager manager;
    private final TokenService service;

    public AutenticacaoController(AuthenticationManager manager ,TokenService service) {
        this.manager = manager;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TokenDtoResponse> efetuarLogin(@RequestBody TokenDtoRequest dados){
        // 1. Cria o objeto de autenticação (sem estar autenticado ainda)
        var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        // 2. O manager chama o CustomUserDetailsService e valida a senha
        var authentication = manager.authenticate(token);
        // 3. Se autenticado, gera o token JWT
        var tokenJWT = service.gerarToken((Usuario)authentication.getPrincipal());
        // 4. Retorna o token para o cliente
        return ResponseEntity.ok(new TokenDtoResponse(tokenJWT));
    }
}

 */
