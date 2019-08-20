package br.edu.unisep.produtoVO;

import javax.persistence.*;

@Entity
@Table(name="produtos")

public class ProdutoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto")
    private Integer id;

    @Column(name="ds_produto")
    private String descricao;

    @Column(name="vl_mercadoa")
    private Double mercadoA;

    @Column(name="vl_mercadob")
    private Double mercadoB;

    @Column(name="vl_mercadoc")
    private Double mercadoC;

    public Double getMaior(){
        if(mercadoA > mercadoB && mercadoA > mercadoC){
            return mercadoA;
        }else if(mercadoB > mercadoA && mercadoB > mercadoC){
            return mercadoB;
        }else{
            return mercadoC;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMenor(){
        if(mercadoA < mercadoB && mercadoA < mercadoC){
            return mercadoA;
        }else if(mercadoB < mercadoA && mercadoB < mercadoC){
            return mercadoB;
        }else{
            return mercadoC;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getMercadoA() {
        return mercadoA;
    }

    public void setMercadoA(Double mercadoA) {
        this.mercadoA = mercadoA;
    }

    public Double getMercadoB() {
        return mercadoB;
    }

    public void setMercadoB(Double mercadoB) {
        this.mercadoB = mercadoB;
    }

    public Double getMercadoC() {
        return mercadoC;
    }

    public void setMercadoC(Double mercadoC) {
        this.mercadoC = mercadoC;
    }
}
