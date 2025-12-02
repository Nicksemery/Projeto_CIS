package Cis.api.infra.repository;

import Cis.api.domain.entity.Sessao;
import Cis.api.domain.enums.SessaoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Query("""
        SELECT s FROM Sessao s
        WHERE s.dataHora = :dataHora
        AND (s.paciente.id = :pacienteId OR s.psicologo.id = :psicologoId)
        AND s.status IN :statusAceitos
    """)
    List<Sessao> buscarConflitoHorario(
            @Param("dataHora") LocalDateTime dataHora,
            @Param("pacienteId") Long pacienteId,
            @Param("psicologoId") Long psicologoId,
            @Param("statusAceitos") List<SessaoStatus> statusAceitos
    );
}
