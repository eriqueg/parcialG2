package br.edu.unisep;

import br.edu.unisep.fx.annotation.ColValueMap;
import br.edu.unisep.fx.annotation.FXColumn;
import br.edu.unisep.fx.annotation.IntToString;
import br.edu.unisep.fx.controller.AppController;
import br.edu.unisep.model.dao.CursoDAO;
import br.edu.unisep.model.vo.CursoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class CursosController extends AppController {

    @FXML private TableView<CursoVO> tabCursos;

    @FXColumn(property = "nome", percentWidth = 35)
    @FXML private TableColumn<CursoVO, String> colCurso;

    @FXColumn(property = "duracao", percentWidth = 35)
    @FXML private TableColumn<CursoVO, String>colDuracao;

    @FXColumn(property = "tipo", percentWidth = 35)
    @ColValueMap({
            @IntToString(from = 1, to="Bacharelado"),
            @IntToString(from = 2, to="Licenciatura"),
            @IntToString(from = 3, to="Tecnologia")
    })
    @FXML private TableColumn<CursoVO, Integer> colTipo;



    private ObservableList<CursoVO> cursos;


    public void listar(){
        var dao = new CursoDAO();
        var list = dao.listar();

        cursos.setAll(list);



    }

    @Override
    protected void onInit() {

        cursos = FXCollections.observableArrayList();

        tabCursos.setItems(cursos);

        var dao = new CursoDAO();

        listar();

    }

    public void novoCurso(ActionEvent event) throws IOException {
        openModal("novoCurso.fxml", this::listar);
    }

}
