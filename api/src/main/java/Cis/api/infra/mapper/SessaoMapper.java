package Cis.api.infra.mapper;

import Cis.api.domain.dtos.request.sessao.SessaoDtoRequest;
import Cis.api.domain.dtos.response.SessaoDtoResponse;
import Cis.api.domain.entity.Coordenacao;
import Cis.api.domain.entity.Paciente;
import Cis.api.domain.entity.Psicologo;
import Cis.api.domain.entity.Sessao;
import Cis.api.domain.enums.DiaSemana;
import org.springframework.stereotype.Component;

@Component
public class SessaoMapper {


    // 1. DTO de Request para Entidade (Usado no agendamento)
    // A assinatura do mapper deve refletir os objetos que a Entidade Sessao precisa
    public Sessao entidade(SessaoDtoRequest dto, Paciente paciente, Psicologo psicologo, Coordenacao coordenacao) {
        if (dto == null) return null;

        // Chama o Construtor Imutável da Sessao
        return new Sessao(
                paciente,
                psicologo,
                coordenacao,
                dto.dataHoraAgendada() // Agora usando o campo correto do DTO
        );
    }

    // 2. Entidade para DTO de Response
    public SessaoDtoResponse dtoResposta(Sessao sessao) {
        if (sessao == null) return null;

        // Usa o construtor do Record de Resposta
        return new SessaoDtoResponse(
                sessao.getId(),
                sessao.getPaciente().getId(),
                sessao.getPsicologo().getId(),
                sessao.getCoordenacaoGestora().getId(),
                sessao.getDataHora(),
                sessao.getStatus(),
                sessao.getDataAprovacao()
        );
    }
}
