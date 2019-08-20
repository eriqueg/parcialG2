package br.edu.unisep.model.dao;

import br.edu.unisep.model.vo.AutorVO;
import br.edu.unisep.model.vo.LivroVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public List<LivroVO> listar() {

        var livros = new ArrayList<LivroVO>();

        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuffer();
            sql.append("select l.id_livro, ");
            sql.append("l.ds_titulo, ");
            sql.append("l.ds_sinopse, ");
            sql.append("l.ds_editora, ");
            sql.append("l.nr_paginas, ");
            sql.append("l.tp_status, ");
            sql.append("a.id_autor, ");
            sql.append("a.ds_autor ");
            sql.append("from livro l ");
            sql.append("inner join autor a ");
            sql.append("on l.id_autor = a.id_autor");

            var ps = con.prepareStatement(sql.toString());

            var rs = ps.executeQuery();

            while (rs.next()){
                var l = new LivroVO();

                l.setId(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("ds_titulo"));
                l.setSinopse(rs.getString("ds_sinopse"));
                l.setEditora(rs.getString("ds_editora"));
                l.setPaginas(rs.getInt("nr_paginas"));
                l.setStatus(rs.getInt("tp_status"));

                var a = new AutorVO();
                a.setId(rs.getInt("id_autor"));
                a.setNome(rs.getString("ds_autor"));

                l.setAutor(a);


                livros.add(l);

            }
            rs.close();
            ps.close();
            con.close();


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }


    public void salvar(LivroVO livro){

        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuilder().
                    append("INSERT INTO public.livro(").
                    append("ds_titulo, ds_editora, nr_paginas, tp_status, ds_sinopse, id_autor) ").
                    append("VALUES (?, ?, ?, ?, ?, ?)").toString();

            var ps = con.prepareStatement(sql);

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getEditora());
            ps.setInt(3, livro.getPaginas());
            ps.setInt(4, livro.getStatus());
            ps.setString(5, livro.getSinopse());
            ps.setInt(6, livro.getAutor().getId());

            ps.execute();

            ps.close();
            con.close();


        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    public void alterar(LivroVO livro){
        try {

            var con = Conexao.obterConexao();

            var sql = new StringBuilder().append("UPDATE public.livro SET  ds_titulo=?, ")
                    .append("ds_editora=?, ")
                    .append("nr_paginas=?, ")
                    .append("tp_status=?, ")
                    .append("ds_sinopse=?, ")
                    .append("id_autor=? ")
                    .append("WHERE id_livro=?").toString();

            var ps = con.prepareStatement(sql);

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getEditora());
            ps.setInt(3, livro.getPaginas());
            ps.setInt(4, livro.getStatus());
            ps.setString(5, livro.getSinopse());
            ps.setInt(6, livro.getAutor().getId());
            ps.setInt(7, livro.getId());

            ps.execute();

            ps.close();
            con.close();


        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

}
