package br.edu.unisep.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unisep.ProdutoVO;

public class ProdutoDAO {

public void salvar(ProdutoVO prod){

    try{
        var con = obterConexao();
        Class.forName("org.postgresql.Driver");

        var ps = con.prepareStatement(
                "insert into produtos (" +
                        "ds_produto, vl_mercadoA, vl_mercadoB, vl_mercadoC) " +
                        "values (?, ?, ?, ?)");

        ps.setString(1,String.valueOf(prod.getDescricao()));
        ps.setDouble(2,Double.valueOf(prod.getMercadoA()));
        ps.setDouble(3,Double.valueOf(prod.getMercadoB()));
        ps.setDouble(4,Double.valueOf(prod.getMercadoC()));

        ps.execute();
        ps.close();
        con.close();

    } catch(ClassNotFoundException | SQLException e){
        e.printStackTrace();
    }
}

    public List<ProdutoVO> listar(){
            var retorno = new ArrayList<ProdutoVO>();

            try{
                var con = obterConexao();
                var ps = con.prepareStatement(
                        "select * from produtos");
                var rs = ps.executeQuery();

                while(rs.next()){
                    var p = new ProdutoVO();
                    p.setId(rs.getInt("id_produto"));
                    p.setDescricao(rs.getString("ds_produto"));
                    p.setMercadoA(rs.getDouble("vl_mercadoa"));
                    p.setMercadoB(rs.getDouble("vl_mercadob"));
                    p.setMercadoC(rs.getDouble("vl_mercadoc"));

                    retorno.add(p);
                }
                rs.close();
                ps.close();
                con.close();

            }catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return retorno;

    }

    public void excluir(Integer id){
        try {
            var con = obterConexao();

            var ps = con.prepareStatement(
                    "delete from produtos where id_produto = ?");

            ps.setInt(1, id);

            ps.execute();

            ps.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }





    private Connection obterConexao() throws ClassNotFoundException, SQLException {
        // Carrega na memória a classe do Driver de conexão com o banco
        Class.forName("org.postgresql.Driver");

        // Estabelece uma conexão com o banco de dados
        var con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/supermercado",
                "postgres", "postgres");

        return con;
    }




}
