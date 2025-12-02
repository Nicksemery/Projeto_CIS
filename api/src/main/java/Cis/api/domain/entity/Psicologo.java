package Cis.api.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "psicologos")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Psicologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String matricula;

    @Setter
    private boolean ativo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coordenacao", nullable = false)
    private Coordenacao coordenacao;

    // Relacionamento com o Usuário (Um Psicólogo é Um Usuário para o Login)
    /*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", unique = true, nullable = false)
    private Usuario usuario;


     */

    public Psicologo(String nome, String matricula,Coordenacao coordenacao) {
        this.nome = nome;
        this.matricula = matricula;
        this.coordenacao = coordenacao;
    }

    public void atualizarEntidade(String nome, String matricula) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
        if (matricula != null && !matricula.isEmpty()) {
            this.matricula = matricula;
        }
    }
}
