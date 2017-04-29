package br.com.zg.base.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import br.com.zg.base.model.Entidade;

import br.com.generic.dao.GenericDAO;

public abstract class BaseServiceImpl<D extends GenericDAO<E>, E extends Entidade> implements BaseService<E> {

	@Inject
	private D dao;

	@Override
	@Transactional
	public E salvar(E entidade) {
		if(entidade.getId() == 0){
			return dao.insert(entidade);
		}
		return dao.update(entidade);
	}

	@Override
	@Transactional(readOnly=true)
	public List<E> listarTodos() {
		return dao.list(0, 0, null);
	}

	@Override
	@Transactional(readOnly=true)
	public E buscarPorId(long id) {
		return dao.findEntityById(id);
	}

	protected D getDao() {
		return dao;
	}
	
	

}
