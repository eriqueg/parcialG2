package br.edu.unisep.controller;

import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.TarefaDAO;
import br.edu.unisep.model.dao.UsuarioDAO;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.TarefaVO;
import br.edu.unisep.model.vo.UsuarioVO;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class NovaTarefaController extends ModalController {

    @FXML private TextArea txtDescricao;

    @FXML private ChoiceBox<UsuarioVO> cmbResponsavel;

    private ObservableList<UsuarioVO> responsaveis;


    private ProjetoVO projeto;

    @Override
    protected void onModalInit() {
        projeto = (ProjetoVO) params[0];

        var dao = new UsuarioDAO();

        var lista = dao.listarEquipeProjeto(projeto);

        responsaveis = FXCollections.observableArrayList(lista);
        cmbResponsavel.setItems(responsaveis);

    }

    public void salvar(ActionEvent event) {

        var tarefa = new TarefaVO();

        tarefa.setDescricao(txtDescricao.getText());
        tarefa.setProjeto(projeto);
        tarefa.setStatus(1);
        tarefa.setResponsavel(cmbResponsavel.getValue());

        var dao = new TarefaDAO();
        dao.salvar(tarefa);

        closeModal();

    }

    public void cancelar(ActionEvent event) {
        closeModal();
    }

}
