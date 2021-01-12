package com.williamspreitzer.autoupdate.network;

import java.util.List;

import com.williamspreitzer.autoupdate.domain.Release;
import com.williamspreitzer.autoupdate.domain.Repo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UpdateServiceAPI {
	
	@GET("/repos/wspreitzer/")
	Call<List<Repo>> getRepos();
	
	@GET("/repos/wspreitzer/{name}")
	Call<Repo> getRepo(@Path(value = "name") String name);
	
	@GET("/repos/wspreitzer/{name}/releases/latest")
	Call<Release> getLatestRelease(@Path(value = "name") String name);
	
	@GET("/repos/wspreitzer/{name}/releases/{id}")
	Call<Release> getRelease(@Path(value = "name") String name, @Path(value = "id") String version);
}