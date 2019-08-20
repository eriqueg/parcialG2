package br.edu.unisep;

import br.edu.unisep.model.dao.LivroDAO;
import br.edu.unisep.model.vo.LivroVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TableView<LivroVO> tabLivros;

    @FXML private TableColumn<LivroVO, String> colTitulo;
    @FXML private TableColumn<LivroVO, String> colAutor;
    @FXML private TableColumn<LivroVO, String> colEditora;
    @FXML private TableColumn<LivroVO, Integer> colPaginas;
    @FXML private TableColumn<LivroVO, String> colStatus;
    @FXML private Button btnLendo;
    @FXML private Button btnCabei;

    private ObservableList<LivroVO> livros;
    private LivroDAO dao;

    private LivroVO livroSel;

    public LivroVO getLivroSel() {
        return livroSel;
    }

    public void setLivroSel(LivroVO livroSel) {
        this.livroSel = livroSel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        livros = FXCollections.observableArrayList();

        tabLivros.setItems(livros);

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colEditora.setCellValueFactory(new PropertyValueFactory<>("editora"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        colStatus.setCellValueFactory(cell ->{
            var l = cell.getValue();

            if(l.getStatus() == 1){
                return new SimpleStringProperty("Não lido");
            }else if(l.getStatus() == 2){
                return new SimpleStringProperty("Lendo");
            }else {
                return new SimpleStringProperty("Lido");
            }

        });

        colTitulo.setMaxWidth(Integer.MAX_VALUE * 35d);
        colAutor.setMaxWidth(Integer.MAX_VALUE * 35d);
        colEditora.setMaxWidth(Integer.MAX_VALUE * 35d);
        colStatus.setMaxWidth(Integer.MAX_VALUE * 35d);
        colPaginas.setMaxWidth(Integer.MAX_VALUE * 35d);

        dao = new LivroDAO();
        listar();

        configurarSelecaoGrid();

    }

    private void configurarSelecaoGrid(){
        tabLivros.getSelectionModel().selectedItemProperty().addListener(
                (ObsVAlue, anterior, novo) ->{
                    btnLendo.setDisable(true);
                    btnCabei.setDisable(true);

                    if(novo != null){
                        if(novo.getStatus() == 1){
                            btnLendo.setDisable(false);
                        }else if(novo.getStatus() == 2){
                            btnCabei.setDisable(false);
                        }
                    }
                }
        );
    }




    public void listar(){
        var lst = dao.listar();
        livros.setAll(lst);


        tabLivros.getSelectionModel().clearSelection();

    }

    public void abrirNovo(ActionEvent event)throws IOException {
        abrirModal();

    }

    private void abrirModal(LivroVO... livroSel) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("novo.fxml"));

        var root = (Parent)loader.load();

        var ctrl = (NovoLivroController) loader.getController();

        var janela = new Stage();


        janela.setScene(new Scene(root));
        janela.initStyle(StageStyle.UTILITY);
        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setResizable(false);

        ctrl.setJanela(janela);
        ctrl.setCrtlLista(this);

        if(livroSel.length != 0){
            ctrl.exibirDadosAuteracao(livroSel[0]);
        }

        janela.show();
    }

    public void selecionarLivro(MouseEvent event) throws  IOException{
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){

           var livroSel = tabLivros.getSelectionModel().getSelectedItem();
           abrirModal(livroSel);
        }

    }

    public void alterarStatus(ActionEvent event){
        var livro = tabLivros.getSelectionModel().getSelectedItem();

        livro.setStatus(livro.getStatus() + 1);

        dao.alterar(livro);
        listar();


    }
}
