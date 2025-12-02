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
    @Column(name = "id_sessao") // Mapeando a PK do SQL
    private Long id;

    // Relacionamentos ManyToOne:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_psicologo", nullable = false)
    private Psicologo psicologo;

    @ManyToOne(fetch = FetchType.LAZY)
    // O nome da coluna no SQL é 'id_coordenacao'
    @JoinColumn(name = "id_coordenacao", nullable = false)
    private Coordenacao coordenacaoGestora;

    @Column(name = "Data_Hora") // Mapeando o nome do SQL
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false) // Mapeando o nome do SQL
    @Setter
    private SessaoStatus status = SessaoStatus.PENDENTE_APROVACAO;

    @Setter
    @Column(name = "Data_Aprovacao") // Mapeando o nome do SQL
    private LocalDateTime dataAprovacao;

    @Column(name = "Observacoes") // Mapeando o nome do SQL
    private String observacoes;


    // Construtor para Agendamento
    public Sessao(Paciente paciente, Psicologo psicologo, Coordenacao coordenacaoGestora, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.psicologo = psicologo;
        this.coordenacaoGestora = coordenacaoGestora;
        this.dataHora = dataHora;
        this.status = SessaoStatus.PENDENTE_APROVACAO;
    }

    // Método para ser usado pela Coordenacao ao aprovar ou cancelar a sessão
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void cancelarSessao() {
        if (this.status != SessaoStatus.REALIZADA) {
            this.status = SessaoStatus.CANCELADA;
        } else {
            // Lançar exceção ou logar erro: não pode cancelar sessão já realizada
        }
    }

}
