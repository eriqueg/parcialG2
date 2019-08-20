package br.edu.unisep.model.vo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tarefa")
public class TarefaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tarefa")
    private Integer id;

    @Column(name="ds_tarefa")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_projeto")
    private ProjetoVO projeto;

    @OneToOne
    @JoinColumn(name = "id_responsavel", referencedColumnName = "id_usuario")
    private UsuarioVO responsavel;

    @Column(name="tp_status")
    private Integer status;

    @Column(name="dt_inicio")
    private LocalDateTime inicio;

    @Column(name="dt_termino")
    private LocalDateTime termino;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ProjetoVO getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoVO projeto) {
        this.projeto = projeto;
    }

    public UsuarioVO getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuarioVO responsavel) {
        this.responsavel = responsavel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getTermino() {
        return termino;
    }

    public void setTermino(LocalDateTime termino) {
        this.termino = termino;
    }
}
