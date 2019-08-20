package br.edu.unisep;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.vo.CursoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;



public class MainController  extends AppController {
    //pra depois abrir a tela dentro do anchor pane
    @FXML private AnchorPane conteudo;


    @Override
    protected void onInit() {

    }

public void abrirCurso(ActionEvent event){
        //abrir a tela
        openScene(conteudo, "cursos.fxml");


}

public void abrirProfessores(ActionEvent event){
        openScene(conteudo, "professores.fxml");


}

public void abrirDisciplinas(ActionEvent event){
        openScene(conteudo, "disciplina.fxml");


}



}


