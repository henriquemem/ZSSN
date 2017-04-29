package br.com.zg.usuario.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.zg.base.model.Entidade;

@Entity
public class Reportagem extends Entidade{

	private static final long serialVersionUID = -7063004430736036469L;
	
	@NotNull(message="{usuarioReportadorObg}")
	private Usuario usuarioReportador;
	@NotNull(message="{usuarioReportadoObg}")
	private Usuario usuarioReportado;
	public Usuario getUsuarioReportador() {
		return usuarioReportador;
	}
	public void setUsuarioReportador(Usuario usuarioReportador) {
		this.usuarioReportador = usuarioReportador;
	}
	public Usuario getUsuarioReportado() {
		return usuarioReportado;
	}
	public void setUsuarioReportado(Usuario usuarioReportado) {
		this.usuarioReportado = usuarioReportado;
	}
	
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder()
				.append(usuarioReportado)
				.append(usuarioReportador)
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
		Reportagem other = (Reportagem) obj;
		
		return new EqualsBuilder()
				.append(usuarioReportado, other.getUsuarioReportado())
				.append(usuarioReportador, other.getUsuarioReportador())
				.isEquals();
	}
}
