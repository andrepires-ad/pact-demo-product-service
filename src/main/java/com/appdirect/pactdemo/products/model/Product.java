package com.appdirect.pactdemo.products.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {

	private String id;

	private String name;
}
