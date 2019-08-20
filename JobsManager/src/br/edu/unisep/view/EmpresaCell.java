package br.edu.unisep.view;

import br.edu.unisep.model.vo.EmpresaVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EmpresaCell extends ListCell<EmpresaVO> {

    private AnchorPane cell;

    private Label lblEmpresa;
    private Label lblRamo;

    public EmpresaCell() {
        try {
            cell = FXMLLoader.load(getClass().getResource("item_empresa.fxml"));

            lblEmpresa = (Label) cell.lookup("#lblEmpresa");
            lblRamo = (Label) cell.lookup("#lblRamo");

            cell.setPrefWidth(0d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(EmpresaVO empresa, boolean vazio) {
        super.updateItem(empresa, vazio);

        if(!vazio){
            lblEmpresa.setText(empresa.getNome());
            lblRamo.setText(empresa.getRamo().getNome());

            setGraphic(cell);
        }else{
            setGraphic(null);
        }

    }
}
