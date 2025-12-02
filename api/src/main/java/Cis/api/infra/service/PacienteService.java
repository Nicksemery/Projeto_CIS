package Cis.api.infra.service;

import Cis.api.domain.dtos.request.paciente.PacienteDtoRequest;
import Cis.api.domain.dtos.request.paciente.PacienteDtoUpdateRequest;
import Cis.api.domain.dtos.response.PacienteDtoResponse;

import java.util.List;

public interface PacienteService {

    PacienteDtoResponse cadastrarPaciente(PacienteDtoRequest dto);

    List<PacienteDtoResponse> listarPacientes();

    PacienteDtoResponse buscarPacientePorId(Long id);

    PacienteDtoResponse buscarPacientePorNome(String nome);

    PacienteDtoResponse atualizarPaciente(Long id ,PacienteDtoUpdateRequest dto);

    void deletarPacientePorId(Long id);

    void deletarPacientePorNome(String nome);

}
