package br.com.zg.base.service;

import java.util.List;

import br.com.zg.base.model.Entidade;

public interface BaseService<E extends Entidade>{
	
	public E salvar(E entidade);
	
	public List<E> listarTodos();
	
	public E buscarPorId(long id);
}
