package br.edu.unisep;

import br.edu.unisep.fx.annotation.OnlyNumber;
import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.AutorDAO;
import br.edu.unisep.model.dao.LivroDAO;
import br.edu.unisep.model.vo.AutorVO;
import br.edu.unisep.model.vo.LivroVO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NovoLivroController extends ModalController {

    @Required(campo = "título")
    @FXML private TextField txtTitulo;
    @Required(campo = "editora")
    @FXML private TextField txtEditora;
    @OnlyNumber()
    @Required(campo = "páginas")
    @FXML private TextField txtPaginas;

    @Required(campo = "sinópse")
    @FXML private TextArea txtSinopse;

    @Required(campo = "autor")
    @FXML private ChoiceBox<AutorVO> cmbAutor;


    @FXML  private Label lblTitulo;


    private LivroVO livro = new LivroVO();


    @Override
    public void onModalInit(){
        listarAutores();

        if(params.length != 0){
            var livroSel = (LivroVO) params[0];
            exibirDadosAuteracao(livroSel);
        }
    }


    public void exibirDadosAuteracao(LivroVO livroSel){

        livro.setId(livroSel.getId());
        livro.setStatus(livroSel.getStatus());



        txtTitulo.setText(livroSel.getTitulo());
        txtEditora.setText(livroSel.getEditora());
        txtPaginas.setText(livroSel.getPaginas().toString());
        txtSinopse.setText(livroSel.getSinopse());
        cmbAutor.setValue(livroSel.getAutor());

        lblTitulo.setText("Alterar Livro");


    }

    private void listarAutores(){
        var dao = new AutorDAO();

        var lista = dao.listar();

        cmbAutor.setItems(FXCollections.observableList(lista));



    }

    public void salvar(){

        if(validate()) {

            livro.setTitulo(txtTitulo.getText().trim());
            livro.setPaginas(Integer.valueOf(txtPaginas.getText()));
            livro.setEditora(txtEditora.getText().trim());
            livro.setSinopse(txtSinopse.getText().trim());
            livro.setStatus(1);

            livro.setAutor(cmbAutor.getValue());

            var dao = new LivroDAO();

            if (livro.getId() != null) {
                dao.alterar(livro);
            } else {
                dao.salvar(livro);
            }

            closeModal();
        }

    }



    public void cancelar(){
        closeModal();
    }



}
