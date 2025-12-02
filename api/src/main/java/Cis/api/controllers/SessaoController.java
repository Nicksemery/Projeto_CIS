package Cis.api.controllers;

import Cis.api.domain.dtos.request.sessao.SessaoDtoRequest;
import Cis.api.domain.dtos.response.SessaoDtoResponse;
import Cis.api.domain.entity.Usuario;
import Cis.api.infra.service.SessaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/sessoes")
public class SessaoController {

    private final SessaoService service;

    public SessaoController(SessaoService service) {
        this.service = service;
    }

    // 1. Agendamento (POST) - Apenas ROLE_PACIENTE
    @PostMapping
    public ResponseEntity<SessaoDtoResponse> agendarSessao(
            @RequestBody SessaoDtoRequest dto,
            // O Spring Security injeta o Usuario logado:
            @AuthenticationPrincipal Usuario usuarioLogado,
            UriComponentsBuilder uriBuilder) {

        // O Service usará o ID para buscar o Paciente associado
        SessaoDtoResponse response = service.agendarSessao(usuarioLogado.getId(), dto);

        URI uri = uriBuilder.path("/sessoes/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    // 2. Aprovação (PUT) - Apenas ROLE_COORDENACAO
    @PutMapping("/aprovar/{id}")
    public ResponseEntity<SessaoDtoResponse> aprovarSessao(@PathVariable Long id) {
        // O SecurityConfig deve bloquear quem não for COORDENACAO
        SessaoDtoResponse response = service.aprovarSessao(id);
        return ResponseEntity.ok(response);
    }
}
