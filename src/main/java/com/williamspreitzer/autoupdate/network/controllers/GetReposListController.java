package com.williamspreitzer.autoupdate.network.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.williamspreitzer.autoupdate.domain.APIResponse;
import com.williamspreitzer.autoupdate.domain.Repo;
import com.williamspreitzer.autoupdate.network.UpdateServiceAPI;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetReposListController extends APIController {
	
	public APIResponse start(String[] props) {
		Gson gson = new GsonBuilder().setLenient().create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(props[0])
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		UpdateServiceAPI api = retrofit.create(UpdateServiceAPI.class);
		
		Call<List<APIResponse>> call = api.getRepos();
		//call.enqueue(this);
		return null;
	}

	@Override
	public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
		// TODO Auto-generated method stub
		if(response.isSuccessful()) {
			List<APIResponse> repos = (List<APIResponse>) response.body();
			repos.forEach(repo -> System.out.println("GHTGH"+repo.toString()));
		}
	}

	@Override
	public void onFailure(Call<APIResponse> call, Throwable t) {
		t.printStackTrace();
	}
}