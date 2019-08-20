package br.edu.unisep.model.vo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="projeto")
public class ProjetoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_projeto")
    private Integer id;

    @Column(name="ds_titulo")
    private String titulo;

    @Column(name="ds_projeto")
    private String descricao;

    @Column(name="dt_inicio")
    private LocalDate inicio;

    @OneToOne
    @JoinColumn(name="id_cliente")
    private ClienteVO cliente;

    @OneToOne
    @JoinColumn(name="id_gerente", referencedColumnName="id_usuario")
    private UsuarioVO gerente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public ClienteVO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    public UsuarioVO getGerente() {
        return gerente;
    }

    public void setGerente(UsuarioVO gerente) {
        this.gerente = gerente;
    }
}
