package br.edu.unisep.modules.DAO;


import br.edu.unisep.modules.hibernate.HibernateSessionFactory;

import java.util.List;

public class GenericDAO<T> {


    public void salvar(T objeto) {

        var session = HibernateSessionFactory.getSession();

        var trans = session.beginTransaction();

        try {

            session.save(objeto);
            trans.commit();

        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }

        session.close();


    }

    public void alterar(T objeto) {

        var session = HibernateSessionFactory.getSession();

        var trans = session.beginTransaction();

        try {

            session.update(objeto);
            trans.commit();

        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }

        session.close();


    }

    public void excuir(T objeto) {

        var session = HibernateSessionFactory.getSession();

        var trans = session.beginTransaction();

        try {

            session.delete(objeto);
            trans.commit();

        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }

        session.close();


    }

    public List<T> listar(Class<T> classe) {
        var session = HibernateSessionFactory.getSession();
        var q = session.createQuery("from " + classe.getName(), classe);
        var resultado = q.list();

        return resultado;
    }

    public T consultar(Class<T> classe, Object id){
        var session = HibernateSessionFactory.getSession();
        var q = session.createQuery("from" + classe.getName() + "where id = :ID", classe);
        q.setParameter("ID", id);

        var resultado = q.uniqueResult();

        session.close();

        return resultado;

    }

}



