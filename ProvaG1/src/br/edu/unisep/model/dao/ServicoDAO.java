package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.ClienteVO;
import br.edu.unisep.model.vo.ServicoVO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    public List<ServicoVO> listar(){

        var servicos = new ArrayList<ServicoVO>();


        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuilder(new StringBuilder()
                    .append("SELECT id_servico, ")
                    .append("c.id_cliente, ")
                    .append("c.ds_cliente, ")
                    .append("qt_delicadas, ")
                    .append("qt_normais, ")
                    .append("qt_pesadas, ")
                    .append("dt_entrega, ")
                    .append("dt_retirada, ")
                    .append("tp_status, ")
                    .append("tp_servico, ")
                    .append("vl_total ")
                    .append("FROM public.servico as s ")
                    .append("inner join cliente c ")
                    .append("on(c.id_cliente  = s.id_cliente)"));

            var ps = con.prepareStatement(sql.toString());

            var rs = ps.executeQuery();

            while(rs.next()){

                var s = new ServicoVO();

                s.setId(rs.getInt("id_servico"));
                var dtEntrada = rs.getDate("dt_entrega");
                s.setDataEntrega(dtEntrada.toLocalDate());
                var dtSaida = rs.getDate("dt_retirada");
                s.setDataRetirada(dtSaida.toLocalDate());
                s.setQtdeDelicadas(rs.getInt("qt_delicadas"));
                s.setQtdeNormais(rs.getInt("qt_normais"));
                s.setQtdePesadas(rs.getInt("qt_pesadas"));

                var c = new ClienteVO();

                c.setId(rs.getInt("c.id_cliente"));
                c.setNome(rs.getString("c.ds_cliente"));

                s.setCliente(c);

                servicos.add(s);



            }

            rs.close();
            ps.close();
            con.close();


        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return servicos;
    }

    public void salvar(ServicoVO servico){

        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuilder(new StringBuilder()
                    .append("INSERT INTO public.servico( ")
                    .append("id_cliente, ")
                    .append("qt_delicadas, ")
                    .append("qt_normais, ")
                    .append("qt_pesadas, ")
                    .append("dt_entrega, ")
                    .append("dt_retirada, ")
                    .append("tp_status, ")
                    .append("tp_servico, ")
                    .append("vl_total) "
                    ).append("VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);"));

            var ps = con.prepareStatement(sql.toString());

            ps.setInt(1,servico.getCliente().getId());
            ps.setInt(2,servico.getQtdeDelicadas());
            ps.setInt(3,servico.getQtdeNormais());
            ps.setInt(4,servico.getQtdePesadas());
            ps.setDate(5, Date.valueOf(servico.getDataEntrega()));
            ps.setDate(6,Date.valueOf(servico.getDataRetirada()));
            ps.setInt(7,servico.getStatus());
            ps.setInt(8,servico.getTipo());
            ps.setDouble(9,servico.getTotal());

            ps.execute();

            ps.close();
            con.close();


        }catch (ClassNotFoundException | SQLException e ){
            e.printStackTrace();
        }


    }




}
