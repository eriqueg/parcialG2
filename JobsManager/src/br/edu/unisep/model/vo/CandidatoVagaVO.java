package br.edu.unisep.model.vo;

import javax.persistence.*;

@Entity
@Table(name = "candidato_vaga")
public class CandidatoVagaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidatovaga")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_candidato")
    private CandidatoVO candidato;

    @OneToOne
    @JoinColumn(name = "id_vaga")
    private VagaVO vaga;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CandidatoVO getCandidato() {
        return candidato;
    }

    public void setCandidato(CandidatoVO candidato) {
        this.candidato = candidato;
    }

    public VagaVO getVaga() {
        return vaga;
    }

    public void setVaga(VagaVO vaga) {
        this.vaga = vaga;
    }
}
