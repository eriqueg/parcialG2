package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.CursoVO;
import br.edu.unisep.model.vo.DisciplinaVO;
import br.edu.unisep.model.vo.ProfessorVO;
import javafx.scene.control.ChoiceBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public void salvar(DisciplinaVO disciplina) {

        try{
            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("INSERT INTO public.disciplina(\n")
                    .append("ds_disciplina, nr_cargahoraria, nr_periodo)\n")
                    .append("\tVALUES (?, ?, ?);");

            var ps = con.prepareStatement(sql.toString());
            ps.setString(1, disciplina.getNome());
            ps.setInt(2, disciplina.getCargaHoraria());
            ps.setInt(3, disciplina.getPeriodo());

            ps.execute();
            ps.close();
            con.close();



        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    public List<DisciplinaVO> listar(ChoiceBox<CursoVO> cbmCurso) {


        var disciplina = new ArrayList<DisciplinaVO>();

        try{

            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("SELECT d.id_disciplina, ")
                    .append("d.ds_disciplina, ")
                    .append("p.id_professor, ")
                    .append("p.ds_professor, ")
                    .append("c.id_curso, ")
                    .append("ds_curso, ")
                    .append("nr_cargahoraria, ")
                    .append("nr_periodo ")
                    .append("FROM disciplina as d ")
                    .append("inner join public.professor p ")
                    .append("on(p.id_professor = d.id_professor) ")
                    .append("inner join public.curso c ")
                    .append("on(c.id_curso = d.id_curso) ").toString();

            var ps = con.prepareStatement(sql);

            var rs = ps.executeQuery();

            while (rs.next()){
                var d = new DisciplinaVO();
                d.setId(rs.getInt("id_disciplina"));
                d.setNome(rs.getString("ds_disciplina"));
                d.setCargaHoraria(rs.getInt("nr_cargahoraria"));
                d.setPeriodo(rs.getInt("nr_periodo"));

                var p = new ProfessorVO();

                p.setId(rs.getInt("id_professor"));
                p.setNome(rs.getString("ds_professor"));

                var c = new CursoVO();
                c.setId(rs.getInt("id_curso"));
                c.setNome(rs.getString("ds_curso"));

                d.setCurso(c);
                d.setProfessor(p);

                disciplina.add(d);

            }

            rs.close();
            ps.close();
            con.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return disciplina;
    }

    public List<DisciplinaVO> listar(CursoVO curso) {

        var disciplina = new ArrayList<DisciplinaVO>();

        try{

            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("SELECT d.id_disciplina,")
                    .append("d.ds_disciplina, ")
                    .append("p.id_professor, ")
                    .append("p.ds_professor, ")
                    .append("c.id_curso, ")
                    .append("ds_curso, ")
                    .append("nr_cargahoraria, ")
                    .append("nr_periodo ")
                    .append("FROM disciplina as d ")
                    .append("inner join public.professor p ")
                    .append("on(p.id_professor = d.id_professor) ")
                    .append("inner join public.curso c ")
                    .append("on(c.id_curso = d.id_curso) ")
                    .append("where d.id_curso = ? ").toString();

            var ps = con.prepareStatement(sql);
            ps.setInt(1, curso.getId());

            var rs = ps.executeQuery();

            while (rs.next()){
                var d = new DisciplinaVO();
                d.setId(rs.getInt("id_disciplina"));
                d.setNome(rs.getString("ds_disciplina"));
                d.setCargaHoraria(rs.getInt("nr_cargahoraria"));
                d.setPeriodo(rs.getInt("nr_periodo"));

                var p = new ProfessorVO();

                p.setId(rs.getInt("id_professor"));
                p.setNome(rs.getString("ds_professor"));

                var c = new CursoVO();
                c.setId(rs.getInt("id_curso"));
                c.setNome(rs.getString("ds_curso"));

                d.setCurso(c);
                d.setProfessor(p);

                disciplina.add(d);

            }

            rs.close();
            ps.close();
            con.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return disciplina;
    }
    }

