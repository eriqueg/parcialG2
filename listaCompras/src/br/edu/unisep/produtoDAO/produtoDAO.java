package br.edu.unisep.produtoDAO;

import br.edu.unisep.produtoVO.ProdutoVO;
import org.postgresql.core.SqlCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class produtoDAO {

    public void salvar(ProdutoVO prod){
        try{
            Class.forName("org.postgresql.Driver");

            var con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/supermercado",
                    "postgres", "dbdv"
            );
            var ps = con.prepareStatement(
                    "insert into produtos(ds_produto, vl_mercadoa, vl_mercadob, vl_mercadoc)" +
                    "values(?, ?, ?, ?)");

            ps.setString(1, prod.getDescricao());
            ps.setDouble(2, prod.getMercadoA());
            ps.setDouble(3, prod.getMercadoB());
            ps.setDouble(4, prod.getMercadoC());

            ps.execute();
            ps.close();
            con.close();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public List<ProdutoVO> listar(){
        var retorno = new ArrayList<ProdutoVO>();
        try{
            Class.forName("org.postgresql.Driver");
            var con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/supermercado",
                    "postgres", "postgres"
            );
            var ps = con.prepareStatement("select * from produtos");
            var rs = ps.executeQuery();
            while (rs.next()){
                var m = new ProdutoVO();
                m.setId(rs.getInt("id_produto"));
                m.setDescricao(rs.getString("ds_produto"));
                m.setMercadoA(rs.getDouble("vl_mercadoa"));
                m.setMercadoB(rs.getDouble("vl_mercadob"));
                m.setMercadoC(rs.getDouble("vl_mercadoc"));

                retorno.add(m);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return retorno;
    }

    public void excluir(Integer id){

        try {
            var con = obterConexao();
            var ps = con.prepareStatement(
                    "delete from produtos where id_produto = ? "
            );

            ps.setInt(1,id);

            ps.execute();

            ps.close();
            con.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }



    }
    public void alterar(ProdutoVO p){
        try {
            var con = obterConexao();
            var ps = con.prepareStatement("update produtos " +
                    "set ds_produto = ?, " +
                    "vl_mercadoa = ?, " +
                    "vl_mercadob = ?, " +
                    "vl_mercadoc = ? " +
                    "where id_produto = ? ");
            ps.setString(1, p.getDescricao());
            ps.setDouble(2, p.getMercadoA());
            ps.setDouble(3, p.getMercadoB());
            ps.setDouble(4, p.getMercadoC());
            ps.setInt(5, p.getId());

            ps.execute();

            ps.close();
            con.close();

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public Connection obterConexao()throws ClassNotFoundException, SQLException{
        //Carrega na memória do Driver de conexão com o banco
        Class.forName("org.postgresql.Driver");
        //Conexão com o banco
        var con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/supermercado",
                "postgres", "postgres");

        return con;
    }


}
