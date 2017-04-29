package br.com.zg.usuario.dto;

import java.io.Serializable;

public class ReportagemDTO implements Serializable{


	private static final long serialVersionUID = 3603634490261127008L;
	
	private Long usuarioReportadorId;
	private Long usuarioReportadoId;
	public Long getUsuarioReportadorId() {
		return usuarioReportadorId;
	}
	public void setUsuarioReportadorId(Long usuarioReportadorId) {
		this.usuarioReportadorId = usuarioReportadorId;
	}
	public Long getUsuarioReportadoId() {
		return usuarioReportadoId;
	}
	public void setUsuarioReportadoId(Long usuarioReportadoId) {
		this.usuarioReportadoId = usuarioReportadoId;
	}
}
