package br.edu.unisep.controller;

import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.model.dao.UsuarioDAO;
import br.edu.unisep.model.vo.ClienteVO;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.UsuarioVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NovoProjetoController extends ModalController {

    @FXML private TextField txtTitulo;

    @FXML private TextArea txtDescricao;

    @FXML private ChoiceBox<ClienteVO> cmbClientes;
    @FXML private ChoiceBox<UsuarioVO> cmbGerente;

    @FXML private DatePicker txtInicio;

    private ObservableList<ClienteVO> clientes;
    private ObservableList<UsuarioVO> gerentes;

    @Override
    protected void onModalInit() {
        listarClientes();
        listarGerentes();
    }

    private void listarClientes() {
        var dao = new GenericDAO<ClienteVO>();
        var lst = dao.listar(ClienteVO.class);

        clientes = FXCollections.observableArrayList(lst);
        cmbClientes.setItems(clientes);
    }

    private void listarGerentes() {
        var dao = new UsuarioDAO();
        var lst = dao.listarPorTipo(2);

        gerentes = FXCollections.observableArrayList(lst);
        cmbGerente.setItems(gerentes);
    }

    public void salvar(ActionEvent event) {
        var proj = new ProjetoVO();

        proj.setTitulo(txtTitulo.getText());
        proj.setDescricao(txtDescricao.getText());
        proj.setInicio(txtInicio.getValue());

        proj.setCliente(cmbClientes.getValue());
        proj.setGerente(cmbGerente.getValue());

        var dao = new GenericDAO<ProjetoVO>();
        dao.salvar(proj);

        closeModal();
    }

}
