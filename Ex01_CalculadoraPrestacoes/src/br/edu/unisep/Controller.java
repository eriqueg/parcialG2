package br.edu.unisep;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class Controller {


    @FXML private TextField txtValor;
    @FXML private TextField txtTaxa;
    @FXML private TextField txtParcelas;

    @FXML private RadioButton rdJurosSimples;

    @FXML private Label lblResultado;

    public void calcular(ActionEvent event){

        var valor = Double.parseDouble(txtValor.getText());
        var taxa = Double.parseDouble(txtTaxa.getText());
        var parcela = Integer.parseInt(txtParcelas.getText());

        var resultado = 0d;

        if(rdJurosSimples.isSelected()){
            resultado = (valor * (1 + (taxa / 100d * parcela)))/parcela;
        }else{
            resultado = (valor * Math.pow(1 + taxa / 100d, parcela)) / parcela;
        }

        var df = new DecimalFormat("#,##0.00");

        lblResultado.setText("R$" + df.format(resultado));

    }

    public void limpar(ActionEvent event){

        txtValor.clear();
        txtParcelas.clear();
        txtTaxa.clear();

        lblResultado.setText("R$ 00,00");
        rdJurosSimples.setSelected(true);

        txtValor.requestFocus();

    }




}
