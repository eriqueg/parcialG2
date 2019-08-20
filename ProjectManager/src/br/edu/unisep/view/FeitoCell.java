package br.edu.unisep.view;

import br.edu.unisep.model.vo.TarefaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class FeitoCell extends ListCell<TarefaVO> {

    private AnchorPane cell;
    private Label lblDesc;
    private Label lblNome;
    private Label lblDataIni;
    private Label lblDataFin;


    public FeitoCell(){
        try{

            cell = FXMLLoader.load(getClass().getResource("item_feito.fxml"));

            lblDesc = (Label) cell.lookup("#lblDesc");
            lblNome = (Label) cell.lookup("#lblNome");
            lblDataIni = (Label) cell.lookup("#lblDataIni");
            lblDataFin = (Label) cell.lookup("#lblDataFin");

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
            lblDataIni.setText(df.format(tarefa.getInicio()));
            lblDataFin.setText(df.format(tarefa.getTermino()));
            setGraphic(cell);
        }else{
            setGraphic(null);
        }


    }
}
