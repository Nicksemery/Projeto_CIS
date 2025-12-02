package Cis.api.domain.entity;

import Cis.api.domain.enums.DiaSemana;
import Cis.api.domain.enums.HorarioAgendamento;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "disponibilidade_data", nullable = true)
    private DiaSemana dia;

    @Enumerated(EnumType.STRING)
    @Column(name = "disponibilidade_horario", nullable = false)
    private HorarioAgendamento horario;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coordenacao", nullable = false)
    private Coordenacao coordenacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", unique = true, nullable = false)
    private Usuario usuario;

    @Setter
    private boolean ativo;

    public Paciente(String nome, String telefone, Coordenacao coordenacao, Usuario usuario, DiaSemana dia, HorarioAgendamento horario) {
        this.nome = nome;
        this.telefone = telefone;
        this.coordenacao = coordenacao;
        this.usuario = usuario;
        this.dia = dia;
        this.horario = horario;
        this.dataRegistro = LocalDate.now();
    }

    // Método para alteração de dados de valor (controlado)
    public void atualizarDados(String nome, String telefone, DiaSemana dia, HorarioAgendamento horario) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
        if (telefone != null && !telefone.isBlank()) {
            this.telefone = telefone;
        }
        if (dia != null) {
            this.dia = dia;
        }
        if (horario != null) {
            this.horario = horario;
        }
    }
}
