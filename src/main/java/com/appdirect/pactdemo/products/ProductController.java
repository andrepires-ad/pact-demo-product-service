package com.appdirect.pactdemo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.demo.product.api.ProductsApi;
import com.appdirect.demo.product.model.Error;
import com.appdirect.demo.product.model.ProductWsDTO;
import com.appdirect.pactdemo.products.model.Product;
import com.appdirect.pactdemo.products.service.ProductService;
import com.appdirect.pactdemo.products.service.ProductsServiceException;

@RestController
public class ProductController implements ProductsApi {

	@Autowired
	private ProductService productService;

	@Override
	public ResponseEntity getProductById(@PathVariable("productId") String productId) {
		try {
			Product product = getProductService().getProductById(productId);
			return ResponseEntity.ok(convert(product));
		} catch (ProductsServiceException e) {
			return ResponseEntity.status(500).body(new Error()
					.code(e.getCode())
					.message(e.getMessage())
					.status(500));
		}
	}

	private ProductWsDTO convert(Product product) {
		return new ProductWsDTO().id(product.getId()).name(product.getName());
	}

	public ProductService getProductService() {
		return productService;
	}

}
