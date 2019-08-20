package br.edu.unisep.view;

import br.edu.unisep.model.vo.TarefaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TarefaCell extends ListCell<TarefaVO> {

    private AnchorPane cell;

    private Label lblTitulo;
    private Label lblInicio;
    private Label lblResp;
    private Label lblTermino;

    private Circle indicador;

    private DateTimeFormatter fmt;

    public TarefaCell() {
        try {
            cell = FXMLLoader.load(getClass().getResource("item_tarefa.fxml"));

            lblTitulo = (Label) cell.lookup("#lblTitulo");
            lblResp = (Label) cell.lookup("#lblResp");
            lblInicio = (Label) cell.lookup("#lblInicio");
            lblTermino = (Label) cell.lookup("#lblTermino");

            indicador = (Circle) cell.lookup("#indicador");

            fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            lblInicio.setText("");
            lblTermino.setText("");

            cell.setPrefWidth(0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void updateItem(TarefaVO tarefa, boolean vazio) {
        super.updateItem(tarefa, vazio);

        if (!vazio) {

            lblTitulo.setText(tarefa.getDescricao());
            lblResp.setText(tarefa.getResponsavel().getNome());

            if (tarefa.getStatus() != 1) {
                lblInicio.setText(tarefa.getInicio().format(fmt));

                if (tarefa.getTermino() != null) {
                    lblTermino.setText(tarefa.getTermino().format(fmt));
                }
            }

            if(tarefa.getStatus() == 1){
                indicador.setFill(Paint.valueOf("#4fc3c7"));
            }else if(tarefa.getStatus() == 2){
                indicador.setFill(Paint.valueOf("#ffb74d"));
            }else{
                indicador.setFill(Paint.valueOf("#81c784"));
            }
            setGraphic(cell);
        } else {
            setGraphic(null);
        }

    }
}
