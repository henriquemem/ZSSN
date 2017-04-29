package br.com.zg.usuario.dao;

import javax.inject.Named;

import br.com.zg.base.dao.EntidadeDaoImpl;
import br.com.zg.base.exception.BaseException;
import br.com.zg.usuario.model.Reportagem;
import br.com.zg.util.Menssagens;

@Named
public class ReportagemDaoImpl extends EntidadeDaoImpl<Reportagem> implements ReportagemDao {

	@Override
	protected Reportagem beforeInsert(Reportagem entity) {
		Reportagem reportagem = searchEntity()
				.eq("usuarioReportado", entity.getUsuarioReportado())
				.eq("usuarioReportador", entity.getUsuarioReportador())
				.search();
		
		if(reportagem != null){
			throw new BaseException(Menssagens.getMenssagem(
					"erro.usuarioJaReportado", 
					reportagem.getUsuarioReportador().getNome(), 
					reportagem.getUsuarioReportado().getNome()));
		}
		return super.beforeInsert(entity);
	}
}
