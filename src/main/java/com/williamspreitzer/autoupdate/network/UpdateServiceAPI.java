package com.williamspreitzer.autoupdate.network;

import java.util.List;

import com.williamspreitzer.autoupdate.domain.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UpdateServiceAPI {
	
	@GET("/repos/wspreitzer/")
	Call<List<APIResponse>> getRepos();
	
	@GET("/repos/wspreitzer/{name}")
	Call<APIResponse> getRepo(@Path(value = "name") String name);
	
	@GET("/repos/wspreitzer/{name}/releases/latest")
	Call<APIResponse> getLatestRelease(@Path(value = "name") String name);
	
	@GET("/repos/wspreitzer/{name}/releases/tags/{id}")
	Call<APIResponse> getRelease(@Path(value = "name") String name, @Path(value = "id") String version);
}