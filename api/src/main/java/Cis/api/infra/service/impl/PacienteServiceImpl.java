package Cis.api.infra.service.impl;

import Cis.api.domain.dtos.request.paciente.PacienteDtoRequest;
import Cis.api.domain.dtos.request.paciente.PacienteDtoUpdateRequest;
import Cis.api.domain.dtos.response.PacienteDtoResponse;
import Cis.api.domain.entity.Coordenacao;
import Cis.api.domain.entity.Paciente;
import Cis.api.infra.mapper.PacienteMapper;
import Cis.api.infra.repository.PacienteRepository;
import Cis.api.infra.service.PacienteService;
import Cis.api.infra.validate.CoordenacaoValidate;
import Cis.api.infra.validate.PacienteValidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;
    private final PacienteValidate validate;
    private final PacienteMapper mapper;
    private final CoordenacaoValidate coordValidate;

    public PacienteServiceImpl(PacienteRepository repository, PacienteValidate validate ,PacienteMapper mapper, CoordenacaoValidate coordValidate) {
        this.repository = repository;
        this.validate = validate;
        this.mapper = mapper;
        this.coordValidate = coordValidate;
    }


    @Override
    public PacienteDtoResponse cadastrarPaciente(PacienteDtoRequest dto) {

        // 1. Busca a Coordenação
        Coordenacao coordenacao = coordValidate.validarCoordenacaoPorId(dto.idCoordenacao());


        // 3. Criação da Entidade Paciente (Via Mapper/Construtor)
        Paciente paciente = mapper.entidade(dto, coordenacao);

        Paciente pacienteSalvo = repository.save(paciente);

        return mapper.dtoResposta(pacienteSalvo);
    }

    @Override
    public List<PacienteDtoResponse> listarPacientes() {
       List<Paciente> pacientes = repository.findAll();
        return pacientes.stream().map(mapper::dtoResposta).collect(Collectors.toList());
    }

    @Override
    public PacienteDtoResponse buscarPacientePorId(Long id) {
        Paciente paciente = validate.validarIdExistente(id);
        return mapper.dtoResposta(paciente);
    }

    @Override
    public PacienteDtoResponse buscarPacientePorNome(String nome) {
        Paciente paciente = validate.validarPorNome(nome);
        return mapper.dtoResposta(paciente);
    }

    @Override
    public PacienteDtoResponse atualizarPaciente(Long id, PacienteDtoUpdateRequest dto) {
        Paciente paciente = validate.validarIdExistente(id);
        paciente.atualizarDados(dto.nome(), dto.telefone(), dto.disponibilidadeData(),dto.disponibilidadeHorario());

        return mapper.dtoResposta(paciente);
    }

    @Override
    public void deletarPacientePorId(Long id) {
        Paciente paciente = validate.validarIdExistente(id);
        paciente.setAtivo(false);
        repository.save(paciente);
    }

    @Override
    public void deletarPacientePorNome(String nome) {
        Paciente paciente = validate.validarPorNome(nome);
        paciente.setAtivo(false);
        repository.save(paciente);
    }
}
