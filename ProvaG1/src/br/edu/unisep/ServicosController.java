package br.edu.unisep;

import br.edu.unisep.fx.annotation.ColValueMap;
import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.annotation.IntToString;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.ServicoDAO;
import br.edu.unisep.model.vo.ServicoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ServicosController extends AppController {

    @FXML private TableView<ServicoVO> tabServicos;

    @FXColumn(property = "cliente", percentWidth = 30)
    @FXML private TableColumn<ServicoVO, String> colCliente;

    @FXColumn(property = "dataEntrega", percentWidth = 20)
    @FXML private TableColumn<ServicoVO, String> colDataEnt;

    @FXColumn(property = "dataRetirada", percentWidth = 20)
    @FXML private TableColumn<ServicoVO, String> colDataRet;

    @FXColumn(property = "total", percentWidth = 15)
    @FXML private TableColumn<ServicoVO, String> colTotal;

    @ColValueMap({
        @IntToString(from = 1, to = "Não Iniciado"),
        @IntToString(from = 2, to = "Em Andamento"),
        @IntToString(from = 3, to = "Finalizado")
    })
    @FXColumn(property = "status", percentWidth = 25)
    @FXML private TableColumn<ServicoVO, String> colStatus;


    @FXML private Button btnIniciar;
    @FXML private Button btnFinalizar;
    @FXML private Button btnRetirar;

    private ObservableList<ServicoVO> servicos;
    private ServicoDAO dao;

    private void configurarSelecao(){
        tabServicos.getSelectionModel().selectedItemProperty().addListener(
                (ObsVAlue, anterior, novo) ->{
                    btnFinalizar.setDisable(true);
                    btnIniciar.setDisable(true);
                    btnRetirar.setDisable(true);

                    if(novo != null){
                        if(novo.getStatus() == 1){
                            btnIniciar.setDisable(false);
                        }else if(novo.getStatus() == 2){
                            btnFinalizar.setDisable(false);
                        }else if(novo.getStatus() == 3){
                            btnRetirar.setDisable(false);
                        }
                    }
                }
        );
    }




    public void listar(){

        var lst = dao.listar();

        servicos.setAll(lst);
    }

    public void alterarStautus(ActionEvent event){

        var servico = tabServicos.getSelectionModel().getSelectedItem();

        servico.setStatus(servico.getStatus() + 1);

        dao.salvar(servico);
        listar();

    }

    @Override
    protected void onInit() {

        servicos = FXCollections.observableArrayList();

        tabServicos.setItems(servicos);

        dao = new ServicoDAO();

        listar();


    }

    public void novoServico(ActionEvent event){
        openModal("novoServico.fxml", this::listar);
    }

}
