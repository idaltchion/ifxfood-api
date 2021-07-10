package com.idaltchion.ifxfood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idaltchion.ifxfood.domain.exception.FotoProdutoNaoEncontradaException;
import com.idaltchion.ifxfood.domain.model.FotoProduto;
import com.idaltchion.ifxfood.domain.repository.ProdutoRepository;
import com.idaltchion.ifxfood.domain.service.FotoProdutoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoProdutoStorageService fotoStorageService;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream inputStream) {
		String nomeArquivoExistente = null;
		Long restauranteId = foto.getProduto().getRestaurante().getId();
		Long produtoId = foto.getProduto().getId();
		String novoNomeArquivo = fotoStorageService.gerarNovoNomeArquivo(foto.getNomeArquivo());
		
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(novoNomeArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNomeArquivo())
				.inputStream(inputStream)
				.contentType(foto.getContentType())
				.build();

		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}

	public FotoProduto buscar(Long restauranteId, Long produtoId) {
		Optional<FotoProduto> foto = produtoRepository.findFotoById(restauranteId, produtoId);
		return foto.orElseThrow(() -> new FotoProdutoNaoEncontradaException(restauranteId, produtoId));
	}
	
	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
		FotoProduto foto = buscar(restauranteId, produtoId);
		produtoRepository.delete(foto);
		produtoRepository.flush();
		fotoStorageService.remover(foto.getNomeArquivo());
	}

}
