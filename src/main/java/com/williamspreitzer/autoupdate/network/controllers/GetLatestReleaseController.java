package com.williamspreitzer.autoupdate.network.controllers;

import java.io.IOException;
import java.net.UnknownHostException;

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

public class GetLatestReleaseController implements Callback<Release> {

	public Response<Release> start(String ...props) {
		Response<Release> retVal = null;
		Gson gson = new GsonBuilder().setLenient().create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(props[0])
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		UpdateServiceAPI api = retrofit.create(UpdateServiceAPI.class);
		
		Call<Release> call = api.getLatestRelease(props[1]);
		try {
			retVal = call.execute();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		return retVal;
	}

	@Override
	public void onResponse(Call<Release> call, Response<Release> response) {
	}

	@Override
	public void onFailure(Call<Release> call, Throwable t) {
		t.printStackTrace();
	}
}