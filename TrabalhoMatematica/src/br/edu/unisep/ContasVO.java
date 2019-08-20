package br.edu.unisep;

public class ContasVO {

    private Double vlrEmprestimo;

    private Double taxa;

    private Integer qtdParcelas;

    private Double vlrParcela;

    private Double vlrJuro;

    private Double amortizacao;

    private Double saldo;

    private Integer nParcela;

    public Integer getnParcela() {
        return nParcela;
    }

    public void setnParcela(Integer nParcela) {
        this.nParcela = nParcela;
    }

    public Double getVlrEmprestimo() {
        return vlrEmprestimo;
    }

    public void setVlrEmprestimo(Double vlrEmprestimo) {
        this.vlrEmprestimo = vlrEmprestimo;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public Integer getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(Integer qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public Double getVlrParcela() {
        return vlrParcela;
    }

    public void setVlrParcela(Double vlrParcela) {
        this.vlrParcela = vlrParcela;
    }

    public Double getVlrJuro() {
        return vlrJuro;
    }

    public void setVlrJuro(Double vlrJuro) {
        this.vlrJuro = vlrJuro;
    }

    public Double getAmortizacao() {
        return amortizacao;
    }

    public void setAmortizacao(Double amortizacao) {
        this.amortizacao = amortizacao;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
