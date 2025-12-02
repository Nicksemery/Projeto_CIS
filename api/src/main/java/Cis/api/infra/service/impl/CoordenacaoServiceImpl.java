package Cis.api.infra.service.impl;

import Cis.api.domain.dtos.request.coordenacao.CoordenacaoDtoRequest;
import Cis.api.domain.dtos.response.CoordenacaoDtoResponse;
import Cis.api.domain.entity.Coordenacao;
import Cis.api.domain.entity.Usuario;
import Cis.api.domain.enums.Roles;
import Cis.api.infra.mapper.CoordenacaoMapper;
import Cis.api.infra.repository.CoordenacaoRepository;
import Cis.api.infra.service.CoordenacaoService;
import Cis.api.infra.service.UsuarioService;
import Cis.api.infra.validate.CoordenacaoValidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoordenacaoServiceImpl implements CoordenacaoService {


    private final CoordenacaoRepository repository;
    private final CoordenacaoMapper mapper;
    private final CoordenacaoValidate validate;

    public CoordenacaoServiceImpl(CoordenacaoRepository repository, CoordenacaoMapper mapper, CoordenacaoValidate validate, UsuarioService usuarioService) {
        this.repository = repository;
        this.mapper = mapper;
        this.validate = validate;
    }

    @Override
    public CoordenacaoDtoResponse criarCoordenacao(CoordenacaoDtoRequest dto) {
        Coordenacao coordenacao = mapper.entidade(dto);
        Coordenacao salvar = repository.save(coordenacao);
        return mapper.dtoResposta(salvar);
    }

    @Override
    public List<CoordenacaoDtoResponse> listarTodosDaCoordenacao() {
        List<Coordenacao> coordenacoes = repository.findAll();

        return coordenacoes.stream().map(mapper::dtoResposta).collect(Collectors.toList());
    }

    @Override
    public CoordenacaoDtoResponse alterarCoordenacao(Long id, CoordenacaoDtoRequest dto) {
        Coordenacao coorExistente = validate.validarCoordenacaoPorId(id);
        coorExistente.atualizarDados(dto.nome(),dto.email(), dto.matricula(), dto.cargo());
        Coordenacao atualizado = repository.save(coorExistente);
        return mapper.dtoResposta(atualizado);
    }

    @Override
    public CoordenacaoDtoResponse buscarPorId(Long id) {
        Coordenacao coordenacao = validate.validarCoordenacaoPorId(id);

        return mapper.dtoResposta(coordenacao);
    }

    @Override
    public CoordenacaoDtoResponse buscarPorNome(String nome) {
        Coordenacao coordenacao = validate.validarCoordenacaoPorNome(nome);
        return mapper.dtoResposta(coordenacao);
    }

    @Override
    public void deletarCoordenacaoPorId(Long id) {
        Coordenacao coordenacao = validate.validarCoordenacaoPorId(id);
        coordenacao.setAtivo(false);
    }

    @Override
    public void deletarCoordenacaoPorNome(String nome) {
        Coordenacao coordenacao = validate.validarCoordenacaoPorNome(nome);
        coordenacao.setAtivo(false);
        repository.save(coordenacao);
    }


}
