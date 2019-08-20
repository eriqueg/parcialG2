package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.AutorVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public List<AutorVO> listar() {

        List<AutorVO> autores = new ArrayList<>();

        try {

            var con = Conexao.obterConexao();

            var ps = con.prepareStatement(
                    "select * from autor order by ds_autor");

            var rs = ps.executeQuery();

            while (rs.next()) {
                var a = new AutorVO();
                a.setId(rs.getInt("id_autor"));
                a.setNome(rs.getString("ds_autor"));

                autores.add(a);
            }

            rs.close();
            ps.close();
            con.close();

        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }

}