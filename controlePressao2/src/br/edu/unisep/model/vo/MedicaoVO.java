package br.edu.unisep.model.vo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="registro_medicao")
public class MedicaoVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicao")
    private Integer id;

    @Column(name = "dt_medicao")
    private LocalDate data;

    @Column(name = "vl_sist")
    private Integer sistolica;

    @Column(name = "vl_diast")
    private Integer diastolica;

    @Column(name = "tp_resultado")
    private Integer resultado;


    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getSistolica() {
        return sistolica;
    }

    public void setSistolica(Integer sistolica) {
        this.sistolica = sistolica;
    }

    public Integer getDiastolica() {
        return diastolica;
    }

    public void setDiastolica(Integer diastolica) {
        this.diastolica = diastolica;
    }

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}