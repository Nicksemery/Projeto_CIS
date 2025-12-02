package Cis.api.infra.service.impl;


import Cis.api.domain.dtos.request.sessao.SessaoDtoRequest;
import Cis.api.domain.dtos.response.SessaoDtoResponse;
import Cis.api.domain.entity.Coordenacao;
import Cis.api.domain.entity.Paciente;
import Cis.api.domain.entity.Psicologo;
import Cis.api.domain.entity.Sessao;
import Cis.api.domain.enums.SessaoStatus;
import Cis.api.infra.mapper.SessaoMapper;
import Cis.api.infra.repository.CoordenacaoRepository;
import Cis.api.infra.repository.PacienteRepository;
import Cis.api.infra.repository.PsicologoRepository;
import Cis.api.infra.repository.SessaoRepository;
import Cis.api.infra.service.SessaoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@Transactional
public class SessaoServiceImpl implements SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;
    private final CoordenacaoRepository coordRepository;
    private final SessaoMapper mapper;

    // Construtor com injeção de dependências
    public SessaoServiceImpl(SessaoRepository sessaoRepository, PacienteRepository pacienteRepository, PsicologoRepository psicologoRepository, CoordenacaoRepository coordRepository, SessaoMapper mapper) {
        this.sessaoRepository = sessaoRepository;
        this.pacienteRepository = pacienteRepository;
        this.psicologoRepository = psicologoRepository;
        this.coordRepository = coordRepository;
        this.mapper = mapper;
    }

    // 1. Agendar Sessão (Removida a lógica de segurança)
    // Assinatura alterada para receber apenas o DTO, usando idPaciente dele.
    @Override
    public SessaoDtoResponse agendarSessao(SessaoDtoRequest dto) {

        // Busca o Paciente pelo ID contido no DTO
        Paciente paciente = pacienteRepository.findById(dto.idPaciente())
                .orElseThrow(() -> new NoSuchElementException("Paciente não encontrado com o ID: " + dto.idPaciente()));

        // Busca as outras entidades
        Psicologo psicologo = psicologoRepository.findById(dto.idPsicologo())
                .orElseThrow(() -> new NoSuchElementException("Psicólogo não encontrado."));
        Coordenacao coordenacao = coordRepository.findById(dto.idCoordenacao())
                .orElseThrow(() -> new NoSuchElementException("Coordenação não encontrada."));

        // TODO: ADICIONAR VALIDAÇÃO DE CONFLITO DE HORÁRIO AQUI

        // Cria a Sessão usando o Mapper (Assinatura corrigida)
        Sessao sessao = mapper.entidade(dto, paciente, psicologo, coordenacao);

        Sessao salva = sessaoRepository.save(sessao);

        return mapper.dtoResposta(salva);
    }

    // 2. Aprovar Sessão
    @Override
    public SessaoDtoResponse aprovarSessao(Long idSessao) {

        Sessao sessao = sessaoRepository.findById(idSessao)
                .orElseThrow(() -> new NoSuchElementException("Sessão não encontrada."));

        if (sessao.getStatus() != SessaoStatus.PENDENTE_APROVACAO) {
            throw new IllegalStateException("Sessão já possui um status definitivo.");
        }

        // Usa os setters controlados da Entidade
        sessao.setStatus(SessaoStatus.APROVADA);
        sessao.setDataAprovacao(LocalDateTime.now());

        Sessao atualizada = sessaoRepository.save(sessao);

        return mapper.dtoResposta(atualizada);
    }
}

