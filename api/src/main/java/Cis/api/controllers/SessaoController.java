package Cis.api.controllers;

import Cis.api.domain.dtos.request.sessao.SessaoDtoRequest;
import Cis.api.domain.dtos.response.SessaoDtoResponse;
import Cis.api.infra.service.SessaoService;
import org.springframework.http.ResponseEntity;
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

    // 1. Agendamento (POST) - A segurança foi removida para testes
    @PostMapping
    public ResponseEntity<SessaoDtoResponse> agendarSessao(
            @RequestBody SessaoDtoRequest dto,
            UriComponentsBuilder uriBuilder) {

        // O Service agora usa o idPaciente que vem no DTO
        SessaoDtoResponse response = service.agendarSessao(dto);

        URI uri = uriBuilder.path("/sessoes/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    // 2. Aprovação (PUT) - A restrição de acesso foi removida para testes
    @PutMapping("/aprovar/{id}")
    public ResponseEntity<SessaoDtoResponse> aprovarSessao(@PathVariable Long id) {
        SessaoDtoResponse response = service.aprovarSessao(id);
        return ResponseEntity.ok(response);
    }
}
