package br.edu.unisep;

import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.dao.DisciplinaDAO;
import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.DisciplinaVO;
import br.edu.unisep.model.vo.ProfessorVO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class DisciplinasController extends AppController {

    @FXML private TableView<ProfessorVO> tabDisc;

    @FXColumn(property = "nome", percentWidth = 25)
    @FXML private TableColumn<DisciplinaVO, String> colDisc;

    @FXColumn(property = "professor", percentWidth = 20)
    @FXML private TableColumn<DisciplinaVO, String> colProf;

    @FXColumn(property = "curso", percentWidth = 20)
    @FXML private TableColumn<DisciplinaVO, String> colCurso;

    @FXColumn(property = "cargaHoraria", percentWidth = 20)
    @FXML private TableColumn<DisciplinaVO, String> colCarga;

    @FXML private ChoiceBox<CursoVO> cbmCurso;

    private DisciplinaVO disciplina = new DisciplinaVO();

    private CursoVO curso = new CursoVO();

    public void listarprofessores(){
        var dao = new CursoDAO();

        var lst = dao.listar();

        cbmCurso.setItems(FXCollections.observableList(lst));

    }

    public void listarDisciplinas(){

        var dao = new DisciplinaDAO();

        var list = dao.listar(cbmCurso);

        var





    }

    public void novaDisc(){
        openModal("novaDisciplina.fxml", this::listarDisciplinas);
    }



    @Override
    protected void onInit() {

        listarprofessores();

    }
}
