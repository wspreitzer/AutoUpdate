package com.williamspreitzer.autoupdate.network.controllers;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.williamspreitzer.autoupdate.domain.APIResponse;
import com.williamspreitzer.autoupdate.domain.Release;
import com.williamspreitzer.autoupdate.network.UpdateServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetReleaseController implements Callback<Release> {
	private APIResponse response;
	public APIResponse start(String[] props) throws IOException {
		Gson gson = new GsonBuilder().setLenient().create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(props[0])
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		UpdateServiceAPI api = retrofit.create(UpdateServiceAPI.class);
		
		Call<Release> call = api.getRelease(props[1],props[2]);
		call.execute();
		return response;
	}
	
	public void onResponse(Call<Release> call, Response<Release> response) {
		if(response.isSuccessful()) {
			this.setResponse(response.body());
		}
	}

	public void onFailure(Call<Release> call, Throwable t) {
		t.printStackTrace();
	}

	public void setResponse(APIResponse response) {
		this.response = response;
	}
}
