package br.edu.unisep.model.vo;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name="usuario")
public class UsuarioVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer id;

    @Column(name="ds_nome")
    private String nome;

    @Column(name="ds_email")
    private String email;

    @Column(name="ds_senha")
    private String senha;

    @Column(name="tp_usuario")
    private Integer tipo;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nome;
    }

}
