package br.edu.unisep.view;

import br.edu.unisep.model.vo.ProjetoVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProjetoCell extends ListCell<ProjetoVO> {

    private AnchorPane cell;

    private Label lblTitulo;
    private Label lblCliente;

    public ProjetoCell() {

        try {
            cell = FXMLLoader.load(getClass().getResource("item_projeto.fxml"));

            lblTitulo = (Label) cell.lookup("#lblTitulo");
            lblCliente = (Label) cell.lookup("#lblCliente");

            cell.setPrefWidth(0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void updateItem(ProjetoVO projeto, boolean vazio) {
        super.updateItem(projeto, vazio);

        if (!vazio) {
            lblTitulo.setText(projeto.getTitulo());
            lblCliente.setText(projeto.getCliente().getNome());

            setGraphic(cell);
        } else {
            setGraphic(null);
        }

    }
}
