package br.edu.unisep;

import br.edu.unisep.model.dao.MedicaoDAO;
import br.edu.unisep.model.vo.MedicaoVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private DatePicker data;

    @FXML private TextField sis;
    @FXML private TextField dis;

    @FXML private TableView<MedicaoVO> tabMedicoes;
    @FXML private TableColumn<MedicaoVO, String> colData;
    @FXML private TableColumn<MedicaoVO, Integer> colSistolica;
    @FXML private TableColumn<MedicaoVO, Integer> colDiastolica;
    @FXML private TableColumn<MedicaoVO, String> colResult;

    private ObservableList<MedicaoVO> tabela;

    private MedicaoDAO dao;
    private MedicaoVO medicao = new MedicaoVO();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inicializa a lista de medicoes vazia
        tabela = FXCollections.observableArrayList();

        tabMedicoes.setItems(tabela);
        //associa table view com o a lista
        colData.setMaxWidth(Integer.MAX_VALUE * 30.0);
        colSistolica.setMaxWidth(Integer.MAX_VALUE * 20.0);
        colDiastolica.setMaxWidth(Integer.MAX_VALUE * 20.0);
        colResult.setMaxWidth(Integer.MAX_VALUE * 30.0);

        //colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colSistolica.setCellValueFactory(new PropertyValueFactory<>("sistolica"));
        colDiastolica.setCellValueFactory(new PropertyValueFactory<>("diastolica"));
        //colResult.setCellValueFactory(new PropertyValueFactory<>("resultado"));

        var dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//    colData.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MedicaoVO, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<MedicaoVO, String> cell) {
//                var data = cell.getValue().getData();
//
//                var dataFmt = new SimpleStringProperty(dtf.format(data));
//                return dataFmt;
//            }
//        });

        colData.setCellValueFactory((cell) -> {
                    var data = cell.getValue().getData();
                    var dataFmt = new SimpleStringProperty(dtf.format(data));
                    return dataFmt;
                }
        );

        colResult.setCellValueFactory((cell)->{
          var tipo = cell.getValue().getResultado();
          var resultado = "";
          if(tipo == 1){
              resultado = "Normal";

          }else if(tipo == 2){
              resultado = "Pré-Hipertenso";
          }else if(tipo == 3){
              resultado = "Hipertenso I";
          }else if(tipo == 4){
              resultado = "Hipertenso II";
          }else{
              resultado = "Crítico";
          }

          return new SimpleStringProperty(resultado);

        });
        this.dao = new MedicaoDAO();
        listar();

    }

    public void salvar(ActionEvent event) {


        medicao.setDiastolica(Integer.valueOf(dis.getText()));
        medicao.setSistolica(Integer.valueOf(sis.getText()));
        medicao.setData(data.getValue());

        if (medicao.getSistolica() <= 120 && medicao.getDiastolica() <= 80){
            medicao.setResultado(1);
        }else if(medicao.getSistolica() < 140 || medicao.getDiastolica() < 90){
            medicao.setResultado(2);
        }else if(medicao.getSistolica() < 160 || medicao.getDiastolica() < 100){
            medicao.setResultado(3);
        }else if(medicao.getSistolica() < 180 || medicao.getDiastolica() < 110){
            medicao.setResultado(4);
        }else{
            medicao.setResultado(5);
        }

        if(medicao.getId() == null){
        dao.salvar(medicao);
        }else{
            dao.alterar(medicao);
            medicao =new MedicaoVO();
        }

        limpar(event);
        listar();
    }

    public void limpar(ActionEvent event) {
        sis.clear();
        dis.clear();
        data.setValue(null);

        data.requestFocus();
    }

    public void excluir(ActionEvent event) {
        var med = tabMedicoes.getSelectionModel().getSelectedItem();

        if (med != null){

            dao.excluir(med);

            listar();
        }else{
            var alerta = "Selecione um item para exclusão";

            Alert msg = new Alert(Alert.AlertType.WARNING, alerta);
            msg.show();
        }

    }

    private void listar(){
        var dao = new MedicaoDAO();
        var lista =dao.listar();

        tabela.setAll(lista);

    }

    public void selecionarItemLista(MouseEvent event){

        if(event.getButton() == MouseButton.PRIMARY &&
        event.getClickCount() == 2){
            medicao = tabMedicoes.getSelectionModel().getSelectedItem();

            data.setValue(medicao.getData());
            sis.setText(medicao.getSistolica().toString());
            dis.setText(medicao.getDiastolica().toString());
            data.requestFocus();
        }

    }



}
