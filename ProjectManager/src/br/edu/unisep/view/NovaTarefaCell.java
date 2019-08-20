package br.edu.unisep.view;

import br.edu.unisep.model.vo.TarefaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NovaTarefaCell extends ListCell<TarefaVO> {
    private AnchorPane cell;

    private Label lblDescricao;

    private Label lblNome;


    public NovaTarefaCell(){
        try {

            cell = FXMLLoader.load(getClass().getResource("item_nova_tarefa.fxml"));

            lblDescricao = (Label) cell.lookup("#lblDescricao");
            lblNome = (Label) cell.lookup("#lblNome");

            cell.setPrefWidth(0d);

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    @Override
    protected void updateItem(TarefaVO tarefa, boolean vazio) {
        super.updateItem(tarefa, vazio);

        if(!vazio){
            lblNome.setText(tarefa.getProjeto().getTitulo());
            lblDescricao.setText(tarefa.getDescricao());

            setGraphic(cell);
        }else{
            setGraphic(null);
        }


    }
}
