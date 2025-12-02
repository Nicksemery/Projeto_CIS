package Cis.api.controllers;

import Cis.api.domain.dtos.request.paciente.PacienteDtoRequest;
import Cis.api.domain.dtos.request.paciente.PacienteDtoUpdateRequest;
import Cis.api.domain.dtos.response.PacienteDtoResponse;
import Cis.api.infra.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService service;
    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PacienteDtoResponse> cadastrarPaciente(@RequestBody PacienteDtoRequest dto, UriComponentsBuilder uriBuilder) {
        PacienteDtoResponse dtoResponse = service.cadastrarPaciente(dto);

        URI uri = uriBuilder.path("/paciente/{id}").buildAndExpand(dtoResponse.id()).toUri();

        return ResponseEntity.created(uri).body(dtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDtoResponse>> listarPacientes() {
        var lista = service.listarPacientes();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<PacienteDtoResponse> buscarPacientePorId(@PathVariable Long id) {
        var list = service.buscarPacientePorId(id);
        return ResponseEntity.ok(list);
    }


    @GetMapping("{nome}")
    public ResponseEntity<PacienteDtoResponse> buscarPacientePorNome(@PathVariable String nome) {
        var list = service.buscarPacientePorNome(nome);
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<PacienteDtoResponse> atualizarPaciente(@RequestParam @PathVariable Long id, @RequestBody PacienteDtoUpdateRequest dto) {
        var atualizacao = service.atualizarPaciente(id,dto);
        return ResponseEntity.ok(atualizacao);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PacienteDtoResponse> deletarPacientePorId(@RequestParam @PathVariable Long id) {
        service.deletarPacientePorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{nome}")
    public ResponseEntity<PacienteDtoResponse> deletarPorNome(@PathVariable @RequestParam String nome) {
        service.deletarPacientePorNome(nome);
        return ResponseEntity.noContent().build();
    }


}
