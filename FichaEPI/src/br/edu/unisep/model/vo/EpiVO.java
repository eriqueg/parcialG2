package br.edu.unisep.model.vo;

import javax.persistence.*;

@Entity
@Table(name = "epi")
public class EpiVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_epi")
    private Integer id;

    @Column(name = "ds_nome")
    private String nome;

    @Column(name = "ds_ca")
    private Integer ca;

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

    public Integer getCa() {
        return ca;
    }

    public void setCa(Integer ca) {
        this.ca = ca;
    }
}
