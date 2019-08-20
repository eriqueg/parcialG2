package br.edu.unisep.model.dao;

import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.hibernate.HibernateSessionFactory;
import br.edu.unisep.model.vo.EmpresaVO;
import br.edu.unisep.model.vo.VagaVO;

import java.util.List;

public class VagaDAO extends GenericDAO<VagaVO> {

    public List<VagaVO> listar(EmpresaVO empresa){
        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery("from VagaVO where empresa.id = :EMPRESA",
                VagaVO.class);
        q.setParameter("EMPRESA", empresa.getId());

        var lista = q.list();
        session.close();

        return lista;
    }


}
