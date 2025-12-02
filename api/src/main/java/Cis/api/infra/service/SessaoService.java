package Cis.api.infra.service;

import Cis.api.domain.dtos.request.sessao.SessaoDtoRequest;
import Cis.api.domain.dtos.response.SessaoDtoResponse;

public interface SessaoService {

    SessaoDtoResponse agendarSessao( SessaoDtoRequest dto);

    // Aprovar Sessão (Restrito à Coordenacao)
    SessaoDtoResponse aprovarSessao(Long idSessao);
}
