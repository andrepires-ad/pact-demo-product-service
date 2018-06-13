package com.appdirect.pactdemo.products.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.pactdemo.products.apiclients.RandomNamesApi;
import com.appdirect.pactdemo.products.model.Product;

@Service
public class DefaultProductService implements ProductService {

	private static final String[] BAD_WORDS = new String[] {
			"trump",
			"tax",
			"poop",
			"urine",
			"toilet"
	};

	@Autowired
	private RandomNamesApi randomNamesApi;

	@Override
	public Product getProductById(String id) {
		// generate 5000 male and female names.
		final String productName = getRandomName();

		Product product = Product.builder()
				.id(id)
				.name(productName)
				.build();

		validate(product);
		return product;
	}

	private void validate(Product product) {
		if (Arrays.stream(BAD_WORDS)
				.filter(stopWord -> product.getName().toLowerCase().contains(stopWord))
				.anyMatch(stopWord -> product.getName().toLowerCase().contains(stopWord))) {

			throw new ProductsServiceException("BAD_WORD_DETECTED", "Product name contains a bad word!");
		}
	}

	private String getRandomName() {
		try {
			return getRandomNamesApi()
					.getRandomNames(1)
					.execute()
					.body()
					.stream()
					.findFirst()
					.orElseThrow(() -> new RuntimeException("Random Name List is empty!"));
		} catch (IOException e) {
			throw new RuntimeException("Error generating random name", e);
		}
	}

	public RandomNamesApi getRandomNamesApi() {
		return randomNamesApi;
	}
}
