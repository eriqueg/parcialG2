package br.edu.unisep;

import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.VagaDAO;
import br.edu.unisep.model.vo.EmpresaVO;
import br.edu.unisep.model.vo.VagaVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NovaVagaController extends ModalController {

    @FXML private TextArea txtDescricao;

    @FXML private TextField txtTitulo;
    @FXML private TextField txtQtde;
    @FXML private TextField txtSalario;

    @FXML private DatePicker txtInicio;
    @FXML private DatePicker txtTermino;

    private EmpresaVO empresa;

    @Override
    protected void onModalInit() {
        empresa = (EmpresaVO) params[0];



    }

    public void salvar(ActionEvent event) {
        var vaga = new VagaVO();

        vaga.setTitulo(txtTitulo.getText());
        vaga.setDescricao(txtDescricao.getText());
        vaga.setEmpresa(empresa);
        vaga.setQuantidade(Integer.parseInt(txtQtde.getText()));
        vaga.setSalario(Double.parseDouble(txtSalario.getText()));
        vaga.setInicio(txtInicio.getValue());
        vaga.setTermino(txtTermino.getValue());

        var dao = new VagaDAO();

        dao.salvar(vaga);

        closeModal();
    }

    public void cancelar(ActionEvent event) {
        closeModal();
    }

}
