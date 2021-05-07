package com.idaltchion.ifxfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.idaltchion.ifxfood.api.assembler.UsuarioDTOAssembler;
import com.idaltchion.ifxfood.api.model.UsuarioDTO;
import com.idaltchion.ifxfood.domain.model.Restaurante;
import com.idaltchion.ifxfood.domain.model.Usuario;
import com.idaltchion.ifxfood.domain.service.CadastroRestauranteService;
import com.idaltchion.ifxfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/restaurantes/{restaurante_id}/responsaveis")
public class RestauranteUsuarioResponsavelController {
	
	@Autowired
	CadastroRestauranteService restauranteService;

	@Autowired
	CadastroUsuarioService usuarioService;
	
	@Autowired
	UsuarioDTOAssembler usuarioAssembler;
	
	@GetMapping
	public List<UsuarioDTO> listar(@PathVariable Long restaurante_id) {
		Restaurante restauranteExistente = restauranteService.buscar(restaurante_id);
		return usuarioAssembler.toDTOCollection(restauranteExistente.getResponsaveis());
	}
	
	@PutMapping("/{usuario_id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restaurante_id, @PathVariable Long usuario_id) {
		Restaurante restauranteExistente = restauranteService.buscar(restaurante_id);
		Usuario usuarioExistente = usuarioService.buscar(usuario_id);
		restauranteService.associarResponsavel(restauranteExistente, usuarioExistente);
	}
	
	@DeleteMapping("/{usuario_id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restaurante_id, @PathVariable Long usuario_id) {
		Restaurante restauranteExistente = restauranteService.buscar(restaurante_id);
		Usuario usuarioExistente = usuarioService.buscar(usuario_id);
		restauranteService.desassociarResponsavel(restauranteExistente, usuarioExistente);
	}
	
}
