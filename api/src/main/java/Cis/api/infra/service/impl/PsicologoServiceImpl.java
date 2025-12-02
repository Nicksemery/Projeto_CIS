package Cis.api.infra.service.impl;

import Cis.api.domain.dtos.request.psicologo.PsicologoDtoRequest;
import Cis.api.domain.dtos.request.TokenDtoRequest;
import Cis.api.domain.dtos.request.psicologo.PsicologoDtoUpdateRequest;
import Cis.api.domain.dtos.response.PsicologoDtoRespons;
import Cis.api.domain.entity.Coordenacao;
import Cis.api.domain.entity.Psicologo;
import Cis.api.domain.entity.Usuario;
import Cis.api.domain.enums.Roles;
import Cis.api.infra.mapper.PsicologoMapper;
import Cis.api.infra.repository.CoordenacaoRepository;
import Cis.api.infra.repository.PsicologoRepository;
import Cis.api.infra.service.PsicologoService;
import Cis.api.infra.service.UsuarioService;
import Cis.api.infra.validate.CoordenacaoValidate;
import Cis.api.infra.validate.PsicologoValidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PsicologoServiceImpl implements PsicologoService {

    private final PsicologoRepository repository;
    private final PsicologoValidate validar;
    private final CoordenacaoValidate coordenacaoValidate;
    private final UsuarioService usuarioService;
    private final PsicologoMapper mapper;

    public PsicologoServiceImpl(PsicologoRepository repository,
                                PsicologoValidate validar,
                                PsicologoMapper mapper,
                                UsuarioService usuarioService,
                                CoordenacaoValidate coordenacaoValidate,
                                CoordenacaoRepository coordRepository) {
        this.repository = repository;
        this.validar = validar;
        this.coordenacaoValidate = coordenacaoValidate;
        this.mapper = mapper;
        this.usuarioService = usuarioService;
    }

    @Override
    public PsicologoDtoRespons cadastrarPsicologo(PsicologoDtoRequest dto) {

        Coordenacao coordenacao = coordenacaoValidate.validarCoordenacaoPorId(dto.idCoordenacao());

        TokenDtoRequest dadosAutenticacao = dto.dadosUsuario();
        Usuario salvarUsuario = usuarioService.criarUsuario(dadosAutenticacao,Roles.PSICOLOGO);

        Psicologo psicologo = mapper.entidade(dto,coordenacao,salvarUsuario);
        validar.validarMatriculaExistente(psicologo.getMatricula());

        Psicologo salvarPsicologo = repository.save(psicologo);

        return mapper.dtoResposta(salvarPsicologo);
    }

    @Override
    public List<PsicologoDtoRespons> listarPsicologos() {
        List<Psicologo> psicologos = repository.findAll();

        return psicologos.stream().map(mapper::dtoResposta).collect(Collectors.toList());
    }

    @Override
    public PsicologoDtoRespons buscarPsicologoPorId(Long id) {
        Psicologo psicologo = validar.validarPsicologoPorId(id);
        return mapper.dtoResposta(psicologo);
    }

    @Override
    public PsicologoDtoRespons buscarPsicologoPorNome(String nome) {
        Psicologo psicologo = validar.validarPsicologoPorNome(nome);
        return mapper.dtoResposta(psicologo);
    }

    @Override
    public PsicologoDtoRespons atualizarPsicologo(Long id , PsicologoDtoUpdateRequest dto) {
        Psicologo psicologoExistente = validar.validarPsicologoPorId(id);

        psicologoExistente.atualizarEntidade(dto.nome(),dto.matricula());

        return mapper.dtoResposta(psicologoExistente);
    }

    @Override
    public void deletarPsicologoPorId(Long id) {
        Psicologo psicologo = validar.validarPsicologoPorId(id);
        psicologo.setAtivo(false);
        repository.save(psicologo);
    }

    @Override
    public void deletarPsicologoPorNome(String nome) {
        Psicologo psicologo = validar.validarPsicologoPorNome(nome);
        psicologo.setAtivo(false);
        repository.save(psicologo);
    }

}
