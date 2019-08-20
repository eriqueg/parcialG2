package br.edu.unisep;

import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.dao.ProfessorDAO;
import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.ProfessorVO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class ProfessoresController extends AppController {

    @FXML private TableView<ProfessorVO> tabProf;

    @FXColumn(property = "nome", percentWidth = 40)
    @FXML private TableColumn<ProfessorVO, String> colProf;

    @FXColumn(property = "email", percentWidth = 40)
    @FXML private TableColumn<ProfessorVO, String> colEmail;


    @FXML private ChoiceBox<CursoVO> cbmCurso;


    private ProfessorVO professor;
    private CursoVO curso;

    public void listarCursos(){
        var dao = new CursoDAO();

        var list = dao.listar();

        cbmCurso.setItems(FXCollections.observableList(list));
    }

    public void listarProfessores(){

        var dao = new ProfessorDAO();

        //curso = cbmCurso;



        var list = dao.listar();




    }

    public void novo(){
        openModal("novoProfessor.fxml", this::listarProfessores);
    }



    @Override
    protected void onInit() {

        listarCursos();



    }
}
