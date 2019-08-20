package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.ProfessorVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public void salvar(ProfessorVO professor) {

        try{
            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("INSERT INTO public.professor( ")
                    .append(" ds_professor, ds_email) ")
                    .append("VALUES (?, ?, ?);").toString();


            var ps = con.prepareStatement(sql.toString());
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());


            ps.execute();
            ps.close();
            con.close();



        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }


    }

    public List<ProfessorVO> listar() {
        var professor = new ArrayList<ProfessorVO>();

        try{
            var con = Conexao.obterConexao();
            var sql = new StringBuilder().append("SELECT id_professor, ds_professor, ds_email ")
                    .append("FROM public.professor; ").toString();

            var ps = con.prepareStatement(sql);
            var rs = ps.executeQuery();

            while (rs.next()){
                var p = new ProfessorVO();
                p.setId(rs.getInt("id_professor"));
                p.setEmail(rs.getString("ds_email"));
                p.setNome(rs.getString("ds_professor"));


            professor.add(p);

            }

            rs.close();
            ps.close();
            con.close();



        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<ProfessorVO> listar(CursoVO curso) {

        var professores = new ArrayList<ProfessorVO>();

        try{
            var con = Conexao.obterConexao();
            var sql = new StringBuilder().append("SELECT p.id_professor, ds_professor, ds_email ")
                    .append("FROM professor as p ")
                    .append("inner join disciplina d ")
                    .append("on(d.id_professor = p.id_professor) ")
                    .append("inner join curso c ")
                    .append("on(c.id_curso = d.id_curso) ")
                    .append("where c.id_curso = ?").toString();

            var ps = con.prepareStatement(sql);
            ps.setInt(1, curso.getId());
            var rs = ps.executeQuery();

            while (rs.next()){
                var p = new ProfessorVO();

                p.setNome(rs.getString("ds_professor"));
                p.setEmail(rs.getString("ds_email"));

                professores.add(p);


            }

            ps.execute();
            con.close();

        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return professores;
    }

}
