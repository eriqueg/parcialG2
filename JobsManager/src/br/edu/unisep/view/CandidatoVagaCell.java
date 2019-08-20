package br.edu.unisep.view;

import br.edu.unisep.model.vo.CandidatoVO;
import br.edu.unisep.model.vo.CandidatoVagaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CandidatoVagaCell extends ListCell<CandidatoVagaVO> {

    private AnchorPane cell;

    private Label lblNome;
    private Label lblEmail;

    public CandidatoVagaCell() {

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
    protected void updateItem(CandidatoVagaVO candidatoVaga, boolean vazio) {
        super.updateItem(candidatoVaga, vazio);

        if(!vazio){
            lblEmail.setText(candidatoVaga.getCandidato().getEmail());
            lblNome.setText(candidatoVaga.getCandidato().getNome());

            setGraphic(cell);
        }else{
            setGraphic(null);
        }
    }
}
