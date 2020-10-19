/*
 * Attributes based on RFC 7807 - Problem Details for HTTP APIs 
 */
package com.idaltchion.ifxfood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	/* default RFC */
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	/* custom extended attributes */
	private String userMessage;
	@CreationTimestamp
	private LocalDateTime timestamp;
}
