package br.edu.unisep.controller;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.TarefaDAO;
import br.edu.unisep.model.vo.TarefaVO;
import br.edu.unisep.utils.UsuarioUtils;
import br.edu.unisep.view.FazendoCell;
import br.edu.unisep.view.FeitoCell;
import br.edu.unisep.view.NovaTarefaCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

import java.time.LocalDateTime;
import java.util.Date;

public class MinhasTarefasController extends AppController {

    @FXML private ListView<TarefaVO> lstAfazer;
    @FXML private ListView<TarefaVO> lstFazendo;
    @FXML private ListView<TarefaVO> lstFeito;


    private ObservableList<TarefaVO> aFazer;
    private ObservableList<TarefaVO> fazendo;
    private ObservableList<TarefaVO> feito;


    @Override
    protected void onInit() {
        var dao = new TarefaDAO();

        aFazer = FXCollections.observableArrayList();
        fazendo = FXCollections.observableArrayList();
        feito = FXCollections.observableArrayList();

        lstAfazer.setCellFactory(f -> new NovaTarefaCell());
        lstFazendo.setCellFactory(y -> new FazendoCell());
        lstFeito.setCellFactory(a -> new FeitoCell());

        var listAfazer = dao.listarPorStatus(UsuarioUtils.getUsuario(), 1);
        var listFazendo = dao.listarPorStatus(UsuarioUtils.getUsuario(), 2);
        var listFeito = dao.listarPorStatus(UsuarioUtils.getUsuario(), 3);

        aFazer.setAll(listAfazer);
        lstAfazer.setItems(aFazer);

        fazendo.setAll(listFazendo);
        lstFazendo.setItems(fazendo);

        feito.setAll(listFeito);
        lstFeito.setItems(feito);


    }


    public void gragAfazerStart(MouseEvent event){
        var dragboard = lstAfazer.startDragAndDrop(TransferMode.MOVE);

        var pos = lstAfazer.getSelectionModel().getSelectedIndex();

        var content = new ClipboardContent();
        content.putString(String.valueOf(pos));

        dragboard.setContent(content);

        event.consume();

    }

    public void DragOver(DragEvent event){
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    public void dropAfazer(DragEvent event){
        var dragboard = event.getDragboard();
        var pos = dragboard.getString();

        var afazer = aFazer.get(Integer.parseInt(pos));

        var dao = new TarefaDAO();

        var faz = new TarefaVO();
        faz.setResponsavel(afazer.getResponsavel());
        faz.setProjeto(afazer.getProjeto());
        faz.setInicio(LocalDateTime.now());
        faz.setStatus(2);
        faz.setDescricao(afazer.getDescricao());
        faz.setTermino(afazer.getTermino());
        faz.setId(afazer.getId());
        dao.alterar(faz);

        fazendo.add(faz);
        aFazer.remove(afazer);

        event.consume();


    }
    public void dragFazendoStart(MouseEvent event){
        var dragboard = lstFazendo.startDragAndDrop(TransferMode.MOVE);

        var pos = lstFazendo.getSelectionModel().getSelectedIndex();

        var content = new ClipboardContent();
        content.putString(String.valueOf(pos));

        dragboard.setContent(content);

        event.consume();

    }
    public void dropFeito(DragEvent event){
        var dragboard = event.getDragboard();
        var pos = dragboard.getString();

        var fazen = fazendo.get(Integer.parseInt(pos));

        var dao = new TarefaDAO();

        var faz = new TarefaVO();
        faz.setResponsavel(fazen.getResponsavel());
        faz.setProjeto(fazen.getProjeto());
        faz.setTermino(LocalDateTime.now());
        faz.setInicio(fazen.getInicio());
        faz.setStatus(3);
        faz.setDescricao(fazen.getDescricao());
        faz.setId(fazen.getId());

        dao.alterar(faz);

        feito.add(faz);
        fazendo.remove(fazen);

        event.consume();


    }

}
