package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.AtendimentoVO;
import br.edu.unisep.model.vo.EspecialidadeVO;
import br.edu.unisep.model.vo.PlanoSaudeVO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AtendimentoDAO {

    public List<AtendimentoVO> listar(){

        List<AtendimentoVO> atendimento = new ArrayList<>();

        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("select ")
                    .append("a.id_atendimento, ")
                    .append("a.ds_paciente, ")
                    .append("p.id_plano, ")
                    .append("p.ds_plano, ")
                    .append("e.id_especialidade, ")
                    .append("e.ds_especialidade, ")
                    .append("a.ds_sintomas, ")
                    .append("a.tp_status, ")
                    .append("a.dt_nascimento ")
                    .append("from atendimento as a ")
                    .append("inner join plano_saude p ")
                    .append("on(p.id_plano = a.id_plano) ")
                    .append("inner join especialidade e ")
                    .append("on(e.id_especialidade = a.id_especialidade) ");


            var ps = con.prepareStatement(sql.toString());

            var rs = ps.executeQuery();

            while (rs.next()){
                var a = new AtendimentoVO();

                a.setId(rs.getInt("id_atendimento"));
                a.setPaciente(rs.getString("ds_paciente"));
                var dt = rs.getDate("dt_nascimento");
                a.setNascimento(dt.toLocalDate());
                a.setSintomas(rs.getString("ds_sintomas"));
                a.setStatus(rs.getInt("tp_status"));



                var e = new EspecialidadeVO();
                e.setId(rs.getInt("id_especialidade"));
                e.setEspecialidade(rs.getString("ds_especialidade"));

                var p = new PlanoSaudeVO();
                p.setId(rs.getInt("id_plano"));
                p.setPlano(rs.getString("ds_plano"));


                a.setEspecialidade(e);
                a.setPlanoSaude(p);

                atendimento.add(a);


            }

            rs.close();
            ps.close();
            con.close();

            return atendimento;

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return atendimento;

    }

    public void alterar(AtendimentoVO atendimento){

        try {
            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("UPDATE public.atendimento ")
                    .append("SET ")
                    .append("ds_paciente=?, ")
                    .append("id_plano=?, ")
                    .append("id_especialidade=?, ")
                    .append("dt_nascimento=?, ")
                    .append("ds_sintomas=?, ")
                    .append("WHERE id_atendimento = ?;").toString();

            var ps = con.prepareStatement(sql);

            ps.setString(1, atendimento.getPaciente());
            ps.setInt(2, atendimento.getPlanoSaude().getId());
            ps.setInt(3, atendimento.getEspecialidade().getId());
            ps.setDate(4, Date.valueOf(atendimento.getNascimento()));
            ps.setString(5, atendimento.getSintomas());
            ps.setInt(6, atendimento.getId());

            ps.execute();

            ps.close();
            con.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }




    }

    public void salvar(AtendimentoVO atendimento){
        try {
            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("INSERT INTO public.atendimento(\n")
                    .append("\tds_paciente, \n")
                    .append("\tid_plano, \n")
                    .append("\tid_especialidade, \n")
                    .append("\tdt_nascimento, \n")
                    .append("\tds_sintomas, \n")
                    .append("\tVALUES (?, ?, ?, ?, ?);").toString();

            var ps = con.prepareStatement(sql);

            ps.setString(1, atendimento.getPaciente());
            ps.setInt(2, atendimento.getPlanoSaude().getId());
            ps.setInt(3, atendimento.getEspecialidade().getId());
            ps.setDate(4, Date.valueOf(atendimento.getNascimento()));
            ps.setString(5, atendimento.getSintomas());



        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }


}
