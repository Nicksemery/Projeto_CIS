package Cis.api.infra.mapper;

import Cis.api.domain.dtos.request.paciente.PacienteDtoRequest;
import Cis.api.domain.dtos.response.PacienteDtoResponse;
import Cis.api.domain.entity.Coordenacao;
import Cis.api.domain.entity.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    // Criação: Usa o Construtor Imutável da Entidade
    public Paciente entidade(PacienteDtoRequest dto, Coordenacao coordenacao) {
        if (dto == null) return null;

        return new Paciente(
                dto.nome(),
                dto.telefone(),
                coordenacao,
                dto.disponibilidadeData(),
                dto.disponibilidadeHorario()
        );
    }

    // Resposta: Usa o Construtor Imutável do Record
    public PacienteDtoResponse dtoResposta(Paciente paciente) {
        if (paciente == null) return null;

        // Assumindo que PacienteDtoRespons é um record
        return new PacienteDtoResponse(
                paciente.getId(),
                paciente.getCoordenacao().getId(),
                paciente.getNome(),
                paciente.getTelefone()

        );
    }

}
