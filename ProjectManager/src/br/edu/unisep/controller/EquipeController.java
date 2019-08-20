package br.edu.unisep.controller;

import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.MembroEquipeDAO;
import br.edu.unisep.model.dao.UsuarioDAO;
import br.edu.unisep.model.vo.MembroEquipeVO;
import br.edu.unisep.model.vo.ProjetoVO;
import br.edu.unisep.model.vo.UsuarioVO;
import br.edu.unisep.view.MembroEquipeCell;
import br.edu.unisep.view.UsuarioCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class EquipeController extends ModalController {

    @FXML private ListView<UsuarioVO> listUsuarios;
    @FXML private ListView<MembroEquipeVO> listEquipe;

    private ObservableList<UsuarioVO> usuarios;
    private ObservableList<MembroEquipeVO> equipe;

    private ProjetoVO projeto;

    @Override
    protected void onModalInit() {
        projeto = (ProjetoVO) params[0];

        usuarios = FXCollections.observableArrayList();
        listUsuarios.setItems(usuarios);
        listUsuarios.setCellFactory(f -> new UsuarioCell());

        equipe = FXCollections.observableArrayList();
        listEquipe.setItems(equipe);
        listEquipe.setCellFactory(f -> new MembroEquipeCell());

        obterUsuarios();
        obterEquipe();
    }

    private void obterUsuarios(){
        var dao = new UsuarioDAO();
        var lista = dao.listarDisponiveisEquipe(projeto);
        usuarios.setAll(lista);
    }

    private void obterEquipe() {
        var dao = new MembroEquipeDAO();
        var lista = dao.listar(projeto);
        equipe.setAll(lista);
    }


    public void dragStartUsuarios(MouseEvent event) {
        var dragboard = listUsuarios.startDragAndDrop(TransferMode.MOVE);

        var pos = listUsuarios.getSelectionModel().getSelectedIndex();

        var content = new ClipboardContent();
        content.putString(String.valueOf(pos));

        dragboard.setContent(content);

        event.consume();
    }


    public void dragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }


    public void dropMembros(DragEvent event) {
        var dragboard = event.getDragboard();
        var pos = dragboard.getString();

        var usuario = usuarios.get(Integer.parseInt(pos));

        var membro = new MembroEquipeVO();
        membro.setProjeto(projeto);
        membro.setUsuario(usuario);

        var dao = new MembroEquipeDAO();
        dao.salvar(membro);

        equipe.add(membro);
        usuarios.remove(usuario);
        event.consume();
    }

    public void dragStartMembros(MouseEvent event) {
        var dragboard = listEquipe.startDragAndDrop(TransferMode.MOVE);

        var pos = listEquipe.getSelectionModel().getSelectedIndex();

        var content = new ClipboardContent();
        content.putString(String.valueOf(pos));

        dragboard.setContent(content);

        event.consume();
    }

    public void dropUsuarios(DragEvent event) {
        var dragboard = event.getDragboard();
        var pos = dragboard.getString();

        var membro = equipe.get(Integer.parseInt(pos));

        var dao = new MembroEquipeDAO();
        dao.excluir(membro);

        equipe.remove(membro);
        usuarios.add(membro.getUsuario());

        event.consume();
    }

}
