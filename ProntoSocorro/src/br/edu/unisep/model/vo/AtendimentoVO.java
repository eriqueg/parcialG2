package br.edu.unisep.model.vo;

import java.time.LocalDate;
import java.util.Date;

public class AtendimentoVO {

    private Integer id;

    private String paciente;

    private LocalDate nascimento;

    private String sintomas;

    private Integer status;

    private EspecialidadeVO especialidade;

    private PlanoSaudeVO planoSaude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate  nascimento) {
        this.nascimento = nascimento;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public EspecialidadeVO getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeVO especialidade) {
        this.especialidade = especialidade;
    }

    public PlanoSaudeVO getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(PlanoSaudeVO planoSaude) {
        this.planoSaude = planoSaude;
    }
}
