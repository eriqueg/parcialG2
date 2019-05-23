package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.EspecialidadeVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO {

    public List<EspecialidadeVO> listar(){

        List<EspecialidadeVO> especialidade = new ArrayList<>();

        try {

            var con = Conexao.obterConexao();
            var sql = new StringBuilder().append("SELECT id_especialidade, ds_especialidade ")
                    .append("FROM public.especialidade;").toString();

            var ps = con.prepareStatement(sql);
            var rs = ps.executeQuery();

            while (rs.next()){
                var e = new EspecialidadeVO();

                e.setId(rs.getInt("id_especialidade"));
                e.setEspecialidade(rs.getString("ds_especialidade"));

                especialidade.add(e);

            }

            rs.close();
            ps.close();
            con.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return especialidade;
    }

}
