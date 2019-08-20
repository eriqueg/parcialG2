package br.edu.unisep.view;

import br.edu.unisep.model.vo.VagaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class VagaCell extends ListCell<VagaVO> {

    private AnchorPane cell;

    private Label lblTitulo;
    private Label lblDescricao;
    private Label lblQtde;
    private Label lblSalario;
    private Label lblInicio;
    private Label lblTermino;

    public VagaCell() {

        try {
            cell = FXMLLoader.load(getClass().getResource("item_vaga.fxml"));

            lblTitulo = (Label) cell.lookup("#lblTitulo");
            lblDescricao = (Label) cell.lookup("#lblDescricao");
            lblQtde = (Label) cell.lookup("#lblQtde");
            lblSalario = (Label) cell.lookup("#lblSalario");
            lblInicio = (Label) cell.lookup("#lblInicio");
            lblTermino = (Label) cell.lookup("#lblTermino");

            cell.setPrefWidth(0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(VagaVO vaga, boolean vazio) {
        super.updateItem(vaga, vazio);

        if(!vazio){
            lblTitulo.setText(vaga.getTitulo());
            lblDescricao.setText(vaga.getDescricao());
            var df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            lblInicio.setText(df.format(vaga.getInicio()));
            lblQtde.setText(vaga.getQuantidade().toString());
            lblSalario.setText(vaga.getSalario().toString());
            lblTermino.setText(df.format(vaga.getTermino()));

            setGraphic(cell);
        }else{
            setGraphic(null);
        }
    }
}
