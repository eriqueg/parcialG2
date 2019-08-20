package br.edu.unisep.view;

import br.edu.unisep.model.vo.MembroEquipeVO;
import br.edu.unisep.model.vo.UsuarioVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MembroEquipeCell extends ListCell<MembroEquipeVO> {

    private AnchorPane cell;

    private Label lblNome;
    private Label lblEmail;

    public MembroEquipeCell() {

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
    protected void updateItem(MembroEquipeVO membroEquipe, boolean vazio) {
        super.updateItem(membroEquipe, vazio);

        if (!vazio) {
            var usuario = membroEquipe.getUsuario();

            lblNome.setText(usuario.getNome());
            lblEmail.setText(usuario.getEmail());

            setGraphic(cell);
        } else {
            setGraphic(null);
        }

    }
}
