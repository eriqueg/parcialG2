package br.edu.unisep;

import br.edu.unisep.fx.controller.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DecimalFormat;
import java.util.List;

public class Controller extends AppController {
    @FXML private TextField vlrEmprestimo;
    @FXML private TextField lblTaxa;
    @FXML private TextField qtdParcelas;
    @FXML private RadioButton btnPrice;
    @FXML private RadioButton btnSaa;
    @FXML private RadioButton btnSac;
    @FXML private TableView<ContasVO> tabela;
    @FXML private TableColumn<ContasVO, Integer> colParcela;
    @FXML private TableColumn<ContasVO, Double> colVlrparcela;
    @FXML private TableColumn<ContasVO, Double> colJuro;
    @FXML private TableColumn<ContasVO, Double> colAmortizacao;
    @FXML private TableColumn<ContasVO, Double> colSaldo;

    private ObservableList<ContasVO> contas;
    private ContasVO co;



    @Override
    protected void onInit() {

        contas = FXCollections.observableArrayList();
        tabela.setItems(contas);

        colVlrparcela.setCellValueFactory(new PropertyValueFactory<>("vlrParcela"));
        colJuro.setCellValueFactory(new PropertyValueFactory<>("vlrJuro"));
        colParcela.setCellValueFactory(new PropertyValueFactory<>("nParcela"));
        colAmortizacao.setCellValueFactory(new PropertyValueFactory<>("amortizacao"));
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));


        colParcela.setCellFactory(col -> formatParcelas());
        colJuro.setCellFactory(col -> formatCelula());
        colVlrparcela.setCellFactory(col -> formatCelula());
        colAmortizacao.setCellFactory(col -> formatCelula());
        colSaldo.setCellFactory(col -> formatCelula());

    }

    private TableCell<ContasVO, Integer> formatParcelas() {
        var cell =  (TableCell) new TableCell<ContasVO, Integer>(){

            @Override
            protected void updateItem(Integer valor, boolean vazio) {
                super.updateItem(valor, vazio);

                if(valor == null ||vazio){
                    setText("");
                }else {
                    setText(String.valueOf(valor));
                    setAlignment(Pos.CENTER_RIGHT);

                }
            }

        };
        return cell;
    }

    private TableCell<ContasVO, Double> formatCelula() {
        var cell =  (TableCell) new TableCell<ContasVO, Double>(){

            @Override
            protected void updateItem(Double valor, boolean vazio) {
                super.updateItem(valor, vazio);

                if(valor == null ||vazio){
                    setText("");
                    setStyle(null);
                }else {
                    setText(formatarValor(valor));
                    setAlignment(Pos.CENTER_RIGHT);

                }
            }

        };
        return cell;
    }

    private String formatarValor(Double v){
        var df = new DecimalFormat("#,##0.00");

        return "R$" + df.format(v);

    }
    public void limpar() {
       vlrEmprestimo.clear();
       lblTaxa.clear();
       qtdParcelas.clear();
    }

    public void calcularPrice(){

        var c = new ContasVO();
        c.setQtdParcelas(Integer.parseInt(qtdParcelas.getText()));
        var parcela = c.getQtdParcelas();
        for(var x = 0; x < parcela; x++){
            c.setTaxa(Double.parseDouble(lblTaxa.getText()));
            c.setVlrEmprestimo(Double.parseDouble(vlrEmprestimo.getText()));

            if(x == 0){
                c.setSaldo(c.getVlrEmprestimo());
            }
            var sd = c.getSaldo();
            var taxa = c.getTaxa() / 100;
            var vlrEmp =c.getVlrEmprestimo();

            var vlrParc = vlrEmp * (Math.pow((1 + taxa), parcela)  * taxa) / (Math.pow((1 + taxa), parcela) - 1);
            var juro = sd * taxa;
            var amort = vlrParc - juro;
            sd = sd - amort;
            c.setVlrParcela(vlrParc);
            c.setAmortizacao(amort);
            c.setSaldo(sd);
            c.setVlrJuro(juro);
            c.setnParcela(x);
            contas.add(c);
        }


    }

    public void calcularSAA(){
        var c = new ContasVO();
        c.setQtdParcelas(Integer.parseInt(qtdParcelas.getText()));
        var parcela = c.getQtdParcelas();
        for(var x = 0; x < parcela; x++){
            c.setTaxa(Double.parseDouble(lblTaxa.getText()));
            c.setVlrEmprestimo(Double.parseDouble(vlrEmprestimo.getText()));

            if(x == 0){
                c.setSaldo(c.getVlrEmprestimo());
            }
            var sd = c.getSaldo();
            var taxa = c.getTaxa() / 100;
            var vlrEmp =c.getVlrEmprestimo();
            var amort = vlrEmp / parcela;
            var juro = sd * taxa;
            var vlrParc = amort + juro;
            sd = sd - amort;
            c.setVlrParcela(vlrParc);
            c.setAmortizacao(amort);
            c.setSaldo(sd);
            c.setVlrJuro(juro);
            c.setnParcela(x);
            contas.add(c);
        }
    }

    public void calcularSAC(){
        var c = new ContasVO();
        c.setQtdParcelas(Integer.parseInt(qtdParcelas.getText()));
        var parcela = c.getQtdParcelas();
        for(var x = 0; x < parcela; x++){
            c.setTaxa(Double.parseDouble(lblTaxa.getText()));
            c.setVlrEmprestimo(Double.parseDouble(vlrEmprestimo.getText()));
            var sd = c.getSaldo();
            var taxa = c.getTaxa() / 100;
            var vlrEmp =c.getVlrEmprestimo();
            var juro = sd * taxa;
            var amort = vlrEmp / parcela;
            var vlrParc = 0d;
            if(x == 0){
                c.setSaldo(c.getVlrEmprestimo());
                vlrParc = juro;
            }else if(x == parcela){
                vlrParc = sd + juro;
            }




            sd = sd - amort;
            c.setVlrParcela(vlrParc);
            c.setAmortizacao(amort);
            c.setSaldo(sd);
            c.setVlrJuro(juro);
            c.setnParcela(x);
            contas.add(c);
        }
    }


    public void calcular(){
       if(btnPrice.isSelected()){
           calcularPrice();
       }else if(btnSaa.isSelected()){
           calcularSAA();
       }else{
           calcularSAC();
       }




    }



}
