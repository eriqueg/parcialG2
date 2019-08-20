package br.edu.unisep;

import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.fx.message.AlertUtils;
import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.model.vo.CandidatoVO;
import br.edu.unisep.model.vo.CandidatoVagaVO;
import br.edu.unisep.model.vo.VagaVO;
import br.edu.unisep.view.CandidatoCell;
import br.edu.unisep.view.CandidatoVagaCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


public class EncaminhamentoController extends ModalController {

    @FXML private Label lblTitulo;
    @FXML private Label lblEmpresa;
    @FXML private Label lblQtde;

    @FXML private ListView<CandidatoVO> listCandidatos;
    @FXML private ListView<CandidatoVagaVO> listSelecionados;

    private ObservableList<CandidatoVO> candidatos;
    private ObservableList<CandidatoVagaVO> selecionados;

    private VagaVO vaga;

    @Override
    protected void onModalInit() {

        vaga = (VagaVO) params[0];
        lblTitulo.setText(vaga.getTitulo());
        lblEmpresa.setText(vaga.getEmpresa().getNome());
        lblQtde.setText(vaga.getQuantidade().toString());

        obterCandidatos();

        selecionados = FXCollections.observableArrayList();

        listSelecionados.setCellFactory(l -> new CandidatoVagaCell());
        listSelecionados.setItems(selecionados);



    }

    public void obterCandidatos(){
        var dao = new GenericDAO<CandidatoVO>();
        var lista = dao.listar(CandidatoVO.class);

        candidatos = FXCollections.observableArrayList(lista);

        listCandidatos.setCellFactory(l -> new CandidatoCell());
        listCandidatos.setItems(candidatos);

    }

//    public void obterSelecionados(){
//        var dao = new <CandidatoVagaVO>();
//        var lista = dao.listar(CandidatoVagaVO.class);
//
//        selecionados = FXCollections.observableArrayList(lista);
//
//
//        listSelecionados.setCellFactory(l -> new CandidatoVagaCell());
//        listSelecionados.setItems(selecionados);
//
//    }

    public void dragStart(MouseEvent event){
        var dragboard = listCandidatos.startDragAndDrop(TransferMode.MOVE);

        var pos = listCandidatos.getSelectionModel().getSelectedIndex();

        var content = new ClipboardContent();
        content.putString(String.valueOf(pos));

        dragboard.setContent(content);

        event.consume();
    }


    public void dragOver(DragEvent event){
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    public void drop(DragEvent event){
        if(vaga.getQuantidade() > 0){
            var dragboard = event.getDragboard();
            var pos = dragboard.getString();

            var selec = candidatos.get(Integer.parseInt(pos));

            var dao = new GenericDAO<CandidatoVagaVO>();

            var sel = new CandidatoVagaVO();

            var daov = new GenericDAO<VagaVO>();

            sel.setCandidato(selec);
            sel.setVaga(vaga);

            dao.salvar(sel);
            vaga.setQuantidade(vaga.getQuantidade() - 1);
            daov.alterar(vaga);
            candidatos.remove(selec);
            selecionados.add(sel);
            var dispo = Integer.parseInt(lblQtde.getText());
            dispo --;
            lblQtde.setText(String.valueOf(dispo));

        }else{
            AlertUtils.exibirWarning("Nenhuma vaga disponível");
        }
        event.consume();
    }


}
