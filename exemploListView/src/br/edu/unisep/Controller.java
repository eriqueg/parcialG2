package br.edu.unisep;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.vo.ContatoVO;
import br.edu.unisep.view.ContatoCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.time.LocalDate;

public class Controller extends AppController {

    @FXML private ListView<ContatoVO> lstContatos;

    private ObservableList<ContatoVO> contatos;



    @Override
    protected void onInit() {
        iniciarLIsta();
    }

    private void iniciarLIsta(){
        contatos = FXCollections.observableArrayList();

        for(int x = 0; x < 20; x++){
            var c = new ContatoVO();
            c.setNome("Contato " + x);
            c.setEmail("Contato" + x + "@gmail.com");
            c.setDtNascimento(LocalDate.now().plusDays(x));

            contatos.add(c);
        }
        lstContatos.setItems(contatos);
        lstContatos.setCellFactory(contatoVOListView -> new ContatoCell());
    }
}
