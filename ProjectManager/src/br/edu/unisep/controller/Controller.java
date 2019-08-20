package br.edu.unisep.controller;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.fx.message.AlertUtils;
import br.edu.unisep.model.dao.UsuarioDAO;
import br.edu.unisep.model.vo.UsuarioVO;
import br.edu.unisep.utils.UsuarioUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller extends AppController {

    @FXML private AnchorPane conteudo;

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtSenha;

    @Override
    protected void onInit() {

    }

    public void entrar(ActionEvent event) {
        var u = new UsuarioVO();
        u.setEmail(txtEmail.getText());
        u.setSenha(txtSenha.getText());

        var dao = new UsuarioDAO();
        var usuario = dao.login(u);

        if (usuario != null) {
            UsuarioUtils.setUsuario(usuario);
            openScene(conteudo, "../view/home.fxml");
        } else {
            txtSenha.setText(null);
            AlertUtils.exibirErro("Dados inválidos para o login!");
        }
    }

}
