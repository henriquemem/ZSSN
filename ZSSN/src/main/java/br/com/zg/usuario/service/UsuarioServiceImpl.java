package br.com.zg.usuario.service;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.com.zg.base.exception.BaseException;
import br.com.zg.base.service.BaseServiceImpl;
import br.com.zg.usuario.dao.ReportagemDao;
import br.com.zg.usuario.dao.UsuarioDao;
import br.com.zg.usuario.dto.TrocaDTO;
import br.com.zg.usuario.model.ItemDeInventario;
import br.com.zg.usuario.model.Reportagem;
import br.com.zg.usuario.model.Usuario;
import br.com.zg.usuario.model.type.Localizacao;
import br.com.zg.util.Menssagens;

@Named
public class UsuarioServiceImpl extends BaseServiceImpl<UsuarioDao, Usuario> implements UsuarioService{

	@Inject
	private ReportagemDao reportagemDao;
	
	@Override
	@Transactional
	public Usuario adicionarItem(Long id, ItemDeInventario item) {
		Usuario usuario = obterUsuario(id);
		usuario.addItem(item);
		return salvar(usuario);
	}

	@Override
	@Transactional
	public void updateItem(Long id, ItemDeInventario item) {
		Usuario usuario = obterUsuario(id);
		ItemDeInventario itemAntigo = obterItemDoUsuario(usuario, item);
		usuario.removerItem(itemAntigo);
		usuario.addItem(item);
		salvar(usuario);
	}
	
	@Override
	@Transactional
	public void updateLocalizacao(Long id, Localizacao localizacao) {
		Usuario usuario = obterUsuario(id);
		usuario.setUltimaLocalizacao(localizacao);
		salvar(usuario);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void reportarUsuario(Long usuarioReportadorId, Long usuarioReportadoId) {
		Usuario usuarioReportador = obterUsuario(usuarioReportadorId);
		Usuario usuarioReportado = obterUsuario(usuarioReportadoId);
		
		Reportagem reportagem = new Reportagem();
		reportagem.setUsuarioReportado(usuarioReportado);
		reportagem.setUsuarioReportador(usuarioReportador);
		reportagemDao.insert(reportagem);
		List<Long> ids = (List<Long>) reportagemDao.searchProperty("id")
				.eq("usuarioReportado", usuarioReportado)
				.eq("usuarioReportador", usuarioReportador)
				.search();
		if(ids.size() > 3){
			usuarioReportado.setAtivo(false);
			salvar(usuarioReportado);
		}
	}
	
	@Override
	public void trocar(TrocaDTO dto) {
		Usuario usuario1 = obterUsuario(dto.getUsuario1Id());
		Usuario usuario2 = obterUsuario(dto.getUsuario2Id());
		validarSaldoDaTroca(dto);
		verificarSaldoDoUsuario(usuario1, dto.getItensUsuario1());
		verificarSaldoDoUsuario(usuario2, dto.getItensUsuario2());
		
		usuario1.abaterItens(dto.getItensUsuario1());
		usuario2.addOuSomarItens(dto.getItensUsuario1());
		
		usuario2.abaterItens(dto.getItensUsuario2());
		usuario1.addOuSomarItens(dto.getItensUsuario2());
		
		salvar(usuario1);
		salvar(usuario2);
	}
	
	private void validarSaldoDaTroca(TrocaDTO dto){
		double saldoUsuario1 = 0;
		double saldoUsuario2 = 0;
		
		for (ItemDeInventario item : dto.getItensUsuario1()) {
			saldoUsuario1 += item.getQuantidade() * item.getTipo().getPontuacao();
		}
		
		for (ItemDeInventario item : dto.getItensUsuario2()) {
			saldoUsuario2 += item.getQuantidade() * item.getTipo().getPontuacao();
		}
		
		if(saldoUsuario1 - saldoUsuario2 != 0){
			throw new BaseException(Menssagens.getMenssagem("error.aTrocaNaoEJusta"));
		}
	}
	
	void verificarSaldoDoUsuario(Usuario usuario, Set<ItemDeInventario> itens){
		ItemDeInventario itemDoBanco = null;
		for (ItemDeInventario item : itens) {
			itemDoBanco = obterItemDoUsuario(usuario, item);
			if(itemDoBanco.getQuantidade() < item.getQuantidade()){
				throw new BaseException(
						Menssagens.getMenssagem("erro.saldoInsuficienteDoItemDoUsuario",
						usuario.getNome(), item.getDescricao()));
			}
		}
	}
	
	private Usuario obterUsuario(long id){
		Usuario usuario = getDao().findEntityById(id);
		if(usuario == null){
			throw new BaseException(Menssagens.getMenssagem("erro.usuarioNaoEncontrado", String.valueOf(id)));
		}
		usuario.verificarEstadoDoUsuario();
		return usuario;
	}
	
	private ItemDeInventario obterItemDoUsuario(Usuario usuario, ItemDeInventario itemDeInventario){
		for (ItemDeInventario item : usuario.getItens()) {
			if(itemDeInventario.getId() == item.getId()){
				return item;
			}
		}
		throw new BaseException(
				Menssagens.getMenssagem("error.itemNaoEncontrado", 
						itemDeInventario.getDescricao()));
	}

}
