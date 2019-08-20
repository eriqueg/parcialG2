package br.edu.unisep.model.vo;


import javax.persistence.*;

@Entity
@Table(name = "candidato")
public class CandidatoVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidato")
    private Integer id;

    @Column(name = "ds_candidato")
    private String nome;

    @Column(name = "ds_email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
