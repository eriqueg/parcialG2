package br.edu.unisep;

import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.AtendimentoDAO;
import br.edu.unisep.model.dao.EspecialidadeDAO;
import br.edu.unisep.model.dao.PlanoSaudeDAO;
import br.edu.unisep.model.vo.AtendimentoVO;
import br.edu.unisep.model.vo.EspecialidadeVO;
import br.edu.unisep.model.vo.PlanoSaudeVO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller2 extends ModalController {

    @Required(campo = "paciente")
    @FXML private TextField txtPaciente;

    @Required(campo = "planoSaude")
    @FXML private ChoiceBox<PlanoSaudeVO> planoSaude;

    @Required(campo = "especialidade")
    @FXML private ChoiceBox<EspecialidadeVO> especialidade;

    @Required(campo = "sintomas")
    @FXML private TextArea txtSintomas;

    @Required(campo = "nascimento")
    @FXML private DatePicker nascimento;

    private AtendimentoVO atendimento = new AtendimentoVO();

    @Override
    protected void onModalInit() {
        listarPlano();
        listarEspecialidade();

    }

    public void listarPlano(){
        var dao = new PlanoSaudeDAO();

        var lista = dao.listar();

        planoSaude.setItems(FXCollections.observableList(lista));

    }

    public void listarEspecialidade(){
        var dao =  new EspecialidadeDAO();

        var lista = dao.listar();

        especialidade.setItems(FXCollections.observableList(lista));

    }

    public void salvar(){

        atendimento.setPaciente(txtPaciente.getText().trim());
        atendimento.setSintomas(txtSintomas.getText().trim());
        atendimento.setNascimento(nascimento.getValue());

        atendimento.setPlanoSaude(planoSaude.getValue());
        atendimento.setEspecialidade(especialidade.getValue());
        atendimento.setStatus(1);

        var dao = new AtendimentoDAO();

        if(atendimento.getId() != null){
            dao.alterar(atendimento);
        }else{
            dao.salvar(atendimento);
        }

        closeModal();






    }

    public void cancelar(){
        closeModal();
    }

}
