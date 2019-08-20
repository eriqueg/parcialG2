package br.edu.unisep;

import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.fx.message.AlertUtils;
import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.model.dao.VagaDAO;
import br.edu.unisep.model.vo.EmpresaVO;
import br.edu.unisep.model.vo.VagaVO;
import br.edu.unisep.view.EmpresaCell;
import br.edu.unisep.view.VagaCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainController extends AppController {

    @FXML private ListView<EmpresaVO> listEmpresas;
    @FXML private ListView<VagaVO> listVagas;

    private ObservableList<EmpresaVO> empresas;
    private ObservableList<VagaVO> vagas;

    @Override
    protected void onInit() {
        vagas = FXCollections.observableArrayList();
        empresas = FXCollections.observableArrayList();

        obterEmpresas();

        listEmpresas.getSelectionModel().selectedItemProperty()
                .addListener((value, anterior, atual) ->{
                    if (atual != null) {
                        obterVagas(atual);
                    }
                });

        listVagas.setCellFactory(l -> new VagaCell());
        listVagas.setItems(vagas);

        listEmpresas.setCellFactory(f -> new EmpresaCell());
        listEmpresas.setItems(empresas);


    }

    private void obterEmpresas() {
        var dao = new GenericDAO<EmpresaVO>();
        var lista = dao.listar(EmpresaVO.class);
        empresas.setAll(lista);


    }

    private void obterVagas(EmpresaVO empresa) {

        var dao = new VagaDAO();
        var lista = dao.listar(empresa);
        vagas.setAll(lista);

    }

    public void abrirNovaVaga(ActionEvent event) {
        var empresa = listEmpresas.getSelectionModel().getSelectedItem();

        if (empresa == null) {
            AlertUtils.exibirWarning("Selecione uma empresa");
        } else {
            openModal("nova_vaga.fxml", () -> obterVagas(empresa), empresa);
        }
    }


    public void encaminhar(ActionEvent event) {
        var vaga = listVagas.getSelectionModel().getSelectedItem();

        if (vaga == null) {
            AlertUtils.exibirWarning("Selecione uma vaga");
        } else {
            openModal("encaminhamento.fxml", vaga);
        }
    }
}
