package com.idaltchion.ifxfood.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.idaltchion.ifxfood.core.storage.StorageProperties;
import com.idaltchion.ifxfood.domain.service.FotoProdutoStorageService;

public class S3FotoProdutoStorage implements FotoProdutoStorageService {

	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
			
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(novaFoto.getContentType());
			
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
					.inputStream(novaFoto.getInputStream())
					.build();
			
			PutObjectRequest putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucketName(), 
					caminhoArquivo, 
					fotoRecuperada.getInputStream(),
					metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
			
			amazonS3.putObject(putObjectRequest);			
		}
		catch (Exception e){
			throw new StorageException("Não foi possível armazenar o arquivo no serviço Amazon S3.", e);
		}
	}

	@Override
	public void remover(String foto) {
		try {
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
					storageProperties.getS3().getBucketName(), 
					getCaminhoArquivo(foto));
			
			amazonS3.deleteObject(deleteObjectRequest );			
		}
		catch (Exception e) {
			throw new StorageException("Não foi possível remover o arquivo do serviço Amazon S3.", e);
		}
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
		
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucketName(), caminhoArquivo);
		
		FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
				.url(url.toString())
				.build();
		
		return fotoRecuperada;
	}
	
	private String getCaminhoArquivo(String foto) {
		return String.format("%s/%s", storageProperties.getS3().getBucketFolder(), foto);
	}

}
