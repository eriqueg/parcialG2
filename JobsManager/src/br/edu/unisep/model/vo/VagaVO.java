package br.edu.unisep.model.vo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaga")
public class VagaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")
    private Integer id;

    @Column(name = "ds_titulo")
    private String titulo;

    @Column(name = "ds_vaga")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_empresa")
    private EmpresaVO empresa;

    @Column(name = "vl_qtde")
    private Integer quantidade;

    @Column(name = "vl_salario")
    private Double salario;

    @Column(name = "dt_inicio")
    private LocalDate inicio;

    @Column(name = "dt_termino")
    private LocalDate termino;

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

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public void setTermino(LocalDate termino) {
        this.termino = termino;
    }
}
