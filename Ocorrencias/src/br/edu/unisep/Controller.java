package br.edu.unisep;

import br.edu.unisep.fx.annotation.ColValueMap;
import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.annotation.IntToString;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.modules.DAO.GenericDAO;
import br.edu.unisep.modules.VO.OcorrenciaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.util.Date;

public class Controller extends AppController {

    @FXML
    private TableView<OcorrenciaVO> tabOcorrencia;

    @FXColumn(property = "ocorrencia", percentWidth = 35)
    @FXML
    private TableColumn<OcorrenciaVO, String> colOcorrencia;


    @FXColumn(property = "email", percentWidth = 25)
    @FXML
    private TableColumn<OcorrenciaVO, String> colEmail;

    @FXColumn(property = "dataOcorrencia", percentWidth = 25)
    @FXML
    private TableColumn<OcorrenciaVO, Date> colData;

    @ColValueMap({
            @IntToString(from = 1, to = "Aberta"),
            @IntToString(from = 2, to = "fechada")
    })
    @FXColumn(property = "status", percentWidth = 20)
    @FXML
    private TableColumn<OcorrenciaVO, Integer> colStatus;

    @ColValueMap({
            @IntToString(from = 1, to = "Baixa"),
            @IntToString(from = 2, to = "Média"),
            @IntToString(from = 3, to = "Alta")
    })
    @FXColumn(property = "prioridade", percentWidth = 20)
    @FXML
    private TableColumn<OcorrenciaVO, Integer> colPrioridade;

    private GenericDAO<OcorrenciaVO> dao;
    private ObservableList<OcorrenciaVO> ocorrencias;


    @Override
    protected void onInit() {

        ocorrencias = FXCollections.observableArrayList();

        tabOcorrencia.setItems(ocorrencias);

        dao = new GenericDAO<>();
        listar();

    }


    private void listar() {

        var lista = dao.listar(OcorrenciaVO.class);

        ocorrencias.setAll(lista);

    }

    public void abrirNovo(ActionEvent event) throws IOException {
        openModal("novo.fxml", this::listar);
    }

    public void fecharOcorrencia(ActionEvent event) {

        var ocorrencia = tabOcorrencia.getSelectionModel().getSelectedItem();

        ocorrencia.setStatus(ocorrencia.getStatus() + 1);

        dao.alterar(ocorrencia);

        listar();
    }
}
