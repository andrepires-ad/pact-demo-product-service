package com.appdirect.pactdemo.products.apiclients;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RandomNamesApi {
	@Headers({
			"Content-Type:application/json"
	})
	@GET("/{count}")
	Call<List<String>> getRandomNames(@Path(value = "count") Integer count);
}