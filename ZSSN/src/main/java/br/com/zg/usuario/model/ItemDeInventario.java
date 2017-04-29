package br.com.zg.usuario.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.zg.base.model.Entidade;
import br.com.zg.usuario.model.type.TipoDeBem;

@Entity
public class ItemDeInventario extends Entidade {
	

	private static final long serialVersionUID = 6186459575489401250L;

	@Enumerated(EnumType.STRING)
	@NotNull(message="{tipoDoItemObg}")
	private TipoDeBem tipo;
	
	@NotEmpty(message="{descricaoObg}")
	private String descricao;
	
	@Min(value=1, message="{quantidadeItemInventarioObg}")
	private Double quantidade;
	
	public TipoDeBem getTipo() {
		return tipo;
	}
	public void setTipo(TipoDeBem tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(getId())
				.append(tipo)
				.append(descricao)
				.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDeInventario other = (ItemDeInventario) obj;
		
		return new EqualsBuilder()
				.append(getId(), other.getId())
				.append(tipo, other.getTipo())
				.append(descricao, other.getDescricao())
				.isEquals();
	}
	
	
	

}
