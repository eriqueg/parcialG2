package br.edu.unisep.view;

import br.edu.unisep.model.vo.ContatoVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ContatoCell extends ListCell<ContatoVO> {

    private AnchorPane cell;

    private Label lblNome;
    private Label lblEmail;
    private Label lblData;

    public ContatoCell(){
        try {
            //carrega o fxml
            cell =  FXMLLoader.load(getClass().getResource("item_contato.fxml"));
            //obtem os componentes que estao no cell
            lblNome = (Label) cell.lookup("#lblNome");
            lblEmail = (Label) cell.lookup("#lblEmail");
            lblData = (Label) cell.lookup("#lblData");

            //cell.setPrefWidth(0d);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(ContatoVO contato, boolean vazio) {
        super.updateItem(contato, vazio);

        if(!vazio){
            lblNome.setText(contato.getNome());
            lblEmail.setText(contato.getEmail());

            var df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            lblData.setText(df.format(contato.getDtNascimento()));

            setGraphic(cell);
        }else{
            setGraphic(null);
        }
    }
}
