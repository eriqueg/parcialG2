package br.edu.unisep;

import br.edu.unisep.fx.annotation.ColValueMap;
import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.annotation.IntToString;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.AtendimentoDAO;
import br.edu.unisep.model.vo.AtendimentoVO;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.Date;

public class Controller extends AppController {
    @FXML private TableView<AtendimentoVO> tabAtendimentos;
    @FXColumn(property = "paciente", percentWidth = 25)
    @FXML private TableColumn<AtendimentoVO, String> tabPaciente;
    @FXColumn(property = "planoSaude", percentWidth = 25)
    @FXML private TableColumn<AtendimentoVO, String> tabPlanoSaude;
    @FXColumn(property = "especialidade", percentWidth = 25)
    @FXML private TableColumn<AtendimentoVO, String> tabEspecialidade;

    @ColValueMap({
            @IntToString(from = 1, to = "Esperando Atendimento"),
            @IntToString(from = 2, to = "Atendido")
    })
    @FXColumn(property = "status", percentWidth = 20)
    @FXML private TableColumn<AtendimentoVO, String> tabStatus;
    @FXColumn(property = "nascimento", percentWidth = 20)
    @FXML private TableColumn<AtendimentoVO, Date> tabDataNasc;

    @FXML private Button btnNovoAtendimento;
    @FXML private Button btnMarcarAtendimento;

    private AtendimentoVO atendimentoSel;


    private ObservableList<AtendimentoVO> atendimento;
    private AtendimentoDAO dao;

    public void onInit(){
        atendimento = FXCollections.observableArrayList();
        tabAtendimentos.setItems(atendimento);

        dao = new AtendimentoDAO();

        listar();

    }

    public void listar(){
        var lista = dao.listar();

        atendimento.setAll(lista);



    }


    public void abrirNovo(ActionEvent event)throws IOException {
        openModal("mainNovo.fxml", this :: listar, atendimentoSel);

    }

    public void alterarStatus(ActionEvent event){

        var a = tabAtendimentos.getSelectionModel().getSelectedItem();

        a.setStatus(a.getStatus() + 1);

        dao.alterar(a);
        listar();

    }



}
