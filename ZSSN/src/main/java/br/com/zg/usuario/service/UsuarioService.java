package br.com.zg.usuario.service;

import br.com.zg.base.service.BaseService;
import br.com.zg.usuario.dto.TrocaDTO;
import br.com.zg.usuario.model.ItemDeInventario;
import br.com.zg.usuario.model.Usuario;
import br.com.zg.usuario.model.type.Localizacao;

public interface UsuarioService extends BaseService<Usuario> {
	
	Usuario adicionarItem(Long id, ItemDeInventario item);
	
	void updateItem(Long id, ItemDeInventario item);
	
	void updateLocalizacao(Long id, Localizacao localizacao);
	
	void reportarUsuario(Long usuarioReportadorId, Long usuarioReportadoId);
	
	
	void trocar(TrocaDTO dto);

}
