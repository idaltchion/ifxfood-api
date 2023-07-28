package com.idaltchion.ifxfood.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Links")
public class LinksModelOpenAPI {
	private LinkModel rel;
	
	@Getter
	@Setter
	@ApiModel(value = "Link")
	private class LinkModel {
		private String href;
		private boolean templated;
	}

}


