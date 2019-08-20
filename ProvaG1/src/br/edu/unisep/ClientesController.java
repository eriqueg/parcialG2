package br.edu.unisep;

import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.ClienteDAO;
import br.edu.unisep.model.vo.ClienteVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class ClientesController extends AppController {

    @FXML private TableView<ClienteVO> tabCliente;

    @FXColumn(property = "nome", percentWidth = 30)
    @FXML private TableColumn<ClienteVO, String> colCliente;

    @FXColumn(property = "email", percentWidth = 30)
    @FXML private TableColumn<ClienteVO, String> colEmail;

    @FXColumn(property = "telefone", percentWidth = 30)
    @FXML private TableColumn<ClienteVO, String> colTelefone;


    private ObservableList<ClienteVO> clientes;
    private ClienteDAO dao;

    public void listar(){



        var lst = dao.listar();

        clientes.setAll(lst);

    }

    @Override
    protected void onInit() {

        clientes = FXCollections.observableArrayList();

        tabCliente.setItems(clientes);

        dao = new ClienteDAO();

        listar();


    }



    public void novoCliente(ActionEvent event)throws IOException {
        openModal("novoCliente.fxml", this::listar);


    }




}
