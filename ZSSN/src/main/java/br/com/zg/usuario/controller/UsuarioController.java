package br.com.zg.usuario.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.zg.usuario.dto.ReportagemDTO;
import br.com.zg.usuario.dto.TrocaDTO;
import br.com.zg.usuario.model.ItemDeInventario;
import br.com.zg.usuario.model.Usuario;
import br.com.zg.usuario.model.type.Localizacao;
import br.com.zg.usuario.service.UsuarioService;

@Named
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioController {
	
	@Inject
	private UsuarioService service;
	
	@GET
	@Path("/usuarios")
	@Produces({"application/json"})
	public List<Usuario> listAll(){
		return service.listarTodos();
	}
	
	/*@GET
	@Path("/usuarios/{id}/itens")
	@Produces({"application/json"})
	public List<ItemDeInventario> listarItens(@PathParam("id") Long id) {
		Usuario usuario = service.buscarPorId(id);
		
	}*/
	
	@GET
	@Path("/usuarios/{id}")
	@Produces({"application/json"})
	public Usuario buscar(@PathParam("id") Long id){ 
		return service.buscarPorId(id);
	}
	
	@POST
	@Path("/usuarios")
	@Consumes("application/json")
	@Produces({"application/json"})
	public Usuario inserir(Usuario usuario) {
		return service.salvar(usuario);
	}
	
	@PUT
	@Path("/usuarios")
	@Consumes("application/json")
	@Produces({"application/json"})
	public Usuario alterar(Usuario usuario) {
		return service.salvar(usuario);
	}
	
	@POST
	@Path("/usuarios/{id}/itens")
	@Consumes("application/json")
	@Produces({"application/json"})
	public Usuario addItem(@PathParam("id") Long id, ItemDeInventario itemDeInventario) {
		return service.adicionarItem(id, itemDeInventario);
	}
	
	@PUT
	@Path("/usuarios/{id}/itens")
	@Consumes("application/json")
	public void updateItem(@PathParam("id") Long id, ItemDeInventario itemDeInventario) {
		service.updateItem(id, itemDeInventario);
	}
	
	@PUT
	@Path("/usuarios/{id}/localizacao")
	@Consumes("application/json")
	public void updateLocalizacao(@PathParam("id") Long id, Localizacao localizacao) {
		service.updateLocalizacao(id, localizacao);
	}
	
	@PUT
	@Path("/usuarios/reportagem")
	@Consumes("application/json")
	public void reportarUsuario(ReportagemDTO reportagem){
		service.reportarUsuario(reportagem.getUsuarioReportadorId(), reportagem.getUsuarioReportadoId());
	}
	
	@PUT
	@Path("/troca")
	@Consumes("application/json")
	public void trocar(TrocaDTO troca){
		service.trocar(troca);
	}

}
