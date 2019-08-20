package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.MedicaoVO;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MedicaoDAO {

    public void salvar(MedicaoVO med){

        try {
            //Carrega na memória do Driver de conexão com o banco
            Class.forName("org.postgresql.Driver");
            //Conexão com o banco
            var con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/contole_pressao",
                    "postgres", "123");

            var ps = con.prepareStatement(
                    "insert into registro_medicao (" + "data_medicao, sistolica, diastolica, resultado)" +
                    "values ('?, ?, ?, ?)");
            ps.setDate(1, med.getData());
        }catch (ClassNotFoundException | SQLException e) {
          e.printStackTrace();
        }

    }

}
