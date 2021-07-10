package com.idaltchion.ifxfood.infrastructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.idaltchion.ifxfood.core.storage.StorageProperties;
import com.idaltchion.ifxfood.domain.service.FotoProdutoStorageService;

public class LocalFotoProdutoStorage implements FotoProdutoStorageService {

	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {		
		try {
			var arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar o arquivo.", e);
		}
	}

	@Override
	public void remover(String nomeArquivoExistente) {
		try {
			var arquivoPath = getArquivoPath(nomeArquivoExistente);
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível remover o arquivo.", e);
		}
	}
	
	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		try {
			var arquivoPath = getArquivoPath(nomeArquivo);
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
					.inputStream(Files.newInputStream(arquivoPath))
					.build();
			return fotoRecuperada;
		} catch (Exception e) {
			throw new StorageException("Não foi possível recuperar o arquivo.", e);
		}
	}
	
	private Path getArquivoPath(String arquivo) {
		return storageProperties.getLocal().getFolder().resolve(Path.of(arquivo));
	}


}
