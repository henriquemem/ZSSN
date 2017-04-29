package br.com.zg.base.dao;

import br.com.zg.base.model.Entidade;

import br.com.generic.dao.GenericDAO;

public interface EntidadeDao<T extends Entidade> extends GenericDAO<T>{

}
