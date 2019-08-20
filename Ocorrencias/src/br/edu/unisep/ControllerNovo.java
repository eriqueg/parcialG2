package br.edu.unisep;

import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.modules.DAO.OcorrenciaDAO;
import br.edu.unisep.modules.VO.OcorrenciaVO;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;


public class ControllerNovo extends ModalController {

    @Required(campo = "ocorrencia")
    @FXML private TextField txtOcorrencia;

    @Required(campo = "detalhes")
    @FXML private TextArea txtDetalhes;

    @Required(campo = "email")
    @FXML private TextField txtEmail;

    @FXML private RadioButton chkBaixo;
    @FXML private RadioButton chkMedio;


    public void cancelar(){
        closeModal();
    }

    public void salvar(){
        var o = new OcorrenciaVO();


        o.setStatus(1);
        o.setDetalhes(txtDetalhes.getText().trim());
        o.setEmail(txtEmail.getText().trim());
        o.setOcorrencia(txtOcorrencia.getText().trim());
        o.setDataOcorrencia(LocalDate.now());

        if(chkBaixo.isSelected()){
            o.setPrioridade(1);
        }else if(chkMedio.isSelected()){
            o.setPrioridade(2);
        }else{
            o.setPrioridade(3);
        }

        var dao = new OcorrenciaDAO();
        dao.salvar(o);

        cancelar();

    }




    @Override
    protected void onModalInit() {
        chkBaixo.isSelected();

    }
}
