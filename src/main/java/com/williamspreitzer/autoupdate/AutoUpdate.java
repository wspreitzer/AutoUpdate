package com.williamspreitzer.autoupdate;

import java.util.Optional;

import com.williamspreitzer.autoupdate.domain.Release;
import com.williamspreitzer.autoupdate.network.controllers.GetLatestReleaseController;

import retrofit2.Response;

public class AutoUpdate {

	public static void main(String[] args) {
		getUpdateReturnCode(args[0],args[1],args[2]);
	}
	
	public static int getUpdateReturnCode(String url, String name, String version) {
		int returnCode = -1;
		
		GetLatestReleaseController controller = new GetLatestReleaseController();
		Optional<Response<Release>> response = Optional.ofNullable(controller.start(url, name, version));
		if(response.isPresent()) {
			if(response.get().isSuccessful()) {
				Release release  = ( Release ) response.get().body();
				if(version.equals(release.getTagName())) {
					System.out.println("up to date");
					returnCode = 0;
				} else {
					System.out.println("Behind");
					returnCode = 1;
				}
			} 
		} else {
			returnCode = 408;
		}
		
		return returnCode;
	}
}