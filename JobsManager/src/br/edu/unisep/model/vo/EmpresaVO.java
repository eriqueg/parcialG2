package br.edu.unisep.model.vo;

import javax.persistence.*;

@Entity
@Table(name = "empresa")
public class EmpresaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer id;

    @Column(name = "ds_empresa")
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_ramo")
    private RamoAtividadeVO ramo;

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

    public RamoAtividadeVO getRamo() {
        return ramo;
    }

    public void setRamo(RamoAtividadeVO ramo) {
        this.ramo = ramo;
    }
}
