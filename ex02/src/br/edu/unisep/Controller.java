package br.edu.unisep;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

public class Controller {

    @FXML private TextField txtCoxinhas;
    @FXML private TextField txtRizolis;
    @FXML private TextField txtKibe;
    @FXML private TextField txtMiniPizza;
    @FXML private TextField txtMiniBurguer;
    @FXML private TextField txtHotDog;
    @FXML private Label txtTotal;

    @FXML private DatePicker txtEntrega;

    @FXML private CheckBox chkDecoracao;

    public void calcular(ActionEvent event){

        var coxinha = Integer.parseInt(txtCoxinhas.getText());
        var rizolis = Integer.parseInt(txtRizolis.getText());
        var kibe = Integer.parseInt(txtKibe.getText());
        var miniPizza = Integer.parseInt(txtMiniPizza.getText());
        var miniBurguer = Integer.parseInt(txtMiniBurguer.getText());
        var hotDog = Integer.parseInt(txtHotDog.getText());

        double valor;

        if(chkDecoracao.isSelected()){
           valor = (coxinha * 0.10) + (rizolis * 0.10) + (kibe * 0.15) + (miniPizza * 0.2) + (miniBurguer * 0.18) + (hotDog * 0.22);
            valor = valor * 1.2;
        }else{
           valor = (coxinha * 0.10) + (rizolis * 0.10) + (kibe * 0.15) + (miniPizza * 0.2) + (miniBurguer * 0.18) + (hotDog * 0.22);
        }

        var hoje = LocalDate.now();
        var entrega = txtEntrega.getValue();
        var dif = Period.between(hoje, entrega).getDays();

        if(dif <= 2){
            valor = valor *1.3;
        }else if(dif <= 5){
            valor = valor * 1.2;
        }

        var df = new DecimalFormat("#,##0.00");

        txtTotal.setText("R$" + df.format(valor));


    }

    public void limpar(ActionEvent event){

        txtCoxinhas.clear();
        txtHotDog.clear();
        txtKibe.clear();
        txtMiniBurguer.clear();
        txtMiniPizza.clear();
        txtRizolis.clear();

        txtTotal.setText("R$ 0,00");

        chkDecoracao.setSelected(false);

        txtCoxinhas.requestFocus();

    }



}
