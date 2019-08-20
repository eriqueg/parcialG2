package br.edu.unisep;

import br.edu.unisep.fx.controller.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.*;

import java.util.List;

public class Controller extends AppController {

    @FXML private ListView<String> lstOrigem;
    @FXML private ListView<String> lstDestino;

    private ObservableList<String> itensOrigem;
    private ObservableList<String> itensDestino;



    @Override
    protected void onInit() {

        itensOrigem = FXCollections.observableArrayList();
        lstOrigem.setItems(itensOrigem);

        itensDestino = FXCollections.observableArrayList();
        lstDestino.setItems(itensDestino);

        carregarItensOrigem();
    }

    public void carregarItensOrigem(){
        for(int i = 0; i< 10; i++){
            itensOrigem.add("Exemplo item" + i + 1);
        }
    }

    public void dragStart(MouseEvent event){
        //inicia o processo de drag board
        //o objeto encapsula o item que sera movido para a lista de destino
        var dragboard = lstOrigem.startDragAndDrop(TransferMode.MOVE);
        //obtem a posição que representa o item selecionado
        var pos = lstOrigem.getSelectionModel().getSelectedIndex();
        //
        var content = new ClipboardContent();
        //adiciona a posicao selecionada na origem
        content.putString(String.valueOf(pos));
        //adiciona o conteudo no dragboard para ser enviado
        dragboard.setContent(content);
        //Indica que i evento drag nao sera visto por outros componentes
        event.consume();
    }

    public void dragOver(DragEvent event){
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }
    public void drop(DragEvent event){
        //recupera o dragboard que foi eviado da origem
        var dragboard = event.getDragboard();

        //recupera a informação da posicao selecionada na origem
        var pos = dragboard.getString();
        //obtem o item na lista destino
        var item = itensOrigem.get(Integer.parseInt(pos));
        //adiciona o item na lista dest
        itensDestino.add(item);
        //retira o item da origem
        itensOrigem.remove(item);

        event.consume();


    }
}
