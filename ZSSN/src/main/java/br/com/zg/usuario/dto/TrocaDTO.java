package br.com.zg.usuario.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.zg.usuario.model.ItemDeInventario;

public class TrocaDTO implements Serializable{
	
	private static final long serialVersionUID = 418096697487808759L;
	
	private Long usuario1Id;
	private Set<ItemDeInventario> itensUsuario1 = new HashSet<>();
	
	private Long usuario2Id;
	private Set<ItemDeInventario> itensUsuario2 = new HashSet<>();
	
	public Long getUsuario1Id() {
		return usuario1Id;
	}
	public void setUsuario1Id(Long usuario1Id) {
		this.usuario1Id = usuario1Id;
	}
	public Set<ItemDeInventario> getItensUsuario1() {
		return itensUsuario1;
	}
	public void setItensUsuario1(Set<ItemDeInventario> itensUsuario1) {
		this.itensUsuario1 = itensUsuario1;
	}
	public Long getUsuario2Id() {
		return usuario2Id;
	}
	public void setUsuario2Id(Long usuario2Id) {
		this.usuario2Id = usuario2Id;
	}
	public Set<ItemDeInventario> getItensUsuario2() {
		return itensUsuario2;
	}
	public void setItensUsuario2(Set<ItemDeInventario> itensUsuario2) {
		this.itensUsuario2 = itensUsuario2;
	}
}
