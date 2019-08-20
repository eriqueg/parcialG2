package br.edu.unisep.model.dao;

import br.edu.unisep.model.hibernate.HibernateSessionFactory;
import br.edu.unisep.model.vo.MedicaoVO;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicaoDAO {

    public void salvar(MedicaoVO med){

        var session = HibernateSessionFactory.getSession();
        var trans = session.beginTransaction();

        try {

            session.save(med);
            trans.commit();




        }catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        session.close();

    }

    public List<MedicaoVO> listar(){
        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery("from MedicaoVO", MedicaoVO.class);

        var medicao = q.list();

        session.close();
        return medicao;


    }
    public void excluir(MedicaoVO med){

        var session = HibernateSessionFactory.getSession();
        var trans = session.beginTransaction();

        try {

            session.delete(med);
            trans.commit();




        }catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        session.close();





    }

    public void alterar(MedicaoVO medicao){
        var session = HibernateSessionFactory.getSession();
        var trans = session.beginTransaction();

        try {

            session.update(medicao);
            trans.commit();




        }catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        session.close();
    }




}
