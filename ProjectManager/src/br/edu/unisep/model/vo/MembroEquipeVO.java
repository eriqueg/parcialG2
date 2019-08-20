package br.edu.unisep.model.vo;

import javax.persistence.*;

@Entity
@Table(name="membro_equipe")
public class MembroEquipeVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_membroequipe")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private UsuarioVO usuario;

    @ManyToOne
    @JoinColumn(name="id_projeto")
    private ProjetoVO projeto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public ProjetoVO getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoVO projeto) {
        this.projeto = projeto;
    }
}
