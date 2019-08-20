package br.edu.unisep.model.vo;

public class LivroVO {

    private Integer id;

    private String titulo;

    private String editora;

    private Integer paginas;

    private Integer status;

    private String sinopse;

    private AutorVO autor;

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

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public AutorVO getAutor() {
        return autor;
    }

    public void setAutor(AutorVO autor) {
        this.autor = autor;
    }
}
