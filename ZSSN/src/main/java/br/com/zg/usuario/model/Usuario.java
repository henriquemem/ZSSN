package br.com.zg.usuario.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.zg.base.exception.BaseException;
import br.com.zg.base.model.Entidade;
import br.com.zg.usuario.model.type.Localizacao;
import br.com.zg.usuario.model.type.Sexo;
import br.com.zg.util.Menssagens;

@Entity
public class Usuario extends Entidade {

	private static final long serialVersionUID = 4980616411136831383L;

	@NotEmpty(message="{nomeObg}")
	private String nome;
	
	private int idade;
	
	@NotNull(message="{sexoObg}")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@NotNull
	@Embedded
	private Localizacao ultimaLocalizacao = new Localizacao();
	
	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
    @JoinColumn(name="usuario_id")
	private Set<ItemDeInventario> itens = new HashSet<>();
	
	public void verificarEstadoDoUsuario(){
		if(!isAtivo()){
			throw new BaseException(Menssagens.getMenssagem("erro.usuarioInativo", getNome()));
		}
	}
	
	public void addItem(ItemDeInventario item){
		itens.add(item);
	}
	
	public void removerItem(ItemDeInventario item){
		itens.remove(item);
	}
	
	public void addOuSomarItem(ItemDeInventario item){
		if(!somarQuantidadeItem(item)){
			addItem(item);
		}
		
	}
	
	private boolean somarQuantidadeItem(ItemDeInventario item){
		for (ItemDeInventario itemDeInventario : itens) {
			if(item.getId() == itemDeInventario.getId()){
				itemDeInventario.setQuantidade(item.getQuantidade() + itemDeInventario.getQuantidade());
				return true;
			}
		}
		return false;
	}
	
	public void addOuSomarItens(Set<ItemDeInventario> itens){
		for (ItemDeInventario item : itens) {
			addOuSomarItem(item);
		}
	}
	
	public void abaterItens(Set<ItemDeInventario> itens){
		for (ItemDeInventario item : itens) {
			abaterItem(item);
		}
	}
	
	public void abaterItem(ItemDeInventario item){
		for (ItemDeInventario itemDeInventario : itens) {
			if(item.getId() == itemDeInventario.getId()){
				itemDeInventario.setQuantidade(item.getQuantidade() - itemDeInventario.getQuantidade());
				if (itemDeInventario.getQuantidade() == 0) {
					removerItem(itemDeInventario);
				}
			}
		}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Localizacao getUltimaLocalizacao() {
		return ultimaLocalizacao;
	}

	public void setUltimaLocalizacao(Localizacao ultimaLocalizacao) {
		this.ultimaLocalizacao = ultimaLocalizacao;
	}

	public Set<ItemDeInventario> getItens() {
		return itens;
	}

	public void setItens(Set<ItemDeInventario> itens) {
		this.itens = itens;
	}
	
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(getId())
				.append(nome)
				.append(sexo)
				.append(idade)
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
		Usuario other = (Usuario) obj;
		
		return new EqualsBuilder()
				.append(getId(), other.getId())
				.append(nome, other.getNome())
				.append(sexo, other.getSexo())
				.append(idade, other.getIdade())
				.isEquals();
	}
	
	
}
