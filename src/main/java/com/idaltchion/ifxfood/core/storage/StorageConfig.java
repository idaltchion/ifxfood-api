package com.idaltchion.ifxfood.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.idaltchion.ifxfood.core.storage.StorageProperties.TipoStorage;
import com.idaltchion.ifxfood.domain.service.FotoProdutoStorageService;
import com.idaltchion.ifxfood.infrastructure.service.storage.LocalFotoProdutoStorage;
import com.idaltchion.ifxfood.infrastructure.service.storage.S3FotoProdutoStorage;

@Configuration
public class StorageConfig {

	@Autowired
	private StorageProperties storageProperties;
	
	@Bean
	public AmazonS3 amazonS3() {
		var credentials = new BasicAWSCredentials(
				storageProperties.getS3().getAccessKey(), 
				storageProperties.getS3().getSecretAccessKey());
		
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(storageProperties.getS3().getBucketRegion())
				.build();
	}
	
	@Bean
	public FotoProdutoStorageService fotoProdutoStorageService() {
		if (TipoStorage.S3.equals(storageProperties.getTipo())) {
			return new S3FotoProdutoStorage(); 
		} else {
			return new LocalFotoProdutoStorage();
		}
	}
	
}
