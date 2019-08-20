package br.edu.unisep.modules.DAO;

import br.edu.unisep.modules.VO.OcorrenciaVO;
import br.edu.unisep.modules.hibernate.HibernateSessionFactory;

import java.util.List;

public class OcorrenciaDAO {

    public void salvar(OcorrenciaVO ocorrencia){

        var session = HibernateSessionFactory.getSession();

        var trans = session.beginTransaction();

        try{

            session.save(ocorrencia);
            trans.commit();

        }catch (Exception e){
            e.printStackTrace();
            trans.rollback();
        }

        session.close();


    }

    public List<OcorrenciaVO> listar(){
        var session = HibernateSessionFactory.getSession();
        var q = session.createQuery("from OcorrenciaVO", OcorrenciaVO.class);
        var ocorrencia = q.list();

        return ocorrencia;
    }

}
