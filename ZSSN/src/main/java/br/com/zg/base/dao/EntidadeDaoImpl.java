package br.com.zg.base.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.context.annotation.RequestScope;

import br.com.generic.dao.GenericDAOImpl;
import br.com.zg.base.model.Entidade;

public class EntidadeDaoImpl<T extends Entidade> extends GenericDAOImpl<T> implements EntidadeDao<T> {

	@Override
    @PersistenceContext
    @RequestScope
    public void setEntityManager(EntityManager manager) {
    	super.setEntityManager(manager);
    }
}
