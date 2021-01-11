package com.williamspreitzer.autoupdate.network.controllers;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.williamspreitzer.autoupdate.domain.APIResponse;
import com.williamspreitzer.autoupdate.network.UpdateServiceAPI;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetLatestReleaseController extends APIController {

	private APIResponse response;
	@Override
	public APIResponse start(String[] props) throws IOException {
		Gson gson = new GsonBuilder().setLenient().create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(props[0])
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		UpdateServiceAPI api = retrofit.create(UpdateServiceAPI.class);
		
		Call<APIResponse> call = api.getLatestRelease(props[1]);
		call.execute();
		return response;
	}

	@Override
	public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
		this.setResponse(response.body());
	}

	@Override
	public void onFailure(Call<APIResponse> call, Throwable t) {
		t.printStackTrace();
	}
	
	private void setResponse(APIResponse response) {
		this.response = response;
	}
}