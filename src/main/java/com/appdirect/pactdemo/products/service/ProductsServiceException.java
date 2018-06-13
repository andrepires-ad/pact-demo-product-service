package com.appdirect.pactdemo.products.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductsServiceException extends RuntimeException {

	private String code;

	public ProductsServiceException(String code, String message) {
		super(message);
		this.code = code;
	}
}
