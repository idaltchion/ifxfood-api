package com.idaltchion.ifxfood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoProdutoStorageService {

	void armazenar(NovaFoto novaFoto);
	
	void remover(String nomeArquivo);
	
	FotoRecuperada recuperar(String nomeArquivo);
	
	default String gerarNovoNomeArquivo(String nomeOriginalArquivo) {
		String novoNomeArquivo = UUID.randomUUID() + "_" + nomeOriginalArquivo;
		return novoNomeArquivo;
	}
	
	default void substituir(String nomeArquivoExistente, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		if (nomeArquivoExistente != null) {
			this.remover(nomeArquivoExistente);			
		}
	}
	
	@Getter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
		private String contentType;
	}
	
	@Getter
	@Builder
	class FotoRecuperada {
		private InputStream inputStream;
		private String url;
		
		public boolean hasUrl() {
			return url != null;
		}
		
		public boolean hasInputStream() {
			return inputStream != null;
		}
	}
	
}
