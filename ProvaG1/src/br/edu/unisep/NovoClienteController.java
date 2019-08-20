package br.edu.unisep;

import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.ClienteDAO;
import br.edu.unisep.model.vo.ClienteVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NovoClienteController extends ModalController {

    @Required(campo = "nome")
    @FXML private TextField txtNome;

    @Required(campo = "telefone")
    @FXML private TextField txtTelefone;

    @Required(campo = "email")
    @FXML private TextField txtEmail;


    private ClienteVO cliente = new ClienteVO();

    public void salvar(){
        cliente.setTelefone(txtTelefone.getText().trim());
        cliente.setEmail(txtEmail.getText().trim());
        cliente.setNome(txtNome.getText().trim());

        var dao = new ClienteDAO();

        dao.salvar(cliente);

        closeModal();

    }


    public void cancelar(){closeModal();}

    @Override
    protected void onModalInit() {

    }
}
