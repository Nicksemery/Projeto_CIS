package Cis.api.domain.entity;

import Cis.api.domain.enums.SessaoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessao")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sessao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamentos ManyToOne:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_psicologo", nullable = false)
    private Psicologo psicologo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coordenacao_gestora", nullable = false)
    private Coordenacao coordenacaoGestora;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter // Precisa mudar no método de aprovação
    private SessaoStatus status = SessaoStatus.PENDENTE_APROVACAO;

    @Setter // Precisa mudar no método de aprovação
    private LocalDateTime dataAprovacao;

    private String observacoes;

    // Construtor para Agendamento
    public Sessao(Paciente paciente, Psicologo psicologo, Coordenacao coordenacaoGestora, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.psicologo = psicologo;
        this.coordenacaoGestora = coordenacaoGestora;
        this.dataHora = dataHora;
        this.status = SessaoStatus.PENDENTE_APROVACAO;
    }



}
