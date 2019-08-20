package br.edu.unisep.produtoDAO;

import br.edu.unisep.hibernate.HibernateSessionFactory;
import br.edu.unisep.produtoVO.ProdutoVO;

import java.util.List;

public class produtoDAO {

    public void salvar(ProdutoVO prod){

        var session = HibernateSessionFactory.getSession();

        var trans = session.beginTransaction();

        try{

            session.save(prod);
            trans.commit();

        }catch (Exception e){
            e.printStackTrace();

            trans.rollback();

        }

        session.close();


    }

    public List<ProdutoVO> listar(){
      var session = HibernateSessionFactory.getSession();
      var q = session.createQuery("from ProdutoVO", ProdutoVO.class);
      var produtos = q.list();
      session.close();
      return produtos;

    }

    public void excluir(ProdutoVO prod){
        var session = HibernateSessionFactory.getSession();

        var trans = session.beginTransaction();

        try{

            session.delete(prod);
            trans.commit();

        }catch (Exception e){
            e.printStackTrace();

            trans.rollback();

        }

        session.close();
    }
    public void alterar(ProdutoVO prod){
        var session = HibernateSessionFactory.getSession();

        var trans = session.beginTransaction();

        try{

            session.update(prod);
            trans.commit();

        }catch (Exception e){
            e.printStackTrace();

            trans.rollback();

        }

        session.close();
    }

}
