package br.edu.unisep;

import br.edu.unisep.fx.controller.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;



public class MainController extends AppController {

    @FXML private AnchorPane conteudo;

    @Override
    protected void onInit() {

    }

    public void abrirClientes(ActionEvent event){
        openScene(conteudo, "clientes.fxml");
    }

    public void abrirServico(ActionEvent event){
        openScene(conteudo, "servicos.fxml");
    }


}
