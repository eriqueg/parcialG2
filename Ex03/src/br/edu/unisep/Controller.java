package br.edu.unisep;

import br.edu.unisep.model.vo.MedicaoVO;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private DatePicker txtData;

    @FXML private TextField txtSist;
    @FXML private TextField txtDiast;

    @FXML private TableView<MedicaoVO> tabMedicoes;

    @FXML private TableColumn<MedicaoVO, String> colData;
    @FXML private TableColumn<MedicaoVO, Integer> colDist;
    @FXML private TableColumn<MedicaoVO, Integer> colSist;
    @FXML private TableColumn<MedicaoVO, String> colResultado;

    private ObservableList<MedicaoVO> medicoes;




    public void salvar(ActionEvent evento){

        var med = new MedicaoVO();
        med.setData(txtData.getValue());
        med.setDiastolica(Integer.valueOf(txtDiast.getText()));
        med.setSistolica(Integer.valueOf(txtSist.getText()));

        if(med.getSistolica() <= 120 && med.getDiastolica() <= 80){
            med.setResultado("Normal");
        }else if(med.getSistolica() < 140 || med.getDiastolica() < 90){
            med.setResultado("Pré-Hipertenso");
        }else if(med.getSistolica() < 160 || med.getDiastolica() < 100){
            med.setResultado("Hipertenso I");
        }else if(med.getSistolica() < 160 || med.getDiastolica() < 110 ){
            med.setResultado("Hipertenso II");
        }else{
            med.setResultado("Critico");
        }

        // adiciona obj criado na lista
        medicoes.add(med);

        limpar(evento);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inicializa a lista de medições vazia
        medicoes = FXCollections.observableArrayList();

        //associar a lista de medições ao Table View de medições
        //Desta forma, os objetos que forem adicionados a lista
        //aparecerão no TableView da tela

        tabMedicoes.setItems(medicoes);

        //define a largura das colunas
        colData.setMaxWidth(Integer.MAX_VALUE * 30.0);
        colSist.setMaxWidth(Integer.MAX_VALUE * 20.0);
        colDist.setMaxWidth(Integer.MAX_VALUE * 20.0);
        colResultado.setMaxWidth(Integer.MAX_VALUE * 30.0);

        //define qual o atributo do obj MedicaoVO sera exibido em cada coluna
        //colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colDist.setCellValueFactory(new PropertyValueFactory<>("diastolica"));
        colSist.setCellValueFactory(new PropertyValueFactory<>("sistolica"));
        colResultado.setCellValueFactory(new PropertyValueFactory<>("resultado"));

        var dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

//        colData.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<MedicaoVO, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<MedicaoVO, String> cell) {
//                var data = cell.getValue().getData();
//                var dataFmt = new SimpleStringProperty(dtf.format(data));
//                return dataFmt;
//            }
//        });

        colData.setCellValueFactory( (cell) -> {

                var data = cell.getValue().getData();
                var dataFmt = new SimpleStringProperty(dtf.format(data));
                return dataFmt;

            }
        );

    }


    public void limpar(ActionEvent event){

        txtData.setValue(null);
        txtSist.clear();
        txtDiast.clear();
        txtData.requestFocus();

    }


    public void excluir(ActionEvent event){
        var med = tabMedicoes.getSelectionModel().getSelectedItem();

        //verifica se o usuario selecionou um item para excluir
        if(med != null){
            medicoes.remove(med);
        }else{
            var mensagem = new Alert(Alert.AlertType.WARNING, "Selecione um item para excluir");
            mensagem.show();
        }

    }
}
