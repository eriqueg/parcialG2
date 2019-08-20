package br.edu.unisep.model.vo;

import java.time.LocalDate;

public class ServicoVO {

    private Integer id;

    private ClienteVO cliente;

    private Integer qtdeDelicadas;

    private Integer qtdeNormais;

    private Integer qtdePesadas;

    private LocalDate dataEntrega;

    private LocalDate dataRetirada;

    private Integer tipo;

    private Integer status;

    private Double total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteVO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    public Integer getQtdeDelicadas() {
        return qtdeDelicadas;
    }

    public void setQtdeDelicadas(Integer qtdeDelicadas) {
        this.qtdeDelicadas = qtdeDelicadas;
    }

    public Integer getQtdeNormais() {
        return qtdeNormais;
    }

    public void setQtdeNormais(Integer qtdeNormais) {
        this.qtdeNormais = qtdeNormais;
    }

    public Integer getQtdePesadas() {
        return qtdePesadas;
    }

    public void setQtdePesadas(Integer qtdePesadas) {
        this.qtdePesadas = qtdePesadas;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
