package br.edu.unisep;

import br.edu.unisep.model.vo.MedicaoVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    tabela = FXCollections.observableArrayList();

    tabMedicoes.setItems(tabela);

    colData.setMaxWidth(Integer.MAX_VALUE * 30.0);
    colDiastolica.setMaxWidth(Integer.MAX_VALUE * 20.0);
    colSistolica.setMaxWidth(Integer.MAX_VALUE * 20.0);
    colResult.setMaxWidth(Integer.MAX_VALUE * 30.0);

    //colData.setCellValueFactory(new PropertyValueFactory<>("data"));
    colSistolica.setCellValueFactory(new PropertyValueFactory<>("sistolica"));
    colDiastolica.setCellValueFactory(new PropertyValueFactory<>("diastolica"));
    colResult.setCellValueFactory(new PropertyValueFactory<>("resultado"));

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
    }

    public void salvar(ActionEvent event) {
     var teste = new MedicaoVO();
     teste.setDiastolica(Integer.valueOf(dis.getText()));
     teste.setSistolica(Integer.valueOf(sis.getText()));
     teste.setData(data.getValue());

     if (teste.getSistolica() <= 120 && teste.getDiastolica() <= 80){
         teste.setResultado(1);
     }else if(teste.getSistolica() < 140 || teste.getDiastolica() < 90){
            teste.setResultado(2);
     }else if(teste.getSistolica() < 160 || teste.getDiastolica() < 100){
         teste.setResultado(3);
     }else if(teste.getSistolica() < 180 || teste.getDiastolica() < 110){
         teste.setResultado(4);
     }else{
         teste.setResultado(5);
     }

     tabela.add(teste);
     limpar(event);
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
    tabela.remove(med);
    }else{
        var alerta = "Selecione um item para exclusão";

        Alert msg = new Alert(Alert.AlertType.WARNING, alerta);
        msg.show();
    }

    }

}
