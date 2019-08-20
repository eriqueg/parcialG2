package br.edu.unisep.view;

import br.edu.unisep.model.vo.UsuarioVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UsuarioCell extends ListCell<UsuarioVO> {

    private AnchorPane cell;

    private Label lblNome;
    private Label lblEmail;

    public UsuarioCell() {

        try {
            cell = FXMLLoader.load(getClass().getResource("item_usuario.fxml"));

            lblNome = (Label) cell.lookup("#lblNome");
            lblEmail = (Label) cell.lookup("#lblEmail");

            cell.setPrefWidth(0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void updateItem(UsuarioVO usuario, boolean vazio) {
        super.updateItem(usuario, vazio);

        if (!vazio) {
            lblNome.setText(usuario.getNome());
            lblEmail.setText(usuario.getEmail());

            setGraphic(cell);
        } else {
            setGraphic(null);
        }

    }
}
