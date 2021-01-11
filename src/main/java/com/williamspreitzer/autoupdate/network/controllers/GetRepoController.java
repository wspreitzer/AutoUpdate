package com.williamspreitzer.autoupdate.network.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.williamspreitzer.autoupdate.domain.APIResponse;
import com.williamspreitzer.autoupdate.domain.Repo;
import com.williamspreitzer.autoupdate.network.UpdateServiceAPI;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRepoController extends APIController {

	static final String BASE_URL = "https://api.github.com";
	
	public APIResponse start(String[] props) {
		Gson gson = new GsonBuilder().setLenient().create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		UpdateServiceAPI api = retrofit.create(UpdateServiceAPI.class);
		
		Call<APIResponse> call = api.getRepo(props[1]);
		call.enqueue(this);
		return null;
	}
	
	@Override
	public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
		if(response.isSuccessful()) {
			Repo repo = (Repo)response.body();
			System.out.println("ADSF"+repo.toString());
		} else {
			System.out.println(response.errorBody());
		}
	}

	@Override
	public void onFailure(Call<APIResponse> call, Throwable t) {
		t.printStackTrace();
	}
}