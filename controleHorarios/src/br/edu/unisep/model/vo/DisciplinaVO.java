package br.edu.unisep.model.vo;

public class DisciplinaVO {

    private Integer id;

    private String nome;

    private ProfessorVO professor;

    private CursoVO curso;

    private Integer cargaHoraria;

    private Integer periodo;

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

    public ProfessorVO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorVO professor) {
        this.professor = professor;
    }

    public CursoVO getCurso() {
        return curso;
    }

    public void setCurso(CursoVO curso) {
        this.curso = curso;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }
}
