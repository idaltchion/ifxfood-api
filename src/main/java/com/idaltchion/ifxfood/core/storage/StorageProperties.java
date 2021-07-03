package com.idaltchion.ifxfood.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(value = "ifxfood.storage")
public class StorageProperties {

	private Local local = new Local();
	private S3 s3 = new S3();
	
	@Getter
	@Setter
	public class Local {
		
		private Path folder;
		
	}
	
	@Getter
	@Setter
	public class S3 {
		
		private String bucket;
		private String folder;
		private String region;
		private String accessKey;
		private String secretAccessKey;
		
	}
	
}
