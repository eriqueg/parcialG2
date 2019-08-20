package br.edu.unisep.controller;

import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.model.vo.ProjetoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class ProjetosController extends AppController {

    @FXML private TableView<ProjetoVO> tabProjetos;

    @FXColumn(property = "titulo", percentWidth = 35)
    @FXML private TableColumn<ProjetoVO, String> colTitulo;

    @FXColumn(property = "cliente", percentWidth = 25)
    @FXML private TableColumn<ProjetoVO, String> colCliente;

    @FXColumn(property = "gerente", percentWidth = 25)
    @FXML private TableColumn<ProjetoVO, String> colGerente;

    @FXColumn(property = "inicio", dateFormat = "dd/MM/yyyy", percentWidth = 15)
    @FXML private TableColumn<ProjetoVO, LocalDate> colInicio;

    private ObservableList<ProjetoVO> projetos;
    private GenericDAO<ProjetoVO> dao = new GenericDAO<>();

    @Override
    protected void onInit() {
        projetos = FXCollections.observableArrayList();
        tabProjetos.setItems(projetos);

        listar();
    }

    public void listar() {
        var list = dao.listar(ProjetoVO.class);
        projetos.setAll(list);
    }

    public void abrirNovo(ActionEvent event) {
        openModal("../view/novoProjeto.fxml", this::listar);
    }

}
