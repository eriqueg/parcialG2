package br.edu.unisep.view;

import br.edu.unisep.model.vo.TarefaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FazendoCell extends ListCell<TarefaVO> {

    private AnchorPane cell;

    private Label lblDesc;

    private Label lblNome;

    private Label lblData;


    public  FazendoCell(){

        try{

            cell = FXMLLoader.load(getClass().getResource("fazendo.fxml"));

            lblDesc = (Label) cell.lookup("#lblDesc");
            lblNome = (Label) cell.lookup("#lblNome");
            lblData = (Label) cell.lookup("#lblData");

            cell.setPrefWidth(0d);

        }catch (IOException e){
            e.printStackTrace();
        }

    }


    @Override
    protected void updateItem(TarefaVO tarefa, boolean vazio) {
        super.updateItem(tarefa, vazio);

        if(!vazio){
            lblDesc.setText(tarefa.getDescricao());
            lblNome.setText(tarefa.getProjeto().getTitulo());
            var df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            lblData.setText(df.format(tarefa.getInicio()));
            setGraphic(cell);
        }else{
            setGraphic(null);
        }

    }
}
