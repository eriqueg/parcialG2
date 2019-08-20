package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.CursoVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void salvar(CursoVO curso) {
        try{
            var con = Conexao.obterConexao();

            var sql = new StringBuilder("INSERT INTO public.curso( " +
                    "ds_curso, tp_curso, vl_duracao) " +
                    "VALUES (?, ?, ?);");

             var ps = con.prepareStatement(sql.toString());


             ps.setString(1, curso.getNome());
             ps.setInt(2, curso.getTipo());
             ps.setInt(3, curso.getDuracao());

             ps.execute();
             ps.close();
             con.close();



        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        }


    public List<CursoVO> listar() {

        var cursos = new ArrayList<CursoVO>();

        try{

            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("SELECT id_curso, ds_curso, tp_curso, vl_duracao ")
                    .append(" FROM public.curso;").toString();


            var ps = con.prepareStatement(sql);
            var rs = ps.executeQuery();

            while (rs.next()){

                var c = new CursoVO();
                c.setId(rs.getInt("id_curso"));
                c.setDuracao(rs.getInt("vl_duracao"));
                c.setNome(rs.getString("ds_curso"));
                c.setTipo(rs.getInt("tp_curso"));

                cursos.add(c);

            }

            rs.close();
            ps.close();
            con.close();




        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return cursos;
    }


}
