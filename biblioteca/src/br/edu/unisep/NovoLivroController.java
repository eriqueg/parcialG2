package br.edu.unisep;

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

public class NovoLivroController implements Initializable {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtEditora;
    @FXML private TextField txtPaginas;

    @FXML private TextArea txtSinopse;

    @FXML private ChoiceBox<AutorVO> cmbAutor;

    @FXML  private Label lblTitulo;



    private Stage janela;

    private Controller crtlLista;

    private LivroVO livro = new LivroVO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listarAutores();



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

        if(validar()) {

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

            janela.close();
            crtlLista.listar();
        }

    }

    private boolean validar(){

        if(txtTitulo.getText().trim().length() == 0){
            exibirMsgErro("Campo Titulo é Obrigatorio");

            return false;
        }

        if(cmbAutor.getValue() == null){
            exibirMsgErro("Campo Autor é Obrigatorio");

            return false;
        }

        if(txtEditora.getText().trim().length() == 0){
            exibirMsgErro("Campo Editora é Obrigatorio");

            return false;
        }

        try{
            Integer.parseInt(txtPaginas.getText());
        }catch(NumberFormatException e){
            exibirMsgErro("Campo Paginas é Inválido");

            return false;
        }

        if(txtSinopse.getText().trim().length() == 0){
            exibirMsgErro("Campo Sinopse é Obrigatorio");

            return false;
        }

        return true;
    }



    private void exibirMsgErro(String s) {
        var msg = new Alert(Alert.AlertType.ERROR, s);

        msg.setHeaderText(null);
        msg.show();
    }

    public void cancelar(){
        janela.close();
    }


    public Stage getJanela() {
        return janela;
    }

    public void setJanela(Stage janela) {
        this.janela = janela;
    }

    public Controller getCrtlLista() {
        return crtlLista;
    }

    public void setCrtlLista(Controller crtlLista) {
        this.crtlLista = crtlLista;
    }
}
