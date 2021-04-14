package com.idaltchion.ifxfood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.api.model.SenhaDTOInput;
import com.idaltchion.ifxfood.domain.exception.NegocioException;
import com.idaltchion.ifxfood.domain.exception.UsuarioNaoEncontradoException;
import com.idaltchion.ifxfood.domain.model.Usuario;
import com.idaltchion.ifxfood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario buscar(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		/* Para evitar a exception abaixo ao atualizar (PUT) um objeto já gerenciado pelo JPA:
		 * Caused by: javax.persistence.NonUniqueResultException: query did not return a unique result: 2 */
		usuarioRepository.detach(usuario);
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException("Já existe e-mail cadastrado com o valor informado");
		}
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void deletar(Long id) {
		Usuario usuario = buscar(id);
		usuarioRepository.delete(usuario);
		usuarioRepository.flush();
	}

	@Transactional
	public void alterarSenha(Long id, SenhaDTOInput senhaInput) {
		Usuario usuario = buscar(id);
		String senhaAtual = senhaInput.getSenhaAtual();
		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha cadastrada no banco de dados");
		}
		
		String novaSenha = senhaInput.getNovaSenha();
		usuario.setSenha(novaSenha);
	}
	
}
