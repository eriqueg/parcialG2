package br.edu.unisep.model.vo;


import javax.persistence.*;

@Entity
@Table(name = "ramo_atividade")
public class RamoAtividadeVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ramo")
    private Integer id;

    @Column(name = "ds_ramo")
    private String nome;

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
}
