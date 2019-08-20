package br.edu.unisep;

import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.vo.ServicoVO;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class NovoServicoController extends ModalController {

   @Required(campo = "cliente")
   @FXML private ChoiceBox clientes;

    @Required(campo = "dataRetirada")
    @FXML private DatePicker dataRetir;

    @Required(campo = "qtdeDelicadas")
    @FXML private TextField txtDelicadas;

    @Required(campo = "qtdeNormais")
    @FXML private TextField txtNorm;

    @Required(campo = "qtdePesadas")
    @FXML private TextField txtPesa;

    @FXML private RadioButton btnLava;

    private ServicoVO servico = new ServicoVO();

    public void salvar(){
        servico.setStatus(1);
        servico.setQtdePesadas(Integer.valueOf(txtPesa.getText().trim()));
        servico.setQtdeNormais(Integer.valueOf(txtNorm.getText().trim()));
        servico.setQtdeDelicadas(Integer.valueOf(txtDelicadas.getText().trim()));
        servico.setDataEntrega(LocalDate.now());



    }

    @Override
    protected void onModalInit() {



    }


    public void cancelar(){closeModal();}
}
