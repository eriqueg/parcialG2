package br.edu.unisep.model.dao;

import br.edu.unisep.hibernate.GenericDAO;
import br.edu.unisep.hibernate.HibernateSessionFactory;
import br.edu.unisep.model.vo.MembroEquipeVO;
import br.edu.unisep.model.vo.ProjetoVO;

import java.util.List;

public class MembroEquipeDAO extends GenericDAO<MembroEquipeVO> {

    public List<MembroEquipeVO> listar(ProjetoVO projeto) {

        var session = HibernateSessionFactory.getSession();

        var q = session.createQuery("from MembroEquipeVO where projeto.id = :PROJETO",
                MembroEquipeVO.class);
        q.setParameter("PROJETO", projeto.getId());

        var lista = q.list();
        session.close();

        return lista;
    }

}
