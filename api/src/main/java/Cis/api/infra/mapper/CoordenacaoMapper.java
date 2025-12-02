package Cis.api.infra.mapper;

import Cis.api.domain.dtos.request.coordenacao.CoordenacaoDtoRequest;
import Cis.api.domain.dtos.response.CoordenacaoDtoResponse;
import Cis.api.domain.entity.Coordenacao;
import org.springframework.stereotype.Component;

@Component
public class CoordenacaoMapper {

    public Coordenacao entidade(CoordenacaoDtoRequest dados) {
        if (dados == null) {
            return null;
        }
        return new Coordenacao(
                dados.nome(),
                dados.email(),
                dados.matricula()

        );
    }

    public CoordenacaoDtoResponse dtoResposta(Coordenacao coordenacao) {
        if (coordenacao == null) {
            return null;
        }
        return new CoordenacaoDtoResponse(
                coordenacao.getId(),
                coordenacao.getNome(),
                coordenacao.getEmail(),
                coordenacao.getMatricula()
        );
    }
}