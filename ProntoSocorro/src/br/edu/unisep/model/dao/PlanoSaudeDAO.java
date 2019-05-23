package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.PlanoSaudeVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoSaudeDAO {

    public List<PlanoSaudeVO> listar(){

        List<PlanoSaudeVO> plano = new ArrayList<>();

        try{

            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("SELECT id_plano, ds_plano ")
                    .append("FROM public.plano_saude;").toString();

            var ps = con.prepareStatement(sql);
            var rs = ps.executeQuery();

            while (rs.next()){

                var p = new PlanoSaudeVO();

                p.setId(rs.getInt("id_plano"));
                p.setPlano(rs.getString("ds_plano"));

                plano.add(p);


            }

            rs.close();
            ps.close();
            con.close();


        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return plano;
    }


}
