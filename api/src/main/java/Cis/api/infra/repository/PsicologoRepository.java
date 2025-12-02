package Cis.api.infra.repository;

import Cis.api.domain.entity.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PsicologoRepository extends JpaRepository<Psicologo, Long> {


    Optional<Psicologo> findByNome(String name);
    Optional<Psicologo> findByMatricula(String matricula);
}
