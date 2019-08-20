package br.edu.unisep;

import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.vo.CursoVO;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class NovoCursoController extends ModalController {

    @Required(campo = "nome")
    @FXML private TextField txtNome;

    @Required(campo = "duracao")
    @FXML private TextField txtDuracao;

    @FXML private RadioButton bacharelado;
    @FXML private RadioButton licenciatura;
    @FXML private RadioButton tecnologia;

    private CursoVO cursos = new CursoVO();


    public void onModalInit(){



    }

    public void salvar(){

        if (bacharelado.isSelected()){
            cursos.setTipo(1);
        } else if(licenciatura.isSelected()){
            cursos.setTipo(2);
        } else if (tecnologia.isSelected()){
            cursos.setTipo(3);
        }

        cursos.setNome(txtNome.getText().trim());
        cursos.setDuracao(Integer.valueOf(txtDuracao.getText()));

        var dao = new CursoDAO();

        dao.salvar(cursos);

        closeModal();

    }

    public void cancelar(){
        closeModal();
    }


}
