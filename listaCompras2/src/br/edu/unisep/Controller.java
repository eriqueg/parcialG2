package br.edu.unisep;

import br.edu.unisep.dao.ProdutoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private TextField txtDescricao;

    @FXML private TextField txtMercadoA;
    @FXML private TextField txtMercadoB;
    @FXML private TextField txtMercadoC;

    @FXML private TableView<ProdutoVO> tabProdutos;
    @FXML private TableColumn<ProdutoVO, String> colDescricao;
    @FXML private TableColumn<ProdutoVO, Double> colMercadoA;
    @FXML private TableColumn<ProdutoVO, Double> colMercadoB;
    @FXML private TableColumn<ProdutoVO, Double> colMercadoC;
    @FXML private Label lblTotal;


    private ObservableList<ProdutoVO> produtos;

    private ProdutoDAO dao;
    private ProdutoVO produto = new ProdutoVO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtos = FXCollections.observableArrayList();
        tabProdutos.setItems(produtos);

        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));



        colMercadoA.setCellValueFactory(new PropertyValueFactory<>("mercadoA"));
        colMercadoB.setCellValueFactory(new PropertyValueFactory<>("MercadoB"));
        colMercadoC.setCellValueFactory(new PropertyValueFactory<>("MercadoC"));

        colMercadoA.setCellFactory( col -> formatcell());
        colMercadoB.setCellFactory( col -> formatcell());
        colMercadoC.setCellFactory( col -> formatcell());
        this.dao = new ProdutoDAO();

        
        listar();

    }

    public void listar(){

        var lista =dao.listar();

        produtos.setAll(lista);

        totalizar();

    }

    private TableCell<ProdutoVO, Double> formatcell() {
        var cell = new TableCell<ProdutoVO, Double>(){
            @Override
            protected void updateItem(Double valor, boolean vazio) {
                super.updateItem(valor, vazio);

                if (valor == null || vazio){
                    setText("");
                    setStyle(null);
                } else {
                    setText(formatarValor(valor));
                    setAlignment(Pos.CENTER_RIGHT);

                    var prod = getTableRow().getItem();
                    if (valor == prod.getMaior()){
                        setStyle("-fx-background-color: #ef9a9a");
                    } else if(valor == prod.getMenor()){
                        setStyle("-fx-background-color: #a5d6a7");
                    } else {
                        setStyle(null);
                    }

                }
            }
        };
        return cell;
    }

    private String formatarValor(Double v){
        var df = new DecimalFormat("#,##0.00");
        return "R$ " + df.format(v);

    }


    public void adicionar(ActionEvent event) {

        produto.setDescricao(txtDescricao.getText());
        produto.setMercadoA(Double.valueOf(txtMercadoA.getText()));
        produto.setMercadoB(Double.valueOf(txtMercadoB.getText()));
        produto.setMercadoC(Double.valueOf(txtMercadoC.getText()));


       if(produto.getId() == null){
           dao.salvar(produto);
       }else{
           //dao.alterar(produto);
       }



        produtos.add(produto);
       //listar();
       totalizar();


        limpar();
    }

    public void excluir(ActionEvent event) {
        var p = tabProdutos.getSelectionModel().getSelectedItem();

        if (p != null)  {
            produtos.remove(p);
            totalizar();
        } else {
            var msg = new Alert(Alert.AlertType.WARNING, "Selecione um produto para excluir");
            msg.show();
        }
    }

    private void limpar() {
        txtDescricao.clear();
        txtMercadoA.clear();
        txtMercadoB.clear();
        txtMercadoC.clear();
    }

    private void totalizar(){
      //  var total = 0d;
      //  for(ProdutoVO p: produtos){
       //     total = total + p.getMenor();
       // }
        var total = produtos.stream().mapToDouble(p -> p.getMenor()).sum();

        lblTotal.setText(formatarValor(total));
    }

}
