package Cis.api.infra.validate;

import Cis.api.domain.entity.Psicologo;
import Cis.api.infra.repository.PsicologoRepository;
import org.springframework.stereotype.Component;

@Component
public class PsicologoValidate {

    private final PsicologoRepository repository;

    public PsicologoValidate(PsicologoRepository repository) {
        this.repository = repository;
    }

    public Psicologo validarPsicologoPorId(Long id) {
        return repository.findById(id).orElseThrow(()->new RuntimeException("Psicologo não encontrado"));
    }

    public Psicologo validarPsicologoPorMatricula(String matricula) {
        return repository.findByMatricula(matricula).orElseThrow(()-> new RuntimeException("Matricula não encontrada"));
    }

    public Psicologo validarPsicologoPorNome(String nome) {
        return repository.findByNome(nome.trim()).orElseThrow(() -> new RuntimeException("Nome não encontrado"));
    }

    public void validarMatriculaExistente(String matricula) {
        if(repository.findByMatricula(matricula).isPresent()) throw new RuntimeException("Matricula já existente");
    }



}
