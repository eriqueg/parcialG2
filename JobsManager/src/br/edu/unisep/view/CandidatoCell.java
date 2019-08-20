package br.edu.unisep.view;

import br.edu.unisep.model.vo.CandidatoVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CandidatoCell extends ListCell<CandidatoVO> {

    private AnchorPane cell;

    private Label lblNome;
    private Label lblEmail;

    public CandidatoCell() {

        try {
            cell = FXMLLoader.load(getClass().getResource("item_candidato.fxml"));

            lblNome = (Label) cell.lookup("#lblNome");
            lblEmail = (Label) cell.lookup("#lblEmail");

            cell.setPrefWidth(0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void updateItem(CandidatoVO candidato, boolean vazio) {
        super.updateItem(candidato, vazio);

        if(!vazio){
            lblEmail.setText(candidato.getEmail());
            lblNome.setText(candidato.getNome());
            setGraphic(cell);
        }else{
            setGraphic(null);
        }

    }
}
