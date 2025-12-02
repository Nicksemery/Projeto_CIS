package Cis.api.infra.validate;

import Cis.api.domain.entity.Paciente;
import Cis.api.infra.repository.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class PacienteValidate {

    private final PacienteRepository repository;

    public PacienteValidate(PacienteRepository repository) {
        this.repository = repository;
    }

    public Paciente validarIdExistente(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Id de paciente não encontrado"));
    }

    public Paciente validarPorNome(String nome) {
        return repository.findByNome(nome.trim()).orElseThrow(() -> new RuntimeException("Nome não encontrado"));
    }


}
