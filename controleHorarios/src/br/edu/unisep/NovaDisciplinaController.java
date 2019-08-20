package br.edu.unisep;

import br.edu.unisep.fx.annotation.Required;
import br.edu.unisep.fx.controller.ModalController;
import br.edu.unisep.model.dao.DisciplinaDAO;
import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.DisciplinaVO;
import br.edu.unisep.model.vo.ProfessorVO;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class NovaDisciplinaController extends ModalController {

    @Required(campo = "nome")
    @FXML private TextField txtNome;

    @Required(campo = "curso")
    @FXML private ChoiceBox<CursoVO> cbmCurso;

    @Required(campo = "professor")
    @FXML private ChoiceBox<ProfessorVO> cbmProfessor;

    @Required(campo = "cargaHoraria")
    @FXML private TextField txtCarga;

    @Required(campo ="periodo")
    @FXML private TextField txtPeriodo;


    private DisciplinaVO disciplina = new DisciplinaVO();


    public void salvar(){
        disciplina.setCurso(cbmCurso.getValue());
        disciplina.setProfessor(cbmProfessor.getValue());
        disciplina.setPeriodo(Integer.valueOf(txtPeriodo.getText().trim()));
        disciplina.setNome(txtNome.getText().trim());
        disciplina.setCargaHoraria(Integer.valueOf(txtCarga.getText().trim()));

        var dao = new DisciplinaDAO();

        dao.salvar(disciplina);

        closeModal();
    }

    public void cancelar(){closeModal();}


    @Override
    protected void onModalInit() {

    }
}
