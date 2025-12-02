package Cis.api.domain.entity;

import Cis.api.domain.enums.Cargos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "coordenacao")
@Getter
@NoArgsConstructor
public class Coordenacao {

    public Coordenacao(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.ativo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String matricula;
    private String email;

    @Enumerated(EnumType.STRING)
    private Cargos cargo;

    @Setter
    private boolean ativo;

    @OneToMany(mappedBy = "coordenacao", fetch = FetchType.LAZY)
    private List<Psicologo> psicologos; // Nome do campo na entidade Psicologo

    // Lado 2: Relacionamento com Paciente (Coordenacao gerencia MUITOS Pacientes)
    @OneToMany(mappedBy = "coordenacao", fetch = FetchType.LAZY)
    private List<Paciente> pacientes; // Nome do campo na entidade Paciente


    public void atualizarDados(String nome, String matricula, String email, Cargos cargo) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
        if (matricula != null && !matricula.isBlank()) {
            this.matricula = matricula;
        }
        if (email != null && !email.isBlank()) {
            this.email = email;
        }
        if (cargo != null) {
            this.cargo = cargo;
        }
    }


}
