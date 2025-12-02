package Cis.api.infra.repository;

import Cis.api.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {


    Optional<Paciente> findByNome(String nome);

    @Override
    Optional<Paciente> findById(Long id);
}
