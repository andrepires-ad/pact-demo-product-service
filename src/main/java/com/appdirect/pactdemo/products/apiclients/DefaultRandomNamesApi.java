package com.appdirect.pactdemo.products.apiclients;

import java.util.List;

import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
public class DefaultRandomNamesApi implements RandomNamesApi {

	@Override
	public Call<List<String>> getRandomNames(Integer count) {
		OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

		OkHttpClient client = builder.build();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://names.drycodes.com")
				.client(client)
				.addConverterFactory(JacksonConverterFactory.create())
				.build();

		RandomNamesApi randomNamesApi = retrofit.create(RandomNamesApi.class);

		return randomNamesApi.getRandomNames(count);
	}
}
