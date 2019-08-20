package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.ClienteVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<ClienteVO> listar(){

        var clientes = new ArrayList<ClienteVO>();

        try{

            var con = Conexao.obterConexao();

            var sql = new StringBuilder(new StringBuilder()
                    .append("SELECT id_cliente, ds_cliente, ds_email, ds_telefone "
            ).append("FROM public.cliente;")).toString();

            var ps = con.prepareStatement(sql);

            var rs = ps.executeQuery();
            while (rs.next()){

                var c =  new ClienteVO();

                c.setId(rs.getInt("id_cliente"));
                c.setNome(rs.getString("ds_cliente"));
                c.setEmail(rs.getString("ds_email"));
                c.setTelefone(rs.getString("ds_telefone"));



                clientes.add(c);


            }

            rs.close();
            ps.close();
            con.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return clientes;
    }


    public void salvar(ClienteVO cliente){

        try{

            var con = Conexao.obterConexao();

            var sql = new StringBuilder(new StringBuilder().append("INSERT INTO public.cliente( ")
                    .append("ds_cliente, ds_email, ds_telefone) ")
                    .append("VALUES (?, ?, ?);")).toString();

            var ps = con.prepareStatement(sql);



            ps.setString(1,cliente.getNome());
            ps.setString(2,cliente.getEmail());
            ps.setString(3,cliente.getTelefone());

            ps.execute();


            ps.close();
            con.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

}
