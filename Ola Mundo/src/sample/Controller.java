package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controller {

    @FXML private TextField txtNome;



    public void exibirMensagem(ActionEvent Event){

        var conteudo = "Olá " + txtNome.getText();

        Alert msg = new Alert(Alert.AlertType.INFORMATION, conteudo);
        msg.show();

    }

}
